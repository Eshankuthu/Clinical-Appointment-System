package Ruleset;

import CAS.Factory;

public class PatientValidation {

	String patId;
	String country;
	String fName;
	String mName;
	String lName;
	String dob;
	String ssn;

	public PatientValidation() {
	}

	public String validate(String patId, String fname, String mname, String lname, String ssn, String country, String dob) {
		this.patId =patId;
		this.fName = fname;
		this.mName = mname;
		this.lName = lname;
		this.dob = dob;
		this.ssn = ssn;
		this.country = country;
		String isValid = emptyValidation();
		if (!isValid.isEmpty())
			return isValid;
		isValid = nameValidation();
		if (!isValid.isEmpty())
			return isValid;
		isValid = ssnValidation();
		if (!isValid.isEmpty())
			return isValid;
		return "";
	}

	public String emptyValidation() {
		if (fName == "")
			return "Please enter name";
		else if (lName == "")
			return "Please enter last name";
		else if (dob == "")
			return "Please enter dob";
		return "";
	}

	public String nameValidation() {
		if (!fName.matches("[a-zA-Z]+"))
			return "Please enter valid name";
		if (!(mName.matches("[a-zA-Z]+") || mName.isEmpty()))
			return "Please enter valid middle name";
		if (!lName.matches("[a-zA-Z]+"))
			return "Please enter valid last name";
		return "";
	}

	public boolean dobValidation() {

		return true;
	}

	public String ssnValidation() {
		if (ssn.trim().equals(""))
			return "";
		if (!Factory.isUniqueSsn(this.ssn.trim(),this.patId))
			return "Please check your ssn";
		return "";
	}

}
