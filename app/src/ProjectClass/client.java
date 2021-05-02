package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class client {
	private int code;
	private String nom;
	private String prenom;
	private String raisonSociale;
	private String adress;
	private String numeroTel;
	private String etatClient;
	public static Connection connection = DBConnector.connector();

	public client(int mat,String Nom, String Prenom, String rsos, String adr, String num, String etat) {
        code=mat;
		nom = Nom;
		prenom = Prenom;
		raisonSociale = rsos;
		adress = adr;
		numeroTel = num;
		etatClient = etat;
	}
	
	public client(String Nom, String Prenom, String rsos, String adr, String num, String etat) {
		if (!Nom.equals("") && !Prenom.equals("") && !rsos.equals("") && !adr.equals("") && !num.equals("")
				&& !etat.equals("")) {
			nom = Nom;
			prenom = Prenom;
			raisonSociale = rsos;
			adress = adr;
			numeroTel = num;
			etatClient = etat;
			String sql = "insert into client (nomCl,prenomCl,raisonSociale,adress,numeroTel,etatClient) values" + "('"
					+ Nom + "','" + Prenom + "','" + rsos + "','" + adr + "','" + num + "','" + etat + "')";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					int x = MySQL.getNumberAutoIncrement("client", "codeClient");
					if (x != -1)
						this.code = x;
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("client ajouté avec succé ^_^");
					alert.showAndWait();
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("client non ajouté ^_^");
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

	public int getCode() {
		return code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		String sql = "update client set nomCl ='" + nom + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.nom = nom;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client non  modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		String sql = "update client set prenomCl ='" + prenom + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.prenom = prenom;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client non  modifié  ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getRaisonSociale() {
		return raisonSociale;
	}

	public void setRaisonSociale(String raisonSociale) {
		String sql = "update client set raisonSociale ='" + raisonSociale + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.raisonSociale = raisonSociale;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client non modifié  ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		String sql = "update client set numeroTel ='" + numeroTel + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.numeroTel = numeroTel;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  non modifié^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		String sql = "update client set adress ='" + adress + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.adress = adress;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client non  modifié ^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getEtatClient() {
		return etatClient;
	}

	public void setEtatClient(String etatClient) {
		String sql = "update client set etatClient ='" + etatClient + "' where codeClient=" + this.code;
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client  modifié avec succé ^_^");
				alert.showAndWait();
				this.etatClient = etatClient;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("client non  modifié^_^");
				alert.showAndWait();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		client c = (client) arg0;
		if (this.code == c.code)
			return true;
		else
			return false;
	}

}
