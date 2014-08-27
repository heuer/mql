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

import com.semagia.mql.MQLException;
import com.semagia.mql.MQLParseException;

/**
 * tolog tokenizer.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
@SuppressWarnings("unused")
%%

%class RealTologLexer

%unicode

%line 
%column
%caseless
%pack

%int

%function token

%yylexthrow com.semagia.mql.MQLException

%{

    public static final int EOF = YYEOF;
    public static final int TM_FRAGMENT_EOF = -1000;

    private int _commentCount;
    private StringBuffer _content = new StringBuffer();
    private boolean _returnFragment;
    private int _leftOffset;
    private int _rightOffset;

    public int getLine() {
        return yyline+1;
    }

    public int getColumn() {
        return yycolumn;
    }

    public String value() {
        if (_returnFragment) {
            _returnFragment = false;
            return _content.toString();
        }
        return new String(zzBuffer, zzStartRead+_leftOffset, yylength()-_leftOffset-_rightOffset);
    }

    private int _token(final int type) throws MQLException {
        return _token(type, 0, 0);
    }

    private int _token(final int type, final int leftOffset, final int rightOffset) throws MQLException {
        _leftOffset = leftOffset;
        _rightOffset = rightOffset;
        return type;
    }
%}

Whitespace      = \s+

IdentifierStart = [a-zA-Z_] | [\u00C0-\u00D6] | [\u00D8-\u00F6] 
                            | [\u00F8-\u02FF] | [\u0370-\u037D] 
                            | [\u037F-\u1FFF] | [\u200C-\u200D] 
                            | [\u2070-\u218F] | [\u2C00-\u2FEF] 
                            | [\u3001-\uD7FF] | [\uF900-\uFDCF] 
                            | [\uFDF0-\uFFFD] | [\u{10000}-\u{EFFFF}]
IdentifierChar  = {IdentifierStart} | [\-\.0-9] | \u00B7 | [\u0300-\u036F] | [\u203F-\u2040]
Identifier      = {IdentifierStart}{IdentifierChar}*
Variable        = "$"{Identifier}
Parameter       = "%"{Identifier}"%"
QName           = {Identifier}":"([0-9]|{IdentifierStart}){IdentifierChar}*

URI             = \"[^\"]+\"
SID             = i{URI}
IID             = s{URI}
SLO             = a{URI}
OID             = @{Identifier}

String          = \"([^\"]|\"\")*\"
IRI             = "<"[^<>\"\{\}\`\\ ]+">"
Integer         = ("-" | "+")? [0-9]+
Decimal         = ("-" | "+")? ( [0-9]+ \. [0-9]+ | \. ([0-9])+ )
Date            = ("-" | "+")? [0-9]{4} [0-9]* "-" (0 [1-9] | 1 [0-2]) "-" (0 [1-9] | 1 [0-9] | 2 [0-9] | 3 [0-1])
Time            = [0-9]{2} : [0-9]{2} : [0-9]{2} (\.[0-9]+)? ({TimeZone})?
TimeZone        = Z | ( ( "+" | "-" ) [0-9]{2} : [0-9]{2} )
DateTime        = {Date}"T"{Time} 

// CTM-specific grammar
CTMComment      = ("#(" ([^)]* [^#])*  ")#") | "#"[^(][^\r\n]*
CTMString       = (\"([^\\\"]|(\\[\\\"rntuU]))*\")|\"{3} ~\"{3} 

%xstates TM_CONTENT, IGNORE, COMMENT

%%

{Whitespace}            { /* noop */ }
"/*"                    { _commentCount=1; yybegin(COMMENT); }

