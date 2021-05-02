package home;

import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlerSettingsClient implements Initializable {
	@FXML Label codeClient;
	@FXML TextField nom;
	@FXML TextField prenom;
	@FXML TextField raisonSociale;
	@FXML TextField adress;
	@FXML TextField numeroTel;
	@FXML TextField etatClient;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if (ControlClient.Client != null) {
		client c=ControlClient.Client;
		codeClient.setText(""+c.getCode());
		nom.setText(c.getNom());
		prenom.setText(c.getPrenom());
		raisonSociale.setText(c.getRaisonSociale());
		adress.setText(c.getAdress());
		numeroTel.setText(c.getNumeroTel());
		etatClient.setText(c.getEtatClient());
		}
	}
	
	public void Modify(ActionEvent event) {
		if (ControlClient.Client != null) {
			client c=ControlClient.Client;
			
			if(c.getNom().equals(nom.getText())==false) {
				ControlClient.Client.setNom(nom.getText());
			}
			if (c.getPrenom().equals(prenom.getText())==false) {
				ControlClient.Client.setPrenom(prenom.getText());
			}
			if (c.getRaisonSociale().equals(raisonSociale.getText())==false) {
				ControlClient.Client.setRaisonSociale(raisonSociale.getText());
			}
			if (c.getAdress().equals(adress.getText())==false) {
				ControlClient.Client.setAdress(adress.getText());
			}
			if (c.getNumeroTel().equals(numeroTel.getText())==false) {
				ControlClient.Client.setNumeroTel(numeroTel.getText());
			}
			if (c.getEtatClient().equals(etatClient.getText())==false) {
				ControlClient.Client.setEtatClient(etatClient.getText());
			}
		}
	}
}
