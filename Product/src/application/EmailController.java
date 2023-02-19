package application;

import java.io.IOException;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
//this class is to send emails
public class EmailController {
	@FXML
	private JFXTextField tfReceiver;
	
	@FXML
	private JFXTextField tfSubject;
	
	@FXML
	private JFXButton btSend;
	
//	Sends an email the the desired address, this email contains, all the information of the application
//	like the logs, the StepsList and the Exercises
	@FXML
	void btSend_clicked(ActionEvent event) throws IOException {
			Methods.Email.send(tfReceiver.getText(), tfSubject.getText());
	}
}
