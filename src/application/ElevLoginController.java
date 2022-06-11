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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ElevLoginController {
	@FXML
	private TextField numeTextField;

	@FXML
	private TextField prenumeTextField;

	@FXML
	private Label loginLabel;
	
	@FXML
	private Button loginButton;

	EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");

	public Elev validate(String nume, String prenume) {
		EntityManager em = EMF.createEntityManager();
		em.getTransaction().begin();
		String queryString = "FROM Elev U where U.nume='" + nume + "' and U.prenume='" + prenume + "'";
		Query query = em.createQuery(queryString);
		em.getTransaction().commit();
		em.clear();
		try {
			return (Elev) query.getSingleResult();
		} catch (Exception e) {
			e.getCause();
			return null;
		}
	}

	public void loginButtonAction(ActionEvent e) {
		String nume = numeTextField.getText();
		String prenume = prenumeTextField.getText();
		if (validate(nume, prenume) != null) {
			switchToElevContent();
			Stage stage = (Stage) loginButton.getScene().getWindow();
			stage.close();
			
		} else
			loginLabel.setText("Nume sau prenume incorect");
	}

	public void switchToElevContent() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ElevContent.fxml"));
			Parent root = loader.load();
			EleviDisplayController controller = loader.getController();
			controller.numeLabel.setText(numeTextField.getText());
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.getCause();
		}
	}

	public void switchToChooseMenu(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChooseMenu.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
