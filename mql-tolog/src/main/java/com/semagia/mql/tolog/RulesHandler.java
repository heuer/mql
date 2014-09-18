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

import java.util.Set;

import com.semagia.mql.MQLException;

/**
 * Internal handler which collects all events until all rules have been parsed.
 * 
 * It translates "startOccurrence" events into "startRuleInvocation" events if
 * necessary.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
final class RulesHandler implements ITologHandler {

    private static final int
        START_RULE = 2,
        END_RULE = 3,
        START_ASSOC = 4,
        END_ASSOC = 5,
        START_OCC = 6,
        END_OCC = 7,
        START_NAME = 8,
        END_NAME = 9,
        START_BUILTIN_PREDICATE = 10,
        END_BUILTIN_PREDICATE = 11,
        START_INFIX_PREDICATE = 12,
        END_INFIX_PREDICATE = 13,
        START_OR = 14,
        END_OR = 15,
        START_BRANCH = 16,
        END_BRANCH = 17,
        START_NOT = 18,
        END_NOT = 19,
        VARIABLE = 20,
        STRING = 21,
        IID = 22,
        SID = 23,
        SLO = 24,
        OID = 25,
        PARAM = 26,
        DECIMAL = 27,
        INTEGER = 28,
        IRI = 29,
        START_TYPE = 30,
        END_TYPE = 31,
        START_PLAYER = 32,
        END_PLAYER = 33,
        START_PAIR = 34,
        END_PAIR = 35,
        IDENTIFIER = 36,
        QNAME = 37,
        START_RULE_INVOCATION = 38,
        END_RULE_INVOCATION = 39,
        START_PREDICATE = 40,
        END_PREDICATE = 41,
        START_INTERNAL_PREDICATE = 42,
        END_INTERNAL_PREDICATE = 43
        ;

    private final ITologHandler _handler;
    private final Events _events;
    private final Set<String> _ruleNames;

    private boolean _isRule;

    RulesHandler(final ITologHandler handler, final Set<String> knownRules) {
        _events = new Events();
        _handler = handler;
        _ruleNames = knownRules;
    }

    ITologHandler getHandler() {
        return _handler;
    }

    @Override
    public void startRules() throws MQLException { }

    @Override
    public void endRules() throws MQLException {
        _handler.startRules();
        int i = 0;
        final int max = _events.size();
        while (i < max) {
            final int event = _events.get(i);
            switch (event) {
                case START_BUILTIN_PREDICATE: _handler.startBuiltinPredicate((String) _events.getValue(i), Hints.EMPTY_HINTS); i++; break;
                case START_INFIX_PREDICATE: _handler.startInfixPredicate((String) _events.getValue(i), Hints.EMPTY_HINTS); i++; break;
                case START_INTERNAL_PREDICATE: _handler.startInternalPredicate((String) _events.getValue(i), new String[0], Hints.EMPTY_HINTS); i++; break;
                case START_PREDICATE: _handler.startPredicate(Hints.EMPTY_HINTS); i++; break;
                case START_ASSOC: _handler.startAssociationPredicate(Hints.EMPTY_HINTS); i++; break;
                case END_ASSOC: _handler.endAssociationPredicate(); i++; break;
                case START_PAIR: _handler.startPair(); i++; break;
                case END_PAIR: _handler.endPair(); i++; break;
                case START_OR: _handler.startOr(); i++; break;
                case END_OR: _handler.endOr(); i++; break;
                case START_NOT: _handler.startNot(); i++; break;
                case END_NOT: _handler.endNot(); i++; break;
                case START_BRANCH: _handler.startBranch(((Boolean) _events.getValue(i)).booleanValue()); i++; break;
                case END_BRANCH: _handler.endBranch(); i++; break;
                case END_BUILTIN_PREDICATE: _handler.endBuiltinPredicate(); i++; break;
                case END_INFIX_PREDICATE: _handler.endInfixPredicate(); i++; break;
                case END_INTERNAL_PREDICATE: _handler.endInternalPredicate(); i++; break;
                case END_PREDICATE: _handler.endPredicate(); i++; break;
                case START_TYPE: _handler.startType(); i++; break;
                case END_TYPE: _handler.endType(); i++; break;
                case START_PLAYER: _handler.startPlayer(); i++; break;
                case END_PLAYER: _handler.endPlayer(); i++; break;
                case START_NAME: _handler.startName(); i++; break;
                case END_NAME: _handler.endName(); i++; break;
                case START_RULE: _handleStartRule(i); i++; break;
                case END_RULE: _handler.endRule(); i++; break;
                case START_RULE_INVOCATION: _handler.startRuleInvocation((String) _events.getValue(i)); i++; break;
                case END_RULE_INVOCATION: _handler.endRuleInvocation(); i++; break;
                case START_OCC: i = _handleStartOccurrence(i); break;
                case END_OCC: if (_isRule) { _handler.endRuleInvocation(); } else { _handler.endOccurrencePredicate(); }; i++; break;
                case SID: _handler.subjectIdentifier((String) _events.getValue(i)); i++; break;
                case SLO: _handler.subjectLocator((String) _events.getValue(i)); i++; break;
                case IID: _handler.itemIdentifier((String) _events.getValue(i)); i++; break;
                case IDENTIFIER: _handler.identifier((String) _events.getValue(i)); i++; break;
                case OID: _handler.objectId((String) _events.getValue(i)); i++; break;
                case IRI: _handler.iri((String) _events.getValue(i)); i++; break;
                case VARIABLE: _handler.variable((String) _events.getValue(i)); i++; break;
                case PARAM: _handler.parameter((String) _events.getValue(i)); i++; break;
                case STRING: _handler.string((String) _events.getValue(i)); i++; break;
                case INTEGER: _handler.integer(((Integer) _events.getValue(i)).intValue()); i++; break;
                case DECIMAL: _handler.decimal((Float) _events.getValue(i)); i++; break;
                case QNAME: _handleQName(i); i++; break;
                default: throw new MQLException("Unexpected state '" + event + "'");
            }
        }
        _events.close();
        _handler.endRules();
    }

    private void _handleStartRule(int i) throws MQLException {
        String[] ruleNameAndArgs = (String[]) _events.getValue(i);
        String[] args = new String[ruleNameAndArgs.length-1];
        System.arraycopy(ruleNameAndArgs, 1, args, 0, ruleNameAndArgs.length-1);
        _handler.startRule(ruleNameAndArgs[0], args);
    }

    private int _handleStartOccurrence(int i) throws MQLException {
        _isRule = _events.get(i+2) == IDENTIFIER && _ruleNames.contains(_events.getValue(i+2));
        if (_isRule) {
            i = i+2; // startName, identifier
            _handler.startRuleInvocation((String) _events.getValue(i));
            i++; // endName
        }
        else {
            _handler.startOccurrencePredicate(Hints.EMPTY_HINTS);
        }
        i++;
        return i;
    }

    private void _handleQName(int idx) throws MQLException {
        final Object[] qname = (Object[]) _events.getValue(idx);
        _handler.qname(((Integer) qname[0]).intValue(), (String) qname[1], (String) qname[2]);
    }

    @Override
    public void startAssociationPredicate(Hints options) throws MQLException {
        _events.add(START_ASSOC);
    }

    @Override
    public void endAssociationPredicate() throws MQLException {
        _events.add(END_ASSOC);
    }

    @Override
    public void startOccurrencePredicate(Hints options) throws MQLException {
        _events.add(START_OCC);
    }

    @Override
    public void endOccurrencePredicate() throws MQLException {
        _events.add(END_OCC);
    }

    @Override
    public void startName() throws MQLException {
        _events.add(START_NAME);
    }

    @Override
    public void endName() throws MQLException {
        _events.add(END_NAME);
    }

    @Override
    public void startBuiltinPredicate(String name, Hints option) throws MQLException {
        _events.add(START_BUILTIN_PREDICATE, name);
    }

    @Override
    public void endBuiltinPredicate() throws MQLException {
        _events.add(END_BUILTIN_PREDICATE);
    }

    @Override
    public void startInternalPredicate(String name, String[] removedVariables, Hints hints) throws MQLException {
        _events.add(START_INTERNAL_PREDICATE, name);
    }

    @Override
    public void endInternalPredicate() throws MQLException {
        _events.add(END_INTERNAL_PREDICATE);
    }

    @Override
    public void startRule(String name, String[] variables) throws MQLException {
        final String[] arr = new String[variables.length+1];
        arr[0] = name;
        System.arraycopy(variables, 0, arr, 1, variables.length);
        _events.add(START_RULE, arr);
        _ruleNames.add(name);
    }

    @Override
    public void endRule() throws MQLException {
        _events.add(END_RULE);
    }

    @Override
    public void startInfixPredicate(String name, Hints options) throws MQLException {
        _events.add(START_INFIX_PREDICATE, name);
    }

    @Override
    public void endInfixPredicate() throws MQLException {
        _events.add(END_INFIX_PREDICATE);
    }

    @Override
    public void startOr() throws MQLException {
        _events.add(START_OR);
    }

    @Override
    public void endOr() throws MQLException {
        _events.add(END_OR);
    }

    @Override
    public void startBranch(boolean shortcircuit) throws MQLException {
        _events.add(START_BRANCH, Boolean.valueOf(shortcircuit));
    }

    @Override
    public void endBranch() throws MQLException {
        _events.add(END_BRANCH);
    }

    @Override
    public void startNot() throws MQLException {
        _events.add(START_NOT);
    }

    @Override
    public void endNot() throws MQLException {
        _events.add(END_NOT);
    }

    @Override
    public void startPair() throws MQLException {
        _events.add(START_PAIR);
    }

    @Override
    public void endPair() throws MQLException {
        _events.add(END_PAIR);
    }

    @Override
    public void startType() throws MQLException {
        _events.add(START_TYPE);
    }

    @Override
    public void endType() throws MQLException {
        _events.add(END_TYPE);
    }

    @Override
    public void startPlayer() throws MQLException {
        _events.add(START_PLAYER);
    }

    @Override
    public void endPlayer() throws MQLException {
        _events.add(END_PLAYER);
    }

    @Override
    public void startRuleInvocation(String name) throws MQLException {
        _events.add(START_RULE_INVOCATION, name);
    }

    @Override
    public void endRuleInvocation() throws MQLException {
        _events.add(END_RULE_INVOCATION);
    }

    @Override
    public void startPredicate(Hints options) throws MQLException {
        _events.add(START_PREDICATE);
    }

    @Override
    public void endPredicate() throws MQLException {
        _events.add(END_PREDICATE);
    }

    @Override
    public void qname(int kind, String prefix, String localPart) throws MQLException {
        Object[] arr = new Object[3];
        arr[0] = Integer.valueOf(kind);
        arr[1] = prefix;
        arr[2] = localPart;
        _events.add(QNAME, arr);
    }

    @Override
    public void identifier(String name) throws MQLException {
        _events.add(IDENTIFIER, name);
    }

    @Override
    public void variable(String name) throws MQLException {
        _events.add(VARIABLE, name);
    }

    @Override
    public void subjectIdentifier(String iri) throws MQLException {
        _events.add(SID, iri);
    }

    @Override
    public void subjectLocator(String iri) throws MQLException {
        _events.add(SLO, iri);
    }

    @Override
    public void itemIdentifier(String iri) throws MQLException {
        _events.add(IID, iri);
    }

    @Override
    public void string(String val) throws MQLException {
        _events.add(STRING, val);
    }

    @Override
    public void objectId(String ident) throws MQLException {
        _events.add(OID, ident);
    }

    @Override
    public void parameter(String name) throws MQLException {
        _events.add(PARAM, name);
    }

    @Override
    public void iri(String iri) throws MQLException {
        _events.add(IRI, iri);
    }

    @Override
    public void integer(Integer val) throws MQLException {
        _events.add(INTEGER, val);
    }

    @Override
    public void decimal(Float val) throws MQLException {
        _events.add(DECIMAL, val);
    }

    @Override
    public void start() throws MQLException {
        unexpected();
    }

    @Override
    public void end() throws MQLException {
        unexpected();
    }

    @Override
    public void startSelect() throws MQLException {
        unexpected();
    }

    @Override
    public void option(String key, String value) throws MQLException {
        unexpected();
    }

    @Override
    public void namespace(String prefix, String iri, int kind) throws MQLException {
        unexpected();
    }

    @Override
    public void endSelect() throws MQLException {
        unexpected();
    }

    @Override
    public void startInsert() throws MQLException {
        unexpected();
    }

    @Override
    public void endInsert() throws MQLException {
        unexpected();
    }

    @Override
    public void startUpdate() throws MQLException {
        unexpected();
    }

    @Override
    public void endUpdate() throws MQLException {
        unexpected();
    }

    @Override
    public void startDelete() throws MQLException {
        unexpected();
    }

    @Override
    public void endDelete() throws MQLException {
        unexpected();
    }

    @Override
    public void startPagination() throws MQLException {
        unexpected();
    }

    @Override
    public void endPagination() throws MQLException {
        unexpected();
    }

    @Override
    public void offset(int offset) throws MQLException {
        unexpected();
    }

    @Override
    public void limit(int limit) throws MQLException {
        unexpected();
    }

    @Override
    public void startOrderBy() throws MQLException {
        unexpected();
    }

    @Override
    public void endOrderBy() throws MQLException {
        unexpected();
    }

    @Override
    public void ascending(String variable) throws MQLException {
        unexpected();
    }

    @Override
    public void descending(String variable) throws MQLException {
        unexpected();
    }

    @Override
    public void startWhere() throws MQLException {
        unexpected();
    }

    @Override
    public void endWhere() throws MQLException {
        unexpected();
    }

    @Override
    public void count(String variable) throws MQLException {
        unexpected();
    }

    @Override
    public void startFunctionInvocation(String name) throws MQLException {
        unexpected();
    }

    @Override
    public void endFunctionInvocation() throws MQLException {
        unexpected();
    }

    @Override
    public void startMerge() throws MQLException {
        unexpected();
    }

    @Override
    public void endMerge() throws MQLException {
        unexpected();
    }

    @Override
    public void startFragment() throws MQLException {
        unexpected();
    }

    @Override
    public void endFragment() throws MQLException {
        unexpected();
    }

    @Override
    public void fragmentContent(String content) throws MQLException {
        unexpected();
    }

    private void unexpected() throws MQLException {
        throw new MQLException("Unexpected event");
    }






    private static final class Events {

        private int[][] _items;
        private int _itemCount;
        private Object[] _values;
        private int _valCount;

        Events() {
            this(110, 36);
        }
        
        Events(final int itemCapacity, final int valueCapacity) {
            _items = new int[itemCapacity][2];
            _itemCount = 0;
            _values = new Object[valueCapacity];
            _valCount = 0;
        }

        public void add(int event) {
            add(event, null);
        }

        public void add(int event, Object val) {
            if (_itemCount == _items.length) {
                int newItems[][] = new int[_itemCount*2 + 1][2];
                System.arraycopy(_items, 0, newItems, 0, _itemCount);
                _items = newItems;
            }
            _items[_itemCount][0] = event;
            if (val != null) {
                if (_valCount == _values.length) {
                    Object[] newVals = new Object[_valCount*2 + 1];
                    System.arraycopy(_values, 0, newVals, 0, _valCount);
                    _values = newVals;
                }
                _values[_valCount] = val;
                _items[_itemCount][1] = _valCount;
                _valCount++;
            }
            _itemCount++;
        }

        public int get(int index) {
            if (index >= _itemCount) {
                throw new IndexOutOfBoundsException();
            }
            return _items[index][0];
        }

        public Object getValue(int index) {
            final int i = _items[index][1];
            return _values[i];
        }

        public int size() {
            return _itemCount;
        }

        public void close() {
            _items = null;
            _itemCount = 0;
            _values = null;
            _valCount = 0;
        }

    }
}
