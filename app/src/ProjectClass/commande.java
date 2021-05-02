package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class commande {
	private int numeroArrive;
	private String dateArrive;
	private EtatCommande etatCommande;
	private client commandeure;
	public static Connection connection = DBConnector.connector();

	public commande(int num,String d,String etat,client c) {
		numeroArrive=num;
		dateArrive = d;
		etatCommande = EtatCommande.valueOf(etat);
		commandeure = c;
	}
	
	public commande(String d, client c) {
		if (c != null && !d.equals("")) {
			dateArrive = d;
			etatCommande = EtatCommande.enAttent;
			commandeure = c;
			String sql = "insert into commande (dateArrive,etatCommande,codeClient) values('" +d+ "','"
					+ EtatCommande.enAttent.toString() + "'," + c.getCode() + ")";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					int x = MySQL.getNumberAutoIncrement("commande", "numeroArrive");
					if (x != -1)
						this.numeroArrive = x;
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("commande ajouté avec succé ^_^");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("commande non ajouté  ^_^");
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

	public String getDateArrive() {
		return dateArrive;
	}

	public void setDateArrive(String dateArrive) {
		String sql = "update commande set dateArrive ='" + dateArrive + "' where numeroArrive="
				+ this.numeroArrive;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande modifié avec succé ^_^");
				alert.showAndWait();
				this.dateArrive = dateArrive;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getNumeroArrive() {
		return numeroArrive;
	}

	public client getCommandeure() {
		return commandeure;
	}

	public void setCommandeure(client commandeure) {
		String sql = "update commande set codeClient =" + commandeure.getCode() + " where numeroArrive="
				+ this.numeroArrive;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande modifié avec succé ^_^");
				alert.showAndWait();
				this.commandeure = commandeure;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public EtatCommande getEtatCommande() {
		return etatCommande;
	}

	public void setEtatCommande(EtatCommande etatCommande) {
		String sql = "update commande set etatCommande ='" + etatCommande.toString() + "' where numeroArrive=" + this.numeroArrive;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande modifié avec succé ^_^");
				alert.showAndWait();
				this.etatCommande = etatCommande;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("commande non modifié^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		commande c = (commande) arg0;
		if (this.numeroArrive == c.numeroArrive)
			return true;
		else
			return false;
	}
}
