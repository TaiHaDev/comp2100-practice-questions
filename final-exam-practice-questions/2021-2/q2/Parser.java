import java.util.LinkedList;
import java.util.List;

public class Parser {

	private final Tokeniser tokeniser;

	public Parser(Tokeniser tokeniser) {
		this.tokeniser = tokeniser;
	}

	public List<Command> parseCmds() {

		List<Command> commands = new LinkedList<>();
		while (tokeniser.hasNext()) {
			Token firstTok = tokeniser.current();
			tokeniser.next();
			String param1 = tokeniser.current().getValue();
			tokeniser.next();
			String to = tokeniser.current().getValue();
			tokeniser.next();
			String param2 = tokeniser.current().getValue();
			tokeniser.next();
			if (!tokeniser.current().getValue().equals(";")) break;
			if (firstTok.getType() == Token.Type.LOAD && to.equalsIgnoreCase("FROM")) {
				commands.add(new LoadCommand(param1, param2));
			} else if (firstTok.getType() == Token.Type.SAVE && to.equalsIgnoreCase("TO")) {
				commands.add(new SaveCommand(param1, param2));
			}
			tokeniser.next();
		}

		// ########## YOUR CODE STARTS HERE ##########

		// ########## YOUR CODE ENDS HERE ##########

		return commands;
	}
}
