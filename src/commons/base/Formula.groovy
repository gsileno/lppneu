package commons.base

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Operator

// TODO: refactoring to allow to statically specify
// methods on T, as minimalClone, negate, reify, etc.

@Log4j @EqualsAndHashCode
class Formula<T> {

    String identifier
    String comment

    // highest abstraction level
    List<Formula<T>> inputFormulas = [] // sub-formulas in input
    Operator operator                // last operator
    List<T> inputPorts = []          // relevant, dependent factors

    //////////////////
    // Builders
    //////////////////
    Formula<T> build(T item) {
        new Formula<T>(
                operator: Operator.POS,
                inputPorts: [item]
        )
    }

    Formula<T> build(Operator op) {
        new Formula<T>(
                    operator: op,
            )
    }

    Formula<T> buildFromObjects(List<Object> objects, Operator op) {

        List<T> terms = new ArrayList<T>()

        for (object in objects) {
            terms << (T) object
        }

        buildFromTerms(terms, op)
    }

    Formula<T> buildFromTerms(List<T> terms, Operator op) {
        Formula<T> formula = new Formula<T>()

        if (terms.size() > 1) {
            if (op == Operator.POS || op == Operator.NEG || op == Operator.NULL || op == Operator.NOT)
                throw new RuntimeException("Unary operator cannot have more than one term")

            formula.operator = op
            for (T term in terms) {
                Formula f = build(term)
                formula.inputFormulas << f
                formula.inputPorts << term
            }
        } else {
            // perform the operation on the term itself
            if (op == Operator.POS) {
                formula = build(terms[0])
            } else if (op == Operator.NEG) {
                formula = build(terms[0].negate())
            } else if (op == Operator.NULL) {
                formula = build(terms[0].nullify())
            }
            // for usual operators, with only one input, the formula becomes identity
            else if (op == Operator.OR || op == Operator.XOR || op == Operator.AND ||
                     op == Operator.PAR || op == Operator.ALT || op == Operator.SEQ || op == Operator.OPT)
                formula = build(terms[0])

            // for these operators, you have to maintain the information about the operator
            else if (op == Operator.NOT ||
                     op == Operator.OCCURS || op == Operator.TRIPLE || op == Operator.ASSOCIATION ||
                     op == Operator.IS_IMPLIED_BY || op == Operator.IS_CAUSED_BY ||
                     op == Operator.SUCCESS || op == Operator.FAILURE || op == Operator.INHIBITION ||
                     op == Operator.ISTANTIATION || op == Operator.EXPIRATION || op == Operator.CATALYST) {
                formula.operator = op
                Formula f = build(terms[0])
                formula.inputFormulas << f
                formula.inputPorts << terms[0]
            } else throw new RuntimeException("Unknown operator ${op}")
        }

        formula
    }

    // isUnary formula
    Formula<T> build(Formula<T> input, Operator op) {

        // if reduction has to be applied
        if (input.isAtomic()) {
            return buildFromTerms(input.inputPorts, op)
        } else {
            return new Formula<T>(
                    operator: op,
                    inputFormulas: [input],
                    inputPorts: input.inputPorts
            )
        }
    }

    // binary formula
    Formula<T> build(Formula<T> leftInput, Formula<T> rightInput, Operator op) {
        buildFromFormulas([leftInput, rightInput], op)
    }

    // n-ary formula
    Formula<T> buildFromFormulas(List<Formula<T>> inputs, Operator op) {

        // if there is only 1 element then simplify
        if (inputs.size() == 1 && op != Operator.NEG && op != Operator.NULL && op != Operator.OCCURS_IN && op != Operator.IS_CAUSED_BY && op != Operator.IS_IMPLIED_BY) {
            return inputs[0]
        }

        // aggregate all terms of the inputs
        List<T> terms = []

//        List<Formula<T>> simplifiedInputs = []
//        inputs.each() {
//            if (it.inputFormulas.size() == 0 && it.inputPorts.size() == 1 && op != Operator.NEG && op != Operator.NULL) {
//                simplifiedInputs << it
//            }
//        }

        inputs.each() {
            terms = terms - it.inputPorts + it.inputPorts
        }

        Formula formula = new Formula()
        formula.operator = op
        formula.inputFormulas = inputs
        formula.inputPorts = terms

        formula
    }

