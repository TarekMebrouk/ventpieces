package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class bonLivraison {
	private int numeroBon;
	private String dateBon;
	private int nbColis;
	private double poid;
	private String nomPreparateur;
	private commande Commande;
	public static Connection connection = DBConnector.connector();

	public bonLivraison(int num,String d, double Poid, int nb, String nomp, commande c) {
		numeroBon=num;
		dateBon = d;
		nbColis = nb;
		nomPreparateur = nomp;
		Commande = c;
		poid = Poid;
	}
	
	public bonLivraison(String d, double Poid, int nb, String nomp, commande c) {
		if (!d.equals("") && !nomp.equals("") && c != null) {
			dateBon = d;
			nbColis = nb;
			nomPreparateur = nomp;
			Commande = c;
			poid = Poid;
			String sql = "insert into bonlivraison (dateBon,poid,nbColis,numeroArrive,nomPreparateur) values ('"
					+ d + "'," + Poid + "," + nb + ","+ c.getNumeroArrive() +",'"+nomp+"')";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					int x = MySQL.getNumberAutoIncrement("bonlivraison", "numeroBon");
					if (x != -1)
						this.numeroBon = x;
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("bon de livraison ajouté avec succé ^_^");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("bon de livraison non ajouté ^_^");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("data Base not connected ^_^");
				alert.showAndWait();
			}
		} else {
			JOptionPane.showMessageDialog(null, "les champs sont pas remplies", "Warning", JOptionPane.WARNING_MESSAGE);
		}
	}

	public int getNumeroBon() {
		return numeroBon;
	}

	public String getDateBon() {
		return dateBon;
	}

	public void setDateBon(String dateBon) {
		String sql = "update bonlivraison set dateBon ='" + dateBon + "' where numeroBon=" + this.numeroBon;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison modifié avec succé ^_^");
				alert.showAndWait();
				this.dateBon = dateBon;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison non  modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public int getNbColis() {
		return nbColis;
	}

	public void setNbColis(int nbColis) {
		String sql = "update bonlivraison set nbColis =" + nbColis + " where numeroBon=" + this.numeroBon;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison modifié avec succé ^_^");
				alert.showAndWait();
				this.nbColis = nbColis;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getPreparateur() {
		return nomPreparateur;
	}

	public void setPreparateur(String nomPreparateur) {
		String sql = "update bonlivraison set  nomPreparateur ='" +nomPreparateur+ "' where numeroBon="
				+ this.numeroBon;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison modifié avec succé ^_^");
				alert.showAndWait();
				this.nomPreparateur = nomPreparateur;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison non modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public commande getCommande() {
		return Commande;
	}

	public void setCommande(commande commande) {
		String sql = "update bonlivraison set  numeroArrive =" + commande.getNumeroArrive() + " where numeroBon="
				+ this.numeroBon;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison modifié avec succé ^_^");
				alert.showAndWait();
				this.Commande = commande;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison non modifié ^_^");
				alert.showAndWait();			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		bonLivraison c = (bonLivraison) arg0;
		if (this.numeroBon == c.numeroBon)
			return true;
		else
			return false;
	}

	public double getPoid() {
		return poid;
	}

	public void setPoid(double poid) {
		String sql = "update bonlivraison set poid =" + poid + " where numeroBon=" + this.numeroBon;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison modifié avec succé ^_^");
				alert.showAndWait();
				this.poid = poid;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("bon de livraison non modifié^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
