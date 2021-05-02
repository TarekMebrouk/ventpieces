package ProjectClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;

public class MySQL {
	public static Connection connection = DBConnector.connector();

	public static boolean isDbConnected() {
		try {
			return !connection.isClosed();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}

	public static boolean ExecuteRequette(String sql) throws SQLException {
		Statement stm = null;
		try {
			stm = connection.createStatement();
			stm.execute(sql);
			return true;
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle(null);
			alert.setContentText("Exception in DataBase please Check your information");
			alert.showAndWait();
			return false;
		} finally {
			stm.close();
		}

	}

	public static int getNumberAutoIncrement(String NameTable, String NameCode) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select max(" + NameCode + ") from " + NameTable;
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getInt(1);
			else
				return -1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return -1;
	}

	public static double getTotaleFacture(int numeroBon) throws SQLException {

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select sum(p.prix*l.qte_livrer) from bonlivraison b,commande c,livrer l,produit p where "
					+ "b.numeroArrive=c.numeroArrive and l.numeroBon=b.numeroBon and l.reference=p.reference and b.numeroBon="
					+ numeroBon;
			rs = stmt.executeQuery(sql);
			if (rs.next())
				return rs.getDouble(1);
			else
				return -1;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return -1;

	}

	public static commande getCommande(int numeroCommande) throws SQLException {
		commande c=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select * from commande where numeroArrive="+numeroCommande;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c=new commande(rs.getInt(1),rs.getString(2),rs.getString(3),getClient(rs.getInt(4)));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return c;
		
	}

	public static client getClient(int numeroClient) throws SQLException {
       client c=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select * from client where codeClient=" + numeroClient;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new client(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return c;

	}

	public static produit getProduit(String reference) throws SQLException {
        produit p=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select * from produit where reference='" + reference + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			  p =new produit(rs.getString(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),rs.getInt(5));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return p;

	}

	public static user getUser(String username) throws SQLException {
		user u=null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select * from user where username= '" + username + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				u=new user(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return u;

	}

	public static double getQte_commande(produit p ,commande c) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.createStatement();
			String sql = "select qte_commande from ligneCommande where reference='" + p.getReference() + "' and numeroArrive="+c.getNumeroArrive();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return rs.getDouble(1);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return -1;

	}
	public static bonLivraison getBon(int numeroBon,commande c) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		bonLivraison bon=null;
		try {
			stmt = connection.createStatement();
			String sql = "select dateBon,poid,nbColis,nomPreparateur from bonlivraison where numeroBon="+numeroBon;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			bon =new bonLivraison(numeroBon,rs.getString(1),rs.getDouble(2),rs.getInt(3),rs.getString(4),c);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return bon;
	}
	public static void changerEtat(commande c) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet res=null;
		int i = -1,j = -2;
		try {
			stmt = connection.createStatement();
			
			String sql = "select count(b.numeroArrive) from livrer l,bonlivraison b where "
					+ "b.numeroBon=l.numeroBon and b.numeroArrive="+c.getNumeroArrive();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 i=rs.getInt(1);
			}
			System.out.println(i);
			 sql = "select count(numeroArrive) from ligneCommande where "
					+ "numeroArrive="+c.getNumeroArrive();
			res = stmt.executeQuery(sql);
			while (res.next()) {
			 j=res.getInt(1);
			}
			System.out.println(i+" "+j);
			if (i!=-1 && j!=-2) {
			if (i==j) c.setEtatCommande(EtatCommande.livrer);
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
			res.close();
		}
	}
	public static boolean isRetard(int numeroFacture) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		String date1=null,date2=null;
		try {
			stmt = connection.createStatement();
			
			String sql = "select dateFacture,dateRelancement from facture where numeroFacture="+numeroFacture;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 date1=rs.getString(1);
			 date2=rs.getString(2);
			}
			
			if (date1.equals(date2)) {
				return false;
			}else {
				return true;
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return false;
		
	}
	public static String getDateEcheance(int numeroFacture) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		String date1=null;
		try {
			stmt = connection.createStatement();
			
			String sql = "select dateEcheance from facture where numeroFacture="+numeroFacture;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 date1=rs.getString(1);
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return date1;
		
	}
	
	public static String getDateRelancement(int numeroFacture) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		String date1=null;
		try {
			stmt = connection.createStatement();
			
			String sql = "select dateRelancement from facture where numeroFacture="+numeroFacture;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 date1=rs.getString(1);
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return date1;
		
	}
	
	public static String getDateFacture(int numeroFacture) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		String date1=null;
		try {
			stmt = connection.createStatement();
			
			String sql = "select dateFacture from facture where numeroFacture="+numeroFacture;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 date1=rs.getString(1);
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return date1;
		
	}
	
	public static double getmontantTotale(int numeroFacture) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		double m = 0;
		try {
			stmt = connection.createStatement();
			
			String sql = "select montant_tot from facture where numeroFacture="+numeroFacture;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 m=rs.getDouble(1);
			}
       
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return m;
		
	}
	
	public static boolean facturePayé (int numeroFacture ,int numeroClient) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		double m = 0;
		try {
			stmt = connection.createStatement();
			
			String sql = "select sum(montant) from payement where numeroFacture="+numeroFacture+" and codeClient="+numeroClient;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 m=rs.getDouble(1);
			}
        if (m==getmontantTotale(numeroFacture)) {
        	return true;
        }
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return false;
	}
	
	public static double getRestePayer(int numeroFacture ,int numeroClient) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null; 
		double m = 0;
		try {
			stmt = connection.createStatement();
			
			String sql = "select sum(montant) from payement where numeroFacture="+numeroFacture+" and codeClient="+numeroClient;
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
			 m=rs.getDouble(1);
			}
			double d=getmontantTotale(numeroFacture);
			m=d-m;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DataBase not Connected", "Erreur", JOptionPane.INFORMATION_MESSAGE);
		} finally {
			stmt.close();
			rs.close();
		}
		return m;
	}
}
