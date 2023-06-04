public class Tokeniser {
	public static void main(String[] args) {
		System.out.println("a".substring(0,0));
		Tokeniser tokeniser = new Tokeniser("LOAD a*;");
		while(tokeniser.hasNext()) {
			System.out.println(tokeniser.current().getValue());
			tokeniser.next();
		}
	}

	private String buffer;
	private Token currentToken;

	public Tokeniser(String text) {
		buffer = text.replaceAll("[^a-zA-Z0-9;*]", " ");
		next();
	}

	public void next() {
		buffer = buffer.trim();
		// ########## YOUR CODE STARTS HERE ##########
		// check in if there's any token left
		int spaceIndex = buffer.indexOf(" ");
		int terminatorIndex = buffer.indexOf(";");
		if (spaceIndex == -1 && terminatorIndex == -1) {
			currentToken = null;
			return;
		}
		// extract the next token
		String token;
		if (spaceIndex != -1) {
			token = buffer.substring(0, spaceIndex);
		} else {
			token = buffer.substring(0, terminatorIndex);
		}

		// find the suitable token to the extracted string
		if (terminatorIndex == 0) {
			currentToken = new Token(Token.Type.TERMINATOR, ";");
		} else if (token.equalsIgnoreCase(Token.Type.LOAD.toString())) {
			currentToken = new Token(Token.Type.LOAD, token);
		} else if (token.equalsIgnoreCase(Token.Type.SAVE.toString())) {
			currentToken = new Token(Token.Type.SAVE, token);
		} else if (token.equalsIgnoreCase(Token.Type.SUM.toString())) {
			currentToken = new Token(Token.Type.SUM, token);
		} else if (token.equalsIgnoreCase(Token.Type.TO.toString())) {
			currentToken = new Token(Token.Type.TO, token);
		} else {
			currentToken = new Token(Token.Type.PARAMETER, token);
		}

		buffer = buffer.substring(currentToken.getValue().length());

		// ########## YOUR CODE ENDS HERE ##########
	}

	public Token current() {
		return currentToken;
	}

	public boolean hasNext() {
		return currentToken != null;
	}

}