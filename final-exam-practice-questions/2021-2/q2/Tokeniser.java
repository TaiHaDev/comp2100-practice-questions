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
		String word = buffer.split(" ")[0];
		if (word.equalsIgnoreCase("LOAD") ) {
			currentToken = new Token(Token.Type.LOAD, word);
		} else if (word.equalsIgnoreCase("SAVE")) {
			currentToken = new Token(Token.Type.SAVE, word);
		} else if (word.equalsIgnoreCase("FROM")) {
			currentToken = new Token(Token.Type.FROM, word);
		} else if (word.equalsIgnoreCase("TO")) {
			currentToken = new Token(Token.Type.TO, word);
		} else if (currentToken != null && (currentToken.getType() == Token.Type.LOAD || currentToken.getType() == Token.Type.SAVE)) {
			currentToken = new Token(Token.Type.PARAMETER, word);
		} else if (currentToken != null && (currentToken.getType() == Token.Type.FROM || currentToken.getType() == Token.Type.TO)) {
			int terminatorIdx = word.indexOf(";");
			if (terminatorIdx != -1) {
				word = word.substring(0, terminatorIdx);
			}
			currentToken = new Token(Token.Type.PARAMETER, word);
		} else if (word.startsWith(";")) {
			currentToken = new Token(Token.Type.TERMINATOR, ";");
		} else {
			return;
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