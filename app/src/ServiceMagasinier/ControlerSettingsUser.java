package ServiceMagasinier;

import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.TypeUser;
import ProjectClass.user;
import home.ControlerServiceCommerciale;
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
		if (ControlerServiceMagasinier.User !=null) {
			user User=ControlerServiceMagasinier.User;
		if(User.getUsername().equals(username.getText())==false) {
			ControlerServiceMagasinier.User.setUsername(username.getText());
		}
		if(User.getPassword().equals(password.getText())==false) {
			ControlerServiceMagasinier.User.setPassword(password.getText());
		}
		if(User.getNom().equals(nom.getText())==false) {
			ControlerServiceMagasinier.User.setNom(nom.getText());
		}
		if(User.getPrenom().equals(prenom.getText())==false) {
			ControlerServiceMagasinier.User.setPrenom(prenom.getText());
		}
		if(User.getType().toString().equals(typeUSer.getValue())==false) {
			ControlerServiceMagasinier.User.setType(TypeUser.valueOf(typeUSer.getValue()));
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
			username.setText(ControlerServiceMagasinier.User.getUsername());
			password.setText(ControlerServiceMagasinier.User.getPassword());
			nom.setText(ControlerServiceMagasinier.User.getNom());
			prenom.setText(ControlerServiceMagasinier.User.getPrenom());
			typeUSer.setValue(ControlerServiceMagasinier.User.getType().toString());
		}
	}
	
}
