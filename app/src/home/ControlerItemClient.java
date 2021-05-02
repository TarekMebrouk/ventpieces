package home;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ProjectClass.*;

public class ControlerItemClient {

	@FXML Label codeClient;
	@FXML Label nomC;
	@FXML Label prenomC;
	@FXML Label raisonSociale;
	@FXML Label adress;
	@FXML Label numeroTel;
	@FXML Label etatClient;
	@FXML Button editClient;
	
	private double x,y;
	client Client;

	public void createClient() {
		Client = new client(Integer.parseInt(codeClient.getText()), nomC.getText(), prenomC.getText(),
				raisonSociale.getText(), adress.getText(), numeroTel.getText(), etatClient.getText());
	}

	public void ClientInfo(ActionEvent e) throws IOException {
		if (Client != null) {
			try {	
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("client.fxml"));
			loader.load();
			ControlClient controler=(ControlClient)loader.getController();
			controler.Client=Client;
			controler.ClientInfo.setText("Code :"+Client.getCode()+" Nom:  "+Client.getNom()+" Prenom:  "+Client.getPrenom());
			Parent root = loader.getRoot();
			
			Scene scene=new Scene(root);
			scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
	        //drag it here
	        root.setOnMousePressed(event -> {
	            x = event.getSceneX();
	            y = event.getSceneY();
	        });
	        root.setOnMouseDragged(event -> {

	            primaryStage.setX(event.getScreenX() - x);
	            primaryStage.setY(event.getScreenY() - y);

	        });
	        primaryStage.show();
			
			}catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "loader : "+e1.getMessage());
			}
		}
	}

	public void SetcodeClient(int code) {
		codeClient.setText("" + code);
	}

	public void SetNomC(String str) {
		nomC.setText(str);
	}

	public void SetprenomC(String str) {
		prenomC.setText(str);
	}

	public void SetRaisonSociale(String str) {
		raisonSociale.setText(str);
	}

	public void SetNumeroTel(String str) {
		numeroTel.setText(str);
	}

	public void SetEtatClient(String str) {
		etatClient.setText(str);
	}

	public void SetAdress(String str) {
		adress.setText(str);
	}

}
