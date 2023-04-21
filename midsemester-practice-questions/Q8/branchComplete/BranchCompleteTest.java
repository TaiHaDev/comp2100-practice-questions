import org.junit.Test;

import static org.junit.Assert.*;

public class BranchCompleteTest {

    @Test
    public void test() {

        // TODO

        assertEquals(-3, BranchComplete.findSomething(-1, 1, 2, 3));
        assertEquals(-4, BranchComplete.findSomething(-1, 1, -2, 3));
        assertEquals(1, BranchComplete.findSomething(1, -1, -2, 3));
        assertEquals(6, BranchComplete.findSomething(1, -1, 3, 3));

        // START YOUR CODE
        // HINT: assertEquals(result, BranchComplete.findSomething(a, b, c, d));

        // END YOUR CODE
    }
}
