package fxmlPages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class NewUser extends Application{

	public static void main(String[] args) {
		Application.launch(HomePage.class, args);
	}
	@Override
	public void start(Stage stage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("NewUser.fxml"));
		stage.setTitle("Clinic Assistance");
		stage.setScene(new Scene(root, 1400, 700));
		stage.show();
	}
}
