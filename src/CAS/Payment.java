package CAS;

import CAdbConnection.DbFetch;
import CAdbConnection.DbUpdate;

/**
 * 
 * @author 986021 This class will be called by UI or CA it will take the patient
 *         It will use the appointment to get the cost then it will process
 *         payment.
 */
public class Payment {

	Appointment app;
	double cost;
	double discount;
	double paymentAmount;
	String remarks;

	Payment() {
	}

	public static Payment getPayment(String paymentId) {
		Payment pay = new Payment();
		DbFetch dbF = new DbFetch();
		String[] str = dbF.fetchPaymentInfo(paymentId);
		pay.app = Appointment.getAppointment(str[1]);
			pay.cost = Double.parseDouble(str[2]);
		pay.discount = Double.parseDouble(str[3]);
		pay.paymentAmount = Double.parseDouble(str[4]);
		pay.remarks = str[6];
		return pay;
	}
	Patient patient;

	public static Payment getPaymentInfo(Appointment app) {
		Payment pay = new Payment();
		pay.patient = app.getPatient();
		pay.cost = app.getSerc().getCost();
		return pay;
	}

	public double getCost() {
		return cost;
	}

	// Payment will update the database
	public static Payment addPayment(String appointmentId, String totalAmount, String discountAmount,
			String paymentAmount, String remarks) {
		DbUpdate dbU = new DbUpdate();
		dbU.insertPayment(appointmentId, totalAmount, discountAmount, paymentAmount, remarks);
		DbFetch dbF = new DbFetch();
		String paymentId = dbF.fetchNewPayment();
		return getPayment(paymentId);

	}
}
