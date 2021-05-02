package home;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ResourceBundle;

import ProjectClass.DBConnector;
import ProjectClass.EtatCommande;
import ProjectClass.bonLivraison;
import ProjectClass.facture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;

public class ControlerAjouteFacture implements Initializable{
  
	static bonLivraison  bon;
	 @FXML VBox boxFactures=null;
	 @FXML DatePicker dateFacture;
	 @FXML DatePicker dateEcheance;
		Connection connection = DBConnector.connector();
		@Override
		public void initialize(URL arg0, ResourceBundle arg1)  {
			Statement stmt = null;
			ResultSet rs = null;
			Node nodes = null;
			if (ControlClient.Client !=null) {
			try {
				stmt = connection.createStatement();
				String sql="select b.numeroBon,b.dateBon,b.poid,b.nbColis,b.nomPreparateur,c.numeroArrive,c.dateArrive from "
						+ "bonLivraison b,commande c,livrer l where l.numeroBon=b.numeroBon and "
						+ " b.numeroArrive=c.numeroArrive and c.codeClient="+ControlClient.Client.getCode();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					FXMLLoader loader = new FXMLLoader();
					nodes = loader.load(getClass().getResource("ItemBonLivraison.fxml").openStream());
					ControlerBonLivraison controler = (ControlerBonLivraison) loader.getController();
					
	                controler.numeroBon.setText(""+rs.getInt(1));
					controler.dateBon.setText(rs.getString(2));
					controler.poid.setText(""+rs.getDouble(3));
					controler.nbColis.setText(""+rs.getInt(4));
					controler.nomPreparateur.setText(rs.getString(5));
					controler.numeroArrive.setText(""+rs.getInt(6));
					controler.DateArrive.setText(rs.getString(7));
					
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
		
		public void insertFacture(ActionEvent event) throws SQLException {
			if (dateFacture.getValue() !=null && dateEcheance.getValue() !=null) {
				
				String time =LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond();
				String datetimeF=dateFacture.getValue().getYear()+"-"+dateFacture.getValue().getMonthValue()+"-"+dateFacture.getValue().getDayOfMonth()+" "+time;
				
				String datetimeE=dateEcheance.getValue().getYear()+"-"+dateEcheance.getValue().getMonthValue()+"-"+dateEcheance.getValue().getDayOfMonth()+" "+time;
				facture f =new facture(datetimeF,datetimeE,bon,datetimeF);
			}
		}
	
}
