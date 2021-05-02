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

public class ControlerListeBonLivraison implements Initializable {
	@FXML VBox pnlItemBons = null;
	Connection connection = DBConnector.connector();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		if (ControlerCommande.Commande!=null) {
		try {
			stmt = connection.createStatement();
			String sql="select * from bonLivraison where numeroArrive ="+ControlerCommande.Commande.getNumeroArrive();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemBonLivraison.fxml").openStream());
				ControlerItemBonLivraison controler = (ControlerItemBonLivraison) loader.getController();
		        controler.numeroBon.setText(""+rs.getInt(1));
		        controler.dateBon.setText(rs.getString(2));
		        controler.poid.setText(""+rs.getDouble(3));
		        controler.nbColis.setText(""+rs.getInt(4));
		        int x=rs.getInt(5);
		        controler.nomPreparateur.setText(rs.getString(6));
				pnlItemBons.getChildren().add(nodes);
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
