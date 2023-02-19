package application;

import java.io.IOException;
import com.fazecast.jSerialComm.SerialPort;
import com.jfoenix.controls.JFXButton;
import Methods.ReadExercises;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jssc.SerialPortException;

//this class is used to connect with the uArm
public class ChoosePortController {

	public static String ChoosenPort;
	@FXML
	private ChoiceBox<String> ports;

	ObservableList<String> portList = FXCollections.observableArrayList();

	@FXML
	private JFXButton btConnect;

	@FXML
	private JFXButton btBack;

//	Initializes the ChoosePortController by getting all the ports
//	that are connected to the computer, the uArm
	@FXML
	private void initialize() throws SerialPortException {
		SerialPort[] portNames = SerialPort.getCommPorts();
		for (int i = 0; i < portNames.length; i++)
			this.portList.add(portNames[i].getSystemPortName());

		ports.setItems(portList);
	}

//	Goes back to the previous window
	@FXML
	void btBack_clicked(ActionEvent event) throws IOException {
//		Checks if the level of difficulty has been chosen, if it has then goes back to the Exercise chooser,
//		as that means that the level was changed in the Exercise chooser
		String gui = "Main.fxml";
		String title = "Main";
		int level = ReadExercises.getLevel();
		if (level == 1 || level == 2 || level == 3) {
			ReadExercises.setLevel(0);
			gui = "ExerciseChooser.fxml";
			title = "Choosing Level of Difficulty";
		}
		Parent tableViewParent = FXMLLoader.load(getClass().getResource(gui));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.centerOnScreen();
		window.setTitle(title);
		window.show();
	}

	public static UArm uarm;

//	Try to connect with the UArm, calling a method in Uarm.java and using public variables
	@FXML
	void btConnect_clicked(ActionEvent event) throws SerialPortException, IOException {
//		Checks if the user has chosen the port that should be connected with the Arm, if not,
//		then an error would appear
		if (getPort() == null) {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Error.fxml"));
			Scene scene = new Scene(root, 525, 155);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle("ERROR");
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} else {
			ChoosenPort = getPort();
			uarm = new UArm(ChoosenPort);
			uarm.setPort();
			String gui = "UarmClient.fxml";
			String title = "uArm Client";
//			Looks at if the exercise level already stated is at which level and then opens the 
//			completing exercises window	or the uArm Client
			int level = ReadExercises.getLevel();
			if (level == 1 || level == 2 || level == 3) {
				gui = "UarmClientExercise.fxml";
				title = "Completing Exercises";
			}
			Parent tableViewParent = FXMLLoader.load(getClass().getResource(gui));
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

			window.setScene(tableViewScene);
			window.centerOnScreen();
			window.setTitle(title);
			window.show();
		}
	}

//	gets the string, port that the user selected
	public String getPort() {
		String result = this.ports.getSelectionModel().getSelectedItem();
		return result;
	}

}
