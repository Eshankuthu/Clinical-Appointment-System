package Ruleset;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import CAS.Factory;

public class AppointmentValidation {
	String appId;
	String docId;
	String start;
	String end;
	Date e;
	Date s;

	public AppointmentValidation() {
	}

	public String validate(String appId, String docId, String start, String end) {
		this.appId = appId;
		this.docId = docId;
		this.start = start;
		this.end = end;
		String isValid = isFuture();
		if (!isValid.isEmpty())
			return isValid;
		isValid = isDocFree();
		if (!isValid.isEmpty())
			return isValid;
		return "";
	}

	public String isFuture() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			s = (Date) formatter.parse(start);
			e = (Date) formatter.parse(end);
			
			if (s.compareTo(new Date()) < 0)
				return "Please select Future Date";
			if (s.compareTo(e) >= 0)
				return "Please select Valid Time. End time should be after start.";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public String isDocFree() {
		if (!Factory.isDocFree(docId, appId, start, end))
			return "Doctor is not available for the Time. Please check Doctors availibility in list below";
	return "";
	}
	
}
