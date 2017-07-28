package fxmlPages;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.List;

import CAS.Appointment;
import CAS.AppointmentStatus;
import CAS.Factory;

public class CaHomeController {

	@FXML
	private Text actiontarget;
	@FXML
	private PasswordField passwordField;
	@FXML
	private TextField uname;

	@FXML
	protected void handlecreateNewAppointmentButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CreateAppointment.fxml"));
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
	protected void handleviewAppointmentAction(ActionEvent event) {
		RefreshAppointment();
	}
	
	@FXML
	protected void handleBookedListAction(ActionEvent event) {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getBookedAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();
	}

	@FXML
	protected void handleClockInSheetAction(ActionEvent event) {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getInAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();
	}

	@FXML
	protected void handleClockOutSheetAction(ActionEvent event) {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getOutAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();

	}

	@FXML
	protected void handleListPaidAction(ActionEvent event) {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getPaidAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();

	}

	@FXML
	protected void handleListCancelledAction(ActionEvent event) {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getCancelledAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();

	}

	void RefreshAppointment() {
		appList.clear();
		AppointmentTableView.getItems().setAll(appList);
		appList.addAll(Factory.getAppointments());
		AppointmentTableView.getItems().addAll(appList);
		AppointmentTableView.getSelectionModel().selectFirst();
	}

	@FXML
	protected void handleCancelAppointmentAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, " Are you sure you want to cancel Appointment? ",
				ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Appointment appointment = AppointmentTableView.getSelectionModel().getSelectedItem();
			Factory.cancelAppointment(appointment);
		}
		RefreshAppointment();
	}
	@FXML
	protected void handleUpdateAction(ActionEvent event) {
		Appointment appointment = AppointmentTableView.getSelectionModel().getSelectedItem();

		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAppointment.fxml"));	
			Scene scene =new Scene((Pane) loader.load());
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());

			stage.setScene(scene);
			CreateAppointmentController controller = loader.<CreateAppointmentController> getController();
			controller.initData(appointment);
			loader.setController(controller);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		RefreshAppointment();
	}
	@FXML
	protected void handlePaymentAction(ActionEvent event) {
		Appointment appointment = AppointmentTableView.getSelectionModel().getSelectedItem();

		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("PaymentForm.fxml"));	
			Scene scene =new Scene((Pane) loader.load());
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
			
			stage.setScene(scene);
			PaymentFormController controller = loader.<PaymentFormController> getController();
			controller.initData(appointment);
			loader.setController(controller);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void handleClockInAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, "Clock In Appointment? ", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Appointment appointment = AppointmentTableView.getSelectionModel().getSelectedItem();
			Factory.clockIn(appointment);
		}
		RefreshAppointment();
	}

	@FXML
	protected void handleClockOutAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION, " Clock Out Appointment? ", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
			Appointment appointment = AppointmentTableView.getSelectionModel().getSelectedItem();
			Factory.clockOut(appointment);
		}
		RefreshAppointment();
	}

	@FXML
	private TableView<Appointment> AppointmentTableView;
	@FXML
	private TableColumn<Appointment, String> AppointmentID;
	@FXML
	private TableColumn<Appointment, String> patCol;
	@FXML
	private TableColumn<Appointment, String> Doctor;
	@FXML
	private TableColumn<Appointment, String> StartTime;
	@FXML
	private TableColumn<Appointment, String> EndTime;
	@FXML
	private TableColumn<Appointment, String> Status;

	private ObservableList<Appointment> appList;

	@FXML
	void initialize() {
		AppointmentID.setCellValueFactory(new PropertyValueFactory<Appointment, String>("appointmentId"));
		patCol.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getPatient().getName()));
		Doctor.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getDoc().getName()));
		StartTime.setCellValueFactory(a -> new SimpleStringProperty(
				(new SimpleDateFormat("MM/dd/yyyy HH:mm")).format(a.getValue().getStart())));
		EndTime.setCellValueFactory(a -> new SimpleStringProperty(
				(new SimpleDateFormat("MM/dd/yyyy HH:mm")).format(a.getValue().getEnd())));
		Status.setCellValueFactory(a -> new SimpleStringProperty(a.getValue().getStatus().toString()));
		appList = Factory.getAppointments();
		AppointmentTableView.getItems().setAll(appList);

		AppointmentTableView.getSelectionModel().selectedItemProperty()
				.addListener((obs, oldSelection, newSelection) -> {
					if (newSelection != null) {
						RefreshButton(newSelection);
					}
				});
		AppointmentTableView.getSelectionModel().selectFirst();
	}

	@FXML
	private Button btnUpdateApp;
	@FXML
	private Button btnClockIn;
	@FXML
	private Button btnClockOut;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnPay;

	void deactivateButton() {
		btnUpdateApp.setDisable(true);
		btnClockIn.setDisable(true);
		btnClockOut.setDisable(true);
		btnCancel.setDisable(true);
		btnPay.setDisable(true);
	}

	void RefreshButton(Appointment app) {
		deactivateButton();
		if (app.getStatus().equals(AppointmentStatus.BOOKED)) {
			btnUpdateApp.setDisable(false);
			btnClockIn.setDisable(false);
			btnCancel.setDisable(false);
		} else if (app.getStatus().equals(AppointmentStatus.ONGOING)) {
			btnClockOut.setDisable(false);
		} else if (app.getStatus().equals(AppointmentStatus.COMPLETED)) {
			btnPay.setDisable(false);
		}

	}
	@FXML
	protected void handleDoctorListButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("DoctorList.fxml"));
			stage.setTitle("Doctor List");
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
	protected void handlePatientListButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("PatientList.fxml"));
			stage.setTitle("Patient List");
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
	protected void handlepaymentListButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("PaymentList.fxml"));
			stage.setTitle("Payments");
			Scene scene = new Scene(root, 1400, 700);
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Platform.setImplicitExit(true);
	}
}
