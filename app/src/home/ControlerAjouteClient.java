package home;
import ProjectClass.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControlerAjouteClient {
    @FXML TextField nom;
    @FXML TextField prenom;
    @FXML TextField raisonSociale;
    @FXML TextField adress;
    @FXML TextField numeroTel;
    @FXML TextField etatClient;
	client client=null;
	public void insertClient(ActionEvent event) {
		client=new client(nom.getText(),prenom.getText(),raisonSociale.getText(),adress.getText(),numeroTel.getText(),etatClient.getText());
	}
}
