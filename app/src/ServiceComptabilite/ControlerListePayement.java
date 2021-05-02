package ServiceComptabilite;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import ProjectClass.DBConnector;
import ProjectClass.MySQL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ControlerListePayement implements Initializable{
	Connection connection = DBConnector.connector();
	@FXML VBox listeItems;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			String sql="select datePayement,montant from payement where codeClient="+ControlerClientFacture.Client.getCode()+" and numeroFacture="+ControlerClientFacture.numeroFacture;
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("payement.fxml").openStream());
				ControlerPayement controler = (ControlerPayement) loader.getController();
				controler.DatePayement.setText(rs.getString(1));
				controler.montant.setText(""+rs.getDouble(2));
				if (MySQL.isRetard(ControlerClientFacture.numeroFacture)) controler.retard.setText("true");
				else controler.retard.setText("false");
				controler.reste.setText(""+MySQL.getRestePayer(ControlerClientFacture.numeroFacture, ControlerClientFacture.Client.getCode()));
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
