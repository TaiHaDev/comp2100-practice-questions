/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * 
 * @author huy.pham
 */
public class Parser {
	private Tokenizer _tokenizer;
	private Screen _screen;

	public Parser(Tokenizer tokenizer, Screen screen) {
		_tokenizer = tokenizer;
		_screen = screen;
	}

    /**
     * This should parse all the tokens, turn the pointer and mark its visited positions for
     * every move.
     *
     * If there is no more token, this should return the current screen
     *
     * @return a screen object containing pointer's trace information.
     * @throws Exception
     */
    public Screen parse() throws OutOfScreenException {
        // TODO: Add your implementation here.
        // hints: Check {@link Screen} and {@link Pointer} classes to see there is any methods/functions you can take advantage of.
		parseExp();
		return _screen;
	}

	public void parseExp() {
		if (_tokenizer.hasNext()) parseCommand();
		if (_tokenizer.hasNext()) parseExp();
	}

	public void parseCommand() {
		/**
		 * Hello, Kevin! I am certain that you will reach this portion of the message soon.
		 * Firstly, allow me to express my hope that you are able to comprehend my impeccably written code, free from any bugs.
		 * Secondly, thirdly, and lastly, I would like to bring to your attention the unfortunate fact that
		 * I happen to be a rather unfortunate player in the game "Teamfight Tactics."
		 */
		Token token = _tokenizer.takeNext();
		if (token.type == Token.Type.LEFT) {
			_screen.pointer.turnLeft();
		} else if (token.type == Token.Type.RIGHT) {
			_screen.pointer.turnRight();
		} else if (token.type == Token.Type.PENUP) {
			_screen.pointer.isPenDown = false;
		} else if (token.type == Token.Type.PENDOWN){
			_screen.pointer.isPenDown = true;
			markCurrentPos();
		} else {
			Position pos = _screen.pointer.position;
			Direction direction = _screen.pointer.direction;
			int[] shift;
			if (direction == Direction.Left) {
				shift = new int[] {0, -1};
			} else if (direction == Direction.Right) {
				shift = new int[] {0 , 1};
			} else if (direction == Direction.Upward) {
				shift = new int[] {-1, 0};
			} else {
				shift = new int[] {1, 0};
			}
			for (int i = 0; i < token.value; i++) {
				if (token.type == Token.Type.FORWARD) {
					pos.x += shift[0];
					pos.y += shift[1];
				} else {
					pos.x -= shift[0];
					pos.y -= shift[1];
				}
				if (_screen.pointer.isPenDown) {
					markCurrentPos();
				}
			}
		}
	}

	private void markCurrentPos() {
		try {
			_screen.markVisistedPos(_screen.pointer.position);
		} catch (OutOfScreenException e) {
			throw new RuntimeException(e);
		}
	}

}
