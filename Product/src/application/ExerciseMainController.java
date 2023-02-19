package application;

import java.io.IOException;

//import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXButton;

//import Exercises.Exercise;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//this class is to choose to view the list of exercises
//or complete exercises
public class ExerciseMainController {

	@FXML
	private JFXButton btTry;

	@FXML
	private JFXButton btImExport;

	@FXML
	private JFXButton btBack;

//	Goes back to the previous window, Practice with UArm/Start Exercises/Help
	@FXML
	void btBack_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(tableViewScene);
		primaryStage.setTitle("Main");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}

//	Opens a window that allows the user to add/edit/delete/export/import exercises
	@FXML
	void btImExport_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ImportExport.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.centerOnScreen();
		window.setTitle("Import or Export Exercises");
		window.show();
	}

//	Opens a window that asks the level of exercises the user wants to try
	@FXML
	void btTry_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExerciseChooser.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("Choosing Level of Difficulty");
		window.show();
	}

}
