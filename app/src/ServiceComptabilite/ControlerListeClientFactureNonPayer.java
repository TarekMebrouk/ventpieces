package ServiceComptabilite;

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

public class ControlerListeClientFactureNonPayer implements Initializable{
	Connection connection = DBConnector.connector();
	@FXML VBox listeItems;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			String sql="select distinct f.numeroFacture,f.dateFacture,f.dateEcheance,f.dateRelancement,f.montant_tot,c.codeClient,c.nomCl,c.prenomCl "
					+ " from client c,facture f,payement p  where p.numeroFacture=f.numeroFacture and p.codeClient=c.codeClient"
					+ " and f.montant_tot != (select sum(p1.montant) "
					+ " from payement p1  "
					+ " where p1.numeroFacture=f.numeroFacture and p1.codeClient=c.codeClient )";
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemClientFacture.fxml").openStream());
				ControlerItemClientFacture controler = (ControlerItemClientFacture) loader.getController();
				controler.select.setVisible(true);
				controler.numeroFacture.setText(""+rs.getInt(1));
				controler.dateFacture.setText(rs.getString(2));
				controler.DateEcheance.setText(rs.getString(3));
				controler.DateRelancement.setText(rs.getString(4));
				controler.montant_tot.setText(""+rs.getDouble(5));
				controler.codeClient.setText(""+rs.getInt(6));
				controler.nom.setText(rs.getString(7));
				controler.prenom.setText(rs.getString(8));
				listeItems.getChildren().add(nodes);
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