<YYINITIAL> {
    // Directives
//    "%prefix"           { return _token(TokenTypes.DIR_PREFIX); }

    // Keywords
    "select"            { return _token(TokenTypes.KW_SELECT); }
    "from"              { return _token(TokenTypes.KW_FROM); }
    "count"             { return _token(TokenTypes.KW_COUNT); }
    "not"               { return _token(TokenTypes.KW_NOT); }
    "limit"             { return _token(TokenTypes.KW_LIMIT); }
    "offset"            { return _token(TokenTypes.KW_OFFSET); }
    "order"             { return _token(TokenTypes.KW_ORDER); }
    "by"                { return _token(TokenTypes.KW_BY); }
    "asc"               { return _token(TokenTypes.KW_ASC); }
    "desc"              { return _token(TokenTypes.KW_DESC); }
    "import"            { return _token(TokenTypes.KW_IMPORT); }
    "as"                { return _token(TokenTypes.KW_AS); }
    "using"             { return _token(TokenTypes.KW_USING); }
    "for"               { return _token(TokenTypes.KW_FOR); }
    // tolog 1.2
    "insert"            { yybegin(TM_CONTENT); _content.setLength(0); return _token(TokenTypes.KW_INSERT); }
    "delete"            { return _token(TokenTypes.KW_DELETE); }
    "merge"             { return _token(TokenTypes.KW_MERGE); }
    "update"            { return _token(TokenTypes.KW_UPDATE); }

    {SID}               { return _token(TokenTypes.SID, 2, 1); }
    {SLO}               { return _token(TokenTypes.SLO, 2, 1); }
    {IID}               { return _token(TokenTypes.IID, 2, 1); }
    {OID}               { return _token(TokenTypes.OID, 1, 0); }
    {Variable}          { return _token(TokenTypes.VARIABLE, 1, 0); }
    {Parameter}         { return _token(TokenTypes.PARAM, 1, 1); }

    {QName}             { return _token(TokenTypes.QNAME); }
    {Identifier}        { return _token(TokenTypes.IDENT); }

    // Brackets
    "("                 { return _token(TokenTypes.LPAREN); }
    ")"                 { return _token(TokenTypes.RPAREN); }
    "{"                 { return _token(TokenTypes.LCURLY); }
    "}"                 { return _token(TokenTypes.RCURLY); }

    "="                 { return _token(TokenTypes.EQ); }
    ":"                 { return _token(TokenTypes.COLON); }
    ","                 { return _token(TokenTypes.COMMA); }
    ":-"                { return _token(TokenTypes.IMPLIES); }
    "|"                 { return _token(TokenTypes.PIPE); }
    "||"                { return _token(TokenTypes.PIPE_PIPE); }
//    "^"                 { return _token(TokenTypes.CIRCUMFLEX); }
//    "^^"                { return _token(TokenTypes.DOUBLE_CIRCUMFLEX); }
    "."                 { return _token(TokenTypes.DOT); }
    "?"                 { yybegin(IGNORE); return _token(TokenTypes.QM); }
    
    "="                 { return _token(TokenTypes.EQ); }
    "/="                { return _token(TokenTypes.NE); }
    ">"                 { return _token(TokenTypes.GT); }
    ">="                { return _token(TokenTypes.GE); }
    "<"                 { return _token(TokenTypes.LT); }
    "<="                { return _token(TokenTypes.LE); }

    // Datatypes
    {String}            { return _token(TokenTypes.STRING, 1, 1); }
    {IRI}               { return _token(TokenTypes.IRI, 1, 1); }
    {Date}              { return _token(TokenTypes.DATE); }
    {DateTime}          { return _token(TokenTypes.DATE_TIME); }
    {Integer}           { return _token(TokenTypes.INTEGER); }
    {Decimal}           { return _token(TokenTypes.DECIMAL); }

}

<TM_CONTENT> {
    {CTMComment}        { _content.append(value()); }
    \s"from"\s          { yybegin(YYINITIAL); yypushback(6); _returnFragment=true; return _token(TokenTypes.TM_FRAGMENT); }
    {CTMString}         { _content.append(value()); }
    [^]                 { _content.append(yycharat(yylength()-1)); }
    <<EOF>>             { _returnFragment=true; return _token(TM_FRAGMENT_EOF); }
}

<IGNORE> {
    // This state is entered after matching the ? since
    //    select $t from topic($t)? Some content here which has nothing to do with tolog
    // is a valid query
    [^]*                { yybegin(YYINITIAL); }
}

<COMMENT> {
    "/*"                { _commentCount++; }
    "*/"                { _commentCount--; if (_commentCount == 0) { yybegin(YYINITIAL); } }
    [^]+                { /* noop */ }
}

.|\n                    { throw new MQLParseException("Illegal character <" + yytext() + "> at line " + getLine() + " column: " + getColumn(), getLine(), getColumn()); }
