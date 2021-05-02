package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class ligneCommande {
	private commande Commande;
	private produit Produit;
	private Double Qte_commande;
	public static Connection connection = DBConnector.connector();

	public ligneCommande(commande com, produit prod, double Qte) {
		if (com != null && prod != null) {
			Commande = com;
			Produit = prod;
			Qte_commande = Qte;
			String sql = "insert into ligneCommande values(" + com.getNumeroArrive() + ",'" + prod.getReference() + "',"
					+ Qte + ")";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("ligneCommande ajouté avec succé ^_^");
					alert.showAndWait();
					
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("ligneCommande non ajouté ^_^");
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

	public commande getCommande() {
		return Commande;
	}

	public void setCommande(commande commande) {
		String sql = "update ligneCommande set numeroArrive =" + commande.getNumeroArrive() + " where numeroArrive="
				+ this.Commande.getNumeroArrive() + " and reference ='" + this.Produit.getReference() + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Commande = commande;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public produit getProduit() {
		return Produit;
	}

	public void setProduit(produit produit) {
		String sql = "update ligneCommande set reference ='" + produit.getReference() + "' where numeroArrive="
				+ this.Commande.getNumeroArrive() + " and reference ='" + this.Produit.getReference() + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Produit = produit;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public Double getQte_commande() {
		return Qte_commande;
	}

	public void setQte_commande(Double qte_commande) {
		String sql = "update ligneCommande set qte_commande =" + qte_commande + " where numeroArrive="
				+ this.Commande.getNumeroArrive() + " and reference ='" + this.Produit.getReference() + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Qte_commande = qte_commande;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("ligneCommande non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
