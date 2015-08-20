

import groovy.io.FileType
import org.leibnizcenter.lppneu.components.language.Program
import org.leibnizcenter.lppneu.parser.LPPNLoader

class LPPNParserTest extends GroovyTestCase {

    void batchTest(String path) {
        // take all the target files from the given directory
        def dir = new File(path); def files = []
        dir.eachFileMatch(FileType.FILES, ~/.*\.lppn$/) { files << it.name  }
        files.sort()

        for (filename in files) {
            print "Testing file \"$path/$filename\"..."
            Program program = LPPNLoader.parseFile(path+"/"+filename)

            if (program.parsingErrors.size() == 0) {
                println " ok."
            } else {
                println " nok!"
                for (error in program.parsingErrors) println (error)
            }

            assert (program.parsingErrors.size() == 0)
        }
    }

    void testBasicParsing() {
        batchTest("examples/basic")
    }

    void testComplexParsing() {
        batchTest("examples/complex")
    }

}