    // a simple formula has only atomic inputs
    Boolean isAtomic() {
        (inputFormulas.size() == 0)
    }

    // a simple formula has all input formulas atomic formulas
    Boolean isSimple() {
        for (input in inputFormulas) {
            if (!input.isAtomic()) {
                log.trace("${input} is not atomic")
                return false
            }
        }
        log.trace("${this} is simple")
        return true
    }

    //////////////////
    // Views
    //////////////////

    String toString(Operator superOperator = null) {
        String output = ""

        // operator on constant
        if (inputPorts.size() == 0 && inputFormulas.size() == 0)
            return operator.toString()

        Boolean printOp = (operator != Operator.POS && (superOperator == null || operator != superOperator))

        if (printOp) output += operator.toString()+"("

        if (!isAtomic()) {
            for (formula in inputFormulas)
                output += formula.toString(operator) + ", "
            output = output[0..-3]
        } else {
            for (T term in inputPorts) {
                output += term.toString() + ", "
            }

            if (output == "" ) {
                print ("ERROR")
            }

            output = output[0..-3]
        }

        if (printOp) output += ")"

        output
    }

    ////////////////////////
    // Recursive functions
    ///////////////////////


    Formula<T> minimalClone() {
        List<Formula<T>> clonedInputFormulas = []
        for (inputFormula in inputFormulas) {
            clonedInputFormulas << inputFormula.minimalClone()
        }

        List<Formula<T>> clonedInputPorts = []
        for (inputPort in inputPorts) {
            clonedInputPorts << inputPort.minimalClone()
        }

        new Formula<T>(
                inputFormulas: clonedInputFormulas,
                inputPorts: clonedInputPorts,
                operator: operator
        )
    }

    Formula<T> reify() {
        List<Formula<T>> reifiedInputFormulas = []
        for (inputFormula in inputFormulas) {
            reifiedInputFormulas << inputFormula.reify()
        }

        List<Formula<T>> reifiedInputPorts = []
        for (inputPort in inputPorts) {
            reifiedInputPorts << inputPort.reify()
        }

        new Formula<T>(
                inputFormulas: reifiedInputFormulas,
                inputPorts: reifiedInputPorts,
                operator: operator
        )
    }

    Boolean subsumes(Formula<T> specific, Map<String, Map<String, String>> mapIdentifiers = [:]) {

        log.trace("checking $this against $specific - "+mapIdentifiers)

        // TODO: refactor
        // jump the context elements, in the general
        if (operator == Operator.AND && inputFormulas[1].inputPorts[0].factLiteral.functor == null) {
            log.trace("jumping to the explicit internal part of general: "+inputFormulas[0])
            return inputFormulas[0].subsumes(specific)
        }
        // jump the context elements, and in the specific
        if (specific.operator == Operator.AND && specific.inputFormulas.size() == 2 && specific.inputFormulas[1].inputPorts[0].factLiteral.functor == null) {
            log.trace("jumping to the explicit internal part of specific: "+specific.inputFormulas[0])
            return subsumes(specific.inputFormulas[0])
        }


        if (operator != specific.operator) {
            log.trace("${this} and ${specific} have different operators. there cannot be subsumption.")
            return false
        } else if (inputPorts.size() != specific.inputPorts.size()) {
            log.trace("${this} and ${specific} have different inputs. there cannot be subsumption.")
            return false
        } else if (inputFormulas.size() != specific.inputFormulas.size()) {
            log.trace("${this} and ${specific} have different inputs. there cannot be subsumption.")
            return false
        }

        if (isAtomic()) {
            for (int i = 0; i < inputPorts.size(); i++) {
                if (!this.inputPorts[i].subsumes(specific.inputPorts[i], mapIdentifiers)) {
                    return false
                }
            }
        } else {
            for (int i = 0; i < inputFormulas.size(); i++) {
                if (!this.inputFormulas[i].subsumes(specific.inputFormulas[i], mapIdentifiers))
                    return false
            }
        }
        return true
    }

}
