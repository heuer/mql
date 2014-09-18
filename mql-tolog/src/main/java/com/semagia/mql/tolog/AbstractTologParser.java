/*
 * Copyright 2010 - 2014 Lars Heuer (heuer[at]semagia.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.semagia.mql.tolog;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.semagia.mql.MQLException;

/**
 * Abstract helper class to avoid too much code within the RealTologParser.y grammar.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
abstract class AbstractTologParser {

    protected ITologHandler _handler;
    protected final PredicateClause _predClause;
    private final Map<String, PrefixBinding> _prefixes;
    private final Set<String> _ruleNames;
    private final Map<String, PrefixBinding> _declPrefixes;
    private final Set<String> _declRuleNames;
    protected boolean _inRule;
    protected boolean _issueEndOfRulesEvent;

    protected AbstractTologParser() {
        _predClause = new PredicateClause();
        _prefixes = new HashMap<String, PrefixBinding>();
        _declPrefixes = new HashMap<String, PrefixBinding>();
        _ruleNames = new HashSet<String>();
        _declRuleNames = new HashSet<String>();
    }

    public void setHandler(final ITologHandler handler) {
        _handler = handler;
    }

    protected static boolean isBuiltinPredicate(final String name) {
        return TologUtils.isBuiltinPredicate(name);
    }

    private boolean _isRule(String name) {
        return _ruleNames.contains(name) || _declRuleNames.contains(name);
    }

    protected final void handleRuleStart() throws MQLException {
        _inRule = true;
        if (!_issueEndOfRulesEvent) {
            _handler = new RulesHandler(_handler, _declRuleNames);
            _handler.startRules();
            _issueEndOfRulesEvent = true;
        }
        String[] variables = new String[_predClause.arguments.length];
        for (int i=0; i<variables.length; i++) {
            if (_predClause.arguments[i].getType() != TologReference.VARIABLE) {
                throw new MQLException(""); // TODO
            }
            variables[i] = _predClause.arguments[i].getValue();
        }
        final String name = _predClause.ref.getValue();
        _handler.startRule(name, variables);
        _ruleNames.add(name);
    }

    protected final void handlePredicateClause() throws MQLException {
        _handleEndOfRules();
        final TologReference ref = _predClause.ref;
        final int kind = ref.getType();
        final String name = ref.getValue();
        final boolean isIdent = kind == TologReference.IDENT;
        final boolean isRule = _isRule(name);
        boolean handled = false;
        if (isIdent && isBuiltinPredicate(name)) {
            _handler.startBuiltinPredicate(name, Hints.EMPTY_HINTS);
            issueArgumentEvents();
            _handler.endBuiltinPredicate();
            handled = true;
        }
        else {
            if (isIdent) {
                if (_predClause.arguments.length == 2) {
                    if (isRule) {
                        _handler.startRuleInvocation(name);
                        issueArgumentEvents();
                        _handler.endRuleInvocation();
                        handled = true;
                    }
                    else {
                        _handler.startOccurrencePredicate(Hints.EMPTY_HINTS);
                        issueNameEvent(_predClause.ref);
                        issueArgumentEvents();
                        _handler.endOccurrencePredicate();
                        handled = true;
                    }
                }
                else if (isRule) {
                    _handler.startRuleInvocation(name);
                    issueArgumentEvents();
                    _handler.endRuleInvocation();
                    handled = true;
                }
            }
            else if (kind == TologReference.QNAME) {
                final int colonIdx = name.indexOf(':');
                final String prefix = name.substring(0, colonIdx);
                final PrefixBinding binding = resolveQName(prefix);
                if (binding.kind != ITologHandler.PREFIX_KIND_MODULE) {
                    _handler.startOccurrencePredicate(Hints.EMPTY_HINTS);
                    issueNameEvent(_predClause.ref);
                    issueArgumentEvents();
                    _handler.endOccurrencePredicate();
                    handled = true;
                }
            }
        }
        if (!handled) {
            _handler.startPredicate(Hints.EMPTY_HINTS);
            issueNameEvent(_predClause.ref);
            issueArgumentEvents();
            _handler.endPredicate();
        }
    }

    private void issueArgumentEvents() throws MQLException {
        for (int i=0; i<_predClause.arguments.length; i++) {
            issueEvent(_predClause.arguments[i]);
        }
    }

    protected final void issueNameEvent(final TologReference name) throws MQLException {
        _handler.startName();
        issueEvent(name);
        _handler.endName();
    }

    protected void handleInfixPredicate(final String name, final TologReference lhs, final TologReference rhs) throws MQLException {
        _handleEndOfRules();
        _handler.startInfixPredicate(name, Hints.EMPTY_HINTS);
        issueEvent(lhs);
        issueEvent(rhs);
        _handler.endInfixPredicate();
    }

    protected final void issueEvent(final TologReference ref) throws MQLException {
        issueEvent(ref, false);
    }

    private final void issueEvent(final TologReference ref, boolean convertStringToIRI) throws MQLException {
        final String val = ref.getValue();
        switch (ref.getType()) {
            case TologReference.VARIABLE: _handler.variable(val); break;
            case TologReference.SID: _handler.subjectIdentifier(val); break;
            case TologReference.SLO: _handler.subjectLocator(val); break;
            case TologReference.IID: _handler.itemIdentifier(val); break;
            case TologReference.OID: _handler.objectId(val); break;
            case TologReference.STRING: if (convertStringToIRI) { _handler.iri(val); } else { _handler.string(val); } break;
            case TologReference.PARAMETER: _handler.parameter(val); break;
            case TologReference.IDENT: _handler.identifier(val); break;
            case TologReference.DECIMAL: _handler.decimal(Float.valueOf(val)); break;
            case TologReference.INTEGER: _handler.integer(Integer.valueOf(val)); break;
            case TologReference.QNAME: { final int colonIdx = val.indexOf(':'); 
                                         final String prefix = val.substring(0, colonIdx);
                                         final String lp = val.substring(colonIdx+1);
                                         final PrefixBinding binding = resolveQName(prefix);
                                         _handler.qname(binding.kind, prefix, lp);
                                         break;
                                        }
        }
    }

    private PrefixBinding resolveQName(final String prefix) throws MQLException {
        PrefixBinding binding = _prefixes.get(prefix);
        if (binding == null) {
            binding = _declPrefixes.get(prefix);
        }
        if (binding == null) {
            throw new MQLException("Unknown prefix '"  + prefix + "'");
        }
        return binding;
    }

    protected void importModule(final String iri, final String prefix) throws MQLException {
        registerNamespace(prefix, iri, ITologHandler.PREFIX_KIND_MODULE);
    }

    protected void registerNamespace(final String prefix, final String iri, final int kind) throws MQLException {
        if (addNamespace(prefix, iri, kind)) {
            _handler.namespace(prefix, iri, kind);
        }
    }

    public boolean addNamespace(final String prefix, final String iri, final int kind) throws MQLException {
        final PrefixBinding existing = _prefixes.get(prefix);
        if (existing != null) {
            if (existing.iri.equals(iri) && existing.kind == kind) {
                return false;
            }
            throw new MQLException("The prefix '" + prefix + "' is already bound to <" + existing.iri + ">");
        }
        _prefixes.put(prefix, new PrefixBinding(iri, kind));
        return true;
    }

    public void addDeclarationNamespace(final String prefix, final String iri, final int kind) throws MQLException {
        System.out.println("Added prefix " + prefix + " <" + iri + ">");
        _declPrefixes.put(prefix, new PrefixBinding(iri, kind));
    }

    public void addDeclaredRulename(String name) {
        _declRuleNames.add(name);
    }

    protected void handlePair(TologReference type, TologReference player) throws MQLException {
        _handler.startPair();
        _handler.startType();
        issueEvent(type);
        _handler.endType();
        _handler.startPlayer();
        issueEvent(player);
        _handler.endPlayer();
        _handler.endPair();
    }

    protected void startAssociationPredicate(final TologReference assocType, final TologReference roleType, final TologReference rolePlayer) throws MQLException {
        _handleEndOfRules();
        _handler.startAssociationPredicate(Hints.EMPTY_HINTS); 
        issueNameEvent(assocType); 
        handlePair(roleType, rolePlayer);
    }

    protected void startNot() throws MQLException {
        _handleEndOfRules();
        _handler.startNot();
    }

    protected void startOr() throws MQLException {
        _handleEndOfRules();
        _handler.startOr();
        _handler.startBranch(false);
    }

    protected void endOr() throws MQLException {
        _handler.endBranch();
        _handler.endOr();
    }

    protected void _handleEndOfRules() throws MQLException {
        if (!_inRule && _issueEndOfRulesEvent) {
            _handler.endRules();
            _handler = ((RulesHandler) _handler).getHandler();
            _issueEndOfRulesEvent = false;
        }
    }

    protected final static class PredicateClause {
        public TologReference ref;
        public TologReference[] arguments;
    }

    private static class PrefixBinding {
        public String iri;
        public int kind;
        PrefixBinding(String iri, int kind) {
            this.iri = iri;
            this.kind = kind;
        }
    }

    protected static final class Tuple {
        
        public static final int 
            IDENT = 100,
            OID = 101,
            IID = 102,
            SID = 103,
            SLO = 104,
            IRI = SID,
            DATE_TIME = 105,
            DATE = 106,
            DECIMAL = 107,
            INTEGER = 108;

        public final int kind;
        public final String value;

        protected Tuple(final int kind, final String value) {
            this.kind = kind;
            this.value = value;
        }
    }

}
