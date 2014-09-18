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

import com.semagia.mio.IRef;
import com.semagia.mql.IQueryHandler;
import com.semagia.mql.MQLException;

/**
 * tolog-specific query handler.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public interface ITologHandler extends IQueryHandler {

    public final int 
        PREFIX_KIND_SUBJECT_IDENTIFIER = IRef.SUBJECT_IDENTIFIER,
        PREFIX_KIND_SUBJECT_LOCATOR = IRef.SUBJECT_LOCATOR,
        PREFIX_KIND_ITEM_IDENTIFIER = IRef.ITEM_IDENTIFIER,
        PREFIX_KIND_MODULE = -1001;


    void option(String key, String value) throws MQLException;

    void namespace(String prefix, String iri, int kind) throws MQLException;

    void startFunctionInvocation(String name) throws MQLException;

    void endFunctionInvocation() throws MQLException;

    void startRuleInvocation(String name) throws MQLException;

    void endRuleInvocation() throws MQLException;

    void startMerge() throws MQLException;

    void endMerge() throws MQLException;

    void startAssociationPredicate(Hints options) throws MQLException;

    void endAssociationPredicate() throws MQLException;

    void startBuiltinPredicate(String name, Hints options) throws MQLException;

    void endBuiltinPredicate() throws MQLException;

    void startRule(String name, String[] variables) throws MQLException;

    void endRule() throws MQLException;

    void startInfixPredicate(String name, Hints options) throws MQLException;

    void endInfixPredicate() throws MQLException;

    void startInternalPredicate(String name, String[] removedVariables, Hints hints) throws MQLException;

    void endInternalPredicate() throws MQLException;

    void startFragment() throws MQLException;
    
    void endFragment() throws MQLException;

    void fragmentContent(String content) throws MQLException;
    
    void qname(int kind, String prefix, String localPart) throws MQLException;

    void startName() throws MQLException;

    void endName() throws MQLException;

    void startOccurrencePredicate(Hints options) throws MQLException;

    void endOccurrencePredicate() throws MQLException;

    void startPredicate(Hints options) throws MQLException;

    void endPredicate() throws MQLException;

    void startPair() throws MQLException;

    void endPair() throws MQLException;

    void startType() throws MQLException;
    
    void endType() throws MQLException;

    void startPlayer() throws MQLException;

    void endPlayer() throws MQLException;

    void objectId(String ident) throws MQLException;

    void subjectIdentifier(String iri) throws MQLException;
    
    void subjectLocator(String iri) throws MQLException;

    void itemIdentifier(String iri) throws MQLException;

    void string(String val) throws MQLException;

    void parameter(String name) throws MQLException;

    void iri(String iri) throws MQLException;

    void integer(Integer val) throws MQLException;

    //TODO: BigDecimal?!?
    void decimal(Float val) throws MQLException;

    void startRules() throws MQLException;

    void endRules() throws MQLException;

    void identifier(String name) throws MQLException;

}
