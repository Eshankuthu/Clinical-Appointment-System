package CAS;

import java.text.SimpleDateFormat;
import java.util.*;

import CAdbConnection.DbUpdate;

/**
 * 
 * @author 986021 This class will be initiated from form the form will pass the
 *         patient ,Appointment and time at checkout
 */
public class ClockOut {

	Patient patient;
	Appointment app;
	Date Timestamp;

	ClockOut(Appointment app) {
		this.app = app;
		this.patient = app.patient;
		Calendar cal = Calendar.getInstance();
		this.Timestamp = cal.getTime();
		this.clockOutApp();

	}

	ClockOut(String appointmentId) {
		this.app = Appointment.getAppointment(appointmentId);
		this.patient = app.patient;
		Calendar cal = Calendar.getInstance();
		this.Timestamp = cal.getTime();
		this.clockOutApp();

	}

	private void clockOutApp() {
		// TODO Auto-generated method stub
		app.setClockOut(this);
		app.setStatus(AppointmentStatus.COMPLETED);
		DbUpdate dbU = new DbUpdate();
		dbU.clockOut(app.getAppointmentId(), this.Timestamp);
	}
	
	public static Appointment clockOutAppointment(Appointment app)
	{
		DbUpdate dbU = new DbUpdate();
		dbU.clockOut(app.getAppointmentId());
		return app;
	}

}
