import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class MyIntegerTest {

	@Test
	void test() {
		// TODO: Write a minimum set of test cases that are code complete 
		// for MyInteger.parseInt method.
		// Use assertEquals and assertThrows to test whether the method works as expected. 
		// 
		// All test cases should be included in this method. Do not create another method. 
		// Auto grader will not check the other methods created individually.
		String s = "123";
		int parsedInt = MyInteger.parseInt(s);
		assertEquals(parsedInt, 123);
		assertThrows(NumberFormatException.class, () -> MyInteger.parseInt("a"));
		assertThrows(NumberFormatException.class, () -> MyInteger.parseInt("/"));

	}

}
