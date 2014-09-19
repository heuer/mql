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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import com.semagia.mql.tolog.TologParser;

/**
 * Basic {@link XMLReader} implementation which emits SAX events from a tolog source.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class TologReader implements XMLReader {

    private final SAXEmittingTologHandler _handler;

    public TologReader() {
        _handler = new SAXEmittingTologHandler();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#parse(org.xml.sax.InputSource)
     */
    @Override
    public void parse(final InputSource input) throws IOException, SAXException {
        // Convert the InputSource into a Reader
        BufferedReader reader = null;
        if (input.getCharacterStream( ) != null) {
            reader = new BufferedReader(input.getCharacterStream( ));
        } 
        else if (input.getByteStream( ) != null) {
            reader = new BufferedReader(new InputStreamReader(input.getByteStream( )));
        }
        else if (input.getSystemId( ) != null) {
            reader = new BufferedReader(new InputStreamReader(new URL(input.getSystemId( )).openStream( )));
        } 
        else {
            throw new SAXException("Invalid InputSource instance");
        }
        final TologParser parser = new TologParser();
        parser.setHandler(_handler);
        parser.parse(reader);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#parse(java.lang.String)
     */
    @Override
    public void parse(final String systemId) throws IOException, SAXException {
        parse(new InputSource(systemId));
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setContentHandler(org.xml.sax.ContentHandler)
     */
    @Override
    public void setContentHandler(final ContentHandler handler) {
        _handler.setContentHandler(handler);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getContentHandler()
     */
    @Override
    public ContentHandler getContentHandler() {
        return _handler.getContentHandler();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getFeature(java.lang.String)
     */
    @Override
    public boolean getFeature(String name) throws SAXNotRecognizedException,
            SAXNotSupportedException {
        throw new SAXNotSupportedException();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setFeature(java.lang.String, boolean)
     */
    @Override
    public void setFeature(String name, boolean value) throws SAXNotRecognizedException,
            SAXNotSupportedException {
        if (name.equals("http://xml.org/sax/features/string-interning")
                || name.equals("http://xml.org/sax/features/validation")) {
            throw new SAXNotSupportedException();
        }
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getProperty(java.lang.String)
     */
    @Override
    public Object getProperty(String name) throws SAXNotRecognizedException,
            SAXNotSupportedException {
        throw new SAXNotSupportedException();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setProperty(java.lang.String, java.lang.Object)
     */
    @Override
    public void setProperty(String name, Object value) throws SAXNotRecognizedException,
            SAXNotSupportedException {
        throw new SAXNotSupportedException();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setEntityResolver(org.xml.sax.EntityResolver)
     */
    @Override
    public void setEntityResolver(EntityResolver resolver) {

    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getEntityResolver()
     */
    @Override
    public EntityResolver getEntityResolver() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setDTDHandler(org.xml.sax.DTDHandler)
     */
    @Override
    public void setDTDHandler(DTDHandler handler) {

    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getDTDHandler()
     */
    @Override
    public DTDHandler getDTDHandler() {
        return null;
    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#setErrorHandler(org.xml.sax.ErrorHandler)
     */
    @Override
    public void setErrorHandler(ErrorHandler handler) {

    }

    /* (non-Javadoc)
     * @see org.xml.sax.XMLReader#getErrorHandler()
     */
    @Override
    public ErrorHandler getErrorHandler() {
        return null;
    }

}
