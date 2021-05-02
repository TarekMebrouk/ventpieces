package ServiceMagasinier;

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

public class ControlerListeCommandeLivre implements Initializable{
	@FXML VBox pnItemsCommandeLivre = null;
	Connection connection = DBConnector.connector();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			String sql="select c1.numeroArrive,c1.dateArrive,c1.etatCommande,c2.nomCl,c2.prenomCl,p.reference,p.designation, p.prix,p.Qte_stock,l.qte_commande from commande c1,client c2,produit p,ligneCommande l where c1.codeClient=c2.codeClient and l.numeroArrive=c1.numeroArrive and l.reference=p.reference  and c1.etatCommande ='livrer'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemCommandeLiv.fxml").openStream());
				ControlerItemCommandeMagasinier controler = (ControlerItemCommandeMagasinier) loader.getController();
		        controler.numeroArrive.setText(""+rs.getInt(1));
				controler.dateArrive.setText(rs.getString(2));
				controler.etatCommande.setText(rs.getString(3));
				controler.nom.setText(rs.getString(4));
				controler.prenom.setText(rs.getString(5)); 
				controler.reference.setText(rs.getString(6));
				controler.designation.setText(rs.getString(7));
				controler.prix.setText(""+rs.getDouble(8));
				controler.Qte_Stock.setText(""+rs.getDouble(9));
				controler.Qte_command.setText(""+rs.getDouble(10));
				controler.edit.setDisable(true);
				controler.modify.setDisable(true);
				pnItemsCommandeLivre.getChildren().add(nodes);
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
