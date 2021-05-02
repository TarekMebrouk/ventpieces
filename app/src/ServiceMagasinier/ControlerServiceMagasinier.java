package ServiceMagasinier;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ProjectClass.MySQL;
import ProjectClass.user;
import home.ControlerServiceCommerciale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControlerServiceMagasinier implements Initializable {

	@FXML
	public Label UserInfo;

	@FXML
	private Button btn1;

	@FXML
	private Button btn2;

	@FXML
	private Button btnProduitenRupture;

	@FXML
	private Button btnSettingUser;

	@FXML
	private Button btnSignOut;

	@FXML
	private StackPane pnl;

	public static user User=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			
		if (User !=null)	UserInfo.setText("User : "+User.getUsername());
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeCommandesLivrées.fxml"));

			pnl.getChildren().add(nodes1);

		} catch (IOException e) {
			System.out.println("ERREUR ");
		}

	}

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btn1) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeCommandesLivrées.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btn2) {
			System.out.println("j ss la");
			Node nodes2 = FXMLLoader.load(getClass().getResource("ListeCommandesNonLivrées.fxml"));
			pnl.getChildren().add(nodes2);
		}
		if (actionEvent.getSource() == btnSettingUser) {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SettingsUser.fxml"));
			loader.load();
			Node nodes4 = loader.getRoot();
			ControlerSettingsUser controlerSettingsUser=(ControlerSettingsUser)loader.getController();
			System.out.println(controlerSettingsUser);
			controlerSettingsUser.typeUSer.setItems(controlerSettingsUser.Items);
				controlerSettingsUser.username.setText(ControlerServiceMagasinier.User.getUsername());
				controlerSettingsUser.password.setText(ControlerServiceMagasinier.User.getPassword());
				controlerSettingsUser.nom.setText(ControlerServiceMagasinier.User.getNom());
				controlerSettingsUser.prenom.setText(ControlerServiceMagasinier.User.getPrenom());
				controlerSettingsUser.typeUSer.setValue(ControlerServiceMagasinier.User.getType().toString());
				Node nodes3 = FXMLLoader.load(getClass().getResource("pane.fxml"));
				pnl.getChildren().add(nodes3);
			pnl.getChildren().add(nodes4);
		}
		if (actionEvent.getSource() == btnProduitenRupture) {
			Node nodes3 = FXMLLoader.load(getClass().getResource("listeProduitenRupture.fxml"));
			pnl.getChildren().add(nodes3);
		}
		if (actionEvent.getSource() == btnSignOut) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}
}

