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
import java.io.Reader;

import com.semagia.mql.MQLException;

/**
 * 
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 517 $ - $Date: 2010-09-12 00:26:02 +0200 (So, 12 Sep 2010) $
 */
final class TMQLParser extends RealTMQLParser {

    public void parse(final Reader reader) throws IOException, MQLException {
        yyInput lexer = new TMQLLexer(reader);
        try {
            yyparse(lexer);
        }
        catch (yyException ex) {
            throw new MQLException(ex.getMessage());
        }
    }

    /* (non-Javadoc)
     * @see com.semagia.mql.tmql.RealTMQLParser#tokenToName(int)
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
