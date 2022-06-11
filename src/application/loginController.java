package application;

import java.io.IOException;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import javax.persistence.Query;



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

public class loginController {

	@FXML
	private Button loginButton;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private Button registerButton;

	@FXML
	private TextField usernameTextField;

	@FXML
	private Label loginLabel;

	Stage stage;
	Scene scene;
	EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");

	public Customer validate(String username) {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		String queryString = "FROM Customer U where U.username='"+username+"'";
		Query query = em.createQuery(queryString);
		em.getTransaction().commit();
		em.clear();
		try {
			return (Customer) query.getSingleResult();
		}catch(Exception e) {
			e.getCause();
			return null;
		}
	}

	public void loginButtonAction(ActionEvent e){
		String username = usernameTextField.getText();
		if (validate(username) != null) {
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.close();
			switchToMainMenu();
		} else
			loginLabel.setText("Incorrect username of password");
	}

	public void switchToRegister(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToChooseLogin(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChooseMenu.fxml"));
		stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	public void switchToMainMenu() {
		try {

			String username = usernameTextField.getText();
			String password = passwordPasswordField.getText();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
			Parent root = loader.load();
			MainMenu controller = loader.getController();
			controller.display(username, password);
			controller.usernameLabel.setText(username);
			controller.passwordLabel.setText(password);
			Stage stage = new Stage();
			stage.setTitle("EntityManager");
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.getCause();
			e.printStackTrace();
		}
	}

}
