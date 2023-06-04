import exceptions.NullCharacterException;
import exceptions.NullKeyEventException;

public interface State {

	default void handle(Character character, Key event) {

		check(character, event);

		this.handleInput(character, event);
	}

	void handleInput(Character character, Key event);



	default String getStateName() {
		return this.getClass().getTypeName();
	}

	default void check(Character character, Key event) {

		// TODO
		// ########## YOUR CODE STARTS HERE ##########
		if (character == null) throw new NullCharacterException();
		if (event == null) throw new NullKeyEventException();
		// ########## YOUR CODE ENDS HERE ##########
	}
}
