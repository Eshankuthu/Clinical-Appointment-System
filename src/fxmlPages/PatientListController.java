package fxmlPages;
import java.net.URL;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import CAS.Appointment;
import CAS.Factory;
import CAS.Patient;
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

public class PatientListController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;


	@FXML private TableView<Patient> PatientTableView ;
	@FXML private TableColumn<Patient, String> PatientID;
	@FXML private TableColumn<Patient, String> PatientName;
	@FXML private TableColumn<Patient, String> dob;
	@FXML private TableColumn<Patient, String> country ;
	@FXML private TableColumn<Patient, String> passNo ;


	@FXML
	void initialize() {
		PatientID.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getPatientId()));
		PatientName.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getName()));
		dob.setCellValueFactory(new PropertyValueFactory<Patient,String>("dob"));
		country.setCellValueFactory(new PropertyValueFactory<Patient,String>("country"));
		passNo.setCellValueFactory(p->new SimpleStringProperty(p.getValue().getPassportNo()));

		PatientTableView.getItems().setAll(Factory.getPatients());
	}
}
