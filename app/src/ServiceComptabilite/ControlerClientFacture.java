package ServiceComptabilite;

import java.io.IOException;
import java.sql.SQLException;

import ProjectClass.MySQL;
import ProjectClass.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControlerClientFacture {

	@FXML
	public Label ClientFactureInfo;

	@FXML
	private Button btnListepayement;

	@FXML
	private Button btnAjoutepayement;
	
	@FXML
	private Button btnSignOut;

	static int numeroFacture;
	
	static client Client;
	
	@FXML
	private StackPane pnl;
	
	public void handleClicks(ActionEvent actionEvent) throws IOException, SQLException {
		if (actionEvent.getSource() == btnListepayement) {
			Node nodes1 = FXMLLoader.load(getClass().getResource("ListePayement.fxml"));
			pnl.getChildren().add(nodes1);
		}
		if (actionEvent.getSource() == btnAjoutepayement) {
			if (!MySQL.facturePayé(numeroFacture, Client.getCode())) {
				Node nodes1 = FXMLLoader.load(getClass().getResource("AjoutePayement.fxml"));
				pnl.getChildren().add(nodes1);	
			}
			else {
				Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
				alert1.setHeaderText(null);
				alert1.setTitle(null);
				alert1.setContentText("Vous avez payé la facture ..merci *-* ");
				alert1.showAndWait();
			}
		}
		if (actionEvent.getSource() == btnSignOut) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}
	
}
