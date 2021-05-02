package home;

import ProjectClass.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlerItemProduit  {

	@FXML Label reference;
	@FXML TextField designation;
	@FXML TextField  prix;
	@FXML TextField Qte_Stock;
	@FXML TextField rupture;
	private produit prod;
	
	public void createProd() {
		int r;
		if (rupture.getText().equals("true")) r=1;
		else r=0;
		setProd(new produit(reference.getText(),designation.getText(),Double.parseDouble(prix.getText()),Double.parseDouble(Qte_Stock.getText()),r));
	}
	
	public void Modify(ActionEvent event) {
		if (prod !=null) {
			if (prod.getDesignation().equals(designation.getText())==false) {
				prod.setDesignation(designation.getText());
			}
			if (prod.getPrix()!=Double.parseDouble(prix.getText())){
				prod.setPrix(Double.parseDouble(prix.getText()));
			}
			if (prod.getQte_Stock() != Double.parseDouble(Qte_Stock.getText())) {
				prod.setQte_Stock(Double.parseDouble(Qte_Stock.getText()));
			}
			int x;
			if (rupture.getText().equals("true")) x=1; else x=0;
			if (prod.getRupture()!=x) {
				prod.setRupture(x);
			}
		}
	}
	
	public void select(ActionEvent event ) {
		ControlerAjouteCommande.Produit=prod;
	}

	public produit getProd() {
		return prod;
	}

	public void setProd(produit prod) {
		this.prod = prod;
	}
	
}
