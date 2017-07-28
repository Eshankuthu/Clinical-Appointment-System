package CAS;

public enum AppointmentStatus {

	BOOKED("0"), ONGOING("1"), COMPLETED("2"), PAID("3"), CANCELLED("-1");
	
	private String val;

	AppointmentStatus(String value) {
        this.val = value;
    }
	
	static public AppointmentStatus get(String val)
	{
		switch(val)
		{
		case "0": return BOOKED;
		case "1": return ONGOING;
		case "2": return COMPLETED;
		case "3": return PAID;
		case "-1": return CANCELLED;
		default:
            return BOOKED;
		
		}
	}
	
	public String value()
	{
		return val;
	}
}
