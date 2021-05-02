package application;
import home.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ProjectClass.TypeUser;
import ProjectClass.user;
import ServiceComptabilite.ControlerServiceComptabilite;
import ServiceMagasinier.ControlerServiceMagasinier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

	
	private double x=0;
	private double y=0;
	ObservableList<String> posteList = FXCollections.observableArrayList("serviceCommercial" , "serviceMagasinier" , "serviceComptable");
	
	@FXML
	private TextField name;
	@FXML
	private TextField fname;
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
	@FXML
	private ComboBox<String> poste;
	
	 @FXML
	 void dragged(MouseEvent event) {
	 	Node node = (Node) event.getSource();
	 	Stage stage = (Stage) node.getScene().getWindow();	 	
		
	 	stage.setX(event.getScreenX() - x);
	 	stage.setY(event.getScreenY() - y);	 	
	 }

	 @FXML
	 void pressed(MouseEvent event) {
	    x = event.getSceneX();
	   	y = event.getSceneY();
	 }
	
	 @FXML
	 void creeUser(ActionEvent event) throws IOException {
		 Alert chmpvide = new Alert(AlertType.ERROR);
		 chmpvide.setTitle("Champs Vide");
		 if (name.getText().compareTo("")==0) {
			 chmpvide.setContentText("Vous devez saisir un nom !");
			 chmpvide.show();
		 }
		 else {
			 if (fname.getText().compareTo("")==0) {
				 chmpvide.setContentText("Vous devez saisir un prenom !");
				 chmpvide.show();
			 }
			 else {
				 if (username.getText().compareTo("")==0) {
					 chmpvide.setContentText("Vous devez saisir un UserName !");
					 chmpvide.show();
				 }
				 else {
					 if (password.getText().compareTo("")==0) {
						 chmpvide.setContentText("Vous devez saisir un mot de passe !");
						 chmpvide.show();
					 }
					 else {
						 if (poste.getValue()==null) {
							 chmpvide.setContentText("Vous devez choisir un poste !");
							 chmpvide.show();
						 }else {
							 
		  user User=new user(username.getText(),password.getText(),name.getText(),fname.getText(),TypeUser.valueOf(poste.getValue()).toString());
					 String path = null;
					
			if (User.getType().compareTo(TypeUser.serviceCommercial)==0) {
				ControlerServiceCommerciale.User=User;
				path="/home/Home.fxml";
			}
			if(User.getType().compareTo(TypeUser.serviceMagasinier)==0) {
				ControlerServiceMagasinier.User=User;
			   path="/ServiceMagasinier/ServiceMagasinier.fxml";
			}
			if (User.getType().compareTo(TypeUser.serviceComptable)==0) {
				ControlerServiceComptabilite.User=User;
				path="/ServiceComptabilite/ServiceComptabilite.fxml";
			}
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(path));
			loader.load();
			Parent root = loader.getRoot();
			
			Scene scene=new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);

						        //drag it here
						        root.setOnMousePressed(event1 -> {
						            x = event1.getSceneX();
						            y = event1.getSceneY();
						        });
						        root.setOnMouseDragged(event1 -> {

						            primaryStage.setX(event1.getScreenX() - x);
						            primaryStage.setY(event1.getScreenY() - y);

						        });
						        primaryStage.show();
						 }
						 
					 }
				 }
			 }
		 }
	 }
	 
	 @FXML
	 void quitter(ActionEvent event) {
		 Node node = (Node) event.getSource();
		 Stage stage = (Stage) node.getScene().getWindow();
		 stage.hide();
	 }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		poste.setValue("");
		poste.setItems(posteList);
		
	}

}
