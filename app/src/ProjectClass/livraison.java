package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class livraison {
	private bonLivraison BonLivraison;
	private produit Produit;
	private double Qte_livrer;
	public static Connection connection = DBConnector.connector();

	public livraison(bonLivraison bon, produit prod, double Qte) {
		if (bon != null && prod != null) {
			BonLivraison = bon;
			Produit = prod;
			Qte_livrer = Qte;
			String sql = "insert into livrer values (" + bon.getNumeroBon() + ",'" + prod.getReference() + "'," + Qte
					+ ")";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("livraison ajouté avec succé ^_^");
					alert.showAndWait();
					
				} else {
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("livraison non ajouté ^_^");
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

	public bonLivraison getBonLivraison() {
		return BonLivraison;
	}

	public void setBonLivraison(bonLivraison bonLivraison) {
		String sql = "update livrer set numeroBon =" + bonLivraison.getNumeroBon() + " where numeroBon="
				+ this.BonLivraison.getNumeroBon() + " and reference='" + this.Produit.getReference() + "' ";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison modifié avec succé ^_^");
				alert.showAndWait();
				
				this.BonLivraison = bonLivraison;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public produit getProduit() {
		return Produit;
	}

	public void setProduit(produit produit) {
		String sql = "update livrer set reference = '" + produit.getReference() + "' where numeroBon="
				+ this.BonLivraison.getNumeroBon() + " and reference='" + this.Produit.getReference() + "' ";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Produit = produit;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public double getQte_livrer() {
		return Qte_livrer;
	}

	public void setQte_livrer(double qte_livrer) {
		String sql = "update livrer set qte_livrer = " + qte_livrer + " where numeroBon="
				+ this.BonLivraison.getNumeroBon() + " and reference='" + this.Produit.getReference() + "' ";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Qte_livrer = qte_livrer;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("livraison non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
