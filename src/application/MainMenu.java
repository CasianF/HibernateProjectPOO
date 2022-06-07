package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainMenu implements Initializable {
	Stage stage;
	Scene scene;

	@FXML
	public Label usernameLabel;
	
	@FXML
	public Label passwordLabel;
	@FXML
	private Label emailLabel;

	public void displayID(String id ) {
		idLabel.setText(id);
	}
	
	public void display(String username, String password) {
		usernameLabel.setText(username);
		passwordLabel.setText(password);
		EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("from Customer c where c.username ='" + usernameLabel.getText() + "'");
		Customer cust = (Customer) query.getSingleResult();
		idLabel.setText(String.valueOf(cust.getId()));
		emailLabel.setText(cust.getEmail());
		em.getTransaction().commit();
		em.close();
	}
	
	public void switchToElevi(ActionEvent e) throws IOException {
		String id = idLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Elevi.fxml"));
		Parent root = loader.load();
		EleviController controller = loader.getController();
		controller.idLabel.setText(id);
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void switchToEditProfile(ActionEvent e) throws IOException {
		String username = usernameLabel.getText();
		String password = passwordLabel.getText();
		String email = emailLabel.getText();
		String id = idLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Profile.fxml"));
		Parent root = loader.load();
		profileController controller = loader.getController();
		controller.display(username, password, id ,email);
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

	@FXML
	private Label idLabel;


	public int findByUsername(String username) {
		EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");
		EntityManager em = EMF.createEntityManager();
		Query query = em.createQuery("from Customer c where c.username ='" + usernameLabel.getText() + "'");
		Customer cust = (Customer) query.getSingleResult();
		em.getTransaction().commit();
		em.close();
		return cust.getId();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
