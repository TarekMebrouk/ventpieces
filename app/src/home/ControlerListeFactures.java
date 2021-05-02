package home;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import ProjectClass.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ControlerListeFactures implements Initializable{

       @FXML VBox boxFactures=null;
   	Connection connection = DBConnector.connector();
         @Override
     	public void initialize(URL arg0, ResourceBundle arg1)  {
     		Statement stmt = null;
     		ResultSet rs = null;
     		Node nodes = null;
     		if (ControlClient.Client !=null) {
     		try {
     			stmt = connection.createStatement();
   String sql="select f.numeroFacture,f.dateFacture,f.dateEcheance,f.montant_tot,b.numeroBon,b.dateBon,b.poid,b.nbColis,b.nomPreparateur,c.numeroArrive, "
   		+ "c.dateArrive,c.etatCommande from facture f,bonLivraison b,commande c where f.numeroBon=b.numeroBon and b.numeroArrive=c.numeroArrive and "
   		+ "c.codeClient="+ControlClient.Client.getCode();
     			rs = stmt.executeQuery(sql);
     			while (rs.next()) {
     				FXMLLoader loader = new FXMLLoader();
     				nodes = loader.load(getClass().getResource("ItemsFacture.fxml").openStream());
     				
     				ControlerItemsFactures controler = (ControlerItemsFactures) loader.getController();
                    controler.numeroFacture.setText(""+rs.getInt(1));
                    controler.dateFacture.setText(rs.getString(2)); 
                    controler.dateEcheance.setText(rs.getString(3));
                    controler.montant.setText(""+rs.getDouble(4));
                    controler.numeroBon.setText(""+rs.getInt(5));
                    controler.dateBon.setText(rs.getString(6));
                    controler.poid.setText(""+rs.getDouble(7));
                    controler.nbColis.setText(""+rs.getInt(8));
                    controler.preparateur.setText(rs.getString(9));
                    controler.numeroCommande.setText(""+rs.getInt(10));
                    controler.dateCommande.setText(rs.getString(11));
                    controler.etatCommande.setText(rs.getString(12));
                    
                     boxFactures.getChildren().add(nodes);
     			}
     		} catch (Exception e) {e.printStackTrace();
     		} finally {
     			try {
     				stmt.close();
     				rs.close();
     			} catch (SQLException e) {
     				System.out.println("erreur");
     			}
     		}
     		}
     	}
}
