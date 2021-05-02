package ServiceMagasinier;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.EtatCommande;
import ProjectClass.commande;
import ProjectClass.user;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ControlerCommande  implements Initializable{

	static commande Commande;
	@FXML
	public Label CommandeInfo;


	@FXML
	private Button btnAjouterBonLivraison;

	@FXML
	private Button btnListeBonLivraison;

	@FXML
	private Button btnLivrerCommande;

	@FXML
	private Button btnPreparerCommande;
	
	@FXML
	private Button btnSignOut;

	@FXML
	private StackPane pnl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			Node nodes2 = FXMLLoader.load(getClass().getResource("ListeBonLivraison.fxml"));
			pnl.getChildren().add(nodes2);
		} catch (Exception e) {
			System.out.println("ERREUR ");
		}

	}

	public void handleClicks(ActionEvent actionEvent) throws IOException {
		
		if (actionEvent.getSource() == btnAjouterBonLivraison) {
			Node nodes2 = FXMLLoader.load(getClass().getResource("AjouteBonLivraison.fxml"));
			pnl.getChildren().add(nodes2);
		}
		if (actionEvent.getSource() == btnListeBonLivraison) {
			Node nodes2 = FXMLLoader.load(getClass().getResource("ListeBonLivraison.fxml"));
			pnl.getChildren().add(nodes2);
		}
		if (actionEvent.getSource() == btnLivrerCommande) {
			Node nodes2 = FXMLLoader.load(getClass().getResource("LivrerCommande.fxml"));
			pnl.getChildren().add(nodes2);
		}
        if (actionEvent.getSource() == btnPreparerCommande) {
			if (Commande !=null) {
				Commande.setEtatCommande(EtatCommande.preparer);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("Votre Commande est préparer Vous pouvez maintenant la crée un Bon de Livraison ^_^");
				alert.showAndWait();
			}
		}
		if (actionEvent.getSource() == btnSignOut) {
			 Node node = (Node) actionEvent.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
	}
}
