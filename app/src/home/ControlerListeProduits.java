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

public class ControlerListeProduits implements Initializable{
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

}
