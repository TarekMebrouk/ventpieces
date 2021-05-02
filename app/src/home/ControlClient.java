package home;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import ProjectClass.*;

public class ControlClient {
	
	/* window Client */
	@FXML
    Label ClientInfo;
	@FXML
	Button btnListeCommande;
	@FXML
	Button btnAjouteCommande;
	@FXML
	Button btnListeFactures;
	@FXML
	Button btnAjouteFacture;
	@FXML
	Button btnSettingsClient;
	@FXML
	Button btnRetour;
    /*-------------------*/
	
	@FXML
	StackPane pane;
	 static client Client;

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		if (actionEvent.getSource() == btnListeCommande) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("listeCommandeClient.fxml"));
			pane.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnAjouteCommande) {
			 Node nodes1 = FXMLLoader.load(getClass().getResource("AjouteCommande.fxml"));
			 pane.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnListeFactures) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("listeFactures.fxml"));
			pane.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnAjouteFacture) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("AjouteFacture.fxml"));
			
			pane.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnSettingsClient) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("SettingsClient.fxml"));
			Node nodes3 = FXMLLoader.load(getClass().getResource("pane.fxml"));
			pane.getChildren().add(nodes3);
			pane.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnRetour) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}

}
