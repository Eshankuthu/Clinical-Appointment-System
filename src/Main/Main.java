package Main;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import CAdbConnection.DbUpdate;
import CAdbConnection.DbFetch;
import CAS.Doctor;
import CAS.Factory;
import CAS.Person;
public class Main {
	public static void main(String args[]){
		/*DbUpdate db=new DbUpdate();
		//db.insertPerson("J", "", "G", "1987-09-02", "", "US", "1234567");
		db.insertDoctor("Ja", "", "Gk", "1950-05-02", "", "US", "12345967","MD Maharishy University, Iowa");
		db.insertDoctor("Jk", "", "Gk", "1973-08-02", "", "US", "12394567","BDS");
		db.insertPatient("kk", "", "jj", "1930-08-12", "", "US", "aa"," Accident ");
		DbFetch dbF = new DbFetch();
		DefaultTableModel dbm = dbF.personList();
		int pplCount = dbm.getRowCount();
		List<Person> lstPpl = new ArrayList<Person>();
		for(int i = 0; i<pplCount;i++)
		{
			String[] perData= new String[dbm.getColumnCount()];
			for(int j = 0; j<dbm.getColumnCount();j++)
			{
				if(dbm.getValueAt(i, j) == null) perData[j] = "";
				else perData[j] = dbm.getValueAt(i, j).toString();
			}
			lstPpl.add(new Person(perData));
		}
		*/
		List<Doctor> lstDr = Factory.getDoctorsList();
		for(Doctor dr:lstDr)
		{
			System.out.println(dr.getFirstName()+" "+dr.getLastName());
		}
		System.out.println("hehrere");

	}
}
