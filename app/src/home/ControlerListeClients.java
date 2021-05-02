package home;

import ProjectClass.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class ControlerListeClients implements Initializable {

	@FXML
	VBox pnItems = null;
	Connection connection = DBConnector.connector();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from client");
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("Item.fxml").openStream());
				ControlerItemClient controler = (ControlerItemClient) loader.getController();
				controler.SetcodeClient(rs.getInt(1));
				controler.SetNomC(rs.getString(2));
				controler.SetprenomC(rs.getString(3));
				controler.SetRaisonSociale(rs.getString(4));
				controler.SetAdress(rs.getString(5));
				controler.SetNumeroTel(rs.getString(6));
				controler.SetEtatClient(rs.getString(7));
                controler.createClient();
				pnItems.getChildren().add(nodes);
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
