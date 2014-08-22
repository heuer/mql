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

/**
 * TMQL token types.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 * @version $Rev: 518 $ - $Date: 2010-09-12 00:29:27 +0200 (So, 12 Sep 2010) $
 */
final class TokenTypes {

    public static final int
        // Directives
        DIR_ENCODING = TMQLParser.DIR_ENCODING,
        DIR_VERSION = TMQLParser.DIR_VERSION,
        DIR_PREFIX = TMQLParser.DIR_PREFIX,

        // Keywords
        KW_SELECT = 2323,
        KW_FOR = 2929,
        KW_IN = 939393,
        KW_RETURN = 39393,
        KW_IF = 93933,
        KW_THEN = 3933,
        KW_ELSE = 392229,
        KW_SOME = 931833,
        KW_EVERY = 38333,
        KW_SATISFIES = 998822,
        KW_WHERE = 37333,
        KW_ORDER = 33933,
        KW_BY = 33388,
        KW_ASC = 393939,
        KW_DESC = 3939333,
        KW_LIMIT = 393593,
        KW_OFFSET = 219393,

        // Identifiers
        IDENT = TMQLParser.IDENT,
        QNAME = TMQLParser.QNAME,
        VARIABLE = TMQLParser.VARIABLE,

        // Delimiters
        // Brackets
        LPAREN = 4,
        RPAREN = 5,
        LCURLY = 6,
        RCURLY = 7,
        LBRACK = 3955393,
        RBRACK = 393933,

        DOT = -11010011,
        ELLIPSIS = -11010021,
        SLASH = -20202,
        AT = -10101,
        COMMA = 8,
        COLON = 9,
        COLON_COLON = TMQLParser.COLON_COLON,
        DOUBLE_CIRCUMFLEX = TMQLParser.DOUBLE_CIRCUMFLEX,
        STAR = 34924324,

        // Comparators
        EQ = -4447,
        NE = -44474,
        GT = -44433,
        LT = -44437,
        GE = -443347,
        LE = -4443447,

        // Datatypes
        STRING = TMQLParser.STRING,
        IRI = TMQLParser.IRI,
        INTEGER = TMQLParser.INTEGER,
        DECIMAL = TMQLParser.DECIMAL,
        DATE = TMQLParser.DATE,
        DATE_TIME = TMQLParser.DATE_TIME
        ;

    /**
     * Returns a name for the specified token <code>type</code>.
     *
     * <p>
     * Useful for debugging purposes and error messages.
     * </p>
     * 
     * @param type A token type.
     * @return The name of the token type.
     */
    public static String name(final int type) {
        switch (type) {
            // Directives
            case DIR_ENCODING:  return "%encoding";
            case DIR_VERSION:   return "%version";
            case DIR_PREFIX:    return "%prefix";

            // Keywords
            case KW_SELECT:     return "select";
            case KW_FOR:        return "for";
            case KW_IN:         return "in";
            case KW_RETURN:     return "return";
            case KW_IF:         return "if";
            case KW_THEN:       return "then";
            case KW_ELSE:       return "else";
            case KW_SOME:       return "some";
            case KW_EVERY:      return "every";
            case KW_SATISFIES:  return "satisfies";
            case KW_WHERE:      return "where";
            case KW_ORDER:      return "order";
            case KW_BY:         return "by";
            case KW_ASC:        return "asc";
            case KW_DESC:       return "desc";
            case KW_LIMIT:      return "limit";
            case KW_OFFSET:     return "offset";

            // Identifiers
            case IDENT:         return "<identifier>";
            case QNAME:         return "<qname>";
            case VARIABLE:      return "<variable>";

            // Delimiters
            // Brackets
            case LPAREN:        return "(";
            case RPAREN:        return ")";
            case LCURLY:        return "{";
            case RCURLY:        return "}";
            case LBRACK:        return "[";
            case RBRACK:        return "]";
            
            case DOT:           return ".";
            case ELLIPSIS:      return "...";
            case SLASH:         return "/";
            case AT:            return "@";
            case COMMA:         return ",";
            case COLON:         return ":";
            case COLON_COLON:   return "::";
            case DOUBLE_CIRCUMFLEX: return "^^";
            case STAR:          return "*";
            
            // Comparators
            case EQ:            return "=";
            case NE:            return "!=";
            case GT:            return ">";
            case LT:            return "<";
            case GE:            return ">=";
            case LE:            return "<=";

            // Datatypes
            case STRING:        return "<string>";
            case IRI:           return "<iri>";
            case INTEGER:       return "<integer>";
            case DECIMAL:       return "<decimal>";
            case DATE:          return "<date>";
            case DATE_TIME:     return "<dateTime>";
        }
        throw new RuntimeException("Unknown token type '" + type + "'");
    }
}
