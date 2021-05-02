package ServiceComptabilite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.user;
import ServiceMagasinier.ControlerServiceMagasinier;
import ServiceMagasinier.ControlerSettingsUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControlerServiceComptabilite implements Initializable{

	@FXML
	public Label UserInfo;

	@FXML
	private Button btnClientRegler;

	@FXML
	private Button btnClientNonRegler;

	@FXML
	private Button btnSettingUser;

	@FXML
	private Button btnListeClientFacture;
	
	@FXML
	private Button btnSignOut;

	@FXML
	private StackPane pnl;

	public static user User=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		if (User !=null)	UserInfo.setText("User : "+User.getUsername());
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeClientFacturePayer.fxml"));

			pnl.getChildren().add(nodes1);

		} catch (Exception e) {
			System.out.println("ERREUR ");
		}

	}

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnClientRegler) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeClientFacturePayer.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnClientNonRegler) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeClientFactureNonPayer.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnSettingUser) {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SettingsUser.fxml"));
			loader.load();
			Node nodes4 = loader.getRoot();
			ServiceComptabilite.ControlerSettingsUser controlerSettingsUser=(ServiceComptabilite.ControlerSettingsUser)loader.getController();
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
		if (actionEvent.getSource() ==btnListeClientFacture) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListeFactureClient.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnSignOut) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}
}

