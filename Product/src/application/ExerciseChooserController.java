package application;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import Methods.ReadExercises;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
//this class is to choose level of difficulty
public class ExerciseChooserController {

	@FXML
	private JFXButton btEasy;

	@FXML
	private JFXButton btMedium;

	@FXML
	private JFXButton btHard;

	@FXML
	private JFXButton btBack;

//	Goes back to the previous window, trying exercises/Export or Import Exercises/Back
	@FXML
	void btBack_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExerciseMain.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(tableViewScene);
		primaryStage.setTitle("Exercises");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}

//	Opens a window that asks the user to choose the port of the UArm and then can start doing
//	exercises at a Easy level, with the UArm
	@FXML
	void btEasy_clicked(ActionEvent event) throws IOException {
//		Change the level of exercise to 1, that is used by the ChoosePort
		ReadExercises.setLevel(1);
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ChoosePort.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(tableViewScene);
		primaryStage.setTitle("Connecting");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}

//	Opens a window that asks the user to choose the port of the UArm and then can start doing
//	exercises at a Medium level, with the UArm
	@FXML
	void btMedium_clicked(ActionEvent event) throws IOException {
//		Change the level of exercise to 2, that is used by the ChoosePort
		ReadExercises.setLevel(2);
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ChoosePort.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(tableViewScene);
		primaryStage.setTitle("Connecting");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}

//	Opens a window that asks the user to choose the port of the UArm and then can start doing
//	exercises at a Hard level, with the UArm
	@FXML
	void btHard_clicked(ActionEvent event) throws IOException {
//		Change the level of exercise to 3, that is used by the ChoosePort
		ReadExercises.setLevel(3);
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ChoosePort.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setScene(tableViewScene);
		primaryStage.setTitle("Connecting");
		primaryStage.centerOnScreen();
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}
}
