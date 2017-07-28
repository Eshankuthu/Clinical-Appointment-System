package CAS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbFetch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Factory {

	public static Patient addPatient(String personId, String firstName, String middleName, String lastName, String dob,
			String ssn, String country, String passportNo, String patientId, String description) {
		return Patient.addPatient(personId, firstName, middleName, lastName, dob, ssn, country, passportNo, patientId,
				description);
	}

	public static boolean isUniqueSsn(String ssn, String patId) {
		return Person.isUniqueSsn(ssn, patId);
	}
	public static boolean isDocFree(String docId, String appId, String start, String end) {
		return DoctorAvailability.isDoctorAvailableAt(docId, appId, start, end);
	}

	public static boolean patientExists(String patientId) {
		return Patient.exists(patientId);
	}

	public static Patient getPatient(String patientId) {
		return Patient.getPatient(patientId);
	}
	public static ObservableList<Patient> getPatients() {
		return FXCollections.observableArrayList(Patient.getPatients());
	}
	public static Doctor getDoctor(String doctorId) {
		return Doctor.getDoctor(doctorId);
	}

	public static Appointment getAppointment(String appId) {
		return Appointment.getAppointment(appId);
	}

	public static Appointment addAppointment(Patient patient, Doctor doc, Service serc, String startTime,
			String endTime) {
		return Appointment.addAppointment(patient, doc, serc, startTime, endTime);
	}

	public static Appointment updateAppointment(String appointmentId, Patient patient, Doctor doc, Service serc,
			String startTime, String endTime) {
		return Appointment.updateAppointment(appointmentId, patient, doc, serc, startTime, endTime);
	}

	public static ObservableList<Appointment> getAppointments() {
		return FXCollections.observableArrayList(Appointment.getAppointments());
	}

	public static ObservableList<Appointment> getBookedAppointments() {
		return FXCollections.observableArrayList(Appointment.getBookedAppointments());
	}

	public static ObservableList<Appointment> getInAppointments() {
		return FXCollections.observableArrayList(Appointment.getInAppointments());
	}

	public static ObservableList<Appointment> getOutAppointments() {
		return FXCollections.observableArrayList(Appointment.getOutAppointments());
	}

	public static ObservableList<Appointment> getPaidAppointments() {
		return FXCollections.observableArrayList(Appointment.getPaidAppointments());
	}

	public static ObservableList<Appointment> getCancelledAppointments() {
		return FXCollections.observableArrayList(Appointment.getCancelledAppointments());
	}
	public static ObservableList<Appointment> getDoctorAppointments(String docId) {
		return FXCollections.observableArrayList(Appointment.getDoctorAppointments(docId));
	}

	public static void cancelAppointment(Appointment appointment) {
		Appointment.cancelAppointment(appointment.getAppointmentId());
	}

	public static Payment addPayment(String appointmentId, String totalAmount, String discountAmount,
			String paymentAmount, String remarks) {
		return Payment.addPayment(appointmentId, totalAmount, discountAmount, paymentAmount, remarks);
	}

	public static void clockOut(String appointmentId) {
		new ClockOut(appointmentId);
	}

	public static void clockIn(String appointmentId) {
		new ClockIn(appointmentId);
	}

	public static void clockOut(Appointment app) {
		ClockOut.clockOutAppointment(app);
		app.getDoc().setIsFree(true);
	}

	public static void clockIn(Appointment app) {
		ClockIn.clockInAppointment(app);
		app.getDoc().setIsFree(false);
	}

	public static List<Doctor> getDoctorsList() {
		return Doctor.getDoctorsList();
	}
	
	public static List<Service> getServiceList() {
		return Service.getServiceList();
	}

	public static List<Doctor> getCurrentlyAvailableDoctorsList() {
		return Doctor.getCurrentlyAvailableDoctorsList();
	}

	public static List<Doctor> getOnDateAvailableDoctorsList(String dateTime) {
		return Doctor.getOnDateAvailableDoctorsList(dateTime);
	}

	public static Appointment getDoctorsNextAppointment(String doctorId, String dateTime) {
		String appointmentId = Doctor.getDoctorsNextAppointment(doctorId, dateTime);
		return Appointment.getAppointment(appointmentId);
	}
}
