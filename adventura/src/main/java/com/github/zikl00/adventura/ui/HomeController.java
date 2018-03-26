package com.github.zikl00.adventura.ui;

import java.util.Observer;

import com.github.zikl00.adventura.logika.*;
import java.util.Observable;
import java.util.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Filip Vencovsky, Libor Zíka
 *
 */
public class HomeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> vychodyZProstoru;
	@FXML private ListView<Postava> postavyVProstoru;
	@FXML private ListView<Vec> predmetyVProstoru;
	@FXML private ListView<Vec> batoh;
	private IHra hra;
	
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------\n"+textVstup.getText()+"\n--------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			textVypis.appendText("\n\n Konec hry \n");
			textVstup.setDisable(true);
			odesli.setDisable(true);
		}
	}
	
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		vychodyZProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		predmetyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci().values());
		postavyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostava());
		batoh.getItems().addAll(hra.getHerniPlan().getHrdinka().vratInventar().values());
		hra.getHerniPlan().addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		vychodyZProstoru.getItems().clear();
		predmetyVProstoru.getItems().clear();
		postavyVProstoru.getItems().clear();
		batoh.getItems().clear();
		
		vychodyZProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		predmetyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci().values());
		postavyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostava());
		batoh.getItems().addAll(hra.getHerniPlan().getHrdinka().vratInventar().values());
	}

}
