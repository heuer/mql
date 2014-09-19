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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import com.semagia.mql.tolog.helpers.SAXEmittingTologHandler;
import com.semagia.mql.tolog.helpers.TologReader;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.canonical.Canonicalizer;
import nu.xom.converters.SAXConverter;
import static org.junit.Assert.assertTrue;

/**
 * Test against {@link SAXEmittingTologHandler} and {@link XMLTologHandler}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
@RunWith(Parameterized.class)
public class TestXMLContentHandler {

    private final String _query;

    public TestXMLContentHandler(final String query) {
        _query = query;
    }

    @Parameters
    public static Collection<String[]> makeTestCases() {
        return Arrays.asList(new String[][] {
                {"select $x from topic($x)?"},
                {"x($y) :- x($y)."},
                {"select $TOPIC, $DESCR, $DATE from "
                        + "  value-like($OBJ, \"horse\"), "
                        + "  { topic-name($TOPIC, $OBJ) | "
                        + "    occurrence($TOPIC, $OBJ), type($OBJ, beskrivelse) }, "
                        + "  topicbelongstosubject($TOPIC : bbtopic, k7ahistory : fag), "
                        + "  userownstopic($TOPIC : ownedtopic, gdm : bruker), "
                        + "  beskrivelse($TOPIC, $DESCR), "
                        + "  lastupdated($TOPIC, $DATE) " + "order by $DATE?"},
                {"association($a)?"},
        });
    }

    @Test
    public void testReading() throws Exception {
        final Document doc = readIntoDoc(_query);
        final Document doc2 = convertDocumentToDocument(doc);
        assertEqualDocs(doc, doc2);
    }


    private static Document readIntoDoc(String query) throws Exception {
        final Builder builder = new Builder(new TologReader());
        return builder.build(new StringReader(query));
    }

    private static Document convertDocumentToDocument(final Document doc) throws Exception {
        final Builder builder = new Builder(new FromDocToSAXXMLReader(doc));
        return builder.build("");
    }

    private static void assertEqualDocs(final Document doc1, final Document doc2) throws Exception {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        Canonicalizer canonicalizer = new Canonicalizer(out);
        canonicalizer.write(doc1);
        byte[] resDoc1 = out.toByteArray();
        out.reset();
        canonicalizer = new Canonicalizer(out);
        canonicalizer.write(doc2);
        byte[] resDoc2 = out.toByteArray();
        assertTrue(Arrays.equals(resDoc1, resDoc2));
    }



    private static final class FromDocToSAXXMLReader implements XMLReader {

        private final Document _doc;
        private final SAXEmittingTologHandler _handler;

        FromDocToSAXXMLReader(final Document doc) {
            _doc = doc;
            _handler = new SAXEmittingTologHandler();
        }

        @Override
        public void parse(InputSource input) throws IOException, SAXException {
            final ContentHandler contentHandler = new XMLTologContentHandler(_handler);
            final SAXConverter converter = new SAXConverter(contentHandler);
            converter.convert(_doc);
        }

        @Override
        public void parse(String systemId) throws IOException, SAXException {
            parse(new InputSource(systemId));
        }

        @Override
        public void setContentHandler(ContentHandler handler) {
            _handler.setContentHandler(handler);
        }

        @Override
        public ContentHandler getContentHandler() {
            return _handler.getContentHandler();
        }

        @Override
        public boolean getFeature(String name) throws SAXNotRecognizedException,
                SAXNotSupportedException {
            return false;
        }

        @Override
        public void setFeature(String name, boolean value)
                throws SAXNotRecognizedException, SAXNotSupportedException {
        }

        @Override
        public Object getProperty(String name) throws SAXNotRecognizedException,
                SAXNotSupportedException {
            return null;
        }

        @Override
        public void setProperty(String name, Object value)
                throws SAXNotRecognizedException, SAXNotSupportedException {
        }

        @Override
        public void setEntityResolver(EntityResolver resolver) {
        }

        @Override
        public EntityResolver getEntityResolver() {
            return null;
        }

        @Override
        public void setDTDHandler(DTDHandler handler) {
        }

        @Override
        public DTDHandler getDTDHandler() {
            return null;
        }

        @Override
        public void setErrorHandler(ErrorHandler handler) {
        }

        @Override
        public ErrorHandler getErrorHandler() {
            return null;
        }

    }

}
