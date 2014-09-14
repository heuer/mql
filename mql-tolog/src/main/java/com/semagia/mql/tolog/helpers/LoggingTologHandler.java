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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semagia.mql.MQLException;
import com.semagia.mql.tolog.ITologHandler;

/**
 * Writes all events to a log and delegates the events to an underlying {@link ITologHandler}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class LoggingTologHandler extends DelegatingTologHandler {

    private static final Logger _LOG = LoggerFactory.getLogger(LoggingTologHandler.class.getName());

    /**
     * @param handler
     */
    public LoggingTologHandler(ITologHandler handler) {
        super(handler);
    }

    @Override
    public void option(String key, String value) throws MQLException {
        _LOG.info("option key='{}' value='{}'", key, value);
        super.option(key, value);
    }

    @Override
    public void start() throws MQLException {
        _LOG.info("start");
        super.start();
    }

    @Override
    public void end() throws MQLException {
        _LOG.info("end");
        super.end();
    }

    @Override
    public void startSelect() throws MQLException {
        _LOG.info("startSelect");
        super.startSelect();
    }

    @Override
    public void namespace(String prefix, String iri, int kind)
            throws MQLException {
        _LOG.info("namespace, prefix='{}' iri='{}' kind='{}'", prefix, iri, kind);
        super.namespace(prefix, iri, kind);
    }

    @Override
    public void startFunctionInvocation(String name) throws MQLException {
        _LOG.info("startFunctionInvocation name='{}'", name);
        super.startFunctionInvocation(name);
    }

    @Override
    public void endSelect() throws MQLException {
        _LOG.info("endSelect");
        super.endSelect();
    }

    @Override
    public void endFunctionInvocation() throws MQLException {
        _LOG.info("endFunctionInvocation");
        super.endFunctionInvocation();
    }

    @Override
    public void startRuleInvocation(String name) throws MQLException {
        _LOG.info("startRuleInvocation name='{}'", name);
        super.startRuleInvocation(name);
    }

    @Override
    public void endRuleInvocation() throws MQLException {
        _LOG.info("endRuleInvocation");
        super.endRuleInvocation();
    }

    @Override
    public void startMerge() throws MQLException {
        _LOG.info("startMerge");
        super.startMerge();
    }

    @Override
    public void endMerge() throws MQLException {
        _LOG.info("endMerge");
        super.endMerge();
    }

    @Override
    public void startAssociationPredicate() throws MQLException {
        _LOG.info("startAssociationPredicate");
        super.startAssociationPredicate();
    }

    @Override
    public void startInsert() throws MQLException {
        _LOG.info("startInsert");
        super.startInsert();
    }

    @Override
    public void endAssociationPredicate() throws MQLException {
        _LOG.info("endAssociationPredicate");
        super.endAssociationPredicate();
    }

    @Override
    public void startBuiltinPredicate(String name) throws MQLException {
        _LOG.info("startBuiltinPredicate, name='{}'", name);
        super.startBuiltinPredicate(name);
    }

    @Override
    public void endInsert() throws MQLException {
        _LOG.info("endInsert");
        super.endInsert();
    }

    @Override
    public void endBuiltinPredicate() throws MQLException {
        _LOG.info("endBuiltinPredicate");
        super.endBuiltinPredicate();
    }

    @Override
    public void startRule(String name, String[] variables) throws MQLException {
        StringBuilder buff = new StringBuilder();
        for (String var: variables) {
            buff.append("'" + var + "', ");
        }
        _LOG.info("startRule name='{}' variables=[{}]", name, buff.toString());
        super.startRule(name, variables);
    }

    @Override
    public void endRule() throws MQLException {
        _LOG.info("endRule");
        super.endRule();
    }

    @Override
    public void startUpdate() throws MQLException {
        _LOG.info("startUpdate");
        super.startUpdate();
    }

    @Override
    public void startInfixPredicate(String name) throws MQLException {
        _LOG.info("startInfixPredicate name='{}'", name);
        super.startInfixPredicate(name);
    }

    @Override
    public void endInfixPredicate() throws MQLException {
        _LOG.info("endInfixPredicate");
        super.endInfixPredicate();
    }

    @Override
    public void startFragment() throws MQLException {
        _LOG.info("startFragment");
        super.startFragment();
    }

    @Override
    public void endFragment() throws MQLException {
        _LOG.info("endFragment");
        super.endFragment();
    }

    @Override
    public void endUpdate() throws MQLException {
        _LOG.info("endUpdate");
        super.endUpdate();
    }

    @Override
    public void fragmentContent(String content) throws MQLException {
        _LOG.info("fragmentContent content='{}'", content);
        super.fragmentContent(content);
    }

    @Override
    public void qname(int kind, String prefix, String localPart)
            throws MQLException {
        _LOG.info("qname kind='{}' prefix='{}' localpart='{}'", kind, prefix, localPart);
        super.qname(kind, prefix, localPart);
    }

    @Override
    public void startDelete() throws MQLException {
        _LOG.info("startDelete");
        super.startDelete();
    }

    @Override
    public void variable(String name) throws MQLException {
        _LOG.info("variable name='{}'", name);
        super.variable(name);
    }

    @Override
    public void startName() throws MQLException {
        _LOG.info("startName");
        super.startName();
    }

    @Override
    public void endName() throws MQLException {
        _LOG.info("endName");
        super.endName();
    }

    @Override
    public void startOccurrencePredicate() throws MQLException {
        _LOG.info("startOccurrencePredicate");
        super.startOccurrencePredicate();
    }

    @Override
    public void endDelete() throws MQLException {
        _LOG.info("endDelete");
        super.endDelete();
    }

    @Override
    public void endOccurrencePredicate() throws MQLException {
        _LOG.info("endOccurrencePredicate");
        super.endOccurrencePredicate();
    }

    @Override
    public void startPredicate() throws MQLException {
        _LOG.info("startPredicate");
        super.startPredicate();
    }

    @Override
    public void endPredicate() throws MQLException {
        _LOG.info("endPredicate");
        super.endPredicate();
    }

    @Override
    public void startPagination() throws MQLException {
        _LOG.info("startPagination");
        super.startPagination();
    }

    @Override
    public void startPair() throws MQLException {
        _LOG.info("startPair");
        super.startPair();
    }

    @Override
    public void endPair() throws MQLException {
        _LOG.info("endPair");
        super.endPair();
    }

    @Override
    public void startType() throws MQLException {
        _LOG.info("startType");
        super.startType();
    }

    @Override
    public void endType() throws MQLException {
        _LOG.info("endType");
        super.endType();
    }

    @Override
    public void endPagination() throws MQLException {
        _LOG.info("endPagination");
        super.endPagination();
    }

    @Override
    public void startPlayer() throws MQLException {
        _LOG.info("startPlayer");
        super.startPlayer();
    }

    @Override
    public void endPlayer() throws MQLException {
        _LOG.info("endPlayer");
        super.endPlayer();
    }

    @Override
    public void offset(int offset) throws MQLException {
        _LOG.info("offset='{}'", offset);
        super.offset(offset);
    }

    @Override
    public void limit(int limit) throws MQLException {
        _LOG.info("limit='{}'", limit);
        super.limit(limit);
    }

    @Override
    public void startOrderBy() throws MQLException {
        _LOG.info("startOrderBy");
        super.startOrderBy();
    }

    @Override
    public void endOrderBy() throws MQLException {
        _LOG.info("endOrderBy");
        super.endOrderBy();
    }

    @Override
    public void ascending(String variable) throws MQLException {
        _LOG.info("ascending='{}'", variable);
        super.ascending(variable);
    }

    @Override
    public void descending(String variable) throws MQLException {
        _LOG.info("descending='{}'", variable);
        super.descending(variable);
    }

    @Override
    public void startWhere() throws MQLException {
        _LOG.info("startWhere");
        super.startWhere();
    }

    @Override
    public void endWhere() throws MQLException {
        _LOG.info("endWhere");
        super.endWhere();
    }

    @Override
    public void count(String variable) throws MQLException {
        _LOG.info("count='{}'", variable);
        super.count(variable);
    }

    @Override
    public void startOr() throws MQLException {
        _LOG.info("startOr");
        super.startOr();
    }

    @Override
    public void endOr() throws MQLException {
        _LOG.info("endOr");
        super.endOr();
    }

    @Override
    public void startBranch(boolean shortcircuit) throws MQLException {
        _LOG.info("startBranch shortcircuit='{}'", shortcircuit);
        super.startBranch(shortcircuit);
    }

    @Override
    public void endBranch() throws MQLException {
        _LOG.info("endBranch");
        super.endBranch();
    }

    @Override
    public void startNot() throws MQLException {
        _LOG.info("startNot");
        super.startNot();
    }

    @Override
    public void endNot() throws MQLException {
        _LOG.info("endNot");
        super.endNot();
    }

    @Override
    public void subjectIdentifier(String iri) throws MQLException {
        _LOG.info("subjectIdentifier iri='{}'", iri);
        super.subjectIdentifier(iri);
    }

    @Override
    public void subjectLocator(String iri) throws MQLException {
        _LOG.info("subjectLocator iri='{}'", iri);
        super.subjectLocator(iri);
    }

    @Override
    public void itemIdentifier(String iri) throws MQLException {
        _LOG.info("itemIdentifier iri='{}'", iri);
        super.itemIdentifier(iri);
    }

    @Override
    public void objectId(String ident) throws MQLException {
        _LOG.info("objectId ident='{}'", ident);
        super.objectId(ident);
    }

    @Override
    public void string(String val) throws MQLException {
        _LOG.info("string value='{}'", val);
        super.string(val);
    }

    @Override
    public void parameter(String name) throws MQLException {
        _LOG.info("parameter name='{}'", name);
        super.parameter(name);
    }

    @Override
    public void iri(String iri) throws MQLException {
        _LOG.info("iri value='{}'", iri);
        super.iri(iri);
    }

    @Override
    public void integer(Integer val) throws MQLException {
        _LOG.info("integer value='{}'", val);
        super.integer(val);
    }

    @Override
    public void decimal(Float val) throws MQLException {
        _LOG.info("decimal value='{}'", val);
        super.decimal(val);
    }

    @Override
    public void startRules() throws MQLException {
        _LOG.info("startRules");
        super.startRules();
    }

    @Override
    public void endRules() throws MQLException {
        _LOG.info("endRules");
        super.endRules();
    }

    @Override
    public void identifier(String name) throws MQLException {
        _LOG.info("identifier name='{}'", name);
        super.identifier(name);
    }

}
