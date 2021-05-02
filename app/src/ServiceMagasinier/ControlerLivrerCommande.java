package ServiceMagasinier;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import ProjectClass.DBConnector;
import ProjectClass.EtatCommande;
import ProjectClass.MySQL;
import ProjectClass.bonLivraison;
import ProjectClass.livraison;
import ProjectClass.produit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ControlerLivrerCommande implements Initializable{

	@FXML TextField qte_livrer;
	@FXML VBox listeBons;
	static bonLivraison bon;
	static produit Produit;
	Connection connection = DBConnector.connector();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Statement stmt = null;
		ResultSet rs = null;
		Node nodes = null;
		if (ControlerCommande.Commande != null) {
		try {
			stmt = connection.createStatement();
			String sql="select b.numeroBon,b.dateBon,b.poid,b.nbColis,b.nomPreparateur,p.reference,p.designation,p.prix,p.Qte_Stock "
					+ "from bonLivraison b,produit p,ligneCommande l "
					+ "where b.numeroArrive=l.numeroArrive and l.reference=p.reference  "
					+ "and l.numeroArrive ="+ControlerCommande.Commande.getNumeroArrive();
			rs=stmt.executeQuery(sql);
			while (rs.next()) {
				FXMLLoader loader = new FXMLLoader();
				nodes = loader.load(getClass().getResource("ItemBonProduit.fxml").openStream());
				ControlerItemBonProduit controler = (ControlerItemBonProduit) loader.getController();
				controler.numeroBon.setText(""+rs.getInt(1));
		        controler.dateBon.setText(rs.getString(2));
		        controler.poid.setText(""+rs.getDouble(3));
		        controler.nbColis.setText(""+rs.getInt(4));
		        controler.nomPreparateur.setText(rs.getString(5));
		        controler.reference.setText(rs.getString(6));
		        controler.designation.setText(rs.getString(7));
		        controler.prix.setText(""+rs.getDouble(8));
		        controler.Qte_stock.setText(""+rs.getDouble(9));
		        listeBons.getChildren().add(nodes);
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
	
	public void livrer(ActionEvent e) throws SQLException {
		if (Produit !=null && bon !=null && !qte_livrer.getText().equals("")  ) {
			Double qte=Double.parseDouble(qte_livrer.getText());
			if (qte<=Produit.getQte_Stock()) {
				livraison l=new livraison (bon,Produit,qte);	
				Double x=Produit.getQte_Stock()-qte;
		        Produit.setQte_Stock(x);
		        MySQL.changerEtat(ControlerCommande.Commande);
		      if(x==0) {
		    	Produit.setRupture(1);  
		      }
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("Votre Quantité saisie depasse la Quantité en Stock du produit a livré ._.");
				alert.showAndWait();
			}

			
		}
	}
	
}