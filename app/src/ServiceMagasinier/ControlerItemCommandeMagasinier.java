package ServiceMagasinier;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import ProjectClass.MySQL;
import ProjectClass.commande;
import ProjectClass.ligneCommande;
import ProjectClass.produit;
import home.ControlClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControlerItemCommandeMagasinier {

	@FXML Label numeroArrive;
	@FXML Label dateArrive;
	@FXML Label etatCommande;
	@FXML TextField Qte_command;
	@FXML Label reference;
	@FXML Label designation;
	@FXML Label prix;
	@FXML Label Qte_Stock;
	@FXML Label nom;
	@FXML Label prenom;
	@FXML Button edit;
	@FXML Button modify;
	private double x,y;
	public void edit(ActionEvent event) throws NumberFormatException, SQLException, IOException {
		if (!numeroArrive.getText().equals("")) {
			commande c =MySQL.getCommande(Integer.parseInt(numeroArrive.getText()));
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("Commande.fxml"));
			loader.load();
			ControlerCommande controler=(ControlerCommande)loader.getController();
			controler.CommandeInfo.setText("numeroCommande: "+c.getNumeroArrive());
		    controler.Commande=c;
			Parent root = loader.getRoot();
			
			Scene scene=new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
		
			//drag it here
	        root.setOnMousePressed(event1 -> {
	            x = event1.getSceneX();
	            y = event1.getSceneY();
	        });
	        root.setOnMouseDragged(event1 -> {

	            primaryStage.setX(event1.getScreenX() - x);
	            primaryStage.setY(event1.getScreenY() - y);

	        });
	        primaryStage.show();
		}
	}
	
	public void Modify(ActionEvent event) throws NumberFormatException, SQLException {
		if (!numeroArrive.getText().equals("") && !reference.getText().equals("")) {
			commande c =MySQL.getCommande(Integer.parseInt(numeroArrive.getText()));
		    ControlerCommande.Commande=c;
		    
		    produit p=MySQL.getProduit(reference.getText());
		    
		    String sql = "update ligneCommande set qte_commande =" +Qte_command.getText()+ " where numeroArrive="
					+ c.getNumeroArrive() + " and reference ='" + p.getReference() + "'";
		    Double qte=MySQL.getQte_commande(p, c);
		    if (qte != Double.parseDouble(Qte_command.getText())) {
		    	try {
					if (MySQL.ExecuteRequette(sql)) {
						JOptionPane.showMessageDialog(null, "ligneCommande modifié avec succé", "Information",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "ligneCommande non modifié", "Warning",
								JOptionPane.WARNING_MESSAGE);
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				}
		    }
		}
	}
}
