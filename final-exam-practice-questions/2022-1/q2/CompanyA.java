import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyA extends ExpressCompany {

	public CompanyA(Point location, int deliveryRadius, List<ItemType> illegalItems) {
		super(location, deliveryRadius, illegalItems);
	}

	@Override
	public double calculateQuote(Parcel parcel) {
		int weight = parcel.getWeight();
		double distanceQuote = calculateDistance(parcel) > deliveryRadius ? calculateDistance(parcel) : 0;
		double weightQuote = 0;
		int[] weightRange = {60, 40, 20, 0};
		double[] rate = {3.2, 2.4, 1.7, 1.3};
		for (int i = 0; i < weightRange.length; i++) {
			int num = weightRange[i];
			if (weight > num) {
				int cur = weight - num;
				weightQuote += rate[i] *cur;
				weight -= cur;
			}
		}
		return weightQuote + distanceQuote;
	}
}
