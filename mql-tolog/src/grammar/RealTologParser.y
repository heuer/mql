%{
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

import java.util.ArrayList;
import java.util.List;

import com.semagia.mio.ctm.CTMUtils;

/**
 * This tolog parser utilizes the AbstractTologParser and is responsible for 
 * the grammar.
 * 
 * @author Lars Heuer (heuer[at]semagia.com) <a href="http://www.semagia.com/">Semagia</a>
 */
class RealTologParser extends AbstractTologParser { 


%}

%token
    KW_USING, KW_FOR, 
    KW_IMPORT, KW_AS
    KW_SELECT, KW_FROM,
    KW_COUNT, KW_ORDER, KW_BY, KW_ASC, KW_DESC,
    KW_LIMIT, KW_OFFSET, 
    KW_NOT,
    KW_DELETE, KW_INSERT, KW_MERGE, KW_UPDATE,

    IDENT, QNAME, VARIABLE, PARAM, SID, SLO, IID, OID,

    EQ, NE, LT, GT, LE, GE,

    LPAREN, RPAREN, LCURLY, RCURLY,

    COMMA, COLON, QM, PIPE, PIPE_PIPE, DOUBLE_CIRCUMFLEX,
    IMPLIES, DOT,

    STRING, DATE, DATE_TIME, INTEGER, DECIMAL, IRI,

    TM_FRAGMENT

%token<String>
    IDENT
    IRI
    SLO
    SID
    IID
    INTEGER
    STRING
    VARIABLE
    TM_FRAGMENT

%type <TologReference>
    ref, qname, qiri, expr,
    uri_ref, variable, value, parameter

%type <List<TologReference>>
    arguments

%%

instance    : head
            | head statement
            | statement
            ;

head        : rule
            | directive
            | head directive
            | head rule
            ;

directive   : using_directive
            | import_directive
            ;

using_directive 
            : KW_USING IDENT KW_FOR uri_ref { super.registerNamespace($2, $4.getIRI(), $4.getType()); }
            ;

import_directive 
            : KW_IMPORT qiri KW_AS IDENT
            | KW_IMPORT STRING KW_AS IDENT
            ;

statement   : select_query
            | clause_query
            | insert
            | merge
            | delete
            | update
            ;

clause_query 
            : clauselist opt_tail QM
            ;

select_query
            : KW_SELECT                     { _handler.startSelect(); } 
              select_elements 
              where_clause opt_tail QM      { _handler.endSelect(); }
            ;

select_elements 
            : select_element
            | select_elements COMMA select_element
            ;

select_element  
            : count_clause
            | variable
            ;

count_clause
            : KW_COUNT LPAREN VARIABLE RPAREN { _handler.count($3); }
            ;

order_clause
            : KW_ORDER KW_BY                { _handler.startOrderBy(); } 
              order_elements                { _handler.endOrderBy(); }
            ;

order_element   
            : VARIABLE                      { _handler.ascending($1); }
            | VARIABLE KW_ASC               { _handler.ascending($1); }
            | VARIABLE KW_DESC              { _handler.descending($1); }
            ;

limit_offset    
            : KW_OFFSET                     { _handler.startPagination(); } 
              INTEGER                       { _handler.offset(Integer.parseInt($3)); _handler.endPagination(); }
            | KW_LIMIT                      { _handler.startPagination(); } 
              INTEGER                       { _handler.limit(Integer.parseInt($3)); }
              opt_offset                    { _handler.endPagination(); }
            ;

opt_offset  : KW_OFFSET INTEGER             { _handler.offset(Integer.parseInt($2)); }
            ;

rule        : predclause IMPLIES            { super.handleRuleStart(); }
              clauselist DOT                { _handler.endRule(); }
            ;

clause      : predclause                       { super.handlePredicateClause(); }
            | assoc_head opt_more_pairs RPAREN { _handler.endAssociationPredicate(); }
            ;

assoc_head  : ref LPAREN expr COLON ref     { _handler.startAssociationPredicate(); super.issueNameEvent($1); super.handlePair($5, $3); }
            ;

predclause  : ref LPAREN arguments RPAREN   { super._predClause.ref = $1; super._predClause.arguments = $3.toArray(new TologReference[0]); }
            ;

opt_more_pairs  
            :
            | COMMA pairs
            ;

pairs       : pair
            | pairs COMMA pair
            ;

pair        : expr COLON ref                { super.handlePair($3, $1); }
            ;

arguments   : expr                          { List<TologReference> args = new ArrayList<TologReference>(2); args.add($1); $$ = args; }
            | arguments COMMA expr          { $1.add($3); $$ = $1; }
            ;

expr        : variable                      { $$ = $1; }
            | ref                           { $$ = $1; }
            | value                         { $$ = $1; }
            | parameter                     { $$ = $1; }
            ;

variable    : VARIABLE                      { $$ = TologReference.createVariable($1); }
            ;

opclause    : expr EQ expr                  { super.handleInfixPredicate("eq", $1, $3); }
            | expr NE expr                  { super.handleInfixPredicate("ne", $1, $3); }
            | expr LE expr                  { super.handleInfixPredicate("le", $1, $3); }
            | expr LT expr                  { super.handleInfixPredicate("lt", $1, $3); }
            | expr GE expr                  { super.handleInfixPredicate("ge", $1, $3); }
            | expr GT expr                  { super.handleInfixPredicate("gt", $1, $3); }
            ;

orclause    : LCURLY                        { _handler.startOr(); _handler.startBranch(false); }
              clauselist opt_oredclauses 
              RCURLY                        { _handler.endBranch(); _handler.endOr(); }
            ;

opt_oredclauses
            : 
            | oredclauses
            ;

oredclauses : oredclause
            | oredclauses oredclause
            ;

oredclause  : PIPE                          { _handler.endBranch(); _handler.startBranch(false); } 
              clauselist
            | PIPE_PIPE                     { _handler.endBranch(); _handler.startBranch(true); } 
              clauselist
            ;

notclause   : KW_NOT                        { _handler.startNot(); } 
              LPAREN clauselist RPAREN      { _handler.endNot(); }
            ;

parameter   : PARAM
            ;

value       : STRING
            | INTEGER
            | DECIMAL
            | DATE
            | DATE_TIME
            ;

string      : STRING                        { $$ = TologReference.createString($1); }
            ;

datatype    : STRING                        { }
            | qiri                          { }
            ;


ref         : uri_ref                       { $$ = $1; }
            | IDENT                         { $$ = TologReference.createIdent($1); }
            | OID                           { }
            ;

uri_ref     : SID                           { $$ = TologReference.createSID($1); }
            | SLO                           { $$ = TologReference.createSLO($1); }
            | IID                           { $$ = TologReference.createIID($1); }
            | qiri                          { $$ = $1; }
            ;

qiri        : IRI                           { $$ = TologReference.createIRI($1); }
            | qname                         { $$ = $1; }
            ;

qname       : QNAME                         { }
            ;

variable    : VARIABLE                      { $$ = TologReference.createVariable($1); }
            ;

clauselist  : clause
            | clauselist COMMA clause
            ;

clause      : opclause
            | orclause
            | notclause
            ;

delete_element  
            : function_call
            | paramlist
            ;

paramlist   : param
            | paramlist COMMA param

opt_where_clause 
            : 
            | where_clause

opt_tail    : 
            | tail
            ;

tail        : order_clause opt_limit_offset
            ;

order_elements
            : order_element
            | order_elements COMMA order_element
            ;

opt_limit_offset 
            : 
            | limit_offset
            ;

delete      : KW_DELETE                       { _handler.startDelete(); }
              delete_element opt_where_clause { _handler.endDelete(); }
            ;

function_call   
            : IDENT LPAREN                  { _handler.startFunctionCall($1); }
              paramlist RPAREN              { _handler.endFunctionCall(); }
            ;

param       : string                        { $$ = $1; }
            | variable                      { $$ = $1; }
            | ref                           { $$ = $1; }
            ;

insert      : KW_INSERT                     { _handler.startInsert(); } 
              TM_FRAGMENT                   { _handler.startFragment(); 
                                              final String content = $3;
                                              for (String var: CTMUtils.findVariables(content, true)) {
                                                  _handler.variable(var);
                                              }
                                              _handler.fragmentContent(content);
                                              _handler.endFragment(); } 
              opt_where_clause              { _handler.endInsert(); }
            ;

update      : KW_UPDATE                     { _handler.startUpdate(); } 
              function_call where_clause    { _handler.endUpdate(); }
            ;

merge       : KW_MERGE                      { _handler.startMerge(); }
              literal COMMA literal 
              opt_where_clause              { _handler.endMerge(); }
            ;

literal     : variable                      { $$ = $1; }
            | ref                           { $$ = $1; }
            ;

where_clause
            : KW_FROM                       { _handler.startWhere(); } 
              clauselist                    { _handler.endWhere(); }
            ;

%%
}
