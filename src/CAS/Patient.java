package CAS;

import java.time.LocalDate;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbFetch;
import CAdbConnection.DbUpdate;

/**
 * 
 * @author 986021 This class will be initiated by the create patient form all
 *         the information will be saved in the database
 */
public class Patient extends Person {
	private String description;
	private String patientId;


	Patient(String patientId) {
		super();
		DbFetch dbF = new DbFetch();
		String[] row = dbF.fetchPatientInfo(patientId);
		setPerson(row[1]);
		this.setPatientId(row[0]);
		this.description = row[2];

	}

	/*
	 * private Patient(String personId, String firstName, String middleName,
	 * String lastName, LocalDate dob, String ssn, String country, String
	 * passportNo,String patientId,String description) { super();
	 * setPersonId(personId); this.setPatientId(patientId); this.description =
	 * description; DbUpdate dbU = new DbUpdate(); dbU.insertPatient(firstName,
	 * middleName, lastName, dob.toString(), ssn, country, passportNo,
	 * description); }
	 */
	public static Patient addPatient(String personId, String firstName, String middleName, String lastName, String dob,
			String ssn, String country, String passportNo, String patientId, String description) {
		DbUpdate dbU = new DbUpdate();
		dbU.insertPatient(firstName, middleName, lastName, dob, ssn, country, passportNo, description);
		DbFetch dbF = new DbFetch();
		return getPatient(dbF.fetchNewPatient());
	}

	public void setPatient(String patientId, String personId, String description) {
		setPerson(personId);
		this.setPatientId(patientId);
		this.description = description;
	}

	public static Patient getNewPatient() {
		DbFetch dbF = new DbFetch();
		String str = dbF.fetchNewPatient();
		return new Patient(str);
	}
	public static Patient[] getPatients() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.fetchPatients();
		Patient[] patients = new Patient[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			patients[i] = getPatient(tbl.getValueAt(i, 0).toString());
		}
		return patients;
	}
	public static boolean exists(String patientId) {
		DbFetch dbF = new DbFetch();
		DefaultTableModel dt = dbF.fetchPatientInfoTable(patientId);
		if (dt.getRowCount() > 0)
			return true;
		else
			return false;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Patient getPatient(String patientId) {
		
		return new Patient(patientId);
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
