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
import java.io.StringReader;

import com.semagia.mql.MQLException;

import junit.framework.TestCase;

/**
 * Tests against the {@link TMQLLexer}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 517 $ - $Date: 2010-09-12 00:26:02 +0200 (So, 12 Sep 2010) $
 */
public class TestTMQLLexer extends TestCase {

    private TMQLLexer _lexer(String input) throws IOException {
        return new TMQLLexer(new StringReader(input));
    }

    private void _lex(String input, int[] expected) throws IOException, MQLException {
        final TMQLLexer lexer = _lexer(input);
        int i = 0;
        while (lexer.advance()) {
            int result = lexer.token();
            int expect = expected[i];
            final String value = lexer.value();
            assertEquals("Expected: <" + TokenTypes.name(expect) + "> got: <" + TokenTypes.name(result) + "> (value: '" + value + "')",
                    expect, result);
            i++;
        }
        assertEquals("Unexpected token length", expected.length, i);
    }

    public void testLexing() throws Exception {
        final String input = "an/expression";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.SLASH,
                TokenTypes.IDENT};
        _lex(input, expected);
    }

    public void testLexing2() throws Exception {
        final String input = "an@expression";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.AT,
                TokenTypes.IDENT};
        _lex(input, expected);
    }

    public void testLexing3() throws Exception {
        final String input = "person / default::email";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.IDENT};
        _lex(input, expected);
    }

    public void testLexing4() throws Exception {
        final String input = "person / occurrence :: email";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.IDENT};
        _lex(input, expected);
    }

    // 
    public void testLexing5() throws Exception {
        final String input = "person / occurrence::email";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.IDENT,
                };
        _lex(input, expected);
    }

    public void testLexing6() throws Exception {
        final String input = "/ association::* / role::*";
        final int[] expected = new int[] { 
                TokenTypes.SLASH, 
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.STAR,
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.STAR
                };
        _lex(input, expected);
    }

    public void testLexing7() throws Exception {
        final String input = "tmql / description @english";
        final int[] expected = new int[] { 
                TokenTypes.IDENT, 
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.AT,
                TokenTypes.IDENT,
                };
        _lex(input, expected);
    }

    public void testLexing8() throws Exception {
        final String input = "/ person [ . / date-of-birth < 1976-09-20 ]";
        final int[] expected = new int[] { 
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.LBRACK,
                TokenTypes.DOT,
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.LT,
                TokenTypes.DATE,
                TokenTypes.RBRACK,
                };
        _lex(input, expected);
    }

    public void testLexing9() throws Exception {
        final String input = "/ association #( comment )# :: #( comment )# * / #( comment )# role #( comment )# :: #( comment )# *";
        final int[] expected = new int[] { 
                TokenTypes.SLASH, 
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.STAR,
                TokenTypes.SLASH,
                TokenTypes.IDENT,
                TokenTypes.COLON_COLON,
                TokenTypes.STAR
                };
        _lex(input, expected);
    }
}
