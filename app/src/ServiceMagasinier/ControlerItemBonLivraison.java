package ServiceMagasinier;

import java.sql.SQLException;

import ProjectClass.MySQL;
import ProjectClass.bonLivraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlerItemBonLivraison {
	
	  @FXML Label numeroBon;
	  @FXML TextField dateBon;
	  @FXML TextField poid;
	  @FXML TextField nbColis;
	  @FXML TextField nomPreparateur;

	  public void modify(ActionEvent event ) throws NumberFormatException, SQLException {
		  if (!numeroBon.getText().equals("")) {
			 bonLivraison bon=MySQL.getBon(Integer.parseInt(numeroBon.getText()), ControlerCommande.Commande);
			 
			 if (dateBon.getText().compareTo(bon.getDateBon())!=0) {
				 bon.setDateBon(dateBon.getText());
			 }
			 if (Double.parseDouble(poid.getText())!=bon.getPoid()) {
				 bon.setPoid(Double.parseDouble(poid.getText()));
			 }
			 if (Integer.parseInt(nbColis.getText())!=bon.getNbColis()) {
				 bon.setNbColis(Integer.parseInt(nbColis.getText()));
			 }
			 if (nomPreparateur.getText().compareTo(bon.getPreparateur())!=0) {
				 bon.setPreparateur(nomPreparateur.getText());
			 }
		  }
	  }
	  
} 
