package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenu {
	Stage stage;
	Scene scene;
	
	@FXML
	public Label usernameLabel;
	
	@FXML
	public Label passwordLabel;
	
	
	public void display(String username,String password) {
		usernameLabel.setText(username);
		passwordLabel.setText(password);
	}
	
	
	public void switchToElevi(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Elevi.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToEditProfile(ActionEvent e) throws IOException {
		String username = usernameLabel.getText();
		String password = passwordLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));	
		Parent root = loader.load();
		profileController controller = loader.getController();
		controller.display(username,password);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	public void switchToLogin(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

}
