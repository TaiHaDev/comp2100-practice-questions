
public class Recipient {

	public Recipient() {
	}

	public void receive(Parcel parcel) {
		// TODO
		// START YOUR CODE
		if (!parcel.getRecipient().equals(this)) {
			parcel.setStatus(Status.RETURNED);
		} else {
			parcel.setStatus(Status.DELIVERED);
		}
		
		
		// END YOUR CODE
	}
}
