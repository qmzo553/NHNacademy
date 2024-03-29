import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nhnacademy.FunctionNode;
import com.nhnacademy.Pipe;

class FunctionNodeTest {
    private FunctionNode functionNode;

    @BeforeEach
    void setUp() {
        functionNode = new FunctionNode("TestFunctionNode", "1", "+");
        functionNode.setInputPipe(new Pipe(5, "inputPipe"));
        functionNode.setOutputPipe(new Pipe(5, "outputPipe"));
    }

    @Test
    void testSetInputPipe() {
        Pipe inputPipe = new Pipe(5, "inputPipe");
        functionNode.setInputPipe(inputPipe);
        assertEquals(inputPipe, functionNode.getInputPipeList().get(0));
    }

    @Test
    void testSetOutputPipe() {
        Pipe outputPipe = new Pipe(5, "outputPipe");
        functionNode.setOutputPipe(outputPipe);
        assertEquals(outputPipe, functionNode.getOutputPipe());
    }
}
