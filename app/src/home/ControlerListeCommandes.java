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

public class ControlerListeCommandes implements Initializable{
	
	@FXML VBox pnItemsCommande = null;
	Connection connection = DBConnector.connector();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select c1.numeroArrive,c1.dateArrive,c1.etatCommande,c1.codeClient,c2.nomCl,c2.prenomCl "
					+ "from commande c1,client c2 where c1.codeClient=c2.codeClient");
			while (rs.next()) {
				System.out.println("je ss rentré");
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemCommande.fxml").openStream());
				ControlerItemCommande controler = (ControlerItemCommande) loader.getController();
				controler.numeroArrive.setText(""+rs.getInt(1));
				controler.dateArrive.setText(rs.getString(2));
				controler.etatCommande.setText(rs.getString(3));
				controler.codeClient.setText(rs.getString(4));
				controler.Nom.setText(rs.getString(5));
				controler.prenom.setText(rs.getString(6));
                pnItemsCommande.getChildren().add(nodes);
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

