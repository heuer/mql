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
public class DefaultTologHandler implements ITologHandler {

    @Override
    public void option(String key, String value) throws MQLException { }

    @Override
    public void start() throws MQLException { }

    @Override
    public void end() throws MQLException { }

    @Override
    public void startSelect() throws MQLException { }

    @Override
    public void endSelect() throws MQLException { }

    @Override
    public void startInsert() throws MQLException { }

    @Override
    public void endInsert() throws MQLException { }

    @Override
    public void startUpdate() throws MQLException { }

    @Override
    public void endUpdate() throws MQLException { }

    @Override
    public void startDelete() throws MQLException { }

    @Override
    public void endDelete() throws MQLException { }

    @Override
    public void startPagination() throws MQLException { }

    @Override
    public void endPagination() throws MQLException { }

    @Override
    public void offset(int offset) throws MQLException { }

    @Override
    public void limit(int limit) throws MQLException { }

    @Override
    public void startOrderBy() throws MQLException { }

    @Override
    public void endOrderBy() throws MQLException { }

    @Override
    public void ascending(String variable) throws MQLException { }

    @Override
    public void descending(String variable) throws MQLException { }

    @Override
    public void startWhere() throws MQLException { }

    @Override
    public void endWhere() throws MQLException { }

    @Override
    public void count(String variable) throws MQLException { }

    @Override
    public void startOr() throws MQLException { }

    @Override
    public void endOr() throws MQLException { }

    @Override
    public void startBranch(boolean shortcircuit) throws MQLException { }

    @Override
    public void endBranch() throws MQLException { }

    @Override
    public void startNot() throws MQLException { }

    @Override
    public void endNot() throws MQLException { }

    @Override
    public void namespace(String ident, String iri, int kind)
            throws MQLException { }

    @Override
    public void startFunctionInvocation(String name) throws MQLException { }

    @Override
    public void endFunctionInvocation() throws MQLException { }

    @Override
    public void startRuleInvocation() throws MQLException { }

    @Override
    public void endRuleInvocation() throws MQLException { }

    @Override
    public void startMerge() throws MQLException { }

    @Override
    public void endMerge() throws MQLException { }

    @Override
    public void startAssociationPredicate() throws MQLException { }

    @Override
    public void endAssociationPredicate() throws MQLException { }

    @Override
    public void startBuiltinPredicate(String name) throws MQLException { }

    @Override
    public void endBuiltinPredicate() throws MQLException { }

    @Override
    public void startRule(String name, String[] variables) throws MQLException { }

    @Override
    public void endRule() throws MQLException { }

    @Override
    public void startInfixPredicate(String name) throws MQLException { }

    @Override
    public void endInfixPredicate() throws MQLException { }

    @Override
    public void qname(int kind, String prefix, String localPart)
            throws MQLException { }

    @Override
    public void startFragment() throws MQLException { }

    @Override
    public void endFragment() throws MQLException { }

    @Override
    public void fragmentContent(String content) throws MQLException { }

    @Override
    public void variable(String name) throws MQLException { }

    @Override
    public void startName() throws MQLException { }

    @Override
    public void endName() throws MQLException { }

    @Override
    public void startOccurrencePredicate() throws MQLException { }

    @Override
    public void endOccurrencePredicate() throws MQLException { }

    @Override
    public void startPredicate() throws MQLException { }

    @Override
    public void endPredicate() throws MQLException { }

    @Override
    public void startPair() throws MQLException { }

    @Override
    public void endPair() throws MQLException { }

    @Override
    public void startType() throws MQLException { }

    @Override
    public void endType() throws MQLException { }

    @Override
    public void startPlayer() throws MQLException { }

    @Override
    public void endPlayer() throws MQLException { }

    @Override
    public void subjectIdentifier(String iri) throws MQLException { }

    @Override
    public void subjectLocator(String iri) throws MQLException { }

    @Override
    public void itemIdentifier(String iri) throws MQLException { }

    @Override
    public void objectId(String ident) throws MQLException { }

    @Override
    public void string(String val) throws MQLException { }

    @Override
    public void parameter(String name) throws MQLException { }

    @Override
    public void iri(String iri) throws MQLException { }

    @Override
    public void integer(Integer val) throws MQLException { }

    @Override
    public void decimal(Float val) throws MQLException { }

    @Override
    public void startRules() throws MQLException { }

    @Override
    public void endRules() throws MQLException { }

    @Override
    public void identifier(String name) throws MQLException { }

}
