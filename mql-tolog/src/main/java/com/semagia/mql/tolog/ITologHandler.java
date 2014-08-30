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

    public final int PREFIX_KIND_SUBJECT_IDENTIFIER = IRef.SUBJECT_IDENTIFIER,
                     PREFIX_KIND_SUBJECT_LOCATOR = IRef.SUBJECT_LOCATOR,
                     PREFIX_KIND_ITEM_IDENTIFIER = IRef.ITEM_IDENTIFIER,
                     PREFIX_KIND_MODULE = -1001;
            

    void namespace(String prefix, String iri, int kind) throws MQLException;

    void startFunctionCall(String name) throws MQLException;

    void endFunctionCall() throws MQLException;

    void startMerge() throws MQLException;

    void endMerge() throws MQLException;

    void startAssociationPredicate() throws MQLException;

    void endAssociationPredicate() throws MQLException;

    void startBuiltinPredicate(String name) throws MQLException;

    void endBuiltinPredicate() throws MQLException;

    void startRule(String name, String[] variables) throws MQLException;

    void endRule() throws MQLException;

    void startInfixPredicate(String name) throws MQLException;

    void endInfixPredicate() throws MQLException;

    void startFragment() throws MQLException;
    
    void endFragment() throws MQLException;

    void fragmentContent(String content) throws MQLException;
    
    void qname(int kind, String prefix, String localPart) throws MQLException;

    void variable(String name) throws MQLException;

    void startName() throws MQLException;

    void endName() throws MQLException;

    void startDynamicPredicate() throws MQLException;

    void endDynamicPredicate() throws MQLException;

    void startPredicate() throws MQLException;

    void endPredicate() throws MQLException;

    void startPair() throws MQLException;

    void endPair() throws MQLException;

    void startType() throws MQLException;
    
    void endType() throws MQLException;

    void startPlayer() throws MQLException;

    void endPlayer() throws MQLException;

}
