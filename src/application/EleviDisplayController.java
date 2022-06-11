package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EleviDisplayController implements Initializable {

	@FXML
	private Label IdLabel;

	@FXML
	private Label m_BioLabel;

	@FXML
	private Label m_GenLabel;

	@FXML
	private Label m_InfoLabel;

	@FXML
	private Label m_MateLabel;

	@FXML
	private Label m_RoLabel;

	@FXML
	private Label m_SportLabel;

	@FXML
	public Label numeLabel;

	@FXML
	private Label prenumeLabel;

	EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");

	public void switchToEleviLogin(ActionEvent e) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("ChooseMenu.fxml"));
		Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void getDataByUsername() {
		try {
			String nume = numeLabel.getText();
			EntityManager em = EMF.createEntityManager();
			em.getTransaction().begin();
			String queryString = "FROM Elev U where U.nume='" + nume + "'";
			Query query = em.createQuery(queryString);
			Elev elev = (Elev) query.getSingleResult();
			IdLabel.setText(String.valueOf(elev.getId()));
			m_GenLabel.setText(elev.getMedia_generala());
			m_InfoLabel.setText(elev.getMedia_info());
			m_MateLabel.setText(elev.getMedia_mate());
			m_RoLabel.setText(elev.getMedia_romana());
			m_BioLabel.setText(elev.getMedia_bio());
			m_SportLabel.setText(elev.getMedia_sport());
			prenumeLabel.setText(elev.getPrenume());
			em.getTransaction().commit();
			em.clear();
		} catch (Exception e) {
			System.out.println("IN GET DATA USER E O PROBLEMA");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			getDataByUsername();
		});
	}

}
