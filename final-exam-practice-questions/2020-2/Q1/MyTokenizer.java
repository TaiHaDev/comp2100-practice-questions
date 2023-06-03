

public class MyTokenizer implements Tokenizer {

    private String buffer;		//save text
    private Token currentToken;	//save token extracted from next()

    /**
     *  Tokenizer class constructor
     *  The constructor extracts the first token and save it to currentToken
     *  **** please do not modify this part ****
     */
    public MyTokenizer(String text) {
    	buffer = text;		// save input text (string)
    	next();		// extracts the first token.
    }

    /**
     *  This function will find and extract a next token from {@code buffer} and
     *  save the token to {@code currentToken}.
     */
    public void next() {
        buffer = buffer.trim(); // remove whitespace

        if (buffer.isEmpty()) {
            currentToken = null;    // if there's no string left, set currentToken null and return
            return;
        }

        // ########## YOUR CODE STARTS HERE ##########
        StringBuilder valueBuilder = new StringBuilder();
        boolean isUpper = false;
        boolean isLower = false;
        boolean isNonAlphanumeric = false;
        boolean isInteger = false;

        for (char c : buffer.toCharArray()) {
            if (c == ' ' || (valueBuilder.length() > 0 && !(Character.isLetterOrDigit(c)))) {
                break;
            }
            valueBuilder.append(c);

            if (Character.isDigit(c)) {
                isInteger = true;
            }
            if (Character.isLowerCase(c)) {
                isLower = true;
            }
            if (Character.isUpperCase(c)) {
                isUpper = true;
            }
            if (!(Character.isLetterOrDigit(c))) {
                isNonAlphanumeric = true;
            }
        }

        String value = valueBuilder.toString();

        if (isUpper && isLower) {
            if (value.length() <= 3) {
                currentToken = new Token(value, Token.Type.SHORT_CAMEL_CASE_WORD);
            } else {
                currentToken = new Token(value, Token.Type.LONG_CAMEL_CASE_WORD);
            }
        } else if (isUpper) {
            currentToken = new Token(value, Token.Type.UPPER_CASE_WORD);
        } else if (isLower) {
            currentToken = new Token(value, Token.Type.LOWER_CASE_WORD);
        } else if (isInteger) {
            currentToken = new Token(value, Token.Type.INTEGER_NUMBER);
        } else if (isNonAlphanumeric) {
            currentToken = new Token(value, Token.Type.NON_ALPHANUMERIC);
        }
        // ########## YOUR CODE ENDS HERE ##########

        // Remove the extracted token from buffer
        int tokenLen = currentToken.getValue().length();
        buffer = buffer.substring(tokenLen);
    }

    /**
     *  returned the current token extracted by {@code next()}
     *  **** please do not modify this part ****
     *  
     * @return type: Token
     */
    public Token current() {
    	return currentToken;
    }

    /**
     *  check whether there still exists another tokens in the buffer or not
     *  **** please do not modify this part ****
     *  
     * @return type: boolean
     */
    public boolean hasNext() {
    	return currentToken != null;
    }


}