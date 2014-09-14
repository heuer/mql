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
import java.io.Reader;

import com.semagia.mql.MQLException;

/**
 * This class is the main entry point for parsing tolog.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public final class TologParser extends RealTologParser implements ITologParser {

    /**
     * Parses a tolog query using the provided {@code reader}.
     * 
     * @param reader The reader to utilize.
     * @throws IOException In case of an IO error.
     * @throws MQLException In case of a parsing error.
     * @see #parse(TologLexer)
     */
    public void parse(final Reader reader) throws IOException, MQLException {
        parse(new TologLexer(reader));
    }

    /**
     * Parses a tolog query using the provided {@code lexer}.
     * 
     * @param lexer
     * @throws IOException In case of an IO error.
     * @throws MQLException In case of a parsing error.
     */
    public void parse(final TologLexer lexer) throws IOException, MQLException {
        try {
            super._handler.start();
            yyparse(lexer);
            super._handleEndOfRules();
            super._handler.end();
        }
        catch (yyException ex) {
            throw new MQLException(ex.getMessage(), ex);
        }
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tolog.RealTologParser#tokenToName(int)
     */
    @Override
    protected String tokenToName(final int token) {
        try {
            return TokenTypes.name(token);
        }
        catch (RuntimeException ex) {
            return super.tokenToName(token);
        }
    }

}
