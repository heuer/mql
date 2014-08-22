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

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import junit.framework.TestCase;

import org.xml.sax.ContentHandler;

import com.semagia.mio.utils.xml.SAXXMLWriter;
import com.semagia.mql.IQueryHandler;

import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.XPathContext;

/**
 * Tests against the {@link AbstractSAXQueryHandler}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class TestAbstractSAXQueryHandler extends TestCase {

    private final String _NS = "http:/www.example.org/";
    
    private ContentHandler _makeWriter(final OutputStream out) {
        return new SAXXMLWriter(out);
    }

    private IQueryHandler _makeQueryHandler(final OutputStream out) {
        return new MockSAXQueryHandler(_makeWriter(out), _NS);
    }

    private Document _makeDocument(final ByteArrayOutputStream out) throws Exception {
        return new Builder().build(out.toString("utf-8"), null);
    }

    private Nodes _query(final Document doc, final String query) {
        return doc.query(query, new XPathContext("mql", _NS));
    }


    public void testNoop() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final IQueryHandler handler = _makeQueryHandler(out);
        handler.start();
        handler.end();
        final Document doc = _makeDocument(out);
        assertEquals(0, _query(doc, "/mql:query/*").size());
    }

    public void testSelect() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final IQueryHandler handler = _makeQueryHandler(out);
        handler.start();
        handler.startSelect();
        handler.endSelect();
        handler.end();
        final Document doc = _makeDocument(out);
        final Nodes nodes = _query(doc, "/mql:query/*");
        assertEquals(1, nodes.size());
        final Element el = (Element) nodes.get(0); 
        assertEquals("select", el.getLocalName());
    }

    public void testCount() throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final IQueryHandler handler = _makeQueryHandler(out);
        handler.start();
        handler.startSelect();
        handler.count("var");
        handler.endSelect();
        handler.end();
        final Document doc = _makeDocument(out);
        final Nodes nodes = _query(doc, "//mql:count/*");
        assertEquals(1, nodes.size());
        final Element el = (Element) nodes.get(0); 
        assertEquals("variable", el.getLocalName());
        assertEquals(1, el.getAttributeCount());
        final Attribute attr = el.getAttribute(0);
        assertEquals("name", attr.getLocalName());
        assertEquals("var", attr.getValue());
    }

    private static class MockSAXQueryHandler extends AbstractSAXQueryHandler {

        public MockSAXQueryHandler(final ContentHandler handler, final String ns) {
            super(handler, ns);
        }
    }

}
