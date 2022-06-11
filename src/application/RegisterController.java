package application;

import java.io.IOException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;

import javafx.stage.Stage;

public class RegisterController {
	public EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");

	@FXML
	private PasswordField confirmPasswordTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private PasswordField passwordTextField;

	@FXML
	private Button registerButton;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label emailLabel;

	@FXML
	private Label enterPasswordLabel;

	@FXML
	private Label confirmPasswordLabel;

	@FXML
	private Label statusLabel;
	
	@FXML
	private Label userNameLabel;

	public void createButtonAction(ActionEvent e) {
		statusLabel.setText("");
		boolean okUsername = true;
		boolean okPassword = true;
		boolean okEmail = true;
		boolean okMatch = false;
		String text = emailTextField.getText();
		Pattern pattern = Pattern.compile("@yahoo.com", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(text);
		boolean matchFound = matcher.find();
		if(usernameTextField.getText().trim().isEmpty()) {
			userNameLabel.setText("Please enter a username");
			okUsername = false;
		}else {
			userNameLabel.setText("");
			okUsername = true;
		}

		if (passwordTextField.getText().trim().isEmpty()) {
			enterPasswordLabel.setText("Please enter a password");
			okPassword = false;
		} else {
			enterPasswordLabel.setText("");
			okPassword = true;
		}
		
		if (passwordTextField.getText().equals(confirmPasswordTextField.getText())) {
			confirmPasswordLabel.setText("");
			okMatch = true;
		} else {
			confirmPasswordLabel.setText("Password does not match");
			okMatch = false;
		}
		
		if (matchFound) {
			emailLabel.setText("");
			okEmail = true;
		} else {
			emailLabel.setText("Please enter a valid email adress");
			okEmail = false;
		}
		
		if (okPassword == true && okEmail == true && okMatch == true && okUsername == true) {
			statusLabel.setText("User created successfully");
			create();
		} else
			System.out.println("Something went wrong!");

	}

	public void create() {
		EntityManager EM = EMF.createEntityManager();
		EM.getTransaction().begin();
		Customer cust = new Customer();
		cust.setUsername(usernameTextField.getText());
		cust.setEmail(emailTextField.getText());
		cust.setPassword(passwordTextField.getText());
		EM.persist(cust);
		EM.getTransaction().commit();
	}

	
	
	Stage stage;
	Scene scene;
	public void switchToLogIn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
