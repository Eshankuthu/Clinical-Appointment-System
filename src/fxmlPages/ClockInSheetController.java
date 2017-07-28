package fxmlPages;

import CAS.Appointment;
import CAS.Factory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ClockInSheetController {
	@FXML
	private Button btn;

	@FXML
	protected void handleButtonAction(ActionEvent event) {
		Appointment app = Factory.getAppointment("1");
		showAppointmentDialog(app);
	}
	@FXML
	protected void handlecreateNewAppointmentButtonAction(ActionEvent event) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("CreateAppointment.fxml"));
			stage.setTitle("Clinic Assistance");
			Scene scene = new Scene(root, 1400, 700);
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Platform.setImplicitExit(true);
	}
	
	public Stage showAppointmentDialog(Appointment app) {
		Stage stage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateAppointment.fxml"));	
			Scene scene =new Scene((Pane) loader.load());
			scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());

			stage.setScene(scene);
			CreateAppointmentController controller = loader.<CreateAppointmentController> getController();
			controller.initData(app);
			loader.setController(controller);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stage;
	}
}
