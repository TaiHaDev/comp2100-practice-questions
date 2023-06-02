
public class VendingMachine {

	private State state = new Idle(this);

	public State getState() {

		// START YOUR CODE
		return state;


		// END YOUR CODE
	}

	public void setState(State state) {
		
		// START YOUR CODE
		this.state = state;

		
		// END YOUR CODE
	}
}
