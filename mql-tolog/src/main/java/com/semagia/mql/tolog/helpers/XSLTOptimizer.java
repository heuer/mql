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

import java.io.Reader;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamSource;

import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;

import com.semagia.mql.tolog.ITologHandler;
import com.semagia.mql.tolog.XMLTologContentHandler;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class XSLTOptimizer {

    private static final String[] DEFAULT_TRANSFORMERS = new String[] {
        "query-c14n",
        "inline-rules",
        "annotate-predicates",
        "remove-redundant-predicates",
        "annotate-costs",
        "fold-type",  // TODO: Move up once annotate-costs can handle predicates which were created here
        "reorder-predicates",
    };

    private final SAXTransformerFactory _tf;
    
    private final ConcurrentHashMap<String, Templates> _templates;

    private final static XSLTOptimizer _INSTANCE = new XSLTOptimizer();

    private XSLTOptimizer() {
        _tf = (SAXTransformerFactory) TransformerFactory.newInstance();
        _templates = new ConcurrentHashMap<String, Templates>();
        try {
            getTemplates("query-c14n");
        }
        catch (TransformerException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }

    public static XSLTOptimizer getInstance() {
        return _INSTANCE ;
    }

    public void applyDefaultTransformations(final Reader src, final ITologHandler handler) throws TransformerException {
        applyTransformations(src, DEFAULT_TRANSFORMERS, handler);
    }

    public void applyDefaultTransformations(final Reader src, final ContentHandler handler) throws TransformerException {
        applyTransformations(src, DEFAULT_TRANSFORMERS, handler);
    }

    public void applyTransformations(final Reader src, final String[] names, final ITologHandler handler) throws TransformerException {
        applyTransformations(src, names, new XMLTologContentHandler(handler));
    }

    public void applyTransformations(final Reader src, final String[] names, final ContentHandler handler) throws TransformerException {
        if (names.length > 0) {
            TransformerHandler th = _tf.newTransformerHandler(getTemplates(names[0]));
            TransformerHandler lastTransformerHandler = th;
            final SAXResult firstSAXResult = new SAXResult(th);
            for (int i=1; i<names.length; i++) {
                th = _tf.newTransformerHandler(getTemplates(names[i]));
                lastTransformerHandler.setResult(new SAXResult(th));
                lastTransformerHandler = th;
            }
            lastTransformerHandler.setResult(new SAXResult(handler));
            final Transformer t = _tf.newTransformer();
            t.transform(new SAXSource(new TologReader(), new InputSource(src)), firstSAXResult);
        }
        else {
            throw new RuntimeException("Bla bla");
        }
    }

    private Templates getTemplates(String name) throws TransformerConfigurationException {
        Templates tpl = _templates.get(name);
        if (tpl == null) {
            tpl = compileStylesheet(name);
            _templates.putIfAbsent(name, tpl);
        }
        if (tpl == null) {
            throw new RuntimeException("Internal error: Templates is null!");
        }
        return tpl;
    }

    private synchronized Templates compileStylesheet(String name) throws TransformerConfigurationException {
        return _tf.newTemplates(new StreamSource(getClass().getResourceAsStream("/xsl/" + name + ".xsl")));
    }

}
