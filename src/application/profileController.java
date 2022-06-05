package application;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class profileController implements Initializable {

	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	
	public EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");
	EntityManager EM = EMF.createEntityManager();
	Session session = EM.unwrap(Session.class);

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		
	}
	
}
