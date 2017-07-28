package CAS;

import java.util.Calendar;
import java.util.Date;

import CAdbConnection.DbUpdate;

/*
 * this class will be initiated by form
 * the form will pass the patient ID, appointment
 *  and time-stamp at clicking checkout
 */
public class ClockIn {

	Patient patient;
	Appointment app;
	Date Timestamp;

	ClockIn(Patient p, Appointment app, Date Timestamp) {
		super();
		this.patient = p;
		this.app = app;
		this.Timestamp = Timestamp;
		this.clockInApp();
	}

	ClockIn(String appointmentId) {
		this.app = Appointment.getAppointment(appointmentId);
		this.patient = app.patient;
		Calendar cal = Calendar.getInstance();
		this.Timestamp = cal.getTime();
		this.clockInApp();

	}

	private void clockInApp() {
		app.setCheckIn(this);
		app.setStatus(AppointmentStatus.ONGOING);
		DbUpdate dbU = new DbUpdate();
		dbU.clockIn(app.getAppointmentId(), this.Timestamp);
	}

	public static Appointment clockInAppointment(Appointment app) {
		DbUpdate dbU = new DbUpdate();
		dbU.clockIn(app.getAppointmentId());
		return app;
	}

}
