package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ChooseLoginController {
	public void switchToProfesor(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	Stage stage;
	@FXML
	private Button exitButton;

	public void closeActionButton(ActionEvent event) throws IOException {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	public void switchToElev(ActionEvent e) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ElevLogin.fxml"));
		Parent root = loader.load();
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
