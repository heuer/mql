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
package com.semagia.mql.base;

import com.semagia.mql.IQueryHandler;
import com.semagia.mql.MQLException;

/**
 * Abstract implementation of {@link IQueryHandler} which swallows all events
 * and does nothing.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public abstract class AbstractQueryHandler implements IQueryHandler {

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#ascending(java.lang.String)
     */
    @Override
    public void ascending(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#count(java.lang.String)
     */
    @Override
    public void count(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#descending(java.lang.String)
     */
    @Override
    public void descending(String variable) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#end()
     */
    @Override
    public void end() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endDelete()
     */
    @Override
    public void endDelete() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endInsert()
     */
    @Override
    public void endInsert() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOrderBy()
     */
    @Override
    public void endOrderBy() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endPagination()
     */
    @Override
    public void endPagination() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endSelect()
     */
    @Override
    public void endSelect() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endUpdate()
     */
    @Override
    public void endUpdate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endWhere()
     */
    @Override
    public void endWhere() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#limit(int)
     */
    @Override
    public void limit(int limit) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#offset(int)
     */
    @Override
    public void offset(int offset) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#start()
     */
    @Override
    public void start() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startDelete()
     */
    @Override
    public void startDelete() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startInsert()
     */
    @Override
    public void startInsert() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOrderBy()
     */
    @Override
    public void startOrderBy() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startPagination()
     */
    @Override
    public void startPagination() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startSelect()
     */
    @Override
    public void startSelect() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startUpdate()
     */
    @Override
    public void startUpdate() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startWhere()
     */
    @Override
    public void startWhere() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOr()
     */
    @Override
    public void endOr() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOr()
     */
    @Override
    public void startOr() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endBranch()
     */
    @Override
    public void endBranch() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endNot()
     */
    @Override
    public void endNot() throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startBranch(boolean)
     */
    @Override
    public void startBranch(boolean shortcircuit) throws MQLException { }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startNot()
     */
    @Override
    public void startNot() throws MQLException { }

}
