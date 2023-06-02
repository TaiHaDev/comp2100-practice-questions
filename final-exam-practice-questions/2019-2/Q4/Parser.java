package parser;
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
		// Hints: Check {@link Screen} and {@link Pointer} classes to see 
    	//        there is any methods/functions you can take advantage of.
    	//        Check the expected outcome in ParserTest.java
    	//        You can make additional methods if needed
    	parseExp();
		return _screen;
	}
	private void parseExp() {
		if (_tokenizer.hasNext()) {
			parseCommand();
		}
		if (_tokenizer.hasNext()) {
			parseExp();
		}

	}
	private void parseCommand() {

		Token cur = _tokenizer.current();
		if (cur.type == Token.Type.LEFT) {
			_screen.pointer.turnLeft();
		} else if (cur.type == Token.Type.RIGHT) {
			_screen.pointer.turnRight();
		} else if (cur.type == Token.Type.PENUP) {
			_screen.pointer.isPenDown = false;
		} else if (cur.type == Token.Type.PENDOWN) {
			_screen.pointer.isPenDown = true;
			markCurrentPos();
		} else {
			int[] mark;
			int count = cur.value;
			if (_screen.pointer.direction == Direction.NORTH) {
				mark = new int[]{-1, 0};
				if (cur.type == Token.Type.FORWARD_TO_END ) {
					count = _screen.pointer.position.x;
				} else if (cur.type == Token.Type.BACK_TO_END) {
					count = _screen.noOfRows - 1- _screen.pointer.position.x;
				}
			}
			else if (_screen.pointer.direction == Direction.SOUTH) {
				mark = new int[]{1, 0};
				if (cur.type == Token.Type.FORWARD_TO_END ) {
					count = _screen.noOfRows - 1- _screen.pointer.position.x;
				} else if (cur.type == Token.Type.BACK_TO_END) {
					count = _screen.pointer.position.x;
				}
			}
			else if (_screen.pointer.direction == Direction.EAST) {
				mark = new int[]{0, 1};
				if (cur.type == Token.Type.FORWARD_TO_END ) {
					count = _screen.noOfColumns - 1- _screen.pointer.position.y;
				} else if (cur.type == Token.Type.BACK_TO_END) {
					count = _screen.pointer.position.y;
				}
			}
			else {
				mark = new int[]{0, -1};
				if (cur.type == Token.Type.FORWARD_TO_END ) {
					count = _screen.pointer.position.y;
				} else if (cur.type == Token.Type.BACK_TO_END) {
					count = _screen.noOfColumns - 1- _screen.pointer.position.y;
				}
			}
			for (int i = 0; i < count; i++) {
				if (cur.type == Token.Type.FORWARD_TO_END || cur.type == Token.Type.FORWARD) {
					_screen.pointer.position.x += mark[0];
					_screen.pointer.position.y += mark[1];
				} else {
					_screen.pointer.position.x -= mark[0];
					_screen.pointer.position.y -= mark[1];
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
