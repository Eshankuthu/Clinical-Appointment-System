package fxmlPages;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClockOutSheet extends Application {


	Stage primaryStage;

	public static void main(String[] args) {
		Application.launch(CreateAppointment.class, args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("ClockOutSheet.fxml"));
		primaryStage.setTitle("Clock Out Sheet");
		Scene scene =new Scene(root, 1400, 700);
		scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}