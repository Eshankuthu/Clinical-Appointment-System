package CAdbConnection;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import com.mysql.jdbc.ResultSetMetaData;

public class DbFetch {

	public DefaultTableModel fetchList(String query) {
		DefaultTableModel table = new DefaultTableModel();
		DbConnection dbCon = DbConnection.getInstance();
		ResultSet set = dbCon.fetchResult(query);
		try {
			ResultSetMetaData metaData = (ResultSetMetaData) set.getMetaData();
			int totalColumn = metaData.getColumnCount();
			Object[] dataRow = new Object[totalColumn];
			if (set != null) {
				for (int i = 1; i <= totalColumn; i++) {
					table.addColumn(metaData.getColumnName(i));
				}
				while (set.next()) {
					for (int i = 1; i <= totalColumn; i++) {
						dataRow[i - 1] = set.getObject(i);
					}
					table.addRow(dataRow);
				}
			}
		} catch (Exception ex) {

		}
		return table;
	}

	public DefaultTableModel personList() {
		return fetchList("Select * from person");
	}

	public DefaultTableModel doctorList() {
		return fetchList("Select * from doctor");
	}

	public DefaultTableModel patientList() {
		return fetchList("Select * from patient");
	}

	public DefaultTableModel appointmentList() {
		return fetchList("Select * from appointment");
	}

	public DefaultTableModel bookedAppointmentList() {
		return fetchList("Select * from appointment where status = 0");
	}

	public DefaultTableModel checkedInAppointmentList() {
		return fetchList("Select * from appointment where status = 1");
	}

	public DefaultTableModel checkedOutAppointmentList() {
		return fetchList("Select * from appointment where status = 2");
	}

	public DefaultTableModel paidAppointmentList() {
		return fetchList("Select * from appointment where status = 3");
	}

	public DefaultTableModel cancelledAppointmentList() {
		return fetchList("Select * from appointment where status = -1");
	}

	public String fetchLatest(String query) {
		DbConnection dbCon = DbConnection.getInstance();
		ResultSet set = dbCon.fetchResult(query);
		try {
			if (set != null) {
				while (set.next()) {
					String a = set.getObject(1).toString();
					return a;
				}
			}
		} catch (Exception ex) {

		}
		return "";
	}

	public String fetchNewPerson() {
		return fetchLatest("select max(personId) newEntry from person");
	}

	public String fetchNewAppointment() {
		return fetchLatest("select max(appointmentId) newEntry from appointment");
	}

	public String fetchNewDoctor() {
		return fetchLatest("select max(doctorId) newEntry from doctor");
	}

	public String fetchNewPatient() {
		return fetchLatest("select max(patientId) newEntry from patient");
	}

	public String fetchNewPayment() {
		return fetchLatest("select max(paymentId) newEntry from payment");
	}

	public String[] fetchRow(String query) {
		DbConnection dbCon = DbConnection.getInstance();
		ResultSet set = dbCon.fetchResult(query);
		String[] row = new String[1];
		try {
			ResultSetMetaData metaData = (ResultSetMetaData) set.getMetaData();
			int totalColumn = metaData.getColumnCount();
			row = new String[totalColumn];

			if (set != null) {
				while (set.next()) {
					for (int i = 1; i <= totalColumn; i++) {
						if (set.getObject(i) != null)
							row[i - 1] = set.getObject(i).toString();
						else
							row[i - 1] = "";
					}
				}
			}
		} catch (Exception ex) {

		}
		return row;
	}

	public String[] fetchServiceInfo(String serviceId) {
		return fetchRow("select * from service where serviceName = '" + serviceId + "'");
	}

	public DefaultTableModel fetchTreatmentInfo() {
		return fetchList("select * from service where serviceName = 'Treatment'");
	}

	public DefaultTableModel fetchConsultationInfo() {
		return fetchList("select * from service where serviceName = 'Consultation'");
	}

	public DefaultTableModel fetchPersonInfoTable(String personId) {
		return fetchList("select * from person where personId = '" + personId + "'");
	}

	public String[] fetchPersonInfo(String personId) {
		return fetchRow("select * from person where personId = '" + personId + "'");
	}

	public DefaultTableModel fetchSsn(String ssn) {
		return fetchList("select personId from person where ssn = '" + ssn + "'");
	}

