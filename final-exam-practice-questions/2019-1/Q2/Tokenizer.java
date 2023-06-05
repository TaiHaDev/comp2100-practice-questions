/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * @author huy.pham
 */
public class Tokenizer {
    public static void main(String[] args) {
        new Tokenizer("LEFTTT;").next();
    }
    private String _buffer;
    private final char COMMAND_SEPARATOR = ';'; 
    
    public Tokenizer(String buffer) {
        this._buffer = buffer;
    }
    
    /**
     * @return the next token
     */
    private Token next() {
        _buffer = _buffer.trim();
        if (_buffer.isEmpty())
            return null;
        //check if there's any token left
        int terminatorIndex = _buffer.indexOf(";");
        if (terminatorIndex == -1) return null;

        // extract the next token's string representation
        String token = _buffer.substring(0, terminatorIndex);

        // find the suitable token to the extracted string
        if (token.equalsIgnoreCase(Token.Type.LEFT.toString()))
            return new Token(Token.Type.LEFT);
        
        if (token.equalsIgnoreCase(Token.Type.RIGHT.toString()))
            return new Token(Token.Type.RIGHT);

        if (token.equalsIgnoreCase(Token.Type.PENUP.toString())) {
            return new Token(Token.Type.PENUP);
        };
        if (token.equalsIgnoreCase(Token.Type.PENDOWN.toString())) {
            return new Token(Token.Type.PENDOWN);
        }

        if (token.startsWith(Token.Type.FORWARD.toString())) {
            return new Token(Token.Type.FORWARD, token, Integer.parseInt(token.substring(token.indexOf("(") + 1, token.indexOf(")"))));

        }
        if (token.startsWith(Token.Type.BACK.toString())) {
            return new Token(Token.Type.BACK, token, Integer.parseInt(token.substring(token.indexOf("(") + 1, token.indexOf(")"))));
        }
        return null;
    }

    /**
     * Return the next token and remove it from the buffer
     * @return the next token
     */
    public Token takeNext() {
        // TODO: Add your implementation here.
        if (!hasNext()) return null;
        Token next = next();
        _buffer = _buffer.substring(_buffer.indexOf(";") + 1);
        return next;
    }

    /**
     * @return whether there is another token to parse in the buffer
     */
    public boolean hasNext() {
        // TODO: Add your implementation here.

        return next() != null;
    }
   
}