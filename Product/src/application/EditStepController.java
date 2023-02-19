package application;
//import java.awt.Window;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Steps.Step;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import javafx.stage.Stage;
//this class is to edit steps
public class EditStepController {

	public static Step currentStep;
	public static UArmClientController parent;

	@FXML
	private ChoiceBox<String> cbMove;

	@FXML
	private JFXTextField tfDegree;

	@FXML
	private JFXButton btSave;

	ObservableList<String> typeList = FXCollections.observableArrayList();

	@FXML
	private JFXButton btCancel;

	@FXML
	void initialize() {
		String[] exType = new String[6];
		exType[0] = "Left";
		exType[1] = "Right";
		exType[2] = "Front";
		exType[3] = "Back";
		exType[4] = "Up";
		exType[5] = "Down";
		for (int i = 0; i < exType.length; i++) {
			typeList.add(exType[i]);
		}

		for (int i = 0; i < exType.length; i++) {
			if (EditStepController.currentStep.getDir().equals(exType[i])) {
				cbMove.setValue(exType[i]);
			}
		}

		cbMove.setItems(typeList);
		this.tfDegree.setText("" + EditStepController.currentStep.getDis());

	}

//	Saves the information to the list in the UarmClient
	@FXML
	void btSave_clicked(ActionEvent event) throws IOException {
		EditStepController.currentStep.setDir(this.cbMove.getSelectionModel().getSelectedItem());
		EditStepController.currentStep.setDis(Integer.parseInt(this.tfDegree.getText()));
		EditStepController.parent.refreshList(currentStep);
		Stage stage = (Stage) this.btSave.getScene().getWindow();
		stage.close();
		EditStepController.parent.save();
	}

//	Cancels any changes made and then goes back to the UarmClient
	@FXML
	void btCancel_clicked(ActionEvent event) {
		Stage stage = (Stage) this.btCancel.getScene().getWindow();
		stage.close();

	}

}
