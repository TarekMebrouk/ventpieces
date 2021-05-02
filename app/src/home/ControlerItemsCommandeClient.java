package home;

import java.sql.SQLException;

import ProjectClass.EtatCommande;
import ProjectClass.MySQL;
import ProjectClass.commande;
import ProjectClass.produit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlerItemsCommandeClient {

	@FXML Label numeroArrive;
	@FXML TextField dateArrive;
	@FXML ComboBox<String> etatCommande;
	@FXML Label Qte_command;
	@FXML Label reference;
	@FXML Label designation;
	@FXML Label prix;
	@FXML Label Qte_Stock;
	@FXML Label rupture;
	
	ObservableList<String> Items=FXCollections.observableArrayList("enAttent","annuler","preparer","livrer");
    
	public void Modify(ActionEvent event) {
		System.out.println(etatCommande.getValue().toString());
		if (!numeroArrive.getText().equals("") ) {
	   commande c;
	try {
		c = MySQL.getCommande(Integer.parseInt(numeroArrive.getText()));
		   if (c !=null) {
			   if (!dateArrive.getText().equals(c.getDateArrive())) {
				   c.setDateArrive(dateArrive.getText());
			   }
			   if (c.getEtatCommande().toString().compareTo(etatCommande.getValue().toString())!=0) {
				   c.setEtatCommande(EtatCommande.valueOf(etatCommande.getValue().toString()));
			   }
		   }
	} catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}
	}
	
	
}
