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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.semagia.mql.MQLException;
import com.semagia.mql.tolog.RealTologParser.yyInput;

/**
 * tolog lexer which provides the API which the parser expects.
 * 
 * This class is used to keep the (JFlex) lexer independent of the (Jay) parser.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
final class TologLexer extends RealTologLexer implements yyInput {

    /**
     * Current token identifier.
     */
    private int _current;
    private boolean _insertEOFSeen;

    /**
     * 
     *
     * @param in
     */
    public TologLexer(final InputStream in) {
        this(new InputStreamReader(in));
    }

    /**
     * 
     *
     * @param reader
     */
    public TologLexer(final Reader reader) {
        super(reader);
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.RealTologParser.yyInput#advance()
     */
    @Override
    public boolean advance() throws IOException, MQLException {
        if (_insertEOFSeen) {
            return false;
        }
        _current = super.token();
        _insertEOFSeen = _current == RealTologLexer.TM_FRAGMENT_EOF;
        if (_insertEOFSeen) {
            _current = TokenTypes.TM_FRAGMENT;
            return true;
        }
        return _current != RealTologLexer.EOF;
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.RealTologLexer#value()
     */
    @Override
    public String value() {
        return super.value();
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.RealTologLexer#token()
     */
    @Override
    public int token() {
        return _current;
    }

}
