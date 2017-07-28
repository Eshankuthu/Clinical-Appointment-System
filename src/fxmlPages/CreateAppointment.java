package fxmlPages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateAppointment extends Application {
	Stage primaryStage;

	public static void main(String[] args) {
		Application.launch(CreateAppointment.class, args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("CreateAppointment.fxml"));
		primaryStage.setTitle("Create Appointment");
		Scene scene =new Scene(root, 1400, 700);
		scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}


}
