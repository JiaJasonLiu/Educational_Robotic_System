package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import Methods.FilesUtil;
import Methods.ReadExercises;
import Methods.SaveOpenInterface;
import Steps.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jssc.SerialPortException;
//this class is to play with the arm, the screen to allow 
//communication between the arm and the user
public class UArmClientController extends ChoosePortController implements SaveOpenInterface {

	@FXML
	private JFXButton btEdit;

	@FXML
	private JFXButton btDelete;

	@FXML
	private JFXButton btClear;

	@FXML
	private JFXTextField tfLeft;

	@FXML
	private JFXButton btAddL;

	@FXML
	private JFXTextField tfRight;

	@FXML
	private JFXButton btAddR;

	@FXML
	private JFXTextField tfFront;

	@FXML
	private JFXButton btAddF;

	@FXML
	private JFXTextField tfBack;

	@FXML
	private JFXButton btAddB;

	@FXML
	private JFXTextField tfUp;

	@FXML
	private JFXButton btAddU;

	@FXML
	private JFXTextField tfDown;

	@FXML
	private JFXButton btAddD;

	@FXML
	private JFXButton btReset;

	@FXML
	private JFXButton btGrab;

	@FXML
	private JFXButton btSaveAs;

	@FXML
	private JFXButton btOpen;

	@FXML
	private JFXButton btHelp;

	@FXML
	private JFXButton btHome;

	@FXML
	private JFXButton btRun;

	@FXML
	private JFXButton btEmail;

	@FXML
	private ChoiceBox<String> ports;

	@FXML
	private JFXListView<Step> lvSteps;

	ObservableList<Step> stepList = FXCollections.observableArrayList();

//	initializes the UArmClientController, by adding all the steps executed
//	by the application to the list
	@FXML
	private void initialize() throws SerialPortException, IOException {
		FilesUtil.fileExist("StepsList.txt");

		String content = FilesUtil.readTextFile("StepsList.txt");
		if (content.length() > 0) {
			String[] arr = content.split(";");
			stepList.clear();
			for (int i = 0; i < arr.length; i++) {
				String[] arr2 = arr[i].split(":");
				stepList.add(new Step(arr2[0], Integer.parseInt(arr2[1])));
			}
		}
		this.lvSteps.setItems(this.stepList);
	}

	@FXML
	void lvSteps_clicked(MouseEvent event) {
	}

//	Adds value of going Back to the list
	@FXML
	void btAddB_clicked(ActionEvent event) {
		Step s = new Step("Back", Integer.parseInt(this.tfBack.getText()));
		this.stepList.add(s);
		this.tfBack.setText("");
	}

//	Adds value of going Left to the list
	@FXML
	void btAddL_clicked(ActionEvent event) {
		Step s = new Step("Left", Integer.parseInt(this.tfLeft.getText()));
		this.stepList.add(s);
		this.tfLeft.setText("");
	}

//	Adds value of going Right to the list
	@FXML
	void btAddR_clicked(ActionEvent event) {
		Step s = new Step("Right", Integer.parseInt(this.tfRight.getText()));
		this.stepList.add(s);
		this.tfRight.setText("");
	}

//	Adds value of going Front to the list
	@FXML
	void btAddF_clicked(ActionEvent event) {
		Step s = new Step("Front", Integer.parseInt(this.tfFront.getText()));
		this.stepList.add(s);
		this.tfFront.setText("");
	}

//	Adds value of going Up to the list
	@FXML
	void btAddU_clicked(ActionEvent event) {
		Step s = new Step("Up", Integer.parseInt(this.tfUp.getText()));
		this.stepList.add(s);
		this.tfUp.setText("");
	}

//	Adds value of going Down to the list
	@FXML
	void btAddD_clicked(ActionEvent event) throws Exception {
		Step s = new Step("Down", Integer.parseInt(this.tfDown.getText()));
		this.stepList.add(s);
		this.tfDown.setText("");
	}

//	Clears the list
	@FXML
	void btClear_clicked(ActionEvent event) {
		stepList.clear();
	}

//	Deletes the selected item from list
	@FXML
	void btDelete_clicked(ActionEvent event) {
		Step s = this.lvSteps.getSelectionModel().getSelectedItem();
		this.stepList.remove(s);
	}

//	Edits the selected item from list
	@FXML
	void btEdit_clicked(ActionEvent event) throws IOException {
		Step s = this.lvSteps.getSelectionModel().getSelectedItem();
		if (s == null) {
			return;
		}
		try {
			EditStepController.currentStep = s;
			EditStepController.parent = this;

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("EditStep.fxml"));
			Scene scene = new Scene(root, 346, 177);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setTitle("Editor");
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		save();
	}

//	Opens a window with information that the user might need
	@FXML
	void btHelp_clicked(ActionEvent event) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("HelpClient.fxml"));
			Scene scene = new Scene(root, 858, 519);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setTitle("Help");
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	Makes the Arm go back to its original state
	@FXML
	void btReset_clicked(ActionEvent event) throws Exception {
		uarm.goToPosition(1, 140, 145);
	}

