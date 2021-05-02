package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class payement {
	private String datePayement;
	private double montant;
	private client Client;
	private int Facture;
	public static Connection connection = DBConnector.connector();

	public payement(client c, int f, String d, double p) {
		if (c != null  && d != null) {
			datePayement = d;
			montant = p;
			Client = c;
			Facture = f;
			String sql = "insert into payement values (" + c.getCode() + "," +f+ ",'"
					+ d+ "'," + p + ")";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("payement ajouté avec succé ^_^");
					alert.showAndWait();
					
				} else {
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("payement non ajouté ^_^");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "les champs sont pas remplies", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public String getDatePayement() {
		return datePayement;
	}

	public void setDatePayement(String datePayement) {
		String sql = "update payement set datePayement ='" + datePayement + "' where codeClient="
				+ this.Client.getCode() +"and numeroFacture=" +Facture
				+ " and datePayement='" + this.datePayement + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement modifié avec succé ^_^");
				alert.showAndWait();
				
				this.datePayement = datePayement;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		String sql = "update payement set montant =" + montant + " where codeClient=" + this.Client.getCode() + ""
				+ "and numeroFacture=" +Facture+ " and datePayement='" + this.datePayement
				+ "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement modifié avec succé ^_^");
				alert.showAndWait();
			
				this.montant = montant;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public client getClient() {
		return Client;
	}

	public void setClient(client client) {
		String sql = "update payement set codeClient =" + client.getCode() + " where codeClient="
				+ this.Client.getCode() + "" + "and numeroFacture=" +Facture
				+ " and datePayement='" + this.datePayement + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Client = client;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getFacture() {
		return Facture;
	}

	public void setFacture(int facture) {
		String sql = "update payement set numeroFacture =" +facture+ " where codeClient="
				+ this.Client.getCode() + "" + "and numeroFacture=" +Facture
				+ " and datePayement='" + this.datePayement + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Facture = facture;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("payement non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
