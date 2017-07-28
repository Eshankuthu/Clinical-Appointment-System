package CAS;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbFetch;

public class Doctor extends Person {
	private String specialization;
	private String doctorId;
	boolean isFree;

	Doctor(String doctorId) {
		super();
		DbFetch dbF = new DbFetch();
		String[] row = dbF.fetchDoctorInfo(doctorId);
		setPerson(row[1]);
		this.setDoctorId(doctorId);
		this.specialization = row[2];

	}

	public Doctor(String personId, String firstName, String middleName, String lastName, LocalDate dob, String ssn,
			String country, String passportNo, String doctorId, String specialization) {
		super(personId);
		this.setDoctorId(doctorId);
		this.specialization = specialization;
	}

	public Doctor(String doctorId, String personId, String specialization) {
		super(personId);
		this.setDoctorId(doctorId);
		this.specialization = specialization;
	}

	public static Doctor getNewDoctor() {
		DbFetch dbF = new DbFetch();
		String str = dbF.fetchNewDoctor();
		return new Doctor(str);
	}

	public static Doctor[] getDoctors() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.doctorList();
		Doctor[] doctors = new Doctor[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			doctors[i] = new Doctor(tbl.getValueAt(i, 0).toString());
		}
		return doctors;
	}

	public static List<Doctor> getDoctorsList() {
		DbFetch dbF = new DbFetch();
		List<Doctor> lstDoctor = new ArrayList<Doctor>();
		DefaultTableModel dt = dbF.doctorList();
		for (int i = 0; i < dt.getRowCount(); i++) {
			String s = dt.getValueAt(i, 0).toString();
			lstDoctor.add(new Doctor(dt.getValueAt(i, 0).toString()));
		}
		return lstDoctor;
	}

	public static List<Doctor> getCurrentlyAvailableDoctorsList() {
		DbFetch dbF = new DbFetch();
		List<Doctor> lstDoctor = new ArrayList<Doctor>();
		DefaultTableModel dt = dbF.currentlyAvailableDoctorsList();
		for (int i = 0; i < dt.getRowCount(); i++) {
			String s = dt.getValueAt(i, 0).toString();
			lstDoctor.add(new Doctor(dt.getValueAt(i, 0).toString()));
		}
		return lstDoctor;
	}

	public static List<Doctor> getOnDateAvailableDoctorsList(String dateTime) {
		DbFetch dbF = new DbFetch();
		List<Doctor> lstDoctor = new ArrayList<Doctor>();
		DefaultTableModel dt = dbF.onDateAvailableDoctorsList(dateTime);
		for (int i = 0; i < dt.getRowCount(); i++) {
			String s = dt.getValueAt(i, 0).toString();
			lstDoctor.add(new Doctor(dt.getValueAt(i, 0).toString()));
		}
		return lstDoctor;
	}

	public static String getDoctorsNextAppointment(String doctorId, String dateTime) {
		DbFetch dbF = new DbFetch();
		return dbF.doctorsNextAppointment(doctorId, dateTime);
	}

	public String getDescription() {
		return specialization;
	}

	public void setDescription(String specialization) {
		this.specialization = specialization;
	}

	public static Doctor getDoctor(String doctorId) {
		return new Doctor(doctorId);
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	public boolean getIsFree()
	{
		return this.isFree;
	}
	public void setIsFree(boolean b)
	{
		this.isFree = b;
	}
	
}
