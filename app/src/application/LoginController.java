package application;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import ProjectClass.MySQL;
import ProjectClass.TypeUser;
import ProjectClass.user;
import ServiceComptabilite.ControlerServiceComptabilite;
import ServiceMagasinier.ControlerServiceMagasinier;
import home.ControlerServiceCommerciale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController implements Initializable {
	
	private double x=0;
	private double y=0;
	
	@FXML
	private TextField username;
	@FXML
	private TextField password;
	
	
	@FXML
    void quitter(ActionEvent event) {
    	Node node = (Node) event.getSource();
	 	Stage stage = (Stage) node.getScene().getWindow();
	 	stage.hide();
    }
	
	 @FXML
	 void seConnecter(ActionEvent event) throws SQLException, IOException {
		 Alert chmpvide = new Alert(AlertType.ERROR);
		 chmpvide.setTitle("Champs Vide");
		 if (username.getText().compareTo("")==0) {
			 chmpvide.setContentText("Vous devez saisir un UserName");
			 chmpvide.show();
		 }
		 else {
			 if (password.getText().compareTo("")==0) {
				 chmpvide.setContentText("Vous devez saisir un Mot de passe");
				 chmpvide.show();
			 }
			 else {
				user User=MySQL.getUser(username.getText());
				if (User==null) {
					chmpvide.setContentText("Votre username est incorrect ");
					chmpvide.show();
				}else {
					if (User.getPassword().compareTo(password.getText())==0) {
						Alert chmpvide1 = new Alert(AlertType.INFORMATION);
						 chmpvide1.setTitle("login");
						 chmpvide1.setContentText("Login with succes ...");
							chmpvide1.show();
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
							((Node)event.getSource()).getScene().getWindow().hide();
							Stage primaryStage = new Stage();
							FXMLLoader loader = new FXMLLoader();
							loader.setLocation(getClass().getResource(path));
							loader.load();
							Parent root = loader.getRoot();
							
							Scene scene=new Scene(root);
							System.out.println(scene);
							
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
					}else {
						chmpvide.setContentText("Votre password est incorrect ");
						chmpvide.show();
					}
				}
			 }
		 }
	 }
	 
	 @FXML
	 void creeCompte(ActionEvent event) throws IOException {
		 Stage primaryStage = new Stage();
		/* Node node = (Node) event.getSource();
		 Stage stage = (Stage) node.getScene().getWindow();
		 stage.hide();*/
		 
		 Parent cnx = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
		 Scene scene = new Scene(cnx);
		 scene.setFill(Color.TRANSPARENT);
		 primaryStage.setScene(scene);
		 primaryStage.initStyle(StageStyle.TRANSPARENT);
		 primaryStage.show();
	 }
	
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
	    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
