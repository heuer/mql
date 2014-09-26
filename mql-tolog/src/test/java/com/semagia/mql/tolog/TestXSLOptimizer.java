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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
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

import com.semagia.mio.cxtm.diff_match_patch;
import com.semagia.mio.cxtm.diff_match_patch.Patch;
import com.semagia.mql.tolog.helpers.SAXEmittingTologHandler;
import com.semagia.mql.tolog.helpers.XSLTOptimizer;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.canonical.Canonicalizer;
import static org.junit.Assert.assertTrue;

/**
 * Test against {@link SAXEmittingTologHandler} and {@link XMLTologHandler}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
@RunWith(Parameterized.class)
public class TestXSLOptimizer {

    private final String _filename;
    private final String[] _optimizers;

    public TestXSLOptimizer(final Object filename, Object optimizers) {
        _filename = (String) filename;
        _optimizers = (String[]) optimizers;
    }

    @Parameters(name="{index}: {0}")
    public static Iterable<Object[]> makeTestCases() {
        JSONTokener tokener = new JSONTokener(TestXSLOptimizer.class.getResourceAsStream("/xsltests/tolog/query2optimizers.json"));
        JSONObject root = new JSONObject(tokener);
        Object[][] res = new Object[root.length()][2];
        @SuppressWarnings("unchecked")
        String[] keys = ((Set<String> ) root.keySet()).toArray(new String[root.length()]);
        Arrays.sort(keys);
        for(int i=0; i<keys.length; i++) {
            res[i][0] = keys[i];
            JSONArray arr = root.getJSONArray(keys[i]);
            String[] values = new String[arr.length()+1];
            values[0] = "query-c14n";
            for (int j=0; j<arr.length(); j++) {
                values[j+1] = arr.getString(j);
            }
            res[i][1] = values;
        }
        return Arrays.asList(res);
    }

    @Test
    public void testTransform() throws Exception {
        if ("tolog-tut-2-4_2.tl".equals(_filename)) {
            return;
        }
        final URL url = TestXSLOptimizer.class.getResource("/xsltests/tolog/in/" + _filename);
        final URL referenceURL = TestXSLOptimizer.class.getResource("/xsltests/tolog/baseline/" + _filename + ".c14n");
        final Builder builder = new Builder(new XSLTOptimizerReader(new InputStreamReader(url.openStream()), _optimizers));
        final Document doc = builder.build("");
        final ByteArrayOutputStream expected = new ByteArrayOutputStream();
        final InputStream tmp = referenceURL.openStream();
        int b;
        while ((b = tmp.read()) != -1) {
            expected.write(b);
        }
        tmp.close();
        byte[] expectedBytes = expected.toByteArray();
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        Canonicalizer canonicalizer = new Canonicalizer(out);
        canonicalizer.write(doc);
        byte[] resDoc = out.toByteArray();
        String c14n = out.toString("utf-8");
        diff_match_patch dmp = new diff_match_patch();
        LinkedList<Patch> patches = dmp.patch_make(new String(expectedBytes, "utf-8"), c14n);
        //System.out.println(c14n);
        assertTrue(dmp.patch_toText(patches), Arrays.equals(expectedBytes, resDoc));
    }



    private static final class XSLTOptimizerReader implements XMLReader {

        private final Reader _source;
        private final String[] _optimizers;
        private final SAXEmittingTologHandler _handler;

        XSLTOptimizerReader(final Reader source, final String[] optimizers) {
            _source = source;
            _optimizers = optimizers;
            _handler = new SAXEmittingTologHandler();
        }

        @Override
        public void parse(InputSource input) throws IOException, SAXException {
            try {
                XSLTOptimizer.getInstance().applyTransformations(_source, _optimizers, _handler);
            }
            catch (Exception ex) {
                throw new SAXException(ex.getMessage(), ex);
            }
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
