import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Fill in the items below with your UID and name:
 * @author:
 * @UID:
 */
public class BSTBranchCompleteTest {
	@Test(timeout=1000)
	public void test() {
		
		BranchComplete bc = new BranchComplete();
		assertEquals(417, bc.DNATreeCalc(209,207, 1));
		assertEquals(209, bc.DNATreeCalc(209, 211, 1));

	}
}
