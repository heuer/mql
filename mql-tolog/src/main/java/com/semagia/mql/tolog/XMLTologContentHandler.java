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

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.semagia.mql.MQLException;

/**
 * {@link ContentHandler} which translates XML into {@link ITologHandler} events.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public final class XMLTologContentHandler extends DefaultHandler {

    private final ITologHandler _handler;
    private final List<String> _ruleProps;
    private final StringBuilder _buff;
    private boolean _omitVariableEvents;
    private boolean _keepContent;

    public XMLTologContentHandler(final ITologHandler handler) {
        _handler = handler;
        _ruleProps = new ArrayList<String>();
        _buff = new StringBuilder();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String name, String qName, Attributes attrs)
            throws SAXException {
        if (name.equals("variable")) {
            if (!_omitVariableEvents) {
                _handler.variable(attrs.getValue("name"));
            }
            else {
                _ruleProps.add(attrs.getValue("name"));
            }
        }
        else if (name.equals("identifier")) {
            _handler.identifier(attrs.getValue("name"));
        }
        else if (name.equals("string")) {
            _handler.string(attrs.getValue("value"));
        }
        else if (name.equals("iri")) {
            _handler.iri(attrs.getValue("value"));
        }
        else if (name.equals("qname")) {
            _handler.qname(prefixStringToKind(attrs.getValue("kind")), attrs.getValue("prefix"), attrs.getValue("localpart"));
        }
        else if (name.equals("subject-identifier")) {
            _handler.subjectIdentifier(attrs.getValue("value"));
        }
        else if (name.equals("subject-locator")) {
            _handler.subjectLocator(attrs.getValue("value"));
        }
        else if (name.equals("item-identifier")) {
            _handler.itemIdentifier(attrs.getValue("value"));
        }
        else if (name.equals("object-id")) {
            _handler.objectId(attrs.getValue("value"));
        }
        else if (name.equals("builtin-predicate")) {
            _handler.startBuiltinPredicate(attrs.getValue("name"), makeHints(attrs));
        }
        else if (name.equals("pair")) {
            _handler.startPair();
        }
        else if (name.equals("type")) {
            _handler.startType();
        }
        else if (name.equals("player")) {
            _handler.startPlayer();
        }
        else if (name.equals("association-predicate")) {
            _handler.startAssociationPredicate(makeHints(attrs));
        }
        else if (name.equals("occurrence-predicate")) {
            _handler.startOccurrencePredicate(makeHints(attrs));
        }
        else if (name.equals("predicate")) {
            _handler.startPredicate(makeHints(attrs));
        }
        else if (name.equals("infix-predicate")) {
            _handler.startInfixPredicate(attrs.getValue("name"), makeHints(attrs));
        }
        else if (name.equals("name")) {
            _handler.startName();
        }
        else if (name.equals("or")) {
            _handler.startOr();
        }
        else if (name.equals("branch")) {
            String sc = attrs.getValue("shortcircuit");
            _handler.startBranch(sc != null && sc.equals("true"));
        }
        else if (name.equals("not")) {
            _handler.startNot();
        }
        else if (name.equals("rule-invocation")) {
            _handler.startRuleInvocation(attrs.getValue("name"));
        }
        else if (name.equals("function-invocation")) {
            _handler.startFunctionInvocation(attrs.getValue("name"));
        }
        else if (name.equals("internal-predicate")) {
            final String removedVars = attrs.getValue("removed-variables");
            _handler.startInternalPredicate(attrs.getValue("name"), removedVars == null ? new String[0] : removedVars.split(" "), makeHints(attrs));
        }
        else if (name.equals("parameter")) {
            _handler.parameter(attrs.getValue("name"));
        }
        else if (name.equals("integer")) {
            _handler.integer(Integer.valueOf(attrs.getValue("value")));
        }
        else if (name.equals("decimal")) {
            _handler.decimal(Float.valueOf(attrs.getValue("value")));
        }
        else if (name.equals("namespace")) {
            _handler.namespace(attrs.getValue("prefix"), attrs.getValue("iri"), prefixStringToKind(attrs.getValue("kind")));
        }
        else if (name.equals("option")) {
            _handler.option(attrs.getValue("key"), attrs.getValue("value"));
        }
        else if (name.equals("rule")) {
            _omitVariableEvents = true;
            _ruleProps.add(attrs.getValue("name"));
        }
        else if (name.equals("body")) {
            _handler.startRule(_ruleProps.get(0), _ruleProps.subList(1, _ruleProps.size()).toArray(new String[_ruleProps.size()-1]));
            _omitVariableEvents = false;
            _ruleProps.clear();
        }
        else if (name.equals("rules")) {
            _handler.startRules();
        }
        else if (name.equals("select")) {
            _handler.startSelect();
        }
        else if (name.equals("where")) {
            _handler.startWhere();
        }
        else if (name.equals("delete")) {
            _handler.startDelete();
        }
        else if (name.equals("insert")) {
            _handler.startInsert();
        }
        else if (name.equals("update")) {
            _handler.startUpdate();
        }
        else if (name.equals("merge")) {
            _handler.startMerge();
        }
        else if (name.equals("pagination")) {
            _handler.startPagination();
        }
        else if (name.equals("order-by")) {
            _handler.startOrderBy();
        }
        else if (name.equals("count")) {
            _handler.count(attrs.getValue("value"));
        }
        else if (name.equals("asc")) {
            _handler.ascending(attrs.getValue("value"));
        }
        else if (name.equals("desc")) {
            _handler.descending(attrs.getValue("value"));
        }
        else if (name.equals("offset")) {
            _handler.offset(Integer.parseInt(attrs.getValue("value")));
        }
        else if (name.equals("limit")) {
            _handler.limit(Integer.parseInt(attrs.getValue("value")));
        }
        else if (name.equals("fragment")) {
            _handler.startFragment();
        }
        else if (name.equals("content")) {
            _keepContent = true;
        }
        else if (name.equals("query")) {
            _handler.start();
        }
        else {
            throw new MQLException("Unknown element " + name);
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String name, String qName)
            throws SAXException {
        if (name.equals("pair")) {
            _handler.endPair();
        }
        else if (name.equals("type")) {
            _handler.endType();
        }
        else if (name.equals("player")) {
            _handler.endPlayer();
        }
        if (name.equals("builtin-predicate")) {
            _handler.endBuiltinPredicate();
        }
        else if (name.equals("association-predicate")) {
            _handler.endAssociationPredicate();
        }
        else if (name.equals("occurrence-predicate")) {
            _handler.endOccurrencePredicate();
        }
        else if (name.equals("predicate")) {
            _handler.endPredicate();
        }
        else if (name.equals("infix-predicate")) {
            _handler.endInfixPredicate();
        }
        else if (name.equals("name")) {
            _handler.endName();
        }
        else if (name.equals("or")) {
            _handler.endOr();
        }
        else if (name.equals("branch")) {
            _handler.endBranch();
        }
        else if (name.equals("not")) {
            _handler.endNot();
        }
        else if (name.equals("rule-invocation")) {
            _handler.endRuleInvocation();
        }
        else if (name.equals("function-invocation")) {
            _handler.endFunctionInvocation();
        }
        else if (name.equals("internal-predicate")) {
            _handler.endInternalPredicate();
        }
        else if (name.equals("rule")) {
            _handler.endRule();
        }
        else if (name.equals("rules")) {
            _handler.endRules();
        }
        else if (name.equals("select")) {
            _handler.endSelect();
        }
        else if (name.equals("where")) {
            _handler.endWhere();
        }
        else if (name.equals("delete")) {
            _handler.endDelete();
        }
        else if (name.equals("insert")) {
            _handler.endInsert();
        }
        else if (name.equals("update")) {
            _handler.endUpdate();
        }
        else if (name.equals("merge")) {
            _handler.endMerge();
        }
        else if (name.equals("pagination")) {
            _handler.endPagination();
        }
        else if (name.equals("order-by")) {
            _handler.endOrderBy();
        }
        else if (name.equals("fragment")) {
            _handler.endFragment();
        }
        else if (name.equals("content")) {
            _handler.fragmentContent(_buff.toString());
            _keepContent = false;
            _buff.setLength(0);
        }
        else if (name.equals("query")) {
            _handler.end();
        }

    }

    /* (non-Javadoc)
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (_keepContent) {
            _buff.append(ch, start, length);
        }
    }

    private static int prefixStringToKind(final String value) throws MQLException {
        if (value.equals("subject-identifier")) {
            return ITologHandler.PREFIX_KIND_SUBJECT_IDENTIFIER;
        }
        else if (value.equals("subject-locator")) {
            return ITologHandler.PREFIX_KIND_SUBJECT_LOCATOR;
        }
        else if (value.equals("item-identifier")) {
            return ITologHandler.PREFIX_KIND_ITEM_IDENTIFIER;
        }
        else if (value.equals("module")) {
            return ITologHandler.PREFIX_KIND_MODULE;
        }
        throw new MQLException("Unknown prefix kind '" + value + "'");
    }

    private static Hints makeHints(final Attributes attrs) throws MQLException {
        final String costs = attrs.getValue("cost");
        final String hints = attrs.getValue("hint");
        if (hints != null) {
            String[] tmp = hints.split(" ");
            int[] constructs = new int[tmp.length];
            int c;
            for (int i=0; i<tmp.length; i++) {
                if (tmp[i].equals("association")) {
                    c = Hints.ASSOCIATION;
                }
                else if (tmp[i].equals("occurrence")) {
                    c = Hints.OCCURRENCE;
                }
                else if (tmp[i].equals("name")) {
                    c = Hints.NAME;
                }
                else if (tmp[i].equals("topic")) {
                    c = Hints.TOPIC;
                }
                else if (tmp[i].equals("role")) {
                    c = Hints.ROLE;
                }
                else if (tmp[i].equals("variant")) {
                    c = Hints.VARIANT;
                }
                else if (tmp[i].equals("topicmap")) {
                    c = Hints.TOPICMAP;
                }
                else {
                    throw new MQLException("Internal error");  //TODO
                }
                constructs[i] = c;
            }
            return new Hints(costs != null ? Integer.parseInt(costs) : Hints.UNKNOWN_COSTS, constructs);
        }
        if (costs != null) {
            return new Hints(Integer.parseInt(costs));
        }
        return Hints.EMPTY_HINTS;
    }

}
