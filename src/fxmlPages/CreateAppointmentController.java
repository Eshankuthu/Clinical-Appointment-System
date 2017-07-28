package fxmlPages;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Date;

import CAS.*;
import Helper.*;
import Ruleset.AppointmentValidation;
import Ruleset.PatientValidation;
import javafx.scene.text.Text;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CreateAppointmentController implements Initializable {

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
	private DatePicker dobDatePicker;

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
	private DatePicker appointmentDatePicker;

	@FXML
	private Label labelSelectStartTime;

	@FXML
	private ChoiceBox<Choice> ddlStartTime;

	@FXML
	private Label labelSelectEndTime;

	@FXML
	private ChoiceBox<Choice> ddlEndTime;

	@FXML
	private Label labelDescription;

	@FXML
	private TextArea txtAreaDescription;

	@FXML
	private Label labelServiceType;

	@FXML
	private ChoiceBox<Choice> ddlServiceType;

	PatientValidation patRules = new PatientValidation();
	AppointmentValidation appRules = new AppointmentValidation();

	void initData(Appointment app) {
		appObj = app;
		// textAppId.setText(app.getAppointmentId());
		Patient pat = app.getPatient();
		textPatientId.setText(pat.getPatientId());
		textFirstName.setText(pat.getFirstName());
		textMiddleName.setText(pat.getMiddleName());
		textLastName.setText(pat.getLastName());
		dobDatePicker.setValue(LocalDate.parse(pat.getDob().toString()));
		appointmentDatePicker.setValue(app.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		DateFormat sdf = new SimpleDateFormat("HH:mm");

		ddlStartTime.getSelectionModel().select(new Choice(sdf.format(app.getStart())));
		ddlEndTime.getSelectionModel().select(new Choice(sdf.format(app.getEnd())));

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
		String dob = dobDatePicker.getValue().toString();
		String ssn = textssn.getText().trim();
		String country = textCountry.getText().trim();
		String passportNo = textPassportNo.getText().trim();
		String description = txtAreaDescription.getText().trim().replace("'", "\'");

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
			dobDatePicker.setValue(LocalDate.parse(pat.getDob().toString()));
			textssn.setText(pat.getSsn());
			textCountry.setText(pat.getCountry());
			textPassportNo.setText(pat.getPassportNo());
			txtAreaDescription.setText(pat.getDescription());
		}
	}

	void updateAppointment() {

		String docId = ddlDoctors.getSelectionModel().getSelectedItem().getId();
		Doctor doc = Factory.getDoctor(docId);
		String serId = ddlDoctors.getSelectionModel().getSelectedItem().getId();
		Service serc = Service.getService(serId);
		String start = appointmentDatePicker.getValue().toString() + " "
				+ ddlStartTime.getSelectionModel().getSelectedItem().toString();
		String end = appointmentDatePicker.getValue().toString() + " "
				+ ddlEndTime.getSelectionModel().getSelectedItem().toString();
		String validation = appRules.validate(appObj.getAppointmentId(), docId, start, end);
		if (!validation.equals("")) {
			Alert alert = new Alert(AlertType.WARNING, validation, ButtonType.OK);
			alert.showAndWait();
			return;
		}
		Factory.updateAppointment(appObj.getAppointmentId(), appObj.getPatient(), doc, serc, start, end);
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
				textCountry.getText().trim(), dobDatePicker.getValue().toString());
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
		String start = appointmentDatePicker.getValue().toString() + " "
				+ ddlStartTime.getSelectionModel().getSelectedItem().toString();
		String end = appointmentDatePicker.getValue().toString() + " "
				+ ddlEndTime.getSelectionModel().getSelectedItem().toString();

		// Create appointment
		Factory.addAppointment(pat, doc, serc, start, end);
		resetForm();

	}

	@FXML
	void handleGoBackButtonAction(ActionEvent event) {
		Stage stage = (Stage) btnGoBack.getScene().getWindow();
		stage.close();
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

	@FXML
	private TableView<Appointment> docAppointmentTableView;
	@FXML
	private TableColumn<Appointment, String> appDocCol;
	@FXML
	private TableColumn<Appointment, String> AppCol;

	public void reloadDocAppointments(String docId) {
		appDocCol.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getDoc().getName()));
		AppCol.setCellValueFactory(a -> new SimpleStringProperty(
				(new SimpleDateFormat("MM/dd/yyyy HH:mm")).format(a.getValue().getStart())+"-"+(new SimpleDateFormat("HH:mm")).format(a.getValue().getEnd())));

		docAppointmentTableView.getItems().setAll(Factory.getDoctorAppointments(docId));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ObservableList<Choice> choices = FXCollections.observableArrayList();
		for (Doctor d : Factory.getDoctorsList()) {
			choices.add(new Choice(d.getDoctorId(), d.getName()));
		}
		ddlDoctors.setItems(choices);
		ddlDoctors.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				reloadDocAppointments(newSelection.getId());
			}
		});
		ddlDoctors.getSelectionModel().select(0);


		ObservableList<Choice> choices1 = FXCollections.observableArrayList();
		for (Service s : Factory.getServiceList()) {
			choices1.add(new Choice(s.getServiceId(), s.getServiceName()));
		}
		ddlServices.setItems(choices1);
		ddlServices.getSelectionModel().select(0);

		ObservableList<Choice> choicesTime = FXCollections.observableArrayList();
		choicesTime.addAll(new Choice("10:00"), new Choice("10:30"), new Choice("11:00"), new Choice("11:30"),
				new Choice("12:00"), new Choice("12:30"), new Choice("13:00"), new Choice("13:30"), new Choice("14:00"),
				new Choice("14:30"), new Choice("15:00"), new Choice("15:30"), new Choice("16:30"));
		ddlStartTime.setItems(choicesTime);
		ddlStartTime.getSelectionModel().select(0);
		ddlEndTime.setItems(choicesTime);
		ddlEndTime.getSelectionModel().select(0);

	}

	public void resetForm() {
		Stage stage = (Stage) btnGoBack.getScene().getWindow();
		stage.close();
	}

}
