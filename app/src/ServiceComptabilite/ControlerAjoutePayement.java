package ServiceComptabilite;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import ProjectClass.MySQL;
import ProjectClass.payement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControlerAjoutePayement implements Initializable{

	@FXML DatePicker datePayement;
	@FXML TextField montant;
	@FXML Label dateFacture;
	@FXML Label dateEcheance;
	@FXML Label dateRelance;
	@FXML Label montant_tot;
	
	public void insertPayement(ActionEvent event) throws SQLException {
		if (datePayement.getValue() !=null && !montant.getText().equals("") ) {
		int h=LocalTime.now().getHour();
		String heure,minute,seconde,mm,dd;
	    if (h<10) {
	    	heure="0"+h;
	    }else {
	    	heure=""+h;
	    }
	    int m=LocalTime.now().getMinute();
	    if(m<10) {
	    	minute="0"+m;
	    }else {
	    	minute=""+m;
	    }
        int s=LocalTime.now().getSecond();
        if(s<10) {
           seconde="0"+s;
        }else  {
        	seconde=""+s;
        }
        
        
        if (datePayement.getValue().getMonthValue()<10) {
        	mm="0"+datePayement.getValue().getMonthValue();
        }else {
        	mm=""+datePayement.getValue().getMonthValue();
        }
        if(datePayement.getValue().getDayOfMonth()<10) {
        	dd="0"+datePayement.getValue().getDayOfMonth();
        }else {
        	dd=""+datePayement.getValue().getDayOfMonth();
        }
	    String time =heure+":"+minute+":"+seconde;
		String datePaye=datePayement.getValue().getYear()+"-"+mm+"-"+dd+" "+time;
		double paye=Double.parseDouble(montant.getText());
		System.out.println(datePaye);
		
		String dateFacture=MySQL.getDateFacture(ControlerClientFacture.numeroFacture);
		String dateEcheance=MySQL.getDateEcheance(ControlerClientFacture.numeroFacture);
		String dateRelancement=MySQL.getDateRelancement(ControlerClientFacture.numeroFacture);
		double montant_tot=MySQL.getmontantTotale(ControlerClientFacture.numeroFacture);

		 if (datePaye.compareTo(dateEcheance)<0) {
			 if (paye >MySQL.getRestePayer(ControlerClientFacture.numeroFacture,ControlerClientFacture.Client.getCode())) {
				 Alert alert1 = new Alert(Alert.AlertType.WARNING);
					alert1.setHeaderText(null);
					alert1.setTitle(null);
					alert1.setContentText("Votre prix introduit dépasse le montant totale de la facture +_+");
					alert1.showAndWait();
			 }else {
			 payement p=new payement (ControlerClientFacture.Client,ControlerClientFacture.numeroFacture,datePaye,paye);
			 }
		 }
		 else {
			 if(!MySQL.isRetard(ControlerClientFacture.numeroFacture)) {
					Alert alert1 = new Alert(Alert.AlertType.WARNING);
					alert1.setHeaderText(null);
					alert1.setTitle(null);
					alert1.setContentText("Vous avez Dépasez la Date Echeance ._.");
					alert1.showAndWait();
				 String sql = "update facture f set f.dateRelancement=(Date_add(f.DateEcheance,interval 30 Day)) where f.numeroFacture="+ControlerClientFacture.numeroFacture;
					try {
						if (MySQL.ExecuteRequette(sql)) {
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
							alert.setHeaderText(null);
							alert.setTitle(null);
							alert.setContentText("DateRelancement ajouté a la facture avec succé ^_^");
							alert.showAndWait();
						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setHeaderText(null);
							alert.setTitle(null);
							alert.setContentText("Facture non modifié .-.");
							alert.showAndWait();
						}
					} catch (SQLException e) {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setHeaderText(null);
						alert.setTitle(null);
						alert.setContentText("Data Base Not Connected -_-");
						alert.showAndWait();
					}
					if (paye >MySQL.getRestePayer(ControlerClientFacture.numeroFacture,ControlerClientFacture.Client.getCode())) {
						 Alert alert2 = new Alert(Alert.AlertType.WARNING);
							alert2.setHeaderText(null);
							alert2.setTitle(null);
							alert2.setContentText("Votre prix introduit dépasse le montant totale de la facture +_+");
							alert2.showAndWait();
					 }else {
					 payement p=new payement (ControlerClientFacture.Client,ControlerClientFacture.numeroFacture,datePaye,paye);
					 }
			 }else  {
					 if (paye >MySQL.getRestePayer(ControlerClientFacture.numeroFacture,ControlerClientFacture.Client.getCode())) {
						 Alert alert1 = new Alert(Alert.AlertType.WARNING);
							alert1.setHeaderText(null);
							alert1.setTitle(null);
							alert1.setContentText("Votre prix introduit dépasse le montant totale de la facture +_+");
							alert1.showAndWait();
					 }else {
					 payement p=new payement (ControlerClientFacture.Client,ControlerClientFacture.numeroFacture,datePaye,paye);
					 } 
				 
			 }
			 
		 }
		
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		try {
			String dateFacture=MySQL.getDateFacture(ControlerClientFacture.numeroFacture);
			String dateEcheance=MySQL.getDateEcheance(ControlerClientFacture.numeroFacture);
			String dateRelancement=MySQL.getDateRelancement(ControlerClientFacture.numeroFacture);
			double montant_tot=MySQL.getmontantTotale(ControlerClientFacture.numeroFacture);
			
	     this.dateFacture.setText(dateFacture);
	     this.dateEcheance.setText(dateEcheance);
	     this.montant_tot.setText(""+montant_tot);
	     if (dateFacture.compareTo(dateRelancement)==0) {
	    	 this.dateRelance.setText(" ");
	     }else  {
	    	 this.dateRelance.setText(dateRelancement);
	     }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
