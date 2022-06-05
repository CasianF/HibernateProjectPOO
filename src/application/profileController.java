package application;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.print.DocFlavor.URL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class profileController implements Initializable {

	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;

	Stage stage;
	Scene scene;
	
	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
	}

	public void display(String username,String password) {
		usernameLabel.setText(username);
		passwordLabel.setText(password);
	}
	
	public void switchToMainMenu(ActionEvent e) throws IOException {
		String username = usernameLabel.getText();
		String password = passwordLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));	
		Parent root = loader.load();
		MainMenu controller = loader.getController();
		controller.display(username,password);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
