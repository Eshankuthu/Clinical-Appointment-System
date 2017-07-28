package CAS;

import CAdbConnection.DbFetch;

public class DoctorAvailability {
	DbFetch dbF = new DbFetch();

	public boolean isDoctorAvailableNow(String doctorId) {
		return dbF.isDoctorAvailableNow(doctorId);
	}

	public boolean isDoctorAvailable(String doctorId, String datetime) {
		return dbF.isDoctorAvailableNow(doctorId, datetime);
	}

	public static boolean isDoctorAvailableAt(String doctorId, String appId, String start, String end) {
		DbFetch dbF = new DbFetch();
		if (appId == "")
			return dbF.isDoctorAvailableAt(doctorId, start, end);
		return dbF.isDoctorAvailableAt(doctorId, appId, start, end);
	}

}
