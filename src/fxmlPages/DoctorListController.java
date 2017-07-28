package fxmlPages;
import java.net.URL;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import CAS.Appointment;
import CAS.Doctor;
import CAS.Factory;
import CAdbConnection.DbFetch;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DoctorListController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;
	    
	    
	    @FXML private TableView<Doctorinfo> DoctorTableView ;
	    @FXML private TableColumn<Doctorinfo, String> DoctorID;
	    @FXML private TableColumn<Doctorinfo, String> DoctorName;
	    @FXML private TableColumn<Doctorinfo, String> dob;
	    @FXML private TableColumn<Doctorinfo, String> country ;
	    @FXML private TableColumn<Doctorinfo, String> passNo ;


	    @FXML
	    void initialize() {
	    	DoctorID.setCellValueFactory(new PropertyValueFactory<Doctorinfo,String>("DoctorID"));
	    	DoctorName.setCellValueFactory(new PropertyValueFactory<Doctorinfo,String>("DoctorName"));
	    	dob.setCellValueFactory(new PropertyValueFactory<Doctorinfo,String>("dob"));
	    	country.setCellValueFactory(new PropertyValueFactory<Doctorinfo,String>("country"));
	    	passNo.setCellValueFactory(new PropertyValueFactory<Doctorinfo,String>("passNo"));

	    	ObservableList<Doctorinfo> D = parseUserList();
	    	if (!D.isEmpty()){
	    	DoctorTableView.getItems().setAll(D);}




	    }



		private ObservableList<Doctorinfo> parseUserList() {
			// TODO Auto-generated method stub
			ObservableList<Doctorinfo> apps = FXCollections.observableArrayList();
			
			
	    	List<Doctor> Doc =Factory.getDoctorsList();
	    	for(Doctor d : Doc){
	    		Doctorinfo tmp = new Doctorinfo(d.getDoctorId(),d.getName(),d.getDob().toString(),d.getCountry(),d.getPassportNo());
	    		apps.addAll(tmp);
	    	}
	    	
			return apps;
		}
		
		
		public class Doctorinfo {

			private final SimpleStringProperty DoctorID = new SimpleStringProperty("");
			   private final SimpleStringProperty DoctorName = new SimpleStringProperty("");
			   private final SimpleStringProperty dob = new SimpleStringProperty("");
			   private final SimpleStringProperty country = new SimpleStringProperty("");
			   private final SimpleStringProperty passNo = new SimpleStringProperty("");

			
			public void setPassNo(String status) {
				passNo.set(status);
			}

			public String getPassNo() {
				return passNo.get();
			}

			public Doctorinfo() {
			        this("", "", "","","");
			    }
			
			public String getDoctorID() {
				return DoctorID.get();
			}

			public String getDoctorName() {
				return DoctorName.get();
			}

			public String getDob() {
				return dob.get();
			}

			public String getCountry() {
				return country.get();
			}

				public Doctorinfo(String DoctorID, String DoctorName, String dob, String Country, String passNo) {
			        setDoctorID(DoctorID);
			        setDoctorName(DoctorName);
			        setDob(dob);
			        setCountry(Country);
			        setPassNo(passNo);
			    }

				private void setCountry(String startTime2) {
					// TODO Auto-generated method stub
					country.set(startTime2);
					
				}

				private void setDob(String doctor2) {
					// TODO Auto-generated method stub
					dob.set(doctor2);
				}

				private void setDoctorName(String patient2) {
					// TODO Auto-generated method stub
					DoctorName.set(patient2);
				}

				private void setDoctorID(String doctorID) {
					// TODO Auto-generated method stub
					DoctorID.set(doctorID);
					
				}
				
				
		}


	

}
