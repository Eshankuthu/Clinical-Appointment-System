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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PaymentFormController implements Initializable {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button MakePayment;

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
	private TextField AppointmentId;

	@FXML
	private TextField PatientName;

	@FXML
	private TextField DoctorName;

	@FXML
	private TextField Service;

	@FXML
	private TextField Amount;

	@FXML
	private TextField DiscountAmt;
	@FXML
	private TextField TotalAmt;

	public Payment addPayment() {

		Payment pay;
		String appointmentId = AppointmentId.getText().trim();
		String patientName = PatientName.getText().trim();
		String doctorName = DoctorName.getText().trim();
		String service = Service.getText().trim();
		String amount = Amount.getText().trim();
		String discount = DiscountAmt.getText().trim();
		String total = TotalAmt.getText().trim();

		pay = Factory.addPayment(appointmentId, total, discount, amount, "payed by " + patientName);
		return pay;
	}

	@FXML
	void handlemakePaymentButtonAction(ActionEvent event) {

		// set Payment
		Payment pat;
		pat = addPayment();
		Stage stage = (Stage) DiscountAmt.getScene().getWindow();
		stage.close();

	}

	public PaymentFormController() {
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	Appointment appObj = null;

	void initData(Appointment app) {
		appObj = app;
		AppointmentId.setText(app.getAppointmentId());
		PatientName.setText(app.getPatient().getName());
		DoctorName.setText(app.getDoc().getName());
		Service.setText(app.getSerc().getServiceName());
		Amount.setText(String.valueOf(app.getSerc().getCost()));
		DiscountAmt.setText("0.0");
		TotalAmt.setText(String.valueOf(app.getSerc().getCost()));
	}

}
