package fxmlPages;


import java.awt.Choice;
import java.util.List;

import CAS.Doctor;
import CAS.Factory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class CAHome extends Application{

	Stage primaryStage;
	List<Doctor> lstDr;
	
	public static void main(String[] args) {
		Application.launch(CAHome.class, args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("CaHome.fxml"));
		primaryStage.setTitle("CA Home");
		Scene scene =new Scene(root, 1400, 700);
		scene.getStylesheets().addAll(this.getClass().getResource("Login.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		lstDr = Factory.getDoctorsList();
		ObservableList<Choice> choices = FXCollections.observableArrayList();
	    choices.add(new Choice());
	    for (Doctor dr : lstDr) {
	      choices.add(new Choice());
	    }
	    final ChoiceBox<Choice> chooser = new ChoiceBox<>(choices);
	    chooser.getSelectionModel().select(0);
	    
	}

}
