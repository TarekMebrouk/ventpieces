package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class facture {
	private int numeroFacture;
	private String dateFacture;
	private String dateEcheance;
	private bonLivraison BonLivraison;
	private String dateRelancement;
	private Double montant_tot;
	public static Connection connection = DBConnector.connector();

	
	public facture(int num,String dFact, String dEcheance, bonLivraison bon, String DateRelancement,double m) {
		numeroFacture=num;
		dateFacture = dFact;
		dateEcheance = dEcheance;
		BonLivraison = bon;
		dateRelancement = DateRelancement;
		montant_tot=m;
	}
	
	public facture(String dFact, String dEcheance, bonLivraison bon, String DateRelancement) throws SQLException {
		if (!dFact.equals("")&& !dEcheance.equals("") && bon != null && !DateRelancement.equals("")) {
			dateFacture = dFact;
			dateEcheance = dEcheance;
			BonLivraison = bon;
			dateRelancement = DateRelancement;
			montant_tot=MySQL.getTotaleFacture(bon.getNumeroBon());
			String sql = "insert into facture (dateFacture,dateEcheance,numeroBon,dateRelancement,montant_tot) values " + "('"
					+ dFact+ "','" + dEcheance + "'," + bon.getNumeroBon() + ",'" + dFact
					+ "',"+montant_tot+" )";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					int x = MySQL.getNumberAutoIncrement("facture", "numeroFacture");
					if (x != -1)
						this.numeroFacture = x;
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("facture ajouté avec succé ^_^");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("facture non ajouté ^_^");
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

	public int getNumeroFacture() {
		return numeroFacture;
	}

	public String getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(String dateFacture) {
		String sql = "update facture set dateFacture ='" + dateFacture + "' where numeroFacture=" + this.numeroFacture;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture modifié avec succé ^_^");
				alert.showAndWait();
				
				this.dateFacture = dateFacture;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture non modifié ^_^");
				alert.showAndWait();			
				}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getDateEcheance() {
		return dateEcheance;
	}

	public void setDateEcheance(String dateEcheance) {
		String sql = "update facture set dateEcheance ='" + dateEcheance + "' where numeroFacture="
				+ this.numeroFacture;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture modifié avec succé ^_^");
				alert.showAndWait();

				this.dateEcheance = dateEcheance;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public bonLivraison getBonLivraison() {
		return BonLivraison;
	}

	public void setBonLivraison(bonLivraison bonLivraison) {
		String sql = "update facture set numeroBon =" + bonLivraison.getNumeroBon() + " where numeroFacture="
				+ this.numeroFacture;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture modifié avec succé ^_^");
				alert.showAndWait();
				
				this.BonLivraison = bonLivraison;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		facture c = (facture) arg0;
		if (this.numeroFacture == c.numeroFacture)
			return true;
		else
			return false;
	}

	public String getDateRelancement() {
		return dateRelancement;
	}

	public void setDateRelancement(String dateRelancement) {
		String sql = "update facture set dateRelancement ='" + dateRelancement + "' where numeroFacture="
				+ this.numeroFacture;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture modifié avec succé ^_^");
				alert.showAndWait();
				
				this.dateRelancement = dateRelancement;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("facture non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public Double getMontant_tot() {
		return montant_tot;
	}

}
