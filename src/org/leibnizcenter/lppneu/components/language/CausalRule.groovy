package org.leibnizcenter.lppneu.components.language

/**
 * Represents a relation of the type
 * antecedent -> consequent
 *
 * to be interpreted as:   
 * cause -> effect
 **/

class CausalRule {
    Event event
    Expression condition
    Operation action
}