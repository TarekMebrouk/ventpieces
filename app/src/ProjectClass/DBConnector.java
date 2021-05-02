
package ProjectClass;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import javafx.scene.control.Alert;


public class DBConnector {
	public static Connection connector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agencedb", "root", "1234");
			return connection;
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle(null);
			alert.setContentText("Base Donnée not connected ");
			alert.showAndWait();
			return null;
		}

	}
}