	public DefaultTableModel fetchSsn(String ssn, String personId) {
		return fetchList("select personId from person where ssn = '" + ssn + "' and personId != '" + personId + "'");
	}

	public DefaultTableModel fetchPatientInfoTable(String patientId) {
		return fetchList("select * from patient where patientId = '" + patientId + "'");
	}
	public DefaultTableModel fetchPatients() {
		return fetchList("select * from patient");
	}

	public String[] fetchPatientInfo(String patientId) {
		return fetchRow("select * from patient where patientId = '" + patientId + "'");
	}

	public DefaultTableModel fetchDoctorInfoTable(String doctorId) {
		return fetchList("select * from doctor where doctorId = '" + doctorId + "'");
	}

	public String[] fetchDoctorInfo(String doctorId) {
		return fetchRow("select * from doctor where doctorId = '" + doctorId + "'");
	}

	public DefaultTableModel fetchAppointmentTable(String appointmentId) {
		return fetchList("select * from appointment where appointmentId = '" + appointmentId + "'");
	}

	public String[] fetchAppointmentInfo(String appointmentId) {
		return fetchRow("select * from appointment where appointmentId = '" + appointmentId + "'");
	}

	public DefaultTableModel fetchPaymentTable(String paymentId) {
		return fetchList("select * from payment where paymentId = '" + paymentId + "'");
	}

	public String[] fetchPaymentInfo(String paymentId) {
		return fetchRow("select * from payment where paymentId = '" + paymentId + "'");
	}

	public DefaultTableModel fetchDoctorAppointments(String doctorId) {
		return fetchList("select * from appointment where doctorId = '" + doctorId + "' and startTime>now() and status!=-1 order by startTime");
	}

	public DefaultTableModel fetchPatientAppointments(String patientId) {
		return fetchList("select * from appointment where patientId = '" + patientId + "'");
	}

	public boolean isDoctorAvailableNow(String doctorId) {
		DefaultTableModel dt = fetchList("select appointmentId from appointment where doctorId='" + doctorId
				+ "' and (status=1 || status=2) and now() between startTime and endTime");
		if (dt.getRowCount() > 0)
			return true;
		return false;
	}

	public boolean isDoctorAvailableNow(String doctorId, String dateTime) {
		DefaultTableModel dt = fetchList("select appointmentId from appointment where doctorId='" + doctorId
				+ "' and (status=0 || status=1) and '" + dateTime + "' between startTime and endTime");
		if (dt.getRowCount() > 0)
			return true;
		return false;
	}

	public boolean isDoctorAvailableAt(String doctorId, String appId, String start, String end) {
		DefaultTableModel dt = fetchList("select appointmentId from appointment where doctorId='" + doctorId
				+ "' and (status!=-1) and ('" + start + "' between startTime and endTime)  and ('" + end
				+ "' between startTime and endTime) and appointmentId != '" + appId + "'");
		if (dt.getRowCount() > 0)
			return false;
		return true;
	}

	public boolean isDoctorAvailableAt(String doctorId, String start, String end) {
		DefaultTableModel dt = fetchList("select appointmentId from appointment where doctorId='" + doctorId
				+ "' and (status!=-1) and ('" + start + "' between startTime and endTime)  and ('" + end
				+ "' between startTime and endTime)");
		if (dt.getRowCount() > 0)
			return false;
		return true;
	}

	public DefaultTableModel currentlyAvailableDoctorsList() {
		return fetchList(
				"select * from doctor where doctorId not in (select distinct doctorId from appointment where (status=0 || status=1) and now() between startTime and endTime)");
	}

	public DefaultTableModel onDateAvailableDoctorsList(String dateTime) {
		return fetchList(
				"select * from doctor where doctorId not in (select distinct doctorId from appointment where (status=0 || status=1) and '"
						+ dateTime + "' between startTime and endTime)");
	}

	public String doctorsNextAppointment(String doctorId, String dateTime) {
		DefaultTableModel dt = fetchList("select appointmentId from appointment where (status=0 || status=1) and '"
				+ dateTime + "' < startTime and doctorId='" + doctorId + "';");
		if (dt.getRowCount() > 0)
			return dt.getValueAt(0, 0).toString();
		else
			return "";
	}
	public DefaultTableModel paymentList() {
		return fetchList("Select * from payment");
	}


}
