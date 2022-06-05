package application;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddElev {
	public EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");
	@FXML
	private TextField numeTextField;

	@FXML
	private TextField prenumeTextField;

	public void createButtonAction(ActionEvent e) {
		create();
	}

	public void create() {
		EntityManager EM = EMF.createEntityManager();
		EM.getTransaction().begin();
		Elev elev = new Elev();
		elev.setNume(numeTextField.getText());
		elev.setPrenume(prenumeTextField.getText());
		EM.persist(elev);
		EM.getTransaction().commit();
	}

	@FXML
	private Button closeButton;

	public void closeButtonAction(ActionEvent event) throws IOException {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
