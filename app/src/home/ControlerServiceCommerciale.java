package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import ProjectClass.*;
import ServiceMagasinier.ControlerServiceMagasinier;
import ServiceMagasinier.ControlerSettingsUser;
public class ControlerServiceCommerciale implements Initializable {

	@FXML
	public Label UserInfo;

	@FXML
	private Button btnClients;

	@FXML
	private Button btnAjouteClient;

	@FXML
	private Button btnCommandes;

	@FXML
	private Button btnSettingsUser;

	@FXML
	private Button btnCreateProduit;
	
	@FXML
	private Button btnListeProduit;
	
	@FXML
	private Button btnSignout;

	@FXML
	private StackPane pnl;

	public static user User=null;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
		if (User !=null)	UserInfo.setText("User : "+User.getUsername());
			Node nodes1 = FXMLLoader.load(getClass().getResource("listeClients.fxml"));

			pnl.getChildren().add(nodes1);

		} catch (IOException e) {
			System.out.println("ERREUR ");
		}

	}

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnClients) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("listeClients.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnAjouteClient) {
			Node nodes2 = FXMLLoader.load(getClass().getResource("AjouteClient.fxml"));
			Node nodes3 = FXMLLoader.load(getClass().getResource("pane.fxml"));
			pnl.getChildren().add(nodes3);
			pnl.getChildren().add(nodes2);
		}
		if (actionEvent.getSource() == btnCommandes) {
			Node nodes3 = FXMLLoader.load(getClass().getResource("listeCommandes.fxml"));
			pnl.getChildren().add(nodes3);
		}
		
		if (actionEvent.getSource() == btnListeProduit) {
			Node nodes4 = FXMLLoader.load(getClass().getResource("ListeProduits.fxml"));
			pnl.getChildren().add(nodes4);
		}
		
		if (actionEvent.getSource() == btnCreateProduit) {
			Node nodes4 = FXMLLoader.load(getClass().getResource("AjouteProduit.fxml"));
			pnl.getChildren().add(nodes4);
		}
		
		if (actionEvent.getSource() == btnSettingsUser) {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("SettingsUser.fxml"));
			loader.load();
			Node nodes4 = loader.getRoot();
			home.ControlerSettingsUser controlerSettingsUser=(home.ControlerSettingsUser)loader.getController();
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
		if (actionEvent.getSource() == btnSignout) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}
}
