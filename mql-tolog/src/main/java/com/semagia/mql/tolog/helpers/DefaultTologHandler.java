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

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#start()
     */
    @Override
    public void start() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#end()
     */
    @Override
    public void end() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startSelect()
     */
    @Override
    public void startSelect() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endSelect()
     */
    @Override
    public void endSelect() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startInsert()
     */
    @Override
    public void startInsert() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endInsert()
     */
    @Override
    public void endInsert() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startUpdate()
     */
    @Override
    public void startUpdate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endUpdate()
     */
    @Override
    public void endUpdate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startDelete()
     */
    @Override
    public void startDelete() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endDelete()
     */
    @Override
    public void endDelete() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startPagination()
     */
    @Override
    public void startPagination() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endPagination()
     */
    @Override
    public void endPagination() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#offset(int)
     */
    @Override
    public void offset(int offset) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#limit(int)
     */
    @Override
    public void limit(int limit) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOrderBy()
     */
    @Override
    public void startOrderBy() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOrderBy()
     */
    @Override
    public void endOrderBy() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#ascending(java.lang.String)
     */
    @Override
    public void ascending(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#descending(java.lang.String)
     */
    @Override
    public void descending(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startWhere()
     */
    @Override
    public void startWhere() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endWhere()
     */
    @Override
    public void endWhere() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#count(java.lang.String)
     */
    @Override
    public void count(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOr()
     */
    @Override
    public void startOr() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOr()
     */
    @Override
    public void endOr() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startBranch(boolean)
     */
    @Override
    public void startBranch(boolean shortcircuit) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endBranch()
     */
    @Override
    public void endBranch() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startNot()
     */
    @Override
    public void startNot() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endNot()
     */
    @Override
    public void endNot() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#namespace(java.lang.String, java.lang.String, int)
     */
    @Override
    public void namespace(String ident, String iri, int kind)
            throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startFunction(java.lang.String)
     */
    @Override
    public void startFunctionCall(String name) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endFunction()
     */
    @Override
    public void endFunctionCall() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startMerge()
     */
    @Override
    public void startMerge() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endMerge()
     */
    @Override
    public void endMerge() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startAssociationPredicate()
     */
    @Override
    public void startAssociationPredicate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endAssociationPredicate()
     */
    @Override
    public void endAssociationPredicate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startBuiltinPredicate(java.lang.String)
     */
    @Override
    public void startBuiltinPredicate(String name) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endBuiltinPredicate()
     */
    @Override
    public void endBuiltinPredicate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startRule(java.lang.String, java.lang.String[])
     */
    @Override
    public void startRule(String name, String[] variables) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endRule()
     */
    @Override
    public void endRule() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#startInfixPredicate(java.lang.String)
     */
    @Override
    public void startInfixPredicate(String name) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.ITologHandler#endInfixPredicate()
     */
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
    public void startDynamicPredicate() throws MQLException { }

    @Override
    public void endDynamicPredicate() throws MQLException { }

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

}
