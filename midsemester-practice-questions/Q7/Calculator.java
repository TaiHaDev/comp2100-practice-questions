public class Calculator {

	public double add(double a, double b) {
		Addition addition = new Addition(a, b);
		return addition.evaluate();
	}

	public double subtract(double a, double b) {

		//START YOUR CODE
		Subtraction subtraction = new Subtraction(a, b);
		return subtraction.evaluate();

		//END YOUR CODE
	}

	public double multiply(double a, double b) {

		//START YOUR CODE
		return new Multiplication(a, b).evaluate();

		//END YOUR CODE
	}

	public double divide(double a, double b) {

		//START YOUR CODE
		return new Division(a, b).evaluate();

		//END YOUR CODE
	}
}
