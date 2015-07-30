package commons.base

import groovy.transform.EqualsAndHashCode
import groovy.util.logging.Log4j
import org.leibnizcenter.lppneu.components.language.Operator

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
            formula.operator = op
            List<Formula> inputs = []
            for (T term in terms) {
                Formula f = build(term)
                formula.inputFormulas << f
                formula.inputPorts << term
            }
        } else { // with only one input, the formula becomes identity, unless it is negation
            if (op == Operator.NEG)
                formula = build(terms[0].negate())
            else if (op == Operator.NULL)
                formula = build(terms[0].nullify())
            else
                formula = build(terms[0])
        }

        formula
    }



    // isUnary formula
    Formula<T> build(Formula<T> input, Operator op) {

        // if reduction has to be applied
        if (input.inputPorts.size() > 0) {
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
        if (inputs.size() == 1 && op != Operator.NEG && op != Operator.NULL) {
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

    Boolean isCompound() {
        (inputFormulas.size() > 0)
    }

    //////////////////
    // Views
    //////////////////

    String toString(Operator superOperator = null) {
        String output = ""

        Boolean printOp = (operator != Operator.POS && (superOperator == null || operator != superOperator))

        if (printOp) output += operator.toString()+"("

        if (isCompound()) {
            for (formula in inputFormulas)
                output += formula.toString(operator) + ", "
            output = output[0..-3]
        } else {
            for (T term in inputPorts) {
                output += term.toString() + ", "
            }
            output = output[0..-3]
        }

        if (printOp) output += ")"

        output
    }

}
