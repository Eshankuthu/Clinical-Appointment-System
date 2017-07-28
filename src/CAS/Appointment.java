package CAS;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbFetch;
import CAdbConnection.DbUpdate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 
 * @author 986021 This class will be initiated by the create appointment form
 * 
 */
public class Appointment {
	String appointmentId;
	Patient patient;
	Doctor doc;
	Service serc;
	Date start;
	Date end;
	ClockIn clockIn;
	ClockOut clockOut;
	AppointmentStatus Status;

	private Appointment(String appointmentId) {
		DbFetch dbF = new DbFetch();
		String[] str = dbF.fetchAppointmentInfo(appointmentId);
		this.appointmentId = appointmentId;
		this.patient = new Patient(str[2]);
		this.doc = new Doctor(str[1]);
		this.serc = Service.getService(str[3]);
		this.Status = AppointmentStatus.get(str[8]);
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			this.start = (Date) formatter.parse(str[6]);
			this.end = (Date) formatter.parse(str[7]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Appointment(Patient patient, Doctor doc, Service serc, String startTime, String endTime) {
		this.patient = patient;
		this.doc = doc;
		this.serc = serc;
		try {
			this.start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime);
			this.end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Appointment addAppointment(Patient patient, Doctor doc, Service serc, String startTime,
			String endTime) {
		Appointment app = new Appointment(patient, doc, serc, startTime, endTime);
		DbUpdate dbU = new DbUpdate();
		app.appointmentId = dbU.insertAppointment(patient.getPatientId(), doc.getDoctorId(), serc.getServiceId(),
				startTime, endTime);
		return app;
	}

	public static Appointment updateAppointment(String appId, Patient patient, Doctor doc, Service serc,
			String startTime, String endTime) {
		DbUpdate dbU = new DbUpdate();
		dbU.updateAppointment(appId, patient.getPatientId(), doc.getDoctorId(), serc.getServiceId(), startTime,
				endTime);
		return new Appointment(appId);
	}

	public static Appointment[] getPatientAppointments(Patient p) {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.fetchPatientAppointments(p.getPatientId());
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = new Appointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.appointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getBookedAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.bookedAppointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getInAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.checkedInAppointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getOutAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.checkedOutAppointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getCancelledAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.cancelledAppointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment[] getPaidAppointments() {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.paidAppointmentList();
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}
	public static Appointment[] getDoctorAppointments(String docId) {
		DbFetch dbF = new DbFetch();
		DefaultTableModel tbl = dbF.fetchDoctorAppointments(docId);
		Appointment[] appointments = new Appointment[tbl.getRowCount()];
		for (int i = 0; i < tbl.getRowCount(); i++) {
			appointments[i] = getAppointment(tbl.getValueAt(i, 0).toString());
		}
		return appointments;
	}

	public static Appointment getAppointment(String appointmentId) {
		return new Appointment(appointmentId);
	}

	public static void cancelAppointment(String appointmentId) {
		DbUpdate dbU = new DbUpdate();
		dbU.cancelAppointment(appointmentId);
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public ClockIn getCheckIn() {
		return clockIn;
	}

	public void setCheckIn(ClockIn clockIn) {
		this.clockIn = clockIn;
	}

	public ClockOut getClockOut() {
		return clockOut;
	}

	public void setClockOut(ClockOut clockOut) {
		this.clockOut = clockOut;
	}

	public AppointmentStatus getStatus() {
		return Status;
	}

	public void setStatus(AppointmentStatus status) {
		Status = status;
	}

	public Service getSerc() {
		return serc;
	}

	public void setSerc(Service serc) {
		this.serc = serc;
	}

	public Patient getPatient() {
		return patient;
	}

	public Doctor getDoc() {
		return doc;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

}
