package ServiceMagasinier;

import java.sql.SQLException;

import ProjectClass.MySQL;
import ProjectClass.bonLivraison;
import ProjectClass.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlerItemBonProduit {

	  @FXML Label numeroBon;
	  @FXML Label dateBon;
	  @FXML Label poid;
	  @FXML Label nbColis;
	  @FXML Label nomPreparateur;

	  @FXML Label reference;
	  @FXML Label designation;
	  @FXML Label prix;
	  @FXML Label Qte_stock;
	  
	  public void select (ActionEvent event) throws SQLException {
		  if (!reference.getText().equals("") && !numeroBon.getText().equals("")) {
		  produit p=MySQL.getProduit(reference.getText());
		  bonLivraison bon=MySQL.getBon(Integer.parseInt(numeroBon.getText()), ControlerCommande.Commande);
		  
		  ControlerLivrerCommande.bon=bon;
		  ControlerLivrerCommande.Produit=p;
		  }
	  }
}
