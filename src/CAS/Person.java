package CAS;

import java.time.LocalDate;

import CAdbConnection.DbFetch;
import CAdbConnection.DbUpdate;

public class Person {

	private String personId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String ssn;
	private LocalDate dob;
	private String country;
	private String passportNo;
	DbUpdate dbU = new DbUpdate();
	DbFetch dbF = new DbFetch();

	Person() {

	}

	Person(String personId) {
		Person pat = new Person();
		DbFetch dbF = new DbFetch();
		String[] str = dbF.fetchPersonInfo(personId);
		pat.personId = str[0];
		pat.firstName = str[1];
		pat.setMiddleName(str[2]);
		pat.lastName = str[3];
		pat.dob = LocalDate.parse(str[4].split(" ")[0]);
		pat.ssn = str[5];
		pat.country = str[6];
		pat.passportNo = str[7];
	}

	public static Person getPerson(String personId) {
		Person pat = new Person();
		DbFetch dbF = new DbFetch();
		String[] str = dbF.fetchPersonInfo(personId);
		pat.personId = str[0];
		pat.firstName = str[1];
		pat.setMiddleName(str[2]);
		pat.lastName = str[3];
		pat.dob = LocalDate.parse(str[4].split(" ")[0]);
		pat.ssn = str[5];
		pat.country = str[6];
		pat.passportNo = str[7];
		return pat;
	}

	Person(String pId, String fName, String mName, String lName, String dob, String ssn, String country, String ppNo) {
		this.personId = pId;
		this.firstName = fName;
		this.setMiddleName(mName);
		this.lastName = lName;
		this.dob = LocalDate.parse(dob.split(" ")[0]);
		this.ssn = ssn;
		this.country = country;
		this.passportNo = ppNo;

	}

	Person(String[] str) {
		this.personId = str[0];
		this.firstName = str[1];
		this.setMiddleName(str[2]);
		this.lastName = str[3];
		this.dob = LocalDate.parse(str[4].split(" ")[0]);
		this.ssn = str[5];
		this.country = str[6];
		this.passportNo = str[7];

	}

	public void setPerson(String personId) {
		String[] str = dbF.fetchPersonInfo(personId);
		this.personId = str[0];
		this.firstName = str[1];
		this.setMiddleName(str[2]);
		this.lastName = str[3];
		if (str[4] != null)
			this.dob = LocalDate.parse(str[4].split(" ")[0]);
		this.ssn = str[5];
		this.country = str[6];
		this.passportNo = str[7];
	}

	public static Person addNewPerson(String fName, String mName, String lName, String dob, String ssn, String country,
			String ppNo) {
		DbUpdate dbU = new DbUpdate();
		dbU.insertPerson(fName, mName, lName, dob, ssn, country, ppNo);
		return getNewPerson();
	}

	public static Person getNewPerson() {
		DbFetch dbF = new DbFetch();
		String str = dbF.fetchNewPerson();
		return new Person(str);
	}

	public static boolean isUniqueSsn(String ssn, String patId) {
		DbFetch dbF = new DbFetch();
		if (patId.isEmpty()) {
			
			if (dbF.fetchSsn(ssn).getRowCount() > 0)
				return false;
			return true;
		}
		Patient p = new Patient(patId);
		if (dbF.fetchSsn(ssn,p.getPersonId()).getRowCount() > 0)
			return false;
		return true;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
}
