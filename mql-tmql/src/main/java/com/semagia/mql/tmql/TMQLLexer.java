/*
 * Copyright 2010 Lars Heuer (heuer[at]semagia.com)
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
package com.semagia.mql.tmql;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import com.semagia.mql.MQLException;
import com.semagia.mql.tmql.RealTMQLParser.yyInput;

/**
 * TMQL lexer which provides the API which the parser expects.
 * 
 * This class is used to keep the (JFlex) lexer independent of the (Jay) parser.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 511 $ - $Date: 2010-09-10 13:50:41 +0200 (Fr, 10 Sep 2010) $
 */
final class TMQLLexer extends RealTMQLLexer implements yyInput {

    /**
     * Current token identifier.
     */
    private int _current;

    /**
     * 
     *
     * @param in
     */
    public TMQLLexer(final InputStream in) {
        this(new InputStreamReader(in));
    }

    /**
     * 
     *
     * @param reader
     */
    public TMQLLexer(final Reader reader) {
        super(reader);
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tmql.RealTMQLParser.yyInput#advance()
     */
    @Override
    public boolean advance() throws IOException, MQLException {
        _current = super.token();
        return _current != RealTMQLLexer.EOF;
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tmql.RealTMQLLexer#value()
     */
    @Override
    public String value() {
        return super.value();
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tmql.RealTMQLLexer#token()
     */
    @Override
    public int token() {
        return _current;
    }

}
