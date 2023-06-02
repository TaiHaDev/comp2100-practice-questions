import java.util.Arrays;

/**
 * This captures the state of the pointer and the trace of its movement.
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * @author huy.pham
 */
public class Screen {
    public static final String NON_VISITED_MARK = "#";
    public static final String VISITED_MARK = ".";

	public int noOfRows;
	public int noOfColumns;
	public Pointer pointer;
	public String[][] trace;
	
	public Screen (int noOfRows, int noOfColumns, Pointer pointer) throws OutOfScreenException {
		this.noOfRows = noOfRows;
		this.noOfColumns = noOfColumns;
		this.pointer = pointer;
		
		this.trace = new String[noOfRows][noOfColumns];
		for (String[] row : trace) {
			Arrays.fill(row, NON_VISITED_MARK);
		}
		
		// mark initial position
		if (this.pointer.isPenDown) this.markVisistedPos(pointer.position);
	}
	
	/**
	 * Mark the current position as visited.
	 * @param p position
	 * @throws Exception 
	 */
	public void markVisistedPos(Position p) throws OutOfScreenException {
		if (p.x >= 0 && p.x < noOfRows && p.y >= 0 && p.y < noOfColumns) {
			trace[p.x][p.y] = VISITED_MARK;
		} else {
			throw new OutOfScreenException();
		}
	}
	
	/**
	 * NOTE: It inserts a new line character after every row including the last one.
	 * @see {@link ScreenTest} for the expected result
	 * @return a string representing the screen including pointer's visited positions, its current position and direction.
	 */
	public String trace() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < trace.length; i++) {
			String[] strings = trace[i];
			for (int j = 0; j < strings.length; j++) {
				String string = strings[j];
				if (pointer.position.x == i && pointer.position.y == j) {
					stringBuilder.append(getPointerIcon());
				} else {
					stringBuilder.append(string);
				}
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
	
	private String getPointerIcon() {
		switch (pointer.direction) {
		case Upward:
			return "^";
		case Downward: 
			return "v";
		case Left: 
			return "<";
		case Right:
			return ">";
		default:
			return null;
		}
	}
}
