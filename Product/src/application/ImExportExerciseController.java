package application;

import java.io.File;

import java.io.IOException;

import java.util.Queue;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import Exercises.Exercise;
import Methods.FilesUtil;
import Methods.ReadExercises;
//import Steps.Step;
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
//this class allows the user to utilize a list of exercises
public class ImExportExerciseController {

	@FXML
	private JFXListView<Exercise> lvExercises;

	@FXML
	private JFXButton btSort;

	@FXML
	private JFXButton btEdit;

	@FXML
	private JFXButton btDelete;

	@FXML
	private JFXButton btClear;

	@FXML
	private JFXButton btBack;

	@FXML
	private ChoiceBox<String> cbType;

	@FXML
	private JFXTextArea taExercise;

	@FXML
	private JFXButton btAdd;

	@FXML
	private JFXButton btExport;

	@FXML
	private JFXButton btImport;

	ObservableList<Exercise> exerciseList = FXCollections.observableArrayList();

	ObservableList<String> typeList = FXCollections.observableArrayList();

//	initializes the ImExportController, by adding all the exercises sorted/imported
//	in this application and allows the user to add exercises at three levels 
	@FXML
	private void initialize() throws IOException {
		String[] exType = new String[3];
		exType[0] = "Easy";
		exType[1] = "Medium";
		exType[2] = "Hard";
		for (int i = 0; i < exType.length; i++) {
			typeList.add(exType[i]);
		}
		cbType.setValue("Easy");
		FilesUtil.fileExist("Exercises.txt");
		String content = FilesUtil.readTextFile("Exercises.txt");
		if (content.length() > 0) {
			String[] arr = content.split(";");
			exerciseList.clear();

			for (int i = 0; i < arr.length; i++) {
				String[] arr2 = arr[i].split(":");
				exerciseList.add(new Exercise(arr2[0], arr2[1]));
			}
		}

		cbType.setItems(typeList);
		this.lvExercises.setItems(this.exerciseList);
	}

//	Goes back to the previous window
	@FXML
	void btBack_clicked(ActionEvent event) throws IOException {
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExerciseMain.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("Exercise");
		window.setResizable(false);
		window.centerOnScreen();
		window.setFullScreen(false);
		window.show();
	}

//	Save all the Exercises in a text document, that can be used for other things
	public void save() throws IOException {
		String output = "";

		for (Exercise e : exerciseList) {
			output += e + ";";
		}
//		Writing the String in the specified document
		Methods.FilesUtil.writeToTextFile("Exercises.txt", output);
	}

//	Adds the written exercise in the list of exercises
	@FXML
	void btAdd_clicked(ActionEvent event) throws IOException {
		String result = this.cbType.getSelectionModel().getSelectedItem();
		Exercise e = new Exercise(result, this.taExercise.getText());
		this.exerciseList.add(e);
		this.taExercise.setText("");
//		Sorts the exercises in the list
		sort();
		save();
	}

//	Clears all exercises	
	@FXML
	void btClear_clicked(ActionEvent event) {
		exerciseList.clear();
	}

//	Opens a window that edits the selected exercise
	@FXML
	void btEdit_clicked(ActionEvent event) throws IOException {
		Exercise e = this.lvExercises.getSelectionModel().getSelectedItem();
		if (e == null) {
			return;
		}
		try {
			EditExerciseController.currentExercise = e;
			EditExerciseController.parent = this;

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("EditExercise.fxml"));
			Scene scene = new Scene(root, 570, 206);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setAlwaysOnTop(true);
			primaryStage.setTitle("Editor");
			primaryStage.initModality(Modality.APPLICATION_MODAL);
			primaryStage.setResizable(false);
			primaryStage.setFullScreen(false);
			primaryStage.show();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

//	Deletes the selected exercise
	@FXML
	void btDelete_clicked(ActionEvent event) throws IOException {
		Exercise e = this.lvExercises.getSelectionModel().getSelectedItem();
		this.exerciseList.remove(e);
		save();
	}

//	Exports the exercises as a text document
	@FXML
	void btExport_clicked(ActionEvent event) throws IOException {
		save();
		String output = "";

		for (Exercise e : exerciseList) {
			output += e + ";";
		}

		FileChooser fileName = new FileChooser();
		fileName.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
		fileName.setTitle("Export");
		
		File file = fileName.showSaveDialog(null);	
		UArmClientController client = new UArmClientController();
		client.SaveAs(output, file);

	}

//	Imports the exercises from a text document to the list of exercises
	@FXML
	void btImport_clicked(ActionEvent event) throws IOException {
		FileChooser fileName = new FileChooser();
		fileName.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Document", "*.txt"));
		fileName.setTitle("Import");
		File file = fileName.showOpenDialog(null);

		UArmClientController client = new UArmClientController();

		String content = client.Open(file);
		String[] arr = content.split(";");
		exerciseList.clear();

		for (int i = 0; i < arr.length; i++) {
			String[] arr2 = arr[i].split(":");
			exerciseList.add(new Exercise(arr2[0], arr2[1]));
		}
		save();
	}

	@FXML
	void lvExercises_clicked(MouseEvent event) {

	}

//	Sorts the exercises from Easy, Medium to Hard
	@FXML
	void sort() throws IOException {
		save();
		ReadExercises method = new ReadExercises();
		String content = FilesUtil.readTextFile("Exercises.txt");
//		Creates a Queue from giving a string that will be sorted in the 
//		ReadExercises class located in Methods package then adds the 
//		items one by one, thereby sorting the list
		if (content != null) {
			Queue<String> exercises = method.sortExercise(content);
			exerciseList.clear();
			while (exercises.size() != 0) {
				String type = exercises.remove();
				String ex = exercises.remove();
				exerciseList.add(new Exercise(type, ex));
			}
			save();
		}
	}

//	Refreshes the list
	public void refreshList(Exercise exercise) {
		this.lvExercises.setItems(null);
		this.lvExercises.setItems(this.exerciseList);

	}

}
