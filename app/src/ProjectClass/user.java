package ProjectClass;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class user {
	private String nom;
	private String prenom;
	private String username;
	private String password;
	private TypeUser type;
	public static Connection connection = DBConnector.connector();

	public user(String Username, String Password, String Nom, String Prenom,String t) {
		nom = Nom;
		prenom = Prenom;
		username = Username;
		password = Password;
		type =TypeUser.valueOf(t);
	}
	public user(String Username, String Password, String Nom, String Prenom, TypeUser t) {
		if (!Nom.equals("") && !Prenom.equals("") && !Username.equals("") && !Password.equals("")) {
			nom = Nom;
			prenom = Prenom;
			username = Username;
			password = Password;
			type = t;
			String sql = "insert into user values ('" + Username + "','" + Password + "','" + Nom + "','" + Prenom
					+ "','" + t.toString() + "')";
			try {
				if (MySQL.ExecuteRequette(sql)) {
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("user ajouté avec succé ^_^");
					alert.showAndWait();
					
				} else {
					
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle(null);
					alert.setContentText("user non ajouté ^_^");
					alert.showAndWait();
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
			
			}
		} else {
			  JOptionPane.showMessageDialog(null,"les champs sont pas remplies","Warning",JOptionPane.WARNING_MESSAGE);
		}
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		String sql = "update user set prenom ='" + prenom + "' where username='" + this.username + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user modifié avec succé ^_^");
				alert.showAndWait();
				
				alert.showAndWait();				this.prenom = prenom;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user non modifié ^_^");			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		String sql = "update user set nom ='" + nom + "' where username='" + this.username + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user modifié avec succé ^_^");
				alert.showAndWait();
								this.nom = nom;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user non modifié ^_^");			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		String sql = "update user set username ='" + username + "' where username='" + this.username + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user modifié avec succé ^_^");
				alert.showAndWait();
								this.username = username;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user non modifié ^_^");			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		String sql = "update user set password ='" + password + "' where username='" + this.username + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user modifié avec succé ^_^");
				alert.showAndWait();
							this.password = password;
			} else {
				
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user non modifié ^_^");			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public TypeUser getType() {
		return type;
	}

	public void setType(TypeUser type) {
		String sql = "update user set typeUser ='" + type.toString() + "' where username='" + this.username + "'";
		try {
			if (MySQL.ExecuteRequette(sql)) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user modifié avec succé ^_^");
				alert.showAndWait();
							this.type = type;
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText(null);
				alert.setTitle(null);
				alert.setContentText("user non modifié ^_^");			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"DataBase not Connected","Erreur",JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
