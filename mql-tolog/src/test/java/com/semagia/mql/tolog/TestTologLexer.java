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
import java.io.StringReader;

import com.semagia.mql.MQLException;

import junit.framework.TestCase;

/**
 * Tests against the {@link TologLexer}.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
public class TestTologLexer extends TestCase {

    private TologLexer _lexer(String input) throws IOException {
        StringReader reader = new StringReader(input);
        return new TologLexer(reader);
    }

    private void _lex(String input, int[] expected) throws IOException, MQLException {
        TologLexer lexer = _lexer(input);
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

    public void testSubjectIdentifier() throws Exception {
        final String input = "i\"http://psi.semagia.com/test\" i<http://psi.semagia.com/test>";
        final int[] expected = new int[] { 
                TokenTypes.SID, 
                TokenTypes.SID
                };
        _lex(input, expected);
    }

    public void testSubjectLocator() throws Exception {
        final String input = "a\"http://psi.semagia.com/test\" a<http://psi.semagia.com/test>";
        final int[] expected = new int[] { 
                TokenTypes.SLO, 
                TokenTypes.SLO
                };
        _lex(input, expected);
    }

    public void testItemIdentifier() throws Exception {
        final String input = "s\"http://psi.semagia.com/test\" s<http://psi.semagia.com/test>";
        final int[] expected = new int[] { 
                TokenTypes.IID, 
                TokenTypes.IID
                };
        _lex(input, expected);
    }

    public void testInsert() throws Exception {
        final String input = "INSERT from-hell - \"I am from hell\".";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT };
        _lex(input, expected);
    }

    public void testInsert2() throws Exception {
        final String input = "INSERT #( from )# me. to. you. from tolog-predicate($x)";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT,
                TokenTypes.KW_FROM,
                TokenTypes.IDENT,
                TokenTypes.LPAREN,
                TokenTypes.VARIABLE,
                TokenTypes.RPAREN
                };
        _lex(input, expected);
    }

    public void testInsert3() throws Exception {
        // This is an invalid insert statement, but the lexer should eat it
        final String input = "INSERT from .";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT,
                TokenTypes.KW_FROM,
                TokenTypes.DOT
                };
        _lex(input, expected);
    }

    public void testInsert4() throws Exception {
        // This is *maybe* an invalid insert statement, but the lexer should eat it
        final String input = "INSERT from.";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT
                };
        _lex(input, expected);
    }

    public void testInsert5() throws Exception {
        // This is an invalid insert statement, but the lexer should eat it
        final String input = "INSERT from . from article-about($topic, $psi)";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT,
                TokenTypes.KW_FROM,
                TokenTypes.DOT,
                TokenTypes.KW_FROM,
                TokenTypes.IDENT,
                TokenTypes.LPAREN,
                TokenTypes.VARIABLE,
                TokenTypes.COMMA,
                TokenTypes.VARIABLE,
                TokenTypes.RPAREN,
                };
        _lex(input, expected);
    }

    public void testInsert7() throws Exception {
        final String input = "INSERT #( from )# me. #( co #(mm)# ent )# to. #( comment )# you. #( comment )# from tolog-predicate($x)";
        final int[] expected = new int[] { 
                TokenTypes.KW_INSERT, 
                TokenTypes.TM_FRAGMENT,
                TokenTypes.KW_FROM,
                TokenTypes.IDENT,
                TokenTypes.LPAREN,
                TokenTypes.VARIABLE,
                TokenTypes.RPAREN
                };
        _lex(input, expected);
    }

    public void testIgnoreAfterQM() throws Exception {
        final String input = "SELECT $t from topic($t)? Here some content which must be ignored";
        final int[] expected = new int[] { 
                TokenTypes.KW_SELECT, 
                TokenTypes.VARIABLE,
                TokenTypes.KW_FROM,
                TokenTypes.IDENT,
                TokenTypes.LPAREN,
                TokenTypes.VARIABLE,
                TokenTypes.RPAREN,
                TokenTypes.QM,
                };
        _lex(input, expected);
    }

    public void testQName() throws Exception {
        final String input = "q:name another:qname legal:q2name.here";
        final int[] expected = new int[] { 
                TokenTypes.QNAME, 
                TokenTypes.QNAME,
                TokenTypes.QNAME,
                };
        _lex(input, expected);
    }

}
