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
package com.semagia.mql.tolog.helpers;

import org.xml.sax.ContentHandler;

import com.semagia.mql.MQLException;
import com.semagia.mql.base.AbstractSAXEmittingQueryHandler;
import com.semagia.mql.tolog.ITologHandler;
import com.semagia.mql.tolog.Hints;

/**
 * {@link ITologHandler} implementations which translates the events into SAX events.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class SAXEmittingTologHandler extends AbstractSAXEmittingQueryHandler implements
        ITologHandler {

    private static final String TOLOG_NS = "http://psi.semagia.com/tolog-xml/";
    private static final String ROOT = "tolog";

    private static final String[][] _EMPTY_HINTS = new String[0][0];

    public SAXEmittingTologHandler() {
        super(TOLOG_NS, ROOT);
    }

    public SAXEmittingTologHandler(final ContentHandler handler) {
        super(handler, TOLOG_NS, ROOT);
    }

    private static String[][] translateHints(Hints hints) {
        final int[] constructs = hints.getConstructs();
        String constructHints = null;
        if (constructs.length != 0) {
            StringBuilder buff = new StringBuilder();
            for (int i=0; i<constructs.length; i++) {
                if (i > 0) {
                    buff.append(' ');
                }
                switch (constructs[i]) {
                    case Hints.ASSOCIATION: buff.append("association"); break;
                    case Hints.OCCURRENCE: buff.append("occurrence"); break;
                    case Hints.NAME: buff.append("name"); break;
                    case Hints.TOPIC: buff.append("topic"); break;
                    case Hints.VARIANT: buff.append("variant"); break;
                    case Hints.ROLE: buff.append("role"); break;
                    case Hints.TOPICMAP: buff.append("topicmap"); break;
                }
            }
            constructHints = buff.toString();
        }
        if (hints.getCost() != Hints.UNKNOWN_COSTS) {
            if (constructHints != null) {
                return new String[][] { {"costs", String.valueOf(hints.getCost())}, 
                                        {"hint", constructHints} };
            }
            else {
                return new String[][] { {"costs", String.valueOf(hints.getCost())} };
            }
        }
        else if (constructHints != null) {
            return new String[][] { {"hint", constructHints } };
        }
        return _EMPTY_HINTS;
    }

    private static String[][] translateHintsAndName(final String name, final Hints hints) {
        String[][] tmp = translateHints(hints);
        String[][] attrs = new String[tmp.length+1][2];
        attrs[0][0] = "name";
        attrs[0][1] = name;
        System.arraycopy(tmp, 0, attrs, 1, tmp.length);
        return attrs;
    }

    private static String prefixKindToString(final int kind) throws MQLException {
        switch (kind) {
            case PREFIX_KIND_SUBJECT_IDENTIFIER: return "subject-identifier";
            case PREFIX_KIND_SUBJECT_LOCATOR: return "subject-locator";
            case PREFIX_KIND_ITEM_IDENTIFIER: return "item-identifier";
            case PREFIX_KIND_MODULE: return "module";
        }
        throw new MQLException("Unknown prefix kind '" + kind + "'");
    }

    @Override
    public void option(String key, String value) throws MQLException {
        super.emptyElement("option", new String[][] {{"key", key}, {"value", value}});
    }

    @Override
    public void namespace(String prefix, String iri, int kind) throws MQLException {
        super.emptyElement("namespace", new String[][]{{"prefix", prefix}, {"iri", iri}, {"kind", prefixKindToString(kind)}});
    }

    @Override
    public void startFunctionInvocation(String name) throws MQLException {
        super.startElement("function-invocation", "name", name);
    }

    @Override
    public void endFunctionInvocation() throws MQLException {
        super.endElement("function-invocation");
    }

    @Override
    public void startRuleInvocation(String name) throws MQLException {
        super.startElement("rule-invocation", "name", name);
    }

    @Override
    public void endRuleInvocation() throws MQLException {
        super.endElement("rule-invocation");
    }

    @Override
    public void startInternalPredicate(String name, String[] removedVariables, Hints hints) throws MQLException {
        String[][] attrs = translateHintsAndName(name, hints);
        if (removedVariables.length > 0) {
            StringBuilder buff = new StringBuilder();
            buff.append(removedVariables[0]);
            for(int i=1; i<removedVariables.length; i++) {
                buff.append(' ');
                buff.append(removedVariables[i]);
            }
            String[][] tmp = new String[attrs.length+1][2];
            tmp[0][0] = "removed-variables";
            tmp[0][1] = buff.toString();
            System.arraycopy(attrs, 0, tmp, 1, attrs.length);
            attrs = tmp;
        }
        super.startElement("internal-predicate", attrs);
    }

    @Override
    public void endInternalPredicate() throws MQLException {
        super.endElement("internal-predicate");
    }

    @Override
    public void startMerge() throws MQLException {
        super.startElement("merge");
    }

    @Override
    public void endMerge() throws MQLException {
        super.endElement("merge");
    }

    @Override
    public void startAssociationPredicate(Hints hints) throws MQLException {
        super.startElement("association-predicate", translateHints(hints));
    }

    @Override
    public void endAssociationPredicate() throws MQLException {
        super.endElement("association-predicate");
    }

    @Override
    public void startBuiltinPredicate(String name, Hints hints) throws MQLException {
        super.startElement("builtin-predicate", translateHintsAndName(name, hints));
    }

    @Override
    public void endBuiltinPredicate() throws MQLException {
        super.endElement("builtin-predicate");
    }

    @Override
    public void startRule(String name, String[] variables) throws MQLException {
        super.startElement("rule", "name", name);
        for (String var: variables) {
            super.variable(var);
        }
        super.startElement("body");
    }

    @Override
    public void endRule() throws MQLException {
        super.endElement("body");
        super.endElement("rule");
    }

    @Override
    public void startInfixPredicate(String name, Hints hints) throws MQLException {
        super.startElement("infix-predicate", translateHintsAndName(name, hints));
    }

    @Override
    public void endInfixPredicate() throws MQLException {
        super.endElement("infix-predicate");
    }

    @Override
    public void startFragment() throws MQLException {
        super.startElement("fragment");
    }

    @Override
    public void endFragment() throws MQLException {
        super.endElement("fragment");
    }

    @Override
    public void fragmentContent(String content) throws MQLException {
        super.dataElement("content", content);
    }

    @Override
    public void qname(int kind, final String prefix, final String localPart) throws MQLException {
        super.emptyElement("qname", new String[][]{{"prefix", prefix}, {"localpart", localPart}, {"kind", prefixKindToString(kind)}});
    }

    @Override
    public void startName() throws MQLException {
        super.startElement("name");
    }

    @Override
    public void endName() throws MQLException {
        super.endElement("name");
    }

    @Override
    public void startOccurrencePredicate(Hints hints) throws MQLException {
        super.startElement("occurrence-predicate", translateHints(hints));
    }

    @Override
    public void endOccurrencePredicate() throws MQLException {
        super.endElement("occurrence-predicate");
    }

    @Override
    public void startPredicate(Hints options) throws MQLException {
        super.startElement("predicate");
    }

    @Override
    public void endPredicate() throws MQLException {
        super.endElement("predicate");
    }

    @Override
    public void startPair() throws MQLException {
        super.startElement("pair");
    }

    @Override
    public void endPair() throws MQLException {
        super.endElement("pair");
    }

    @Override
    public void startType() throws MQLException {
        super.startElement("type");
    }

    @Override
    public void endType() throws MQLException {
        super.endElement("type");
    }

    @Override
    public void startPlayer() throws MQLException {
        super.startElement("player");
    }

    @Override
    public void endPlayer() throws MQLException {
        super.endElement("player");
    }

    @Override
    public void subjectIdentifier(String iri) throws MQLException {
        super.emptyElement("subject-identifier", "value", iri);
    }

    @Override
    public void subjectLocator(String iri) throws MQLException {
        super.emptyElement("subject-locator", "value", iri);
    }

    @Override
    public void itemIdentifier(String iri) throws MQLException {
        super.emptyElement("item-identifier", "value", iri);
    }

    @Override
    public void objectId(String ident) throws MQLException {
        super.emptyElement("object-id", "value", ident);
    }

    @Override
    public void string(String val) throws MQLException {
        super.emptyElement("string", "value", val);
    }

    @Override
    public void parameter(String name) throws MQLException {
        super.emptyElement("parameter", "name", name);
    }

    @Override
    public void iri(String iri) throws MQLException {
        super.emptyElement("iri", "value", iri);
    }

    @Override
    public void integer(Integer val) throws MQLException {
        super.emptyElement("integer", "value", val.toString());
    }

    @Override
    public void decimal(Float val) throws MQLException {
        super.emptyElement("decimal", "value", val.toString());
    }

    @Override
    public void startRules() throws MQLException {
        super.startElement("rules");
    }

    @Override
    public void endRules() throws MQLException {
        super.endElement("rules");
    }

    @Override
    public void identifier(String name) throws MQLException {
        super.emptyElement("identifier", "value", name);
    }

}
