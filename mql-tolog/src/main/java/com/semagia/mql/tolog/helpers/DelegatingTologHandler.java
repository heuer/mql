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

    public ITologHandler getTologHandler() {
        return _handler;
    }

    @Override
    public void start() throws MQLException {
        _handler.start();
    }

    @Override
    public void end() throws MQLException {
        _handler.end();
    }

    @Override
    public void startSelect() throws MQLException {
        _handler.startSelect();
    }

    @Override
    public void namespace(String prefix, String iri, int kind)
            throws MQLException {
        _handler.namespace(prefix, iri, kind);
    }

    @Override
    public void startFunctionCall(String name) throws MQLException {
        _handler.startFunctionCall(name);
    }

    @Override
    public void endSelect() throws MQLException {
        _handler.endSelect();
    }

    @Override
    public void endFunctionCall() throws MQLException {
        _handler.endFunctionCall();
    }

    @Override
    public void startMerge() throws MQLException {
        _handler.startMerge();
    }

    @Override
    public void endMerge() throws MQLException {
        _handler.endMerge();
    }

    @Override
    public void startAssociationPredicate() throws MQLException {
        _handler.startAssociationPredicate();
    }

    @Override
    public void startInsert() throws MQLException {
        _handler.startInsert();
    }

    @Override
    public void endAssociationPredicate() throws MQLException {
        _handler.endAssociationPredicate();
    }

    @Override
    public void startBuiltinPredicate(String name) throws MQLException {
        _handler.startBuiltinPredicate(name);
    }

    @Override
    public void endInsert() throws MQLException {
        _handler.endInsert();
    }

    @Override
    public void endBuiltinPredicate() throws MQLException {
        _handler.endBuiltinPredicate();
    }

    @Override
    public void startRule(String name, String[] variables) throws MQLException {
        _handler.startRule(name, variables);
    }

    @Override
    public void endRule() throws MQLException {
        _handler.endRule();
    }

    @Override
    public void startUpdate() throws MQLException {
        _handler.startUpdate();
    }

    @Override
    public void startInfixPredicate(String name) throws MQLException {
        _handler.startInfixPredicate(name);
    }

    @Override
    public void endInfixPredicate() throws MQLException {
        _handler.endInfixPredicate();
    }

    @Override
    public void startFragment() throws MQLException {
        _handler.startFragment();
    }

    @Override
    public void endFragment() throws MQLException {
        _handler.endFragment();
    }

    @Override
    public void endUpdate() throws MQLException {
        _handler.endUpdate();
    }

    @Override
    public void fragmentContent(String content) throws MQLException {
        _handler.fragmentContent(content);
    }

    @Override
    public void qname(int kind, String prefix, String localPart)
            throws MQLException {
        _handler.qname(kind, prefix, localPart);
    }

    @Override
    public void startDelete() throws MQLException {
        _handler.startDelete();
    }

    @Override
    public void variable(String name) throws MQLException {
        _handler.variable(name);
    }

    @Override
    public void startName() throws MQLException {
        _handler.startName();
    }

    @Override
    public void endName() throws MQLException {
        _handler.endName();
    }

    @Override
    public void startDynamicPredicate() throws MQLException {
        _handler.startDynamicPredicate();
    }

    @Override
    public void endDelete() throws MQLException {
        _handler.endDelete();
    }

    @Override
    public void endDynamicPredicate() throws MQLException {
        _handler.endDynamicPredicate();
    }

    @Override
    public void startPredicate() throws MQLException {
        _handler.startPredicate();
    }

    @Override
    public void endPredicate() throws MQLException {
        _handler.endPredicate();
    }

    @Override
    public void startPagination() throws MQLException {
        _handler.startPagination();
    }

    @Override
    public void startPair() throws MQLException {
        _handler.startPair();
    }

    @Override
    public void endPair() throws MQLException {
        _handler.endPair();
    }

    @Override
    public void startType() throws MQLException {
        _handler.startType();
    }

    @Override
    public void endType() throws MQLException {
        _handler.endType();
    }

    @Override
    public void endPagination() throws MQLException {
        _handler.endPagination();
    }

    @Override
    public void startPlayer() throws MQLException {
        _handler.startPlayer();
    }

    @Override
    public void endPlayer() throws MQLException {
        _handler.endPlayer();
    }

    @Override
    public void offset(int offset) throws MQLException {
        _handler.offset(offset);
    }

    @Override
    public void limit(int limit) throws MQLException {
        _handler.limit(limit);
    }

    @Override
    public void startOrderBy() throws MQLException {
        _handler.startOrderBy();
    }

    @Override
    public void endOrderBy() throws MQLException {
        _handler.endOrderBy();
    }

    @Override
    public void ascending(String variable) throws MQLException {
        _handler.ascending(variable);
    }

    @Override
    public void descending(String variable) throws MQLException {
        _handler.descending(variable);
    }

    @Override
    public void startWhere() throws MQLException {
        _handler.startWhere();
    }

    @Override
    public void endWhere() throws MQLException {
        _handler.endWhere();
    }

    @Override
    public void count(String variable) throws MQLException {
        _handler.count(variable);
    }

    @Override
    public void startOr() throws MQLException {
        _handler.startOr();
    }

    @Override
    public void endOr() throws MQLException {
        _handler.endOr();
    }

    @Override
    public void startBranch(boolean shortcircuit) throws MQLException {
        _handler.startBranch(shortcircuit);
    }

    @Override
    public void endBranch() throws MQLException {
        _handler.endBranch();
    }

    @Override
    public void startNot() throws MQLException {
        _handler.startNot();
    }

    @Override
    public void endNot() throws MQLException {
        _handler.endNot();
    }

    @Override
    public void subjectIdentifier(String iri) throws MQLException {
        _handler.subjectIdentifier(iri);
    }

    @Override
    public void subjectLocator(String iri) throws MQLException {
        _handler.subjectLocator(iri);
    }

    @Override
    public void itemIdentifier(String iri) throws MQLException {
        _handler.itemIdentifier(iri);
    }

    @Override
    public void objectId(String ident) throws MQLException {
        _handler.objectId(ident);
    }

    @Override
    public void string(String val) throws MQLException {
        _handler.string(val);
    }

    @Override
    public void parameter(String name) throws MQLException {
        _handler.parameter(name);
    }

    @Override
    public void iri(String iri) throws MQLException {
        _handler.iri(iri);
    }

}
