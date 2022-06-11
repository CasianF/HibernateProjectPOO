package application;

import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EleviController implements Initializable {

	@FXML
	private TableColumn<Elev, Integer> idCol;

	@FXML
	private TableColumn<Elev, String> mBioCol;

	@FXML
	private TableColumn<Elev, String> mGeneralaCol;

	@FXML
	private TableColumn<Elev, String> mInfoCol;

	@FXML
	private TableColumn<Elev, String> mMateCol;

	@FXML
	private TableColumn<Elev, String> mRoCol;

	@FXML
	private TableColumn<Elev, String> mSportCol;

	@FXML
	private TableColumn<Elev, String> numeCol;

	@FXML
	private TableColumn<Elev, String> prenumeCol;

	@FXML
	private TableView<Elev> studentTable;
	public EntityManagerFactory EMF = Persistence.createEntityManagerFactory("idk");
	ObservableList<Elev> ElevList = FXCollections.observableArrayList();

	@FXML
	Label idLabel;

	public void loadId(String id) {
		idLabel.setText(id);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			loadTable();
		});
	}

	Stage stage;
	Scene scene;
	EntityManager em = EMF.createEntityManager();
	Session session = em.unwrap(Session.class);

	public void loadTable() {
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Elev where prof_id='" + idLabel.getText() + "'");
		@SuppressWarnings("unchecked")
		List<Elev> list = query.getResultList();
		em.getTransaction().commit();
		em.clear();
		int n = list.size();
		for (int i = 0; i < n; i++) {
			Elev e = list.get(i);
			ElevList.add(e);
		}

		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		numeCol.setCellValueFactory(new PropertyValueFactory<>("nume"));
		prenumeCol.setCellValueFactory(new PropertyValueFactory<>("prenume"));
		mRoCol.setCellValueFactory(new PropertyValueFactory<>("media_romana"));
		mMateCol.setCellValueFactory(new PropertyValueFactory<>("media_mate"));
		mInfoCol.setCellValueFactory(new PropertyValueFactory<>("media_info"));
		mBioCol.setCellValueFactory(new PropertyValueFactory<>("media_bio"));
		mSportCol.setCellValueFactory(new PropertyValueFactory<>("media_sport"));
		mGeneralaCol.setCellValueFactory(new PropertyValueFactory<>("media_generala"));
		studentTable.setItems(ElevList);
	}

	public void delete() throws SQLException {
		EntityManager EM = EMF.createEntityManager();
		Elev elev = studentTable.getSelectionModel().getSelectedItem();
		Elev e1 = EM.find(Elev.class, elev.getId());
		EM.getTransaction().begin();
		EM.merge(e1);
		EM.remove(e1);
		EM.getTransaction().commit();
		EM.close();
		refreshTable();
	}

	public void refreshTable() {
		ElevList.clear();
		em.getTransaction().begin();
		Query query = em.createQuery("FROM Elev where prof_id='" + idLabel.getText() + "'");
		@SuppressWarnings("unchecked")
		List<Elev> list = query.getResultList();
		em.getTransaction().commit();
		em.clear();
		int n = list.size();
		for (int i = 0; i < n; i++) {
			Elev e = list.get(i);
			ElevList.add(e);
		}
	}

	public void refreshTableButtonAction(ActionEvent event) {
		refreshTable();
	}

	public void deleteButtonAction(ActionEvent event) throws SQLException {
		delete();
	}

	public void switchToAddElev(ActionEvent event) throws IOException {
		String id = idLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AddElev.fxml"));
		Parent root = loader.load();
		AddElev controller = loader.getController();
		controller.setprofIdLabel(String.valueOf(id));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void switchToMainMenu(ActionEvent event) throws IOException {
		String id = idLabel.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
		Parent root = loader.load();
		MainMenu controller = loader.getController();
		controller.idLabel.setText((String.valueOf(id)));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(new Scene(root));
		stage.show();
	}

	///////////////// Edit elev part

	@FXML
	private TextField media_bioTextField;

	@FXML
	private TextField media_generalaTextField;

	@FXML
	private TextField media_infoTextField;

	@FXML
	private TextField media_mateTextField;

	@FXML
	private TextField media_roTextField;

	@FXML
	private TextField media_sportTextField;

	@FXML
	private TextField nTextField;

	@FXML
	private TextField prenumeTextField;

	@FXML
	public void clickedElev(MouseEvent event) {
		Elev elev = studentTable.getSelectionModel().getSelectedItem();
		nTextField.setText(elev.getNume());
		prenumeTextField.setText(elev.getPrenume());
		media_mateTextField.setText(elev.getMedia_mate());
		media_roTextField.setText(elev.getMedia_romana());
		media_sportTextField.setText(elev.getMedia_sport());
		media_infoTextField.setText(elev.getMedia_info());
		media_bioTextField.setText(elev.getMedia_bio());
	}

	public void update() {
		EntityManager EM = EMF.createEntityManager();
		Elev elev = studentTable.getSelectionModel().getSelectedItem();
		Elev e1 = EM.find(Elev.class, elev.getId());
		EM.getTransaction().begin();
		e1.setNume(nTextField.getText());
		e1.setPrenume(prenumeTextField.getText());
		e1.setMedia_romana(media_roTextField.getText());
		e1.setMedia_mate(media_mateTextField.getText());
		e1.setMedia_info(media_infoTextField.getText());
		e1.setMedia_bio(media_bioTextField.getText());
		e1.setMedia_sport(media_sportTextField.getText());
		e1.setMedia_generala(convertor(e1.getMedia_romana(), e1.getMedia_mate(), e1.getMedia_info(), e1.getMedia_bio(),
				e1.getMedia_sport()));
		EM.getTransaction().commit();
	}

	public String convertor(String mR, String mM, String mI, String mB, String mS) {
		double mRO = Integer.valueOf(mR);
		double mMat = Integer.valueOf(mM);
		double mInf = Integer.valueOf(mI);
		double mBio = Integer.valueOf(mB);
		double mSp = Integer.valueOf(mS);
		double sum = (mRO + mMat + mInf + mBio + mSp) / 5;
		return String.valueOf(sum);
	}

	public void clearButtonAction(ActionEvent event) {
		media_mateTextField.setText(null);
		media_roTextField.setText(null);
		media_infoTextField.setText(null);
		media_bioTextField.setText(null);
		media_sportTextField.setText(null);
	}

	public void saveButtonAction(ActionEvent e) {
		update();
	}
}