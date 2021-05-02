package home;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import ProjectClass.DBConnector;
import ProjectClass.MySQL;
import ProjectClass.commande;
import ProjectClass.ligneCommande;
import ProjectClass.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ControlerAjouteCommande implements Initializable{
	@FXML TextField Qte_commande;
	@FXML DatePicker dateArrive;
	@FXML VBox listeProduits;
	static produit Produit;
	commande c;
	Connection connection = DBConnector.connector();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from produit");
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemProduit.fxml").openStream());
				ControlerItemProduit controler = (ControlerItemProduit) loader.getController();
				controler.reference.setText(rs.getString(1));
				controler.designation.setText(rs.getString(2));
				controler.prix.setText(""+rs.getDouble(3));
				controler.Qte_Stock.setText(""+rs.getDouble(4));
				if (rs.getInt(5)==0) controler.rupture.setText("false");
				else controler.rupture.setText("true");
                controler.createProd();
				listeProduits.getChildren().add(nodes);
			}
		} catch (Exception e) {
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println("erreur");
			}
		}	
		
	}
	public void insertCommande() {
		if (dateArrive.getValue() !=null && ControlClient.Client != null) {
			String time =LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond();
			String datetime=dateArrive.getValue().getYear()+"-"+dateArrive.getValue().getMonthValue()+"-"+dateArrive.getValue().getDayOfMonth()+" "+time;
			 c=new commande(datetime,ControlClient.Client);
		}
	}
	public void insertLigneCommande(ActionEvent event) {
		if(Produit != null && ControlClient.Client != null ) {
			if (c!=null) {
			ligneCommande ligne =new ligneCommande(c,Produit,Double.parseDouble(Qte_commande.getText()));
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("Vous avez pas ajouté une commande !!");
				alert.showAndWait();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "les champs sont pas remplies ou Vous avez pas selectionné un Client ._.", "Warning", JOptionPane.WARNING_MESSAGE);			
		}
	}
	
}
