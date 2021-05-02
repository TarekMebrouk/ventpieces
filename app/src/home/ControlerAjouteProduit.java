package home;

import ProjectClass.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ControlerAjouteProduit {
	
	@FXML TextField reference;
	@FXML TextField designation;
	@FXML TextField prix;
	@FXML TextField Qte_Stock;
	
	public void createProduit (ActionEvent event) {
     produit Produit=new produit(reference.getText(),designation.getText(),
		Double.parseDouble(prix.getText()),Double.parseDouble(Qte_Stock.getText()));
                                                  }
}
