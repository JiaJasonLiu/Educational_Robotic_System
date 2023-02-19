package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//this class is the main screen, to allow user to 
//choose to do exercises or play with the uArm
public class MainController {

	@FXML
	private JFXButton btPlay;

	@FXML
	private JFXButton btExercise;

	@FXML
	private JFXButton btHelp;

//	Opens a window that allows the user to try the exercises or import and export the exercises 
	@FXML
	void btExercise_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExerciseMain.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("Exercises");
		window.centerOnScreen();
		window.setResizable(false);
		window.setFullScreen(false);
		window.show();
	}

//	Opens a window that informs the user what are the overall aspects of this application
	@FXML
	void btHelp_clicked(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("HelpMain.fxml"));
		Scene scene = new Scene(root, 525, 297);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.setAlwaysOnTop(true);
		primaryStage.setTitle("Help");
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.show();

	}

//	Opens a window that asks the user to choose the port of the UArm and then can start playing
//	the UArm
	@FXML
	void btPlay_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ChoosePort.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("Connecting");
		window.setResizable(false);
		window.centerOnScreen();
		window.setFullScreen(false);
		window.show();

	}
}
