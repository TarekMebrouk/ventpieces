package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class produit {
	private String reference;
	private String designation;
	private double prix;
	private double Qte_Stock;
	private int rupture;
	public static Connection connection = DBConnector.connector();
    
	public produit (String ref, String des, double p, double qte,int r) {
		reference = ref;
		designation = des;
		prix = p;
		Qte_Stock = qte;
		rupture = r;
	}
	
	public produit(String ref, String des, double p, double qte) {
		if (!ref.equals("") && !des.equals("")) {
			reference = ref;
			designation = des;
			prix = p;
			Qte_Stock = qte;
			if (Qte_Stock ==0) rupture = 1;
			else rupture=0;
			String sql = "insert into produit values ('" + ref + "','" + des + "'," + p + "," + qte + ",0)";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("produit ajouté avec succé ^_^");
					alert.showAndWait();
					
				} else {
					
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("produit non ajouté ^_^");
					alert.showAndWait();				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "les champs sont pas remplies", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		String sql = "update produit set designation ='" + designation + "' where reference='" + this.reference + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit modifié avec succé ^_^");
				alert.showAndWait();
				
				this.designation = designation;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		String sql = "update produit set reference ='" + reference + "' where reference='" + this.reference + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit modifié avec succé ^_^");
				alert.showAndWait();
				
				this.reference = reference;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public double getQte_Stock() {
		return Qte_Stock;
	}

	public void setQte_Stock(double qte_Stock) {
		String sql = "update produit set qte_stock =" + qte_Stock + " where reference='" + this.reference + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit modifié avec succé ^_^");
				alert.showAndWait();
				
				this.Qte_Stock = qte_Stock;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		String sql = "update produit set prix =" + prix + " where reference='" + this.reference + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit modifié avec succé ^_^");
				alert.showAndWait();
				
				this.prix = prix;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getRupture() {
		return rupture;
	}

	public void setRupture(int rupture) {
		String sql = "update produit set rupture =" + rupture + " where reference='" + this.reference + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit modifié avec succé ^_^");
				alert.showAndWait();
				
				this.rupture = rupture;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("produit non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		produit p = (produit) arg0;
		return this.reference.equals(p.reference);
	}

}
