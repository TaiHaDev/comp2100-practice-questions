import org.junit.Before;
import org.junit.Test;

import exceptions.NullCharacterException;
import exceptions.NullKeyEventException;

public class ExceptionTest {

	private Character character;

	@Before
	public void init() {
		this.character = new Character();
	}

	@Test(expected = NullKeyEventException.class, timeout = 1000)
	public void testNullKeyException() {
		// TODO
		// START YOUR CODE
		character.getState().handle(character, null);
		// END YOUR CODE
	}

	@Test(expected = NullCharacterException.class, timeout = 1000)
	public void testNullCharacterException() {
		// TODO
		// START YOUR CODE
		character.getState().handle(null, Key.DOWN);

		// END YOUR CODE
	}
}
