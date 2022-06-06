package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mysql.cj.Query;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		loadTable();
		
	}

	Stage stage;
	Scene scene;

	public void loadTable() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test4", "root",
					"Minecraft20");
			ResultSet rs = connection.createStatement().executeQuery("select * from elevi");
			while (rs.next())
				ElevList.add(new Elev(rs.getInt("id"), rs.getString("nume"), rs.getString("prenume"),
						rs.getString("media_romana"), rs.getString("media_mate"), rs.getString("media_info"),
						rs.getString("media_bio"), rs.getString("media_sport"), rs.getString("media_generala")));
		} catch (SQLException e) {

			e.printStackTrace();
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
		Elev elev = new Elev();
		elev = studentTable.getSelectionModel().getSelectedItem();
		String query = "delete from elevi where id = " + (Integer) elev.getId();
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test4", "root", "Minecraft20");
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.execute();
		refreshTable();
	}

	public void refreshTable() {
		try {

			ElevList.clear();
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test4", "root",
					"Minecraft20");
			ResultSet rs = connection.createStatement().executeQuery("select * from elevi");
			while (rs.next())
				ElevList.add(new Elev(rs.getInt("id"), rs.getString("nume"), rs.getString("prenume"),
						rs.getString("media_romana"), rs.getString("media_mate"), rs.getString("media_info"),
						rs.getString("media_bio"), rs.getString("media_sport"), rs.getString("media_generala")));
			studentTable.setItems(ElevList);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void refreshTableButtonAction(ActionEvent event) {
		refreshTable();
	}

	public void deleteButtonAction(ActionEvent event) throws SQLException {
		delete();
	}

	public void switchToAddElev(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("AddElev.fxml"));
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.show();
	}

	public void switchToMainMenu(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	///////////////////////////////////// Edit elev part

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