package CAS;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbFetch;

public class Consultation extends Service {

	@Override
	public DefaultTableModel getInfo() {
		DbFetch dbF = new DbFetch();
		return dbF.fetchConsultationInfo();
	}
}
