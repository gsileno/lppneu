grammar LPPN;

@header {}

/*----------------
* PARSER RULES
*----------------*/

/** The overall program consists of directives, facts and rules. */
program : (situationfact | eventfact | logicrule | causalrule)* EOF ;

/** A fact is the head followed by a dot. */
situationfact : head DOT ;

/** An event is a DO prefix with a head followed by a dot. */
eventfact : CAUSES operation DOT ;

/** A logic rule can be a normal rule or a constraint. */
logicrule : normrule | constraint ;

/** A normal rule has a head and body. */
normrule : head ( IS_IMPLIED_BY | IS_EQUIVALENT_TO ) body DOT ;

/** A constraint has NO head, only a body. */
constraint : IS_IMPLIED_BY body DOT ;

/** A causal rule can be a Condition-Action, an Event-Condition-Action rule or a dependency. */
causalrule : body_expression CAUSES operation DOT ;

/** The head consists of a literal. */
head : head_expression ;

/** The body consists of a list of literals or expressions separated. */
body : body_expression ;

/** A list of literals is separated by comma */
list_literals : literal ( COMMA list_literals )? ;

/** A literal expression is a literal or compositions of it */

situation : literal ;

head_expression : situation
| NEG head_expression
| operation WHEN head_expression
| LPAR head_expression RPAR
| head_expression SEQ head_expression
| head_expression (PAR | ALT) head_expression
| head_expression AND head_expression
| head_expression (OR | XOR) head_expression
;

event : literal ;

operation : event
| LPAR operation RPAR
| operation SEQ operation
| operation (PAR | ALT) operation
;

body_expression : situation | body_constraint
| operation WHEN body_expression
| LPAR body_expression RPAR
| body_expression SEQ body_expression
| body_expression (PAR | ALT) body_expression
| NOT body_expression
| NEG body_expression
| body_expression AND body_expression
| body_expression (OR | XOR) body_expression
;

/** A constraint is a boolean function operating variables and constants */
body_constraint : ( identifier | variable | INTEGER | num_expression ) ( EQ | NEQ | LT | LE | GT | GE ) ( IDENTIFIER | VARIABLE | INTEGER | num_expression) ;

/** A numeric expression is an integer expression made by numeric variables and constants */
num_expression : (variable | INTEGER) (PLUS | MINUS) (variable | INTEGER) ;

/** An extended literal adds the default negation to the classic literal */
ext_literal : ( NOT )? literal ;

/** A literal can be positive, negative or null. */
literal : ( NULL | NEG )? pos_literal ;

/** A positive literal consists of symbols (no predicates) or symbols and terms (predicate literal). */
pos_literal : predicate ( LPAR list_parameters RPAR )? ;

/** Parameters are separated by comma. */
list_parameters :  parameter (COMMA list_parameters)? ;

/** Parameters are variable structures, constants (e.g. numbers), numeric expressions of those or positive literals  */
parameter : variable_structure | constant | pos_literal | num_expression ;

predicate : IDENTIFIER ;
identifier : IDENTIFIER ;
constant : INTEGER ;

variable_structure : variable
| variable COLON variable
| variable COLON constant ;

variable : VARIABLE ;

/*----------------
* LEXER RULES
*----------------*/

WS : (' ' | '\t' | '\n' | '\r' | '\f')+ -> skip ;

// boolean operators
AND : '&' | 'AND' | 'and' ;
OR : '|' | 'OR' | 'or' ;
XOR : 'XOR' | 'xor' ;

// event operators
SEQ : 'SEQ' | 'seq' ;
PAR : 'PAR' | 'par' ;
ALT : 'ALT' | 'alt' ;
NEG : 'neg' | 'NEG' | MINUS ; // strong negation
NOT : 'not' | TILDE ;
NULL : 'null' | 'NULL' ; // default negation

// NOT = NULL OR NEG

WHEN : COLON | 'in' ;
CAUSES : '->' |  '=>';
IS_IMPLIED_BY : ':-' ;
IS_EQUIVALENT_TO : '::-' | ':=' | '::=' ;

PLUS : '+' ;
MINUS : '-' ; // both for strong negation and arithmetic deletion
TILDE : '~' ; // used for the union between default and strong

DOT : '.' ;
COMMA : ',' ;
COLON : ':' ;

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

DOMAIN : '#domain' ;

RANGE : '..' ;
INTEGER : '0' | [1-9] ([0-9])* ;
IDENTIFIER: [a-z] ([0-9a-zA-Z_])* ;
VARIABLE: [_A-Z] ([0-9a-zA-Z_])* ;

// in addition to ASP comments (%) I added the standard C, Java comment style.
SINGLE_LINE_COMMENT : ('%' | '//') ~('\n'|'\r')* -> channel(HIDDEN) ;
MULTILINE_COMMENT : '/*' .*? ( '*/' | EOF ) -> channel(HIDDEN) ;