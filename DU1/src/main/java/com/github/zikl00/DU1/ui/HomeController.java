package com.github.zikl00.DU1.ui;

import java.util.Observer;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Random;

import com.github.zikl00.DU1.logika.*;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury (měl by... :D )
 * 
 * @author Libor Zíka
 *
 */
public class HomeController extends BorderPane implements Observer {
	
	@FXML private BorderPane bordelPane;
	@FXML private TextArea badTry;
	@FXML private TextArea goodTry;
	@FXML private TextArea engWord;
	@FXML private ImageView img1;
	@FXML private ImageView img2;
	@FXML private ImageView img3;
	@FXML private ImageView img4;
	@FXML private ImageView img5;
	@FXML private ImageView img6;
	@FXML private Button buttonGuess;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	@FXML private Button button6;
	private Hra hra;
	private String hadaneSlovo = null;
	
	/**
     *  Metoda k inicializaci hry. Načte všechny potřebné prvky
     *  GUI a přidá observery.
     */
	public void inicializuj(Hra hra) {
		this.hra = hra;
		
		naplnitTextAreas();
		pridejObservers();
		setButtonsInactive();
		setObrazky();
		engWord.setText("");
	}
	public void naplnitTextAreas() {
		badTry.setText(hra.ukazSpatnePokusy());
		goodTry.setText(hra.ukazSpravnePokusy());
	}
	public void setObrazky() {
		Image image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(0).getCzPreklad() + ".png").toUri().toString());
		img1.setDisable(false);
		img1.setImage(image);
		Tooltip.install(img1, new Tooltip(hra.vratSlovicka().get(0).getCzPreklad()));
		image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(1).getCzPreklad() + ".png").toUri().toString());
		img2.setDisable(false);
		img2.setImage(image);
		Tooltip.install(img2, new Tooltip(hra.vratSlovicka().get(1).getCzPreklad()));
		image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(2).getCzPreklad() + ".png").toUri().toString());
		img3.setDisable(false);
		img3.setImage(image);
		Tooltip.install(img3, new Tooltip(hra.vratSlovicka().get(2).getCzPreklad()));
		image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(3).getCzPreklad() + ".png").toUri().toString());
		img4.setDisable(false);
		img4.setImage(image);
		Tooltip.install(img4, new Tooltip(hra.vratSlovicka().get(3).getCzPreklad()));
		image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(4).getCzPreklad() + ".png").toUri().toString());
		img5.setDisable(false);
		img5.setImage(image);
		Tooltip.install(img5, new Tooltip(hra.vratSlovicka().get(4).getCzPreklad()));
		image = new Image(Paths.get("obrazky/" + hra.vratSlovicka().get(5).getCzPreklad() + ".png").toUri().toString());
		img6.setDisable(false);
		img6.setImage(image);
		Tooltip.install(img6, new Tooltip(hra.vratSlovicka().get(5).getCzPreklad()));
	}
	private void pridejObservers() {
		//přidání observerů
		hra.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		naplnitTextAreas();
	}
	public void buttonGuess() {
		int trigger = randInt(7) - 1;
		for(Integer klic : hra.vratSlovicka().keySet() ){
			if (trigger == klic) {
				engWord.setText(hra.vratSlovicka().get(klic).getAngPreklad());
				hadaneSlovo = hra.vratSlovicka().get(klic).getCzPreklad();
			}
		}
		if (hadaneSlovo == null) {
			buttonGuess();
		}
		buttonGuess.setDisable(true);
		setButtonsActive();
	}
	public void setButtonsInactive(){
		button1.setDisable(true);
		button2.setDisable(true);
		button3.setDisable(true);
		button4.setDisable(true);
		button5.setDisable(true);
		button6.setDisable(true);
	}
	public void setButtonsActive(){
		button1.setDisable(false);
		button2.setDisable(false);
		button3.setDisable(false);
		button4.setDisable(false);
		button5.setDisable(false);
		button6.setDisable(false);
	}
	public void setPoUhadnuti() {
		setButtonsInactive();
		buttonGuess.setDisable(false);
		engWord.setText("");
		hadaneSlovo = null;
		
	}
	public void handleButton1() {
		if (hadaneSlovo == hra.vratSlovicka().get(0).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button1.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void handleButton2() {
		if (hadaneSlovo == hra.vratSlovicka().get(1).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button2.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void handleButton3() {
		if (hadaneSlovo == hra.vratSlovicka().get(2).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button3.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void handleButton4() {
		if (hadaneSlovo == hra.vratSlovicka().get(3).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button4.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void handleButton5() {
		if (hadaneSlovo == hra.vratSlovicka().get(4).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button5.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void handleButton6() {
		if (hadaneSlovo == hra.vratSlovicka().get(5).getCzPreklad()) {
			hra.zvysSpravne();
			setPoUhadnuti();
			ukazAlertGood();
		}else {
			button6.setDisable(true);
			hra.zvysSpatne();
			ukazAlertBad();
		}
	}
	public void ukazAlertGood() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Upozornění");
		alert.setHeaderText(null);
		alert.setContentText("Blahopřeji! Uhádli jste!");
		alert.showAndWait();
	}
	public void ukazAlertBad() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Upozornění");
		alert.setHeaderText(null);
		alert.setContentText("Bohužel, to není ono. Zkuste to znovu.");
		alert.showAndWait();
	}
	public void menuItemEndFast() {
		System.exit(0);
	}
	public void menuItemRestart() {
		setPoUhadnuti();
		hra.resetSpatne();
		hra.resetSpravne();
	}
	
	/**
     * Metoda na generování náhodného čísla od 0 do max.
     * 
     * @param max Určuje horní hranici pro generované číslo.
     * 
     * @return int.
     */
    private static int randInt(int max){
        Random rand = new Random();
        return (int)rand.nextInt(max) + 1;
    }

	public void otevriNapovedu() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("O programu");
		alert.setHeaderText("4IT115 - Domácí úkol 1");
		alert.setContentText("Jedná se o snadnou klikací aplikaci, kde uživatel hádá význam anglických slovíček pomocí obrázků. "
				+ "V případě, že si není jistý českým významem, může najet kurzorem na obrázek a podívat se na český význam obrázku.\n"
				+ "Správné i špatné pokusy se počítají, počty se však dají resetovat v menu, pomocí volby pro restart hry.");
		alert.showAndWait();
	}
	
}
