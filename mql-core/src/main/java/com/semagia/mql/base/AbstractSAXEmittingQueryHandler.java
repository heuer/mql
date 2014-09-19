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

    private static final Attributes _EMPTY_ATTRS = new AttributesImpl();
    protected ContentHandler _handler;
    private final AttributesImpl _attrs = new AttributesImpl();

    private final String _namespace;

    protected AbstractSAXEmittingQueryHandler(final String namespace) {
        _namespace = namespace;
    }

    protected AbstractSAXEmittingQueryHandler(final ContentHandler handler, final String namespace) {
        _handler = handler;
        _namespace = namespace;
    }

    public void setContentHandler(final ContentHandler handler) {
        _handler = handler;
    }

    public ContentHandler getContentHandler() {
        return _handler;
    }

    @Override
    public void start() throws MQLException {
        try {
            _handler.startDocument();
            _handler.startPrefixMapping("", _namespace);
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
        startElement("query");
    }

    @Override
    public void end() throws MQLException {
        endElement("query");
        try {
            _handler.endPrefixMapping("");
            _handler.endDocument();
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

    @Override
    public void startSelect() throws MQLException {
        startElement("select");
    }

    @Override
    public void endSelect() throws MQLException {
        endElement("select");
    }

    @Override
    public void startInsert() throws MQLException {
        startElement("insert");
    }

    @Override
    public void endInsert() throws MQLException {
        endElement("insert");
    }

    @Override
    public void startUpdate() throws MQLException {
        startElement("update");
    }

    @Override
    public void endUpdate() throws MQLException {
        endElement("update");
    }

    @Override
    public void startDelete() throws MQLException {
        startElement("delete");
    }

    @Override
    public void endDelete() throws MQLException {
        endElement("delete");
    }

    @Override
    public void startPagination() throws MQLException {
        startElement("pagination");
    }

    @Override
    public void endPagination() throws MQLException {
        endElement("pagination");
    }

    @Override
    public void offset(int offset) throws MQLException {
        emptyElement("offset", "value", offset);
    }

    @Override
    public void limit(int limit) throws MQLException {
        emptyElement("limit", "value", limit);
    }

    @Override
    public void startOrderBy() throws MQLException {
        startElement("order-by");
    }

    @Override
    public void endOrderBy() throws MQLException {
        endElement("order-by");
    }

    @Override
    public void ascending(String variable) throws MQLException {
        emptyElement("asc", "value", variable);
    }

    @Override
    public void descending(String variable) throws MQLException {
        emptyElement("desc", "value", variable);
    }

    @Override
    public void startWhere() throws MQLException {
        startElement("where");
    }

    @Override
    public void endWhere() throws MQLException {
        endElement("where");
    }

    @Override
    public void count(String variable) throws MQLException {
        emptyElement("count", "value", variable);
    }

    @Override
    public void startOr() throws MQLException {
        startElement("or");
    }

    @Override
    public void endOr() throws MQLException {
        endElement("or");
    }

    @Override
    public void startBranch(boolean shortcircuit) throws MQLException {
        if (shortcircuit)   {
            startElement("branch", "shortcircuit", "true");
        }
        else {
            startElement("branch");
        }
    }

    @Override
    public void endBranch() throws MQLException {
        endElement("branch");
    }

    @Override
    public void startNot() throws MQLException {
        startElement("not");
    }

    @Override
    public void endNot() throws MQLException {
        endElement("not");
    }

    @Override
    public void variable(String name) throws MQLException {
        emptyElement("variable", "name", name);
    }

    private final void characters(String value) throws MQLException {
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
            _attrs.addAttribute("", "", attrs[i][0], "CDATA", attrs[i][1]);
        }
        startElement(name, _attrs);
    }

    private final void startElement(String name, Attributes attrs) throws MQLException {
        try {
            _handler.startElement(_namespace, "", name, attrs);
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
            _handler.endElement(_namespace, "", name);
        }
        catch (SAXException ex) {
            throw new MQLException(ex);
        }
    }

}
