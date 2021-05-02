package home;

import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.TypeUser;
import ProjectClass.user;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControlerSettingsUser implements Initializable{
    
	@FXML PasswordField password;
	@FXML ComboBox<String> typeUSer;
	@FXML TextField username;
	@FXML TextField nom;
	@FXML TextField prenom;
	ObservableList<String> Items=FXCollections.observableArrayList("serviceCommercial", "serviceMagasinier" , "serviceComptable");
	public void Modify(ActionEvent event) {
		if (ControlerServiceCommerciale.User !=null) {
			user User=ControlerServiceCommerciale.User;
		if(User.getUsername().equals(username.getText())==false) {
			ControlerServiceCommerciale.User.setUsername(username.getText());
		}
		if(User.getPassword().equals(password.getText())==false) {
			ControlerServiceCommerciale.User.setPassword(password.getText());
		}
		if(User.getNom().equals(nom.getText())==false) {
			ControlerServiceCommerciale.User.setNom(nom.getText());
		}
		if(User.getPrenom().equals(prenom.getText())==false) {
			ControlerServiceCommerciale.User.setPrenom(prenom.getText());
		}
		if(User.getType().toString().equals(typeUSer.getValue())==false) {
			ControlerServiceCommerciale.User.setType(TypeUser.valueOf(typeUSer.getValue()));
			 Node node = (Node) event.getSource();
			 Stage stage = (Stage) node.getScene().getWindow();
			 stage.hide();
		}
		
	}
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		typeUSer.setItems(Items);
		if(ControlerServiceCommerciale.User !=null) {
			username.setText(ControlerServiceCommerciale.User.getUsername());
			password.setText(ControlerServiceCommerciale.User.getPassword());
			nom.setText(ControlerServiceCommerciale.User.getNom());
			prenom.setText(ControlerServiceCommerciale.User.getPrenom());
			typeUSer.setValue(ControlerServiceCommerciale.User.getType().toString());
		}
	}
	
}
