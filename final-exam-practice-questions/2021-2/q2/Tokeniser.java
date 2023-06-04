public class Tokeniser {
	public static void main(String[] args) {
		System.out.println(new Tokeniser("LOAD abc**FROM"));
	}
	private String buffer; // save text
	private Token currentToken; // save token extracted from next()

	public Tokeniser(String text) {
		buffer = text; // save input text (string)
		next(); // extracts the first token.
	}

	/**
	 * This function will find and extract a next token from {@code buffer} and save
	 * the token to {@code currentToken}.
	 */
	public void next() {

		buffer = buffer.trim(); // remove whitespace
		if (buffer.isEmpty()) {
			currentToken = null; // if there's no string left, set currentToken null and return
			return;
		}

		// ########## YOUR CODE STARTS HERE ##########
		// check if there's any token left
		int terminatorIndex = buffer.indexOf(";");
		int spaceIndex = buffer.indexOf(" ");
		if (spaceIndex == -1 && terminatorIndex == -1) {
			currentToken = null;
			return;
		}

		// extract the next token's string representation
		String token;
		if (spaceIndex != -1) {
			token = buffer.substring(0, spaceIndex);
		} else {
			token = buffer.substring(0, terminatorIndex);
		}

		// find the suitable token to the extracted string
		if (terminatorIndex == 0) {
			currentToken = new Token(Token.Type.TERMINATOR, ";");
		} else if (token.equalsIgnoreCase("LOAD") ) {
			currentToken = new Token(Token.Type.LOAD, token);
		} else if (token.equalsIgnoreCase("SAVE")) {
			currentToken = new Token(Token.Type.SAVE, token);
		} else if (token.equalsIgnoreCase("FROM")) {
			currentToken = new Token(Token.Type.FROM, token);
		} else if (token.equalsIgnoreCase("TO")) {
			currentToken = new Token(Token.Type.TO, token);
		} else {
			currentToken = new Token(Token.Type.PARAMETER, token);
		}

		// ########## YOUR CODE ENDS HERE ##########

		// Remove the extracted token from buffer
		int tokenLen = currentToken.getValue().length();
		buffer = buffer.substring(tokenLen);
	}

	/**
	 * returned the current token extracted by {@code next()} **** please do not
	 * modify this part ****
	 * 
	 * @return type: Token
	 */
	public Token current() {
		return currentToken;
	}

	/**
	 * check whether there still exists another tokens in the buffer or not ****
	 * please do not modify this part ****
	 * 
	 * @return type: boolean
	 */
	public boolean hasNext() {
		return currentToken != null;
	}

}