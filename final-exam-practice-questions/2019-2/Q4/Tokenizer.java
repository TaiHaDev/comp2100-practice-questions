package parser;
/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * @author huy.pham
 * @author dongwoo
 */
public class Tokenizer {
    private String _buffer;
    private final char COMMAND_SEPARATOR = ';'; 
    private Token current;
    
    public Tokenizer(String buffer) {
        this._buffer = buffer;
        this.next();
    }
    
    /**
     *  Extract the next token from {@code _buffer}
     */
    public void next() {
        _buffer = _buffer.trim();
        if (_buffer.isEmpty()) {
            current = null;
        	return;
        }
        if (_buffer.startsWith(Token.Type.LEFT.toString()))
            current = new Token(Token.Type.LEFT);
        
        else if (_buffer.startsWith(Token.Type.RIGHT.toString()))
            current = new Token(Token.Type.RIGHT);

        // TODO: Implement "FORWARD_TO_END" and "BACK_TO_END" tokenization.
        // TODO: Implement "FORWARD" and "BACK" tokenization.
        // TODO: Implement "PENUP" and "PENDOWN" tokenization.
        else if (_buffer.startsWith(Token.Type.PENUP.toString())) {
            current = new Token(Token.Type.PENUP);
        }
        else if (_buffer.startsWith(Token.Type.PENDOWN.toString())) {
            current = new Token(Token.Type.PENDOWN);
        }
        else if (_buffer.startsWith(Token.Type.FORWARD_TO_END.toString())) {
            current = new Token(Token.Type.FORWARD_TO_END);
        } else if (_buffer.startsWith(Token.Type.BACK_TO_END.toString())) {
            current = new Token(Token.Type.BACK_TO_END);
        } else {
            String origin = _buffer.substring(0, _buffer.indexOf(";"));
            String len = origin.substring(origin.indexOf("(") + 1, origin.indexOf(")"));
            boolean isValid = true;
            for (char c : len.toCharArray()) {
                if (!Character.isDigit(c)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                if (_buffer.startsWith(Token.Type.BACK.toString())) {
                    current = new Token(Token.Type.BACK, origin, Integer.parseInt(len));
                } else {
                    current = new Token(Token.Type.FORWARD, origin, Integer.parseInt(len));
                }
            }
        }
    }
    

    /**
     * Return the token in {@code _current}
     * @return the next token
     */
    public Token current() {
        if (hasNext()) {
            next();
            _buffer = _buffer.substring(_buffer.indexOf(";") + 1);
            return current;
        }
        return null;
    }

    /**
     * @return whether there is another token in {@code _current}
     */
    public boolean hasNext() {
        Token prev = current;
        next();
        return prev != current;
    }

}