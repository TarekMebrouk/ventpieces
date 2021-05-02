package home;

import java.sql.SQLException;

import ProjectClass.MySQL;
import ProjectClass.bonLivraison;
import ProjectClass.commande;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlerBonLivraison {

	@FXML
	Label numeroBon;
	@FXML
	Label dateBon;
	@FXML
	Label poid;
	@FXML
	Label nbColis;
	@FXML
	Label nomPreparateur;
	@FXML
	Label numeroArrive;
	@FXML
	Label DateArrive;

	public void select () throws NumberFormatException, SQLException {
	  if (!numeroBon.getText().equals("") && !dateBon.getText().equals("") && !poid.getText().equals("") && !nbColis.getText().equals("")  && !nomPreparateur.getText().equals("") && !numeroArrive.getText().equals("") ) {
	     commande c=MySQL.getCommande(Integer.parseInt(numeroArrive.getText()));
		  bonLivraison bon=new bonLivraison(Integer.parseInt(numeroBon.getText()),dateBon.getText(),Double.parseDouble(poid.getText()),Integer.parseInt(nbColis.getText()),nomPreparateur.getText(),c);
		  ControlerAjouteFacture.bon=bon;
	  }
  }
}
