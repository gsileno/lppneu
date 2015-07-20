grammar LPPN;

@header {}

/*----------------
* PARSER RULES
*----------------*/

/** The overall program consists of directives, facts and rules. */
program : (situationfact | eventfact | logicrule | causalrule )* EOF ;

/** A fact is the head followed by a dot. */
situationfact : head DOT ;

/** An event is a DO prefix with a head followed by a dot. */
eventfact : CAUSES operation ;

/** A logic rule can be a normal rule or a constraint. */
logicrule : normrule | constraint ;

/** A normal rule has a head and body. */
normrule : head IF body DOT ;

/** A constraint has NO head, only a body. */
constraint : IF body DOT ;

/** A causal rule can be a Condition-Action, an Event-Condition-Action rule or a dependency. */
causalrule : carule | ecarule | dependency ;

/** A condition action rule is a situation that generates an operation */
carule : situation CAUSES operation ;

/** An event condition action rule is an event, that in a certain situation generates an operation */
ecarule : event WHEN situation CAUSES operation ;

/** A dependency is an ECA without condition */
dependency : event CAUSES operation ;

/** The head consists of a literal. */
head : literal ;

/** The body consists of a list of literals or expressions separated. */
body : list_ext_literals_expressions ;

/** A list of literals is separated by comma */
list_literals : literal ( COMMA list_literals )? ;

/** A list of literals and expressions is separated by comma */
list_ext_literals_expressions : ( ext_literal | expression ) ( COMMA list_ext_literals_expressions )? ;

/** An expression is a boolean function operating variables and constants */
expression : ( identifier | variable | INTEGER | num_expression ) ( EQ | NEQ | LT | LE | GT | GE ) ( IDENTIFIER | VARIABLE | INTEGER | num_expression) ;

/** A numeric expression is an integer expression made by numeric variables and constants */
num_expression : (variable | INTEGER) (PLUS | MINUS) (variable | INTEGER) ;

/** An extended literal adds the default negation to the classic literal */
ext_literal : ( NOT )? literal ;

/** A literal can be positive or negative. */
literal : ( MINUS )? pos_literal ;

/** A positive literal consists of symbols (no predicates) or symbols and terms (predicate literal). */
pos_literal : predicate ( LPAR list_parameters RPAR )? ;

/** Parameters are identifiers or variables, separated by comma. */
list_parameters :  (identifier | variable | constant | pos_literal | num_expression ) (COMMA list_parameters)? ;

predicate : IDENTIFIER ;
identifier : IDENTIFIER ;
constant : INTEGER ;
variable : VARIABLE ;

/*----------------
* LEXER RULES
*----------------*/

WS : (' ' | '\t' | '\n' | '\r' | '\f')+ -> skip ;

IF : ':-' ;
WHEN : ':' ;

DOT : '.' ;
COMMA : ',' ;

LPAR : '(' ;
RPAR : ')' ;
LACC : '{' ;
RACC : '}' ;

EQ : '=' ;
NEQ : '!=' ;
GT : '>' ;
LT : '<' ;
GE : '>=' ;
LE : '<=' ;

CAUSES : '>>' ;

NOT : 'not' ; // default negation
PLUS : '+' ;
MINUS : '-' ; // both for strong negation and arithmetic deletion

DOMAIN : '#domain' ;

RANGE : '..' ;
INTEGER : '0' | [1-9] ([0-9])* ;
IDENTIFIER: [a-z] ([0-9a-zA-Z_])* ;
VARIABLE: [_A-Z] ([0-9a-zA-Z_])* ;

// in addition to ASP comments (%) I added the standard C, Java comment style.
SINGLE_LINE_COMMENT : ('%' | '//') ~('\n'|'\r')* -> channel(HIDDEN) ;
MULTILINE_COMMENT : '/*' .*? ( '*/' | EOF ) -> channel(HIDDEN) ;