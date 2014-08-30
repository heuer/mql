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

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.semagia.mql.IQueryHandler;
import com.semagia.mql.MQLException;

/**
 * Abstract {@link IQueryHandler} which reports all events to an underlying
 * SAX {@link ContentHandler}
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public abstract class AbstractSAXEmittingQueryHandler implements IQueryHandler {

    protected static final Attributes _EMPTY_ATTRS = new AttributesImpl();
    
    protected final ContentHandler _handler;
    private final AttributesImpl _attrs = new AttributesImpl();

    private final String _namespace;

    protected AbstractSAXEmittingQueryHandler(final ContentHandler handler, final String namespace) {
        _handler = handler;
        _namespace = namespace;
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#start()
     */
    @Override
    public void start() throws MQLException {
        try {
            _handler.startDocument();
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
        startElement("query", "xmlns", _namespace);
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#end()
     */
    @Override
    public void end() throws MQLException {
        endElement("query");
        try {
            _handler.endDocument();
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startSelect()
     */
    @Override
    public void startSelect() throws MQLException {
        startElement("select");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endSelect()
     */
    @Override
    public void endSelect() throws MQLException {
        endElement("select");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startInsert()
     */
    @Override
    public void startInsert() throws MQLException {
        startElement("insert");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endInsert()
     */
    @Override
    public void endInsert() throws MQLException {
        endElement("insert");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startUpdate()
     */
    @Override
    public void startUpdate() throws MQLException {
        startElement("update");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endUpdate()
     */
    @Override
    public void endUpdate() throws MQLException {
        endElement("update");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startDelete()
     */
    @Override
    public void startDelete() throws MQLException {
        startElement("delete");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endDelete()
     */
    @Override
    public void endDelete() throws MQLException {
        endElement("delete");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startPagination()
     */
    @Override
    public void startPagination() throws MQLException {
        startElement("pagination");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endPagination()
     */
    @Override
    public void endPagination() throws MQLException {
        endElement("pagination");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#offset(int)
     */
    @Override
    public void offset(int offset) throws MQLException {
        emptyElement("offset", "value", offset);
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#limit(int)
     */
    @Override
    public void limit(int limit) throws MQLException {
        emptyElement("limit", "value", limit);
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOrderBy()
     */
    @Override
    public void startOrderBy() throws MQLException {
        startElement("order-by");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOrderBy()
     */
    @Override
    public void endOrderBy() throws MQLException {
        endElement("order-by");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#ascending(java.lang.String)
     */
    @Override
    public void ascending(String variable) throws MQLException {
        startElement("asc");
        variable(variable);
        endElement("asc");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#descending(java.lang.String)
     */
    @Override
    public void descending(String variable) throws MQLException {
        startElement("desc");
        variable(variable);
        endElement("desc");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startWhere()
     */
    @Override
    public void startWhere() throws MQLException {
        startElement("where");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endWhere()
     */
    @Override
    public void endWhere() throws MQLException {
        endElement("where");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#count(java.lang.String)
     */
    @Override
    public void count(String variable) throws MQLException {
        startElement("count");
        variable(variable);
        endElement("count");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startOr()
     */
    @Override
    public void startOr() throws MQLException {
        startElement("or");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endOr()
     */
    @Override
    public void endOr() throws MQLException {
        endElement("or");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startBranch(boolean)
     */
    @Override
    public void startBranch(boolean shortcircuit) throws MQLException {
        if (shortcircuit)   {
            startElement("branch", "shortcircuit", "true");
        }
        else {
            startElement("branch");
        }
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endBranch()
     */
    @Override
    public void endBranch() throws MQLException {
        endElement("branch");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#startNot()
     */
    @Override
    public void startNot() throws MQLException {
        startElement("not");
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.IQueryHandler#endNot()
     */
    @Override
    public void endNot() throws MQLException {
        endElement("not");
    }

    public void variable(String name) throws MQLException {
        emptyElement("variable", "name", name);
    }

    protected final void characters(String value) throws MQLException {
        try {
            final char[] ch = value.toCharArray();
            _handler.characters(ch, 0, ch.length);
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

    protected final void emptyElement(String name, String key, int value) throws MQLException {
        emptyElement(name, key, Integer.toString(value));
    }

    protected final void emptyElement(String name, String key, String value) throws MQLException {
        emptyElement(name, new String[][]{{key, value}});
    }

    protected final void emptyElement(final String name, final String[][] attrs) throws MQLException {
        startElement(name, attrs);
        endElement(name);
    }

    protected final void startElement(String name) throws MQLException {
        startElement(name, _EMPTY_ATTRS);
    }

    protected final void startElement(String name, String key, String value) throws MQLException {
        startElement(name, new String[][]{{key, value}});
    }

    protected final void startElement(String name, String[][] attrs) throws MQLException {
        _attrs.clear();
        for (int i=0; i < attrs.length; i++) {
            _attrs.addAttribute("", attrs[i][0], "", "CDATA", attrs[i][1]);
        }
        startElement(name, _attrs);
    }

    private final void startElement(String name, Attributes attrs) throws MQLException {
        try {
            _handler.startElement("", name, "", attrs);
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

    protected final void dataElement(String name, String content) throws MQLException {
        startElement(name);
        characters(content);
        endElement(name);
    }

    protected final void endElement(String name) throws MQLException {
        try {
            _handler.endElement("", name, "");
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

}
