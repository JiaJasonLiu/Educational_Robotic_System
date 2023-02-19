package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import Methods.FilesUtil;
import Methods.ReadExercises;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jssc.SerialPortException;

//this class is to complete exercises with the arm, using the
//UArmClientController as its base
public class UArmClientExerciseController extends UArmClientController {

	@FXML
	private JFXTextArea taExercise;

	@FXML
	private JFXButton bt_Back;

	@FXML
	private JFXButton bt_Next;

	@FXML
	private VBox bpClient;

//	Initializing the controller to complete exercises in, which would
//	present the exercise at the level chosen
	@FXML
	private void initialize() throws IOException, SerialPortException {

		FilesUtil.fileExist("Exercises.txt");

		ReadExercises e = new ReadExercises();

		taExercise.setFont(new Font(20));
//		checks the level of the exercises chosen and gets the level of exercises
		int level = ReadExercises.getLevel();
		if (level == 1) {
			taExercise.setText(e.getEasyExercises());
		} else if (level == 2) {
			taExercise.setText(e.getMediumExercises());
		} else if (level == 3) {
			taExercise.setText(e.getHardExercises());
		}
//		Adds the UarmClient window below the exercises
		BorderPane temp = FXMLLoader.load(getClass().getResource("UarmClient.fxml"));
		bpClient.getChildren().add(temp);
	}

//	Goes back to the previous window
	@FXML
	void btBack_Clicked(ActionEvent event) throws IOException, SerialPortException {
//		Reduces the question number, to go back to the previous question or previous window
		ReadExercises.decreaseQuesNum();
		if (ReadExercises.getQuesNum() == -1) {
			ReadExercises.setLevel(0);
			ReadExercises.resetQuesNum();
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExerciseChooser.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("Choosing Level of Difficulty");
			window.centerOnScreen();
			window.show();
			uarm.serialPort.closePort();
		} else {
			Parent tableViewParent = FXMLLoader.load(getClass().getResource("UarmClientExercise.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("Completing Exercises");
			window.centerOnScreen();
			window.show();
		}
	}
	
//	Goes to the next exercise, until there aren't anymore
	@FXML
	void btNext_Clicked(ActionEvent event) throws IOException {
		ReadExercises.increaseQuesNum();
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("UarmClientExercise.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);
		
		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("Completing Exercises");
		window.show();
	}
}
