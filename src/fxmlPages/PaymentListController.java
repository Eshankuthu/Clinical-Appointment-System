package fxmlPages;
import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;

import javax.swing.table.DefaultTableModel;

import CAS.Appointment;
import CAS.Factory;
import CAdbConnection.DbFetch;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PaymentListController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;
	    
	    
	    @FXML private TableView<Payment> PaymentTableView ;
	    @FXML private TableColumn<Payment, String> AppointmentId;
	    @FXML private TableColumn<Payment, String> PatientName;
	    @FXML private TableColumn<Payment, String> DoctorName;
	    @FXML private TableColumn<Payment, String> Service ;
	    @FXML private TableColumn<Payment, String> PaymentDate ;
	    @FXML private TableColumn<Payment, String> TotalAmt ;
	    @FXML private TableColumn<Payment, String> PaymentAmt ;
	    @FXML private TableColumn<Payment, String> DiscountAmt ;



	    @FXML
	    void initialize() {
	    	AppointmentId.setCellValueFactory(new PropertyValueFactory<Payment,String>("AppointmentId"));
	    	PatientName.setCellValueFactory(new PropertyValueFactory<Payment,String>("PatientName"));
	    	DoctorName.setCellValueFactory(new PropertyValueFactory<Payment,String>("DoctorName"));
	    	Service.setCellValueFactory(new PropertyValueFactory<Payment,String>("Service"));
	    	PaymentDate.setCellValueFactory(new PropertyValueFactory<Payment,String>("PaymentDate"));
	    	TotalAmt.setCellValueFactory(new PropertyValueFactory<Payment,String>("TotalAmt"));
	    	PaymentAmt.setCellValueFactory(new PropertyValueFactory<Payment,String>("PaymentAmt"));
	    	DiscountAmt.setCellValueFactory(new PropertyValueFactory<Payment,String>("DiscountAmt"));

	    	PaymentTableView.getItems().setAll(parseUserList());




	    }



		private ObservableList<Payment> parseUserList() {
			// TODO Auto-generated method stub
			ObservableList<Payment> apps = FXCollections.observableArrayList();
			
			DbFetch fetch = new DbFetch();
	    	DefaultTableModel table=fetch.paymentList();
	    	for(int i=0; i<table.getRowCount();i++){
	    		Appointment app= Factory.getAppointment(table.getValueAt(i, 1).toString());
	    		String DoctorName = app.getDoc().getName();
	    		String PatientName =app.getPatient().getName();
	    		String ServiceName = app.getSerc().getServiceName();
	    		Payment tmp =new Payment(table.getValueAt(i, 1).toString(),
	    				PatientName,
	    				DoctorName,
	    				ServiceName,
	    				table.getValueAt(i, 2).toString(),
	    				table.getValueAt(i, 3).toString(),
	    				table.getValueAt(i, 4).toString(),
	    				table.getValueAt(i, 5).toString());
	    	
	    		apps.addAll(tmp);
	    		
	    	}
			return apps;
		}
		
		
		
		
		public class Payment {

			private final SimpleStringProperty AppointmentId = new SimpleStringProperty("");
			   private final SimpleStringProperty PatientName = new SimpleStringProperty("");
			   private final SimpleStringProperty DoctorName = new SimpleStringProperty("");
			   private final SimpleStringProperty Service = new SimpleStringProperty("");
			   private final SimpleStringProperty PaymentDate = new SimpleStringProperty("");
			   private final SimpleStringProperty TotalAmt = new SimpleStringProperty("");
			   private final SimpleStringProperty PaymentAmt = new SimpleStringProperty("");
			   private final SimpleStringProperty DiscountAmt = new SimpleStringProperty("");

			
			
			

				public Payment(String PaymentId, String PatientName, String dob, String Service, String TotalAmt,  String DiscountAmt,String PaymentAmt,String PaymentDate) {
			        setAppointmentId(PaymentId);
			        setPatientName(PatientName);
			        setDoctorName(dob);
			        setService(Service);
			        setPaymentDate(PaymentDate);
			        setTotalAmt(TotalAmt);
			        setPaymentAmt(PaymentAmt);
			        setDiscountAmt(DiscountAmt);
			    }

				private void setService(String startTime2) {
					// TODO Auto-generated method stub
					Service.set(startTime2);
					
				}

				private void setDoctorName(String doctor2) {
					// TODO Auto-generated method stub
					DoctorName.set(doctor2);
				}

				private void setPatientName(String patient2) {
					// TODO Auto-generated method stub
					PatientName.set(patient2);
				}

				private void setAppointmentId(String applicationID2) {
					// TODO Auto-generated method stub
					AppointmentId.set(applicationID2);
					
				}
				public void setPaymentDate(String status) {
					PaymentDate.set(status);
				}

				public String getPaymentDate() {
					return PaymentDate.get();
				}

				public Payment() {
				        this("", "", "","","","","","");
				    }
				
				public String getAppointmentId() {
					return AppointmentId.get();
				}

				public String getPatientName() {
					return PatientName.get();
				}

				public String getDoctorName() {
					return DoctorName.get();
				}

				public String getService() {
					return Service.get();
				}

				public String getTotalAmt() {
					return TotalAmt.get();
				}

				public void setTotalAmt(String totalAmt) {
					TotalAmt.set(totalAmt);
				}

				public String getPaymentAmt() {
					return PaymentAmt.get();
				}

				public void setPaymentAmt(String paymentAmt) {
					PaymentAmt.set(paymentAmt);
				}

				public String getDiscountAmt() {
					return DiscountAmt.get();
				}

				public void setDiscountAmt(String discountAmt) {
					DiscountAmt.set(discountAmt);
				}
				
		}


	

}
