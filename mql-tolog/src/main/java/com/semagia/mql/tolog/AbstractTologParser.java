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
import java.util.Map;

import com.semagia.mql.MQLException;
import com.semagia.mql.tolog.helpers.DefaultTologHandler;

/**
 * Abstract helper class to avoid too much code within the RealTologParser.y grammar.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
abstract class AbstractTologParser {

    private static final ITologHandler _DEFAULT_HANDLER = new DefaultTologHandler();

    protected ITologHandler _handler;
    protected final PredicateClause _predClause;
    private final Map<String, PrefixBinding> _prefixes;

    protected AbstractTologParser() {
        setHandler(_DEFAULT_HANDLER);
        _predClause = new PredicateClause();
        _prefixes = new HashMap<String, PrefixBinding>();
    }

    public ITologHandler getHandler() {
        return _handler;
    }

    public void setHandler(final ITologHandler handler) {
        _handler = handler;
    }

    protected static boolean isBuiltinPredicate(final String name) {
        return TologUtils.isBuiltinPredicate(name);
    }

    private boolean _isRule(String name) {
        return false;
    }

    protected final void handleRuleStart() throws MQLException {
        String[] variables = new String[_predClause.arguments.length];
        for (int i=0; i<variables.length; i++) {
            if (_predClause.arguments[i].getType() != TologReference.VARIABLE) {
                throw new MQLException(""); // TODO
            }
            variables[i] = _predClause.arguments[i].getValue();
        }
        _handler.startRule(_predClause.ref.getValue(), variables);
    }

    protected final void handlePredicateClause() throws MQLException {
        final TologReference ref = _predClause.ref;
        final int kind = ref.getType();
        final String name = ref.getValue();
        final boolean isIdent = kind == TologReference.IDENT;
        boolean handled = false;
        if (isIdent && isBuiltinPredicate(name)) {
            _handler.startBuiltinPredicate(name);
            issueArgumentEvents();
            _handler.endBuiltinPredicate();
            handled = true;
        }
        else {
            if (isIdent && _predClause.arguments.length == 2 && !_isRule(name)) {
                _handler.startOccurrencePredicate();
                issueNameEvent(_predClause.ref);
                issueArgumentEvents();
                _handler.endOccurrencePredicate();
                handled = true;
            }
            else if (kind == TologReference.QNAME) {
                final int colonIdx = name.indexOf(':');
                final String prefix = name.substring(0, colonIdx);
                final PrefixBinding binding = resolveQName(prefix);
                if (binding.kind != ITologHandler.PREFIX_KIND_MODULE) {
                    _handler.startOccurrencePredicate();
                    issueNameEvent(_predClause.ref);
                    issueArgumentEvents();
                    _handler.endOccurrencePredicate();
                    handled = true;
                }
            }
        }
        if (!handled) {
            _handler.startPredicate();
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

    public void handleInfixPredicate(final String name, final TologReference lhs, final TologReference rhs) throws MQLException {
        _handler.startInfixPredicate(name);
        issueEvent(lhs);
        issueEvent(rhs);
        _handler.endInfixPredicate();
    }

    private final void issueEvent(final TologReference ref) throws MQLException {
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
            case TologReference.IDENT: _handler.itemIdentifier("#" + val); break;
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
        final PrefixBinding binding = _prefixes.get(prefix);
        if (binding == null) {
            throw new MQLException("Unknown prefix '"  + prefix + "'");
        }
        return binding;
    }

    protected void importModule(final String iri, final String prefix) throws MQLException {
        registerNamespace(prefix, iri, ITologHandler.PREFIX_KIND_MODULE);
    }

    protected void registerNamespace(final String prefix, final String iri, final int kind) throws MQLException {
        //TODO: Check if exists
        _prefixes.put(prefix, new PrefixBinding(iri, kind));
        _handler.namespace(prefix, iri, kind);
    }

    public void handlePair(TologReference type, TologReference player) throws MQLException {
        _handler.startPair();
        _handler.startType();
        issueEvent(type);
        _handler.endType();
        _handler.startPlayer();
        issueEvent(player);
        _handler.endPlayer();
        _handler.endPair();
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
