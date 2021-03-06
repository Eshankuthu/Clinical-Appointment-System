package fxmlPages;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Date;

import CAS.*;
import Helper.*;

import Ruleset.PatientValidation;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddDoctorController2 implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnConfirmAppointment;

	@FXML
	private Button btnGoBack;
	@FXML
	private Button btnFillForm;

	@FXML
	private Button btnViewAppointments;

	@FXML
	private Label labelCountry;

	@FXML
	private Label labelFirstName;

	@FXML
	private Label labelLastName;

	@FXML
	private Label labelMiddleName;

	@FXML
	private Label labelPassportNo;

	@FXML
	private Label labelSelectDoctor;

	@FXML
	private Label labeldob;

	@FXML
	private Label labelssn;

	@FXML
	private Label personIDlabel;

	@FXML
	private TextField textPatientId;

	@FXML
	private TextField textCountry;

	@FXML
	private TextField textFirstName;

	@FXML
	private TextField textLastName;

	@FXML
	private TextField textMiddleName;

	@FXML
	private TextField textPassportNo;
	@FXML
	private TextField textPersonId;

	@FXML
	private TextField textdob;

	@FXML
	private TextField textssn;
	@FXML
	private ChoiceBox<Choice> ddlDoctors;
	@FXML
	private ChoiceBox<Choice> ddlServices;
	@FXML
	private TextField textAppId;
	private Appointment appObj = null;;

	@FXML
	private Text output;
	PatientValidation patRules = new PatientValidation();

	void initData(Appointment app) {
		appObj = app;
		textAppId.setText(app.getAppointmentId());
		Patient pat = app.getPatient();
		textPatientId.setText(pat.getPatientId());
		textFirstName.setText(pat.getFirstName());
		textMiddleName.setText(pat.getMiddleName());
		textLastName.setText(pat.getLastName());
		textdob.setText(pat.getDob().toString());
		textssn.setText(pat.getSsn());
		textCountry.setText(pat.getCountry());
		textPassportNo.setText(pat.getPassportNo());

		Doctor d = app.getDoc();
		ddlDoctors.getSelectionModel().select(new Choice(d.getDoctorId(), d.getName()));
		btnConfirmAppointment.setText("Update Appointment");

		textPatientId.setEditable(false);
		btnFillForm.setVisible(false);
		textFirstName.setEditable(false);
		textMiddleName.setEditable(false);
		textLastName.setEditable(false);
		textssn.setEditable(false);
		textCountry.setEditable(false);
		textPassportNo.setEditable(false);
	}

	public Patient addPatient() {

		Patient pat;
		String patientId = textPatientId.getText().trim();
		String personId = textPersonId.getText().trim();
		String firstName = textFirstName.getText().trim();
		String middleName = textMiddleName.getText().trim();
		String lastName = textLastName.getText().trim();
		String dob = textdob.getText().trim();
		String ssn = textssn.getText().trim();
		String country = textCountry.getText().trim();
		String passportNo = textPassportNo.getText().trim();
		String description = "tetst";

		pat = Factory.addPatient(personId, firstName, middleName, lastName, dob, ssn, country, passportNo, patientId,
				description);
		return pat;
	}

	@FXML
	void handleFillFormButtonAction(ActionEvent event) {
		if (!textPatientId.getText().isEmpty()) {
			Patient pat = Factory.getPatient(textPatientId.getText().trim());
			textFirstName.setText(pat.getFirstName());
			textMiddleName.setText(pat.getMiddleName());
			textLastName.setText(pat.getLastName());
			textdob.setText(pat.getDob().toString());
			textssn.setText(pat.getSsn());
			textCountry.setText(pat.getCountry());
			textPassportNo.setText(pat.getPassportNo());
		}
	}

	void updateAppointment() {
		String docId = ddlDoctors.getSelectionModel().getSelectedItem().getId();
		Doctor doc = Factory.getDoctor(docId);
		Service serc = Service.getService("1"); // get from dropdown

		Factory.updateAppointment(appObj.getAppointmentId(), appObj.getPatient(), doc, serc, "2017-06-11 10:00:00",
				"2017-06-11 11:00:00");
		resetForm();

	}

	@FXML
	void addAppointmentButtonClick(ActionEvent event) {
		if (appObj != null) {
			updateAppointment();
			return;
		}
		String validation = patRules.validate(textPatientId.getText().trim(), textFirstName.getText().trim(),
				textMiddleName.getText().trim(), textLastName.getText().trim(), textssn.getText().trim(),
				textCountry.getText().trim(), textdob.getText().trim());
		if (!validation.equals("")) {
			Alert alert = new Alert(AlertType.WARNING, validation, ButtonType.OK);
			alert.showAndWait();
			return;
		}

		// set Patient first
		Patient pat;
		String patientId = textPatientId.getText().trim();
		if (patientId != "" && Factory.patientExists(patientId)) {
			pat = Factory.getPatient(textPatientId.getText().trim());
		} else {

			pat = addPatient();
		}

		// get DoctorInfo
		String docId = ddlDoctors.getSelectionModel().getSelectedItem().getId();
		Doctor doc = Factory.getDoctor(docId); // get the value from dropdown
		String serId = ddlDoctors.getSelectionModel().getSelectedItem().getId();
		Service serc = Service.getService(serId); // get from dropdown

		// Create appointment
		Factory.addAppointment(pat, doc, serc, "2017-06-11 10:00:00", "2017-06-11 11:00:00");
		resetForm();

	}

	@FXML
	void handleGoBackButtonAction(ActionEvent event) {
	}

	@FXML
	void handleViewAppointmentButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("ViewAppointment.fxml"));
			stage.setTitle("Clinic Assistance");
			Scene scene = new Scene(root, 1400, 700);
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Platform.setImplicitExit(true);
	}

	@FXML
	void initialize() {
	}

	public AddDoctorController2() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Choice> choices = FXCollections.observableArrayList();
		for (Doctor d : Factory.getDoctorsList()) {
			choices.add(new Choice(d.getDoctorId(), d.getName()));
		}
		ddlDoctors.setItems(choices);
		ddlDoctors.getSelectionModel().select(0);
		ObservableList<Choice> choices1 = FXCollections.observableArrayList();
		for (Service s : Factory.getServiceList()) {
			choices1.add(new Choice(s.getServiceId(), s.getServiceName()));
		}
		ddlServices.setItems(choices1);
		ddlServices.getSelectionModel().select(0);

	}

	public void resetForm() {
		textPersonId.setText("");
		textFirstName.setText("");
		textMiddleName.setText("");
		textLastName.setText("");
		textdob.setText("");
		textssn.setText("");
		textCountry.setText("");
		textPassportNo.setText("");
		textPatientId.setText("");
	}

}
