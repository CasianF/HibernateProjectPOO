package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
import javafx.scene.image.Image;
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

	public boolean validate(String username, String password) {
		username = usernameTextField.getText();
		password = passwordPasswordField.getText();
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		Query query = em
				.createQuery("from Customer c where c.username ='" + username + "' and c.password ='" + password + "'");
		Customer cust = (Customer) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		if (cust != null) {
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.close();
			return true;
		} else
			return false;
	}

	public void loginButtonAction(ActionEvent e) throws SQLException {
		String username = usernameTextField.getText();
		String password = passwordPasswordField.getText();
		if (validate(username, password) == true) {
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
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.getCause();
			e.printStackTrace();
		}
	}

}
