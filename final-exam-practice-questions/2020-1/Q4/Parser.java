import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 * 
 * Implement a parser to extract the columns and values from tokens and execute
 * the SQL command to insert new customers. Do not insert customers if the
 * following errors are found: 
 * 1 - some brackets are missing 
 * 2 - some columnnames are wrong
 * 
 * Please see the columns names in Customer.java file. Please see test cases in
 * ParserTest.java
 */
public class Parser {

	Tokeniser tokeniser;
	XMLTable table;
	private final String[] COLUMNS_NAME = {"ID", "Name", "Address", "City", "Postcode", "Country"};

	public Parser(Tokeniser tokeniser, XMLTable table) {
		this.tokeniser = tokeniser;
		this.table = table;
	}

	/**
	 * Extract the columns and values from tokens and execute the SQL command to insert new customers
	 */
	public void parseExp() {

		// TODO: Complete this method
		// START YOUR CODE
		Token insert = tokeniser.takeNext();

		String tableName = insert.value.substring(0, insert.value.indexOf("(") - 1);
		String[] columns = insert.value.substring(insert.value.indexOf("(") + 1, insert.value.indexOf(")")).split(",");
		if (!Arrays.equals(COLUMNS_NAME, columns)) return;
		List<Customer> customerList = new ArrayList<>();

		while (tokeniser.hasNext()) {
			Token values = tokeniser.takeNext();
			String[] vals = values.value.substring(values.value.indexOf("(") + 1, values.value.indexOf(")")).split(",");
			int id = Integer.parseInt(vals[0]);
			String name = vals[1].substring(2, vals[1].length() - 1);
			String address = vals[2].substring(2, vals[2].length() - 1);
			String city = vals[3].substring(2, vals[3].length() - 1);
			String postcode = vals[4].substring(2, vals[4].length() - 1);
			String country = vals[5].substring(2, vals[5].length() - 1);
			table.insert(tableName, new Customer(id, name, address, city, postcode, country));
		}

	}
}
