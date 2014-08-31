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
package com.semagia.mql;

/**
 * Handler which provides common callbacks for parsing queries.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public interface IQueryHandler {

    /**
     * This is the first event of an event stream.
     *
     * @throws MQLException In case of an error.
     */
    void start() throws MQLException;
    
    /**
     * This is the last event of an event stream.
     *
     * @throws MQLException In case of an error.
     */
    void end() throws MQLException;

    /**
     * Notification of the start of a select statement.
     *
     * @throws MQLException In case of an error.
     */
    void startSelect() throws MQLException;

    /**
     * Notification of the end of a select statement.
     *
     * @throws MQLException In case of an error.
     */
    void endSelect() throws MQLException;

    /**
     * Notification of the start of an insert statement.
     *
     * @throws MQLException In case of an error.
     */
    void startInsert() throws MQLException;

    /**
     * Notification of the end of an insert statement.
     *
     * @throws MQLException In case of an error.
     */
    void endInsert() throws MQLException;

    /**
     * Notification of the start of a update statement.
     *
     * @throws MQLException In case of an error.
     */
    void startUpdate() throws MQLException;

    /**
     * Notification of the end of a update statement.
     *
     * @throws MQLException In case of an error.
     */
    void endUpdate() throws MQLException;

    /**
     * Notification of the start of a delete statement.
     *
     * @throws MQLException In case of an error.
     */
    void startDelete() throws MQLException;

    /**
     * Notification of the end of a delete statement.
     *
     * @throws MQLException In case of an error.
     */
    void endDelete() throws MQLException;

    /**
     * Notification of the start of pagination.
     *
     * @throws MQLException In case of an error.
     */
    public void startPagination() throws MQLException;

    /**
     * Notification of the start of pagination.
     *
     * @throws MQLException In case of an error.
     */
    public void endPagination() throws MQLException;

    /**
     * Sets the pagination offset.
     *
     * @param offset The offset.
     * @throws MQLException In case of an error.
     */
    void offset(int offset) throws MQLException;

    /**
     * Sets the pagination limit.
     *
     * @param limit The limit.
     * @throws MQLException In case of an error.
     */
    void limit(int limit) throws MQLException;

    /**
     * Indicates the start of an order by.
     *
     * @throws MQLException In case of an error.
     */
    void startOrderBy() throws MQLException;

    /**
     * Indicates the end of an order by.
     *
     * @throws MQLException In case of an error.
     */
    void endOrderBy() throws MQLException;

    /**
     * Notification about a variable which should be sorted ascending.
     *
     * @param variable The variable name (without any prefix like "$").
     * @throws MQLException In case of an error.
     */
    void ascending(String variable) throws MQLException;

    /**
     * Notification about a variable which should be sorted descending.
     *
     * @param variable The variable name (without any prefix like "$").
     * @throws MQLException In case of an error.
     */
    void descending(String variable) throws MQLException;

    /**
     * Notification of the start of a where clause.
     *
     * @throws MQLException In case of an error.
     */
    void startWhere() throws MQLException;

    /**
     * Notification of the end of a where clause.
     *
     * @throws MQLException In case of an error.
     */
    void endWhere() throws MQLException;

    /**
     * Notification a "count" clause.
     *
     * @param variable The variable name (without any prefix like "$").
     * @throws MQLException In case of an error.
     */
    void count(String variable) throws MQLException;

    /**
     * Notification of the start of an "or" clause.
     *
     * @throws MQLException In case of an error.
     */
    void startOr() throws MQLException;

    /**
     * Notification of the end of an "or" clause.
     *
     * @throws MQLException In case of an error.
     */
    void endOr() throws MQLException;

    /**
     * Notification of the start of an or-branch.
     *
     * @param shortcircuit Indicates if this branch is a short circuit branch.
     * @throws MQLException In case of an error.
     */
    void startBranch(boolean shortcircuit) throws MQLException;

    /**
     * Notification of the end of an or-branch.
     *
     * @throws MQLException In case of an error.
     */
    void endBranch() throws MQLException;

    /**
     * Notification of the start of a "not" expression.
     *
     * @throws MQLException In case of an error.
     */
    void startNot() throws MQLException;

    /**
     * Notification of the end of a "not" expression.
     *
     * @throws MQLException In case of an error.
     */
    void endNot() throws MQLException;

    void variable(String name) throws MQLException;
}
