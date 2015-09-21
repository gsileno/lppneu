*TODO*
multiple mapping of identifiers may be possible in subsumption checking
decide what to do about Position field in Event, should be probably refactored to always use PositionRef
adjust the printing of Operations (now +p, +NEG(p)... there should be also -) 
add OPT (optional) process operator
test IN event/condition operator
consider the simplification (e IN c1) IN c2 = (e IN c1 AND c2) 
expression comparison (a AND b) should be true for (b AND a), etc. apart a SEQ b
rule comparison (a ::- b) should be true for (b ::- a).
optimization: simplification can occurs at construction, i.e. avoiding to create already existing net.
basic petri nets are resources places, while lp petri nets are propositional places: they cannot have two propositions with the same content. This could be allowed in principle but requires more work. 
anoynimous identifiers are not reset during analysis: this requires refactoring!
correct the operator priority for (a in b and c).. should be a in (b and c) rather than (a in b) and c

*ERRORS*
URGENT: unification does not work properly in variables!!! object(Object) vs object(piece(Object))...
error in net comparison with link transitions!!!
URGENT: repair the overlap between specific and general model variables in MapIdentifiers!!! in PosLiteral class

*INFO*



