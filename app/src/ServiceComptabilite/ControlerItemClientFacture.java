package ServiceComptabilite;

import java.io.IOException;
import java.sql.SQLException;

import ProjectClass.MySQL;
import ProjectClass.client;
import ServiceMagasinier.ControlerCommande;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControlerItemClientFacture {

	@FXML Button select;
	@FXML Label numeroFacture;
	@FXML Label dateFacture;
	@FXML Label DateEcheance;
	@FXML Label DateRelancement;
	@FXML Label montant_tot;
	@FXML Label codeClient;
	@FXML Label nom;
	@FXML Label prenom;
	private double x,y;
	
	public void select (ActionEvent e) throws NumberFormatException, SQLException, IOException {
		if(!numeroFacture.getText().equals("") && !codeClient.getText().equals("")) {
		client c=MySQL.getClient(Integer.parseInt(codeClient.getText()));
		ControlerClientFacture.Client=c;
		ControlerClientFacture.numeroFacture=Integer.parseInt(numeroFacture.getText());
			
		Stage primaryStage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("ClientFacture.fxml"));
		loader.load();
		ControlerClientFacture controler=(ControlerClientFacture)loader.getController();
		controler.ClientFactureInfo.setText("numeroFacture : "+ControlerClientFacture.numeroFacture+" CodeClient : "+ControlerClientFacture.Client.getCode());
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
