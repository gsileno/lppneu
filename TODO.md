TODO
decide what to do about Position field in Event, should be probably refactored to always use PositionRef
adjust the printing of Operations (now +p, +NEG(p)... there should be also -) 
add OPT (optional) process operator
test IN event/condition operator
// switch event with operation with the IN operator
consider the simplification (e IN c1) IN c2 = (e IN c1 AND c2) 
expression comparison (a AND b) should be true for (b AND a), etc.

ERRORS
// "=>" in parsing does something against '->' 
correct the operator priority for (a in b and c).. should be a in (b and c) rather than (a in b) and c

INFO


