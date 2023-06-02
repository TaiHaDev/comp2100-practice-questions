/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * @author huy.pham
 */
public class Tokenizer {
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
        
        if (_buffer.startsWith(Token.Type.LEFT.toString())) 
            return new Token(Token.Type.LEFT);
        
        if (_buffer.startsWith(Token.Type.RIGHT.toString())) 
            return new Token(Token.Type.RIGHT);

        if (_buffer.startsWith(Token.Type.PENUP.toString())) {
            return new Token(Token.Type.PENUP);
        };
        if (_buffer.startsWith(Token.Type.PENDOWN.toString())) {
            return new Token(Token.Type.PENDOWN);
        }
        String tokenStr = _buffer.substring(0, _buffer.indexOf(";"));
        String distance = tokenStr.substring(tokenStr.indexOf("(") + 1, tokenStr.indexOf(")"));
        boolean isDigit = true;
        for (char c : distance.toCharArray()) {
            if (!Character.isDigit(c)) {
                isDigit = false;
            }
        }
        if (!isDigit) return null;
        if (_buffer.startsWith(Token.Type.FORWARD.toString())) {
            return new Token(Token.Type.FORWARD, tokenStr, Integer.parseInt(distance));

        }
        if (_buffer.startsWith(Token.Type.BACK.toString())) {
            return new Token(Token.Type.BACK, tokenStr, Integer.parseInt(distance));
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

        return !_buffer.isEmpty();
    }
   
}