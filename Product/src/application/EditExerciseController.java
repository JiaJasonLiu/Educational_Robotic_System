package application;

import java.io.IOException;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;


import Exercises.Exercise;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
//import Steps.Step;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
//this class is to edit exercises
public class EditExerciseController {

	public static Exercise currentExercise;
	public static ImExportExerciseController parent;

	@FXML
	private ChoiceBox<String> cbType;

	ObservableList<String> typeList = FXCollections.observableArrayList();

	@FXML
	private JFXTextArea taExercise;

	@FXML
	private JFXButton btSave;

	@FXML
	private JFXButton btCancel;

	@FXML
	void initialize() {
		String[] exType = new String[3];
		exType[0] = "Easy";
		exType[1] = "Medium";
		exType[2] = "Hard";
		for (int i = 0; i < exType.length; i++) {
			typeList.add(exType[i]);
		}
		
		for (int i = 0; i < exType.length; i++) {
	    	if (EditExerciseController.currentExercise.getTypeEx().equals(exType[i])) {
	    		cbType.setValue(exType[i]);
	    	}
			}
		cbType.setItems(typeList);
		this.taExercise.setText("" + EditExerciseController.currentExercise.getExercise());
	}
	
//	Save the information in the list in the Importing and Exporting Exercises
	@FXML
	void btSave_clicked(ActionEvent event) throws IOException {
		EditExerciseController.currentExercise.setTypeEx(this.cbType.getSelectionModel().getSelectedItem());
		EditExerciseController.currentExercise.setExercise(this.taExercise.getText());
		EditExerciseController.parent.refreshList(currentExercise);
		Stage stage = (Stage) this.btSave.getScene().getWindow();
		stage.close();
		EditExerciseController.parent.sort();
	}

//	Cancels any changes made and then goes back to the UarmClient
	@FXML
	void btCancel_clicked(ActionEvent event) {
		Stage stage = (Stage) this.btCancel.getScene().getWindow();
		stage.close();

	}

}
