
public class TransportOffice {

	private static TransportOffice instance;

	private TransportOffice() {
	}

	public static TransportOffice getInstance() {

		// TODO
		// START YOUR CODE
		if (instance == null) instance = new TransportOffice();
		// YOU ARE ALLOWED TO CHANGE THIS RETURNED VALUE
		return instance;

		// END YOUR CODE
	}

	public Transport transportBy(Parcel parcel) {

		// TODO
		// START YOUR CODE
		int allowDay = parcel.getAllowedDays();
		int weight = parcel.getWeight();
		if (allowDay >= 7) {
			parcel.setStatus(Status.TRANSFERRING);
			return Transport.SHIP;
		} else if (allowDay >= 3) {
			parcel.setStatus(Status.TRANSFERRING);
			return Transport.TRAIN;
		} else if (allowDay >= 1) {
			parcel.setStatus(Status.TRANSFERRING);
			if (weight <= 20) return Transport.AIRPLANE;
		}
		parcel.setStatus(Status.RETURNED);
		// YOU ARE ALLOWED TO CHANGE THIS RETURNED VALUE
		return null;

		// END YOUR CODE
	}
}
