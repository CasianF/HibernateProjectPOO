package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainMenu {
	Stage stage;
	Scene scene;
	
	public void switchToProfile(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToElevi(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Elevi.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
