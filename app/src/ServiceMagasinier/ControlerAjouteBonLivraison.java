package ServiceMagasinier;

import java.time.LocalTime;

import ProjectClass.bonLivraison;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ControlerAjouteBonLivraison {

	@FXML DatePicker dateBon;
	@FXML TextField poid;
	@FXML TextField nbColis;
	@FXML TextField nomPreparateur;
	
	public void ajouter(ActionEvent event) {
		if (dateBon.getValue()!=null && !poid.getText().equals("") && !nbColis.getText().equals("") && !nomPreparateur.getText().equals("")) {
		String time =LocalTime.now().getHour()+":"+LocalTime.now().getMinute()+":"+LocalTime.now().getSecond();
		String datetimeF=dateBon.getValue().getYear()+"-"+dateBon.getValue().getMonthValue()+"-"+dateBon.getValue().getDayOfMonth()+" "+time;
		if (ControlerCommande.Commande.getEtatCommande().toString().compareTo("preparer")==0) {
			bonLivraison bon=new bonLivraison (datetimeF,Double.parseDouble(poid.getText()),Integer.parseInt(nbColis.getText()),nomPreparateur.getText(),ControlerCommande.Commande);
		}else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle(null);
			alert.setContentText("Vous ne pouvez pas crée un BonLivraison pour cette commande car elle n'est pas preparer ^_^");
			alert.showAndWait();
		}
		}
	}
}
