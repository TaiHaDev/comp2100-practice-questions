
public class Division extends Operation {

	public Division(double a, double b) {
		super(a, b);
	}

	@Override
	public double evaluate() {

		//START YOUR CODE
		return this.a / this.b;

		//END YOUR CODE
	}
}
