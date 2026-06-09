grammar GYH;

@header {
package gyh.parser;
}

// Parser Rules

programa : Delim PCDec declarations Delim PCProg statementList EOF ;

declarations : variableDeclaration* ;

variableDeclaration : Var Delim type ;

type : PCInt | PCReal ;

statementList : statement* ;

statement
    : assignment
    | readCommand
    | printCommand
    | ifCommand
    | whileCommand
    | subAlgorithm
    ;

assignment : Var Atrib expression ;

readCommand : PCLer Var ;

printCommand : PCImprimir ( Cadeia | expression ) ;

ifCommand : PCSe expression PCEntao statementList ( PCSenao statementList )? PCFim ;

whileCommand : PCEnqto expression subAlgorithm ;

subAlgorithm : PCIni statementList PCFim ;

expression : logicalExpression ;

logicalExpression
    : relationalExpression ( ( OpBoolE | OpBoolOu ) relationalExpression )*
    ;

relationalExpression
    : arithmeticExpression ( ( OpRelMaior | OpRelMaiorIgual | OpRelMenor | OpRelMenorIgual | OpRelIgual | OpRelDif ) arithmeticExpression )*
    ;

arithmeticExpression
    : term ( ( OpAritSoma | OpAritSub ) term )*
    ;

term
    : factor ( ( OpAritMult | OpAritDiv ) factor )*
    ;

factor
    : Var
    | NumInt
    | NumReal
    | AbrePar expression FechaPar
    ;

// Lexer Rules

PCDec : 'DEC' ;
PCProg : 'PROG' ;
PCInt : 'INT' ;
PCReal : 'REAL' ;
PCLer : 'LER' ;
PCImprimir : 'IMPRIMIR' ;
PCSe : 'SE' ;
PCEntao : 'ENTAO' ;
PCSenao : 'SENAO' ;
PCEnqto : 'ENQTO' ;
PCIni : 'INI' ;
PCFim : 'FIM' ;
OpBoolE : 'E' ;
OpBoolOu : 'OU' ;

OpAritMult : '*' ;
OpAritDiv : '/' ;
OpAritSoma : '+' ;
OpAritSub : '-' ;

OpRelMenorIgual : '<=' ;
OpRelMenor : '<' ;
OpRelMaiorIgual : '>=' ;
OpRelMaior : '>' ;
OpRelIgual : '==' ;
OpRelDif : '!=' ;

Delim : ':' ;
Atrib : ':=' ;
AbrePar : '(' ;
FechaPar : ')' ;

Var : [a-z] [a-zA-Z0-9_]* ;

NumReal : [0-9]* '.' [0-9]+ ;
NumInt : [0-9]+ ;

Cadeia : '"' (~["\r\n])* '"' ;

WS : [ \t\r\n]+ -> skip ;
COMMENT : '#' ~[\r\n]* -> skip ;

INVALID_KEYWORD : [A-Z]+ ;
UNKNOWN : . ;