//	Makes the Uarm Move, by calling the Run class
	@FXML
	void btRun_clicked(ActionEvent event) throws Exception {
		save();
		Run run = new Run(uarm);
		run.getClass();
	}

//	Changes the state of the pump
	@FXML
	void btGrab_clicked(ActionEvent event) throws Exception {
		int pump = 0;

		if (btGrab.getText().equals("Grab")) {
			pump = 1;
			btGrab.setText("UnGrab");
		} else if (btGrab.getText().equals("UnGrab")) {
			pump = 0;
			btGrab.setText("Grab");
		}
		try {
			uarm.pump(pump);
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Opens a text document file that the user chooses
	@FXML
	void btOpen_clicked(ActionEvent event) throws IOException {
		FileChooser fileName = new FileChooser();
		fileName.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
		fileName.setTitle("Open");
		File file = fileName.showOpenDialog(null);
		String content = Open(file);
		String[] arr = content.split(";");
		stepList.clear();

		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split(":");
			stepList.add(new Step(arr2[0], Integer.parseInt(arr2[1])));

		}
	}

//	Saves the StepsList text document in the location the user chooses
	@FXML
	void btSaveAs_clicked(ActionEvent event) throws IOException {
		String output = "";

		for (Step s : stepList) {
			output += s + ";";
		}

		FileChooser fileName = new FileChooser();
		fileName.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
		fileName.setTitle("Save As");
		
		File file = fileName.showSaveDialog(null);
		SaveAs(output, file);
		save();
	}

//	Refreshes the list
	public void refreshList(Step step) {
		this.lvSteps.setItems(null);
		this.lvSteps.setItems(this.stepList);
	}

//	Goes back to the main window
	@FXML
	void btHome_clicked(ActionEvent event) throws IOException {
			ReadExercises.setLevel(0);
			ReadExercises.resetQuesNum();

			Parent tableViewParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene tableViewScene = new Scene(tableViewParent);
			tableViewScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("Main");
			window.centerOnScreen();
			window.show();
		try {
			uarm.serialPort.closePort();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	Saves the information as a string in the StepsList file
	@FXML
	void save() throws IOException {
		String output = "";

		for (Step s : stepList) {
			output += s + ";";
		}
		// this.btSaveAs
		Methods.FilesUtil.writeToTextFile("StepsList.txt", output);
	}

// 	Inputs the information from the string into the file, using the Interface, SaveOpenInterface
	@Override
	public void SaveAs(String output, File file) {
		if (file != null) {
			try {
				@SuppressWarnings("resource")
				PrintStream printS = new PrintStream(file);
				printS.println(output);
				printS.flush();
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			}
		}
	}

// 	Outputs the information from the string into the file, using the Interface, SaveOpenInterface
	@Override
	public String Open(File file) throws IOException {
		if (file != null) {
			InputStream is = new FileInputStream(file);
			@SuppressWarnings("resource")
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("");
				line = buf.readLine();
			}
//			Close the input file
			buf.close();

			String fileAsString = sb.toString();
			return fileAsString;
		}
		return null;
	}

//	Open an email window, that allows the user to send their information of the application
	@FXML
	void btEmail_clicked(ActionEvent event) throws IOException {
		BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Email.fxml"));
		Scene scene = new Scene(root, 466, 214);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Emailing");
		primaryStage.setResizable(false);
		primaryStage.setFullScreen(false);
		primaryStage.show();
	}

}
