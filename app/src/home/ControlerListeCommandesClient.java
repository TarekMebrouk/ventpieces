package home;

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
import javafx.scene.layout.VBox;

public class ControlerListeCommandesClient implements Initializable {

	 @FXML VBox boxCommande=null;
		Connection connection = DBConnector.connector();
		@Override
		public void initialize(URL arg0, ResourceBundle arg1)  {
			Statement stmt = null;
			ResultSet rs = null;
			Node nodes = null;
			if (ControlClient.Client !=null) {
			try {
				stmt = connection.createStatement();
				String sql="select c.numeroArrive,c.dateArrive,c.etatCommande,l.qte_commande,p.reference,p.designation,p.prix, "
						+ "p.Qte_stock,p.rupture from produit p,commande c,ligneCommande l where "
						+ "p.reference=l.reference and c.numeroArrive=l.numeroArrive and c.codeClient="+ControlClient.Client.getCode();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					FXMLLoader loader = new FXMLLoader();
					nodes = loader.load(getClass().getResource("ItemsCommandeClient.fxml").openStream());
					ControlerItemsCommandeClient controler = (ControlerItemsCommandeClient) loader.getController();
	                controler.numeroArrive.setText(rs.getString(1));
	                controler.dateArrive.setText(rs.getString(2));
	                controler.etatCommande.setItems(controler.Items);
	                controler.etatCommande.setValue(rs.getString(3));
	                controler.Qte_command.setText(rs.getString(4));
	                controler.reference.setText(rs.getString(5));
	                controler.designation.setText(rs.getString(6));
	                controler.prix.setText(rs.getString(7));
	                controler.Qte_Stock.setText(rs.getString(8));
	                if (rs.getInt(9)==0) controler.rupture.setText("false");
	                else controler.rupture.setText("true");
	                
	                boxCommande.getChildren().add(nodes);
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
