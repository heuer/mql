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

import com.semagia.mql.MQLException;
import com.semagia.mql.tolog.ITologHandler;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class DelegatingTologHandler implements ITologHandler {

    private final ITologHandler _handler;

    public DelegatingTologHandler(final ITologHandler handler) {
        if (handler == null) {
            throw new IllegalArgumentException("The handler must not be null");
        }
        _handler = handler;
    }

    public void start() throws MQLException {
        _handler.start();
    }

    public void end() throws MQLException {
        _handler.end();
    }

    public void startSelect() throws MQLException {
        _handler.startSelect();
    }

    public void namespace(String prefix, String iri, int kind)
            throws MQLException {
        _handler.namespace(prefix, iri, kind);
    }

    public void startFunctionCall(String name) throws MQLException {
        _handler.startFunctionCall(name);
    }

    public void endSelect() throws MQLException {
        _handler.endSelect();
    }

    public void endFunctionCall() throws MQLException {
        _handler.endFunctionCall();
    }

    public void startMerge() throws MQLException {
        _handler.startMerge();
    }

    public void endMerge() throws MQLException {
        _handler.endMerge();
    }

    public void startAssociationPredicate() throws MQLException {
        _handler.startAssociationPredicate();
    }

    public void startInsert() throws MQLException {
        _handler.startInsert();
    }

    public void endAssociationPredicate() throws MQLException {
        _handler.endAssociationPredicate();
    }

    public void startBuiltinPredicate(String name) throws MQLException {
        _handler.startBuiltinPredicate(name);
    }

    public void endInsert() throws MQLException {
        _handler.endInsert();
    }

    public void endBuiltinPredicate() throws MQLException {
        _handler.endBuiltinPredicate();
    }

    public void startRule(String name, String[] variables) throws MQLException {
        _handler.startRule(name, variables);
    }

    public void endRule() throws MQLException {
        _handler.endRule();
    }

    public void startUpdate() throws MQLException {
        _handler.startUpdate();
    }

    public void startInfixPredicate(String name) throws MQLException {
        _handler.startInfixPredicate(name);
    }

    public void endInfixPredicate() throws MQLException {
        _handler.endInfixPredicate();
    }

    public void startFragment() throws MQLException {
        _handler.startFragment();
    }

    public void endFragment() throws MQLException {
        _handler.endFragment();
    }

    public void endUpdate() throws MQLException {
        _handler.endUpdate();
    }

    public void fragmentContent(String content) throws MQLException {
        _handler.fragmentContent(content);
    }

    public void qname(int kind, String prefix, String localPart)
            throws MQLException {
        _handler.qname(kind, prefix, localPart);
    }

    public void startDelete() throws MQLException {
        _handler.startDelete();
    }

    public void variable(String name) throws MQLException {
        _handler.variable(name);
    }

    public void startName() throws MQLException {
        _handler.startName();
    }

    public void endName() throws MQLException {
        _handler.endName();
    }

    public void startDynamicPredicate() throws MQLException {
        _handler.startDynamicPredicate();
    }

    public void endDelete() throws MQLException {
        _handler.endDelete();
    }

    public void endDynamicPredicate() throws MQLException {
        _handler.endDynamicPredicate();
    }

    public void startPredicate() throws MQLException {
        _handler.startPredicate();
    }

    public void endPredicate() throws MQLException {
        _handler.endPredicate();
    }

    public void startPagination() throws MQLException {
        _handler.startPagination();
    }

    public void startPair() throws MQLException {
        _handler.startPair();
    }

    public void endPair() throws MQLException {
        _handler.endPair();
    }

    public void startType() throws MQLException {
        _handler.startType();
    }

    public void endType() throws MQLException {
        _handler.endType();
    }

    public void endPagination() throws MQLException {
        _handler.endPagination();
    }

    public void startPlayer() throws MQLException {
        _handler.startPlayer();
    }

    public void endPlayer() throws MQLException {
        _handler.endPlayer();
    }

    public void offset(int offset) throws MQLException {
        _handler.offset(offset);
    }

    public void limit(int limit) throws MQLException {
        _handler.limit(limit);
    }

    public void startOrderBy() throws MQLException {
        _handler.startOrderBy();
    }

    public void endOrderBy() throws MQLException {
        _handler.endOrderBy();
    }

    public void ascending(String variable) throws MQLException {
        _handler.ascending(variable);
    }

    public void descending(String variable) throws MQLException {
        _handler.descending(variable);
    }

    public void startWhere() throws MQLException {
        _handler.startWhere();
    }

    public void endWhere() throws MQLException {
        _handler.endWhere();
    }

    public void count(String variable) throws MQLException {
        _handler.count(variable);
    }

    public void startOr() throws MQLException {
        _handler.startOr();
    }

    public void endOr() throws MQLException {
        _handler.endOr();
    }

    public void startBranch(boolean shortcircuit) throws MQLException {
        _handler.startBranch(shortcircuit);
    }

    public void endBranch() throws MQLException {
        _handler.endBranch();
    }

    public void startNot() throws MQLException {
        _handler.startNot();
    }

    public void endNot() throws MQLException {
        _handler.endNot();
    }

}
