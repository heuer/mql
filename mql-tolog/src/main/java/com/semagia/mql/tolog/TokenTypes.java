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

/**
 * tolog token types.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
final class TokenTypes {

    public static final int
        // Directives
        //DIR_PREFIX = TologParser.DIR_PREFIX,

        // Keywords
        KW_SELECT = TologParser.KW_SELECT,
        KW_FROM = TologParser.KW_FROM,
        KW_INSERT = TologParser.KW_INSERT,
        KW_MERGE = TologParser.KW_MERGE,
        KW_DELETE = TologParser.KW_DELETE,
        KW_UPDATE = TologParser.KW_UPDATE,
        KW_USING = TologParser.KW_USING,
        KW_IMPORT = TologParser.KW_IMPORT,
        KW_AS = TologParser.KW_AS,
        KW_FOR = TologParser.KW_FOR,
        KW_COUNT = TologParser.KW_COUNT,
        KW_ORDER = TologParser.KW_ORDER,
        KW_BY = TologParser.KW_BY,
        KW_ASC = TologParser.KW_ASC,
        KW_DESC = TologParser.KW_DESC,
        KW_NOT = TologParser.KW_NOT,
        KW_LIMIT = TologParser.KW_LIMIT,
        KW_OFFSET = TologParser.KW_OFFSET,

        // Identifiers
        IDENT = TologParser.IDENT,
        QNAME = TologParser.QNAME,
        SID = TologParser.SID,
        SLO = TologParser.SLO,
        IID = TologParser.IID,
        OID = TologParser.OID,
        VARIABLE = TologParser.VARIABLE,
        PARAMETER = TologParser.PARAMETER,

        // Delimiters
        // Brackets
        LPAREN = TologParser.LPAREN,
        RPAREN = TologParser.RPAREN,
        LCURLY = TologParser.LCURLY,
        RCURLY = TologParser.RCURLY,
    
        COMMA = TologParser.COMMA,
        COLON = TologParser.COLON,
        DOT = TologParser.DOT,
        //CIRCUMFLEX = TologParser.CIRCUMFLEX,
        DOUBLE_CIRCUMFLEX = TologParser.DOUBLE_CIRCUMFLEX,
        QM = TologParser.QM,
        IMPLIES = TologParser.IMPLIES,
        PIPE = TologParser.PIPE,
        PIPE_PIPE = TologParser.PIPE_PIPE,

        EQ = TologParser.EQ,
        NE = TologParser.NE,
        GT = TologParser.GT,
        LT = TologParser.LT,
        GE = TologParser.GE,
        LE = TologParser.LE,
        
        // Datatypes
        STRING = TologParser.STRING,
        IRI = TologParser.IRI,
        INTEGER = TologParser.INTEGER,
        DECIMAL = TologParser.DECIMAL,
        DATE = TologParser.DATE,
        DATE_TIME = TologParser.DATE_TIME,
        
        TM_FRAGMENT = TologParser.TM_FRAGMENT,
        
        OPT_KEY = TologParser.OPT_KEY,
        OPT_VALUE = TologParser.OPT_VALUE
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
            //case DIR_PREFIX:    return "%prefix";

            // Keywords
            case KW_SELECT:     return "select";
            case KW_FROM:       return "from";
            case KW_INSERT:     return "insert";
            case KW_MERGE:      return "merge";
            case KW_DELETE:     return "delete";
            case KW_UPDATE:     return "update";
            case KW_USING:      return "using";
            case KW_IMPORT:     return "import";
            case KW_AS:         return "as";
            case KW_FOR:        return "for";
            case KW_COUNT:      return "count";
            case KW_ORDER:      return "order";
            case KW_BY:         return "by";
            case KW_ASC:        return "asc";
            case KW_DESC:       return "desc";
            case KW_NOT:        return "not";
            case KW_LIMIT:      return "limit";
            case KW_OFFSET:     return "offset";

            // Identifiers
            case IDENT:         return "<identifier>";
            case QNAME:         return "<qname>";
            case SID:           return "<subject-identifier>";
            case SLO:           return "<subject-locator>";
            case IID:           return "<item-identifier>";
            case OID:           return "<object-identifier>";
            case VARIABLE:      return "<variable>";
            case PARAMETER:     return "<parameter>";

            // Delimiters
            // Brackets
            case LPAREN:        return "(";
            case RPAREN:        return ")";
            case LCURLY:        return "{";
            case RCURLY:        return "}";
            
            case COMMA:         return ",";
            case COLON:         return ":";
            case DOT:           return ".";
            //case CIRCUMFLEX:    return "^";
            case DOUBLE_CIRCUMFLEX: return "^^";
            case QM:            return "?";
            case IMPLIES:       return ":-";
            case PIPE:          return "|";
            case PIPE_PIPE:     return "||";

            case EQ:            return "=";
            case NE:            return "/=";
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
            
            case TM_FRAGMENT:   return "<tm-fragment>";
            
            case OPT_KEY:       return "<opt-key>";
            case OPT_VALUE:     return "<opt-value>";
        }
        throw new RuntimeException("Unknown token type '" + type + "'");
    }
}
