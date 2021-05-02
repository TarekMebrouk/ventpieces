package ServiceMagasinier;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import ProjectClass.DBConnector;
import home.ControlerItemProduit;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ControlerListeProduitenRupture implements Initializable {
	@FXML
	VBox listeProduits = null;
	Connection connection = DBConnector.connector();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select reference,designation,prix,Qte_Stock from produit where rupture=1");
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemProduitRupture.fxml").openStream());
				ControlerItemProduitenRupture controler = (ControlerItemProduitenRupture) loader.getController();
                controler.reference.setText(rs.getString(1));
                controler.designation.setText(rs.getString(2));
                controler.prix.setText(""+rs.getDouble(3));
                controler.Qte_Stock.setText(""+rs.getDouble(4));
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

}
