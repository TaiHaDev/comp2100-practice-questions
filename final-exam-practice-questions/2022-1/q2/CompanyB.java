import java.util.List;

public class CompanyB extends ExpressCompany {

	public CompanyB(Point location, int deliveryRadius, List<ItemType> illegalItems) {
		super(location, deliveryRadius, illegalItems);
	}

	@Override
	public double calculateQuote(Parcel parcel) {
		int weight = parcel.getWeight();
		double distanceQuote = calculateDistance(parcel) > deliveryRadius ? calculateDistance(parcel) * 1.1 : 0;
		double weightQuote = 0;
		int[] weightRange = {60, 50, 40, 30};
		double[] rate = {3.6, 2.9, 2.1, 1.5};
		for (int i = 0; i < weightRange.length; i++) {
			int num = weightRange[i];
			if (weight > num) {
				int cur = weight - num;
				weightQuote += rate[i] *cur;
				weight -= cur;
			}
		}
		weightQuote += 46;
		return weightQuote + distanceQuote;
	}
}
