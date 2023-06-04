public class QuoteCalculator {

	public static Quote bestQuote(Parcel parcel, ExpressCompany a, ExpressCompany b) throws IllegalParcelItemException {

		Quote quote = null;

		// TODO
		// ########## YOUR CODE STARTS HERE ##########
		double quoteA = a.handle(parcel);
		double quoteB = b.handle(parcel);
		if (quoteB < quoteA) {
			quote = new Quote(b, quoteB);
		} else {
			quote = new Quote(a, quoteA);
		}
		// ########## YOUR CODE ENDS HERE ##########

		return quote;

	}
}
