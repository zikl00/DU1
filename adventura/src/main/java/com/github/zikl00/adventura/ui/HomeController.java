package com.github.zikl00.adventura.ui;

import java.util.Observer;

import com.github.zikl00.adventura.logika.*;
//import com.github.zikl00.adventura.main.Start;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import java.util.Observer;
import javafx.fxml.FXML;
//import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
//import javafx.stage.Window;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury (měl by... :D )
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
	@FXML private ListView<String> zivoty;
	@FXML private ListView<Integer> utocneCislo;
	@FXML private ListView<Vec> equip;
	@FXML private ListView<String> mapa;
	@FXML private ImageView mistoProObrazek;
	@FXML private ImageView imgPostava;
	@FXML private ImageView imgVeciNaZemi1;
	@FXML private ImageView imgVeciNaZemi2;
	@FXML private ImageView imgVeciNaZemi3;
	@FXML private ImageView imgVeciNaZemi4;
	@FXML private ImageView imgVeciNaZemi5;
	@FXML private ImageView imgVeciNaZemi6;
	@FXML private ImageView imgVeciInv1;
	@FXML private ImageView imgVeciInv2;
	@FXML private ImageView imgVeciInv3;
	@FXML private ImageView imgVeciInv4;
	@FXML private ImageView imgVeciInv5;
	@FXML private ImageView imgVeciInv6;
	@FXML private ImageView imgEq;
	@FXML private Button buttonProstor;
	@FXML private Button buttonTalk;
	@FXML private Button buttonFight;
	@FXML private Button buttonZahodEquip;
	@FXML private ContextMenu contextMenuVychody;
	private IHra hra;
	private Prostor kontrol;
	/**
	 * Metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho...
	 */
	public void odesliPrikaz() {
		
		String vypis = hra.zpracujPrikaz(textVstup.getText());
		textVypis.appendText("\n--------------------------------------------------------------------------\n"+
							 textVstup.getText()+
							 "\n--------------------------------------------------------------------------\n");
		textVypis.appendText(vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			setKonecHry();
		}
	}
	/**
     *  Metoda k inicializaci hry. Načte všechny potřebné prvky
     *  GUI a přidá observery.
     */
	public void inicializuj(IHra hra) {
		this.hra = hra;
		textVypis.setText(hra.vratUvitani().replaceAll("^\n", ""));
		naplnitListViews();
		pridejObservers();
		kontrol = hra.getHerniPlan().getAktualniProstor();
		setObrazekProstor();
		setObrazekPostavy();
		setObrazekVeciVProstoru();
		setObrazekEquip();
		setObrazekVeciVInventari();
	}
	/**
     *  Metoda sloužící k aktualizaci všech potřebných prvků GUI
     *  
     *  @param o - the observable object
     *  @param arg - an argument passed to the notifyObservers method
     */
	@Override
	public void update(Observable o, Object arg) {
		if(hra.konecHry()) {
			setKonecHry();
		}else {
		hra.getHerniPlan().addObserver(this);
		vycistitListViews();
		naplnitListViews();
		upravObserveraOblasti();
		setObrazekProstor();
		setObrazekPostavy();
		vycistitVeciProstorViews();
		vycistitVeciInventarViews();
		setObrazekVeciVProstoru();
		setObrazekEquip();
		setObrazekVeciVInventari();
		kontrolaEmpty();//když je postava odebrána po souboji z prostoru, zůstalo by vypsáno null
		}
	}
	/**
     *  Metoda, která při aktualizaci zkontroluje přitomnost
     *  postavy a následně vymaže ListView
     */
		private void kontrolaEmpty() {// když bude postava zabitá - tj. null - tak se znovu vyčistí ListView
			if (postavyVProstoru.getItems().get(0) == null) {
				postavyVProstoru.getItems().clear();
					}
		}
		/**
	     *  Metoda přidává observery do tříd,
	     *  kde je potřeba
	     */
		private void pridejObservers() {
			//přidání observerů
			hra.getHerniPlan().addObserver(this);//východy
			hra.getHerniPlan().getAktualniProstor().getPostava().addObserver(this);//inventář
			hra.getHerniPlan().getAktualniProstor().addObserver(this);//pro sledování změn věcí a postav v prostoru
			hra.getHerniPlan().getHrdinka().addObserver(this);//pro sledování sebraných a zahozených věcí
		}
		/**
	     *  Metoda upravující observera aktuálního prostoru
	     */
		private void upravObserveraOblasti() {
			if (kontrol != hra.getHerniPlan().getAktualniProstor()) {
				kontrol.deleteObservers();
				kontrol = hra.getHerniPlan().getAktualniProstor();
				hra.getHerniPlan().getAktualniProstor().addObserver(this);
			}
		}
		/**
	     *  Metoda, která naplňuje ListViews GUI
	     */
		private void naplnitListViews() {
			vychodyZProstoru.setDisable(false);
			vychodyZProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
			predmetyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVeci().values());
			postavyVProstoru.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getPostava());
			//zakomentovano protoze je batoh znazornen obrazky
			//batoh.getItems().addAll(hra.getHerniPlan().getHrdinka().vratInventar().values());
			zivoty.getItems().addAll(hra.getHerniPlan().getHrdinka().ukazZivoty());
			utocneCislo.getItems().addAll(hra.getHerniPlan().getHrdinka().ukazUtocneCislo());
			equip.getItems().addAll(hra.getHerniPlan().getHrdinka().vratEquipped().values());
			mapa.getItems().addAll(hra.zpracujPrikaz("mapa"));
		}
		/**
	     *  Metoda, která vymaže obsah ListViews GUI
	     */
		private void vycistitListViews() {
			vychodyZProstoru.getItems().clear();
			vychodyZProstoru.setDisable(true);
			predmetyVProstoru.getItems().clear();
			postavyVProstoru.getItems().clear();
			//zakomentovano protoze je batoh znazornen obrazky
			//batoh.getItems().clear();
			zivoty.getItems().clear();
			utocneCislo.getItems().clear();
			equip.getItems().clear();
			mapa.getItems().clear();
			contextMenuVychody.getItems().clear();
		}		
		/**
	     *  Metoda nastavuje null hodnotu u všech ImgView
	     *  věcí v prostoru, setDisable je zde kvůli
	     *  bezpečnosti a hlavně kvůli lenosti a rychlosti
	     */
		private void vycistitVeciProstorViews() {
			imgVeciNaZemi1.setImage(null);
			imgVeciNaZemi1.setDisable(true);
			imgVeciNaZemi2.setImage(null);
			imgVeciNaZemi2.setDisable(true);
			imgVeciNaZemi3.setImage(null);
			imgVeciNaZemi3.setDisable(true);
			imgVeciNaZemi4.setImage(null);
			imgVeciNaZemi4.setDisable(true);
			imgVeciNaZemi5.setImage(null);
			imgVeciNaZemi5.setDisable(true);
			imgVeciNaZemi6.setImage(null);
			imgVeciNaZemi6.setDisable(true);
		}
		/**
	     *  Metoda nastavuje null hodnotu u všech ImgView
	     *  věcí v inventáři, setDisable je zde kvůli
	     *  bezpečnosti a hlavně kvůli lenosti a rychlosti
	     */
		private void vycistitVeciInventarViews() {
			imgVeciInv1.setImage(null);
			imgVeciInv1.setDisable(true);
			imgVeciInv2.setImage(null);
			imgVeciInv2.setDisable(true);
			imgVeciInv3.setImage(null);
			imgVeciInv3.setDisable(true);
			imgVeciInv4.setImage(null);
			imgVeciInv4.setDisable(true);
			imgVeciInv5.setImage(null);
			imgVeciInv5.setDisable(true);
			imgVeciInv6.setImage(null);
			imgVeciInv6.setDisable(true);
		}
		/**
	     *  Metoda sloužící ke zpracování příkazu, který
	     *  je zadáván pomocí parametru, navíc na výstup
	     *  nevypíše část běžného komentáře
	     *  
	     *  @param parametr Název příkazu
	     */
		public void odesliPrikazString(String parametr) {
			hra.zpracujPrikaz(parametr);			
			if(hra.konecHry()) {
				textVypis.appendText("\n\n Konec hry \n");
				textVstup.setDisable(true);
				odesli.setDisable(true);
				setKonecHry();
			}
		}
		/**
	     *  Metoda sloužící ke zpracování příkazu, který
	     *  je zadáván pomocí parametru, navíc na výstup
	     *  vypíše i část běžného komentáře
	     *  
	     *  @param parametr Název příkazu
	     */
		public void odesliPrikazStringVypis(String parametr) {
			String vypis = hra.zpracujPrikaz(parametr);
			textVypis.appendText("\n--------------------------------------------------------------------------\n");
			textVypis.appendText(vypis);
			textVstup.setText("");
			
			if(hra.konecHry()) {
				textVypis.appendText("\n\n Konec hry \n");
				textVstup.setDisable(true);
				odesli.setDisable(true);
				setKonecHry();
			}
		}
		/**
	     *  Metoda, která se zavolá při volbě 
	     *  menuItem ke konci hry
	     */
		public void menuItemEndGame() {
			posliKonecHry();
		}
		/**
	     *  Metoda, která se zavolá při volbě 
	     *  menuItem k rychlému konci hry
	     */
		public void menuItemEndGameFast() {
			System.exit(0);
		}
		/**
	     *  Metoda, která se zavolá při volbě 
	     *  menuItem ke spuštění nové hry
	     */
		//konec hry a začátek
		public void menuItemNewGame() {
			if (hra.konecHry()) {
				startZnovu();
			}
			posliKonecHry();
			startZnovu();
		}
		/**
	     *  Metoda, která definuje jednotlivé instrukce,
	     *  které se volají při ukončování hry
	     */
		public void posliKonecHry() {
			odesliPrikazString("konec");
			vycistitListViews();
			textVypis.clear();
			vycistitVeciProstorViews();
			vycistitVeciInventarViews();
			imgPostava.setImage(null);
			imgEq.setImage(null);
			setTlacitkaBlokovane();
		}
		/**
	     *  Metoda, která definuje jednotlivé instrukce,
	     *  které se volají při nové hře
	     */
		public void startZnovu(){
			IHra hra = new Hra();
			inicializuj(hra);
			//hra.getHerniPlan().zalozProstoryHry();
			setTlacitkaAktivni();
		}
		/**
	     *  Metoda nastavující konec hry, znemožňující
	     *  další zadávání příkazů a mačkání tlačítek.
	     *  Pouze pole s textovým výpisem nechá.
	     */
		public void setKonecHry () {
			vycistitListViews();
			//textVypis.clear();
			vycistitVeciProstorViews();
			vycistitVeciInventarViews();
			imgPostava.setImage(null);
			imgEq.setImage(null);
			setTlacitkaBlokovane();
		}
		/**
	     *  Metoda nastavující atribut tlačítek a textového
	     *  pole na AKTIVNÍ
	     */
		public void setTlacitkaAktivni() {
			textVstup.setDisable(false);
			odesli.setDisable(false);
			buttonProstor.setDisable(false);
			buttonTalk.setDisable(false);
			buttonFight.setDisable(false);
			buttonZahodEquip.setDisable(false);
		}
		/**
	     *  Metoda nastavující atribut tlačítek a textového
	     *  pole na NEAKTIVNÍ
	     */
		public void setTlacitkaBlokovane() {
			textVstup.setDisable(true);
			odesli.setDisable(true);
			buttonProstor.setDisable(true);
			buttonTalk.setDisable(true);
			buttonFight.setDisable(true);
			buttonZahodEquip.setDisable(true);
		}
		/**
	     *  Metoda nastavující obrázek předmětu, se kterým
	     *  hrdinka právě bojuje
	     */
		//obrazek eq
		public void setObrazekEquip() {
			if (hra.getHerniPlan().getHrdinka().vratEquipped() == null) {
				imgEq.setImage(null);
			}else {
				String jmenoEq = null;
				for (String nazev : hra.getHerniPlan().getHrdinka().vratEquipped().keySet()) {
					jmenoEq = nazev;
		        }
				try {
					Image image = new Image (Paths.get("img/items/" + jmenoEq + ".jpg").toUri().toString());
					imgEq.setImage(image);
			    } catch (Exception e) {
			    	System.exit(0);
			    }
			}
		}
		/**
	     *  Metoda, která se volá při stisku příslušného tlačítka,
	     *  maže obrázek předmětu, se kterým
	     *  hlavní hrdinka bojuje, navíc provede zahození předmětu
	     */
		//zahozeni veci + vycisteni obrazku
		public void buttonZahodEq() {
			if (hra.getHerniPlan().getHrdinka().vratEquipped() != null) {
				String jmenoEq = null;
				for (String nazev : hra.getHerniPlan().getHrdinka().vratEquipped().keySet()) {
					jmenoEq = nazev;
		        }
				odesliPrikazString("zahoď " + jmenoEq);
				setObrazekEquip();
			}
		}
		/**
	     *  Metoda, která se provede při stisknutí tlačítka.
	     *  Odešle příkaz promluv s výpisem
	     */
		public void promluvButton() {
			odesliPrikazStringVypis("promluv");
		}
		/**
	     *  Metoda, která se provede při stisknutí tlačítka.
	     *  Odešle příkaz souboj s výpisem
	     */
		public void soubojButton() {
			odesliPrikazStringVypis("souboj");
		}
		/**
	     *  Metoda, která se provede při stisknutí tlačítka.
	     *  Odešle příkaz popis s výpisem
	     */
		public void popisButton() {
			odesliPrikazStringVypis("popis");
		}
		/**
	     *  Metoda nastavující obrázek aktuálního prostoru,
	     *  navíc nastaví popisek
	     */
		//obrazek prostoru
		public void setObrazekProstor(){
			try {
				Image image = new Image (Paths.get("img/prostors/" + hra.getHerniPlan().getAktualniProstor().getNazev() + ".jpg").toUri().toString());
		        mistoProObrazek.setImage(image);
		        Tooltip.install(mistoProObrazek, new Tooltip(hra.getHerniPlan().getAktualniProstor().getNazev()));
		    } catch (Exception e) {
		    	System.exit(0);
		    }
			
		}
		/**
	     *  Metoda nastavující obrázek postavy
	     *  v aktuálním prostoru
	     */
		public void setObrazekPostavy(){
			if (hra.getHerniPlan().getAktualniProstor().getPostava() != null) {
				try {
					Image image = new Image (Paths.get("img/postavy/" + hra.getHerniPlan().getAktualniProstor().getPostava().getNazev() + ".png").toUri().toString());
					imgPostava.setImage(image);
					Tooltip.install(imgPostava, new Tooltip(hra.getHerniPlan().getAktualniProstor().getPostava().getNazev()));
				} catch (Exception e) {
					System.exit(0);
				}
			}else {
				imgPostava.setImage(null);
			}
		}
		/**
	     *  Metoda nastavující akci u 1. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		//metody na provedeni prikazu "seber" u obrazku
		public void seberImg1() {
			if (imgVeciNaZemi1.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi1.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda nastavující akci u 2. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		public void seberImg2() {
			if (imgVeciNaZemi2.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi2.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda nastavující akci u 3. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		public void seberImg3() {
			if (imgVeciNaZemi3.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi3.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda nastavující akci u 4. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		public void seberImg4() {
			if (imgVeciNaZemi4.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi4.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda nastavující akci u 5. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		public void seberImg5() {
			if (imgVeciNaZemi5.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi5.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda nastavující akci u 6. obrázku v prostoru,
	     *  při stisknutí tlačítka myši
	     */
		public void seberImg6() {
			if (imgVeciNaZemi6.getImage() != null) {
				odesliPrikazStringVypis("seber " + imgVeciNaZemi6.getImage().impl_getUrl().replaceAll("^.*/", "").replaceAll(".jpg$", ""));
			}
		}
		/**
	     *  Metoda přidělující akci u prvků v ListView.
	     *  Po kliknutí na prvek seznamu se provede příkaz
	     *  jdi s parametrem
	     */
		public void createMouseClickVychody() {
			odesliPrikazStringVypis("jdi " + vychodyZProstoru.getSelectionModel().getSelectedItem());
		}
		/**
	     *  Metoda, která do daných ImgViews přiřadí obázek
	     *  předmětů v prostoru s popiskem
	     */
		//jestli to bude fungovat, tak se pos*** :D :D
		public void setObrazekVeciVProstoru() {
			if (hra.getHerniPlan().getAktualniProstor().getVeci() != null ) {
				int counter = 0;
				Map<Integer, ImageView> mapaImgViews = new HashMap<Integer, ImageView>();
				mapaImgViews.put(0, imgVeciNaZemi1);
				mapaImgViews.put(1, imgVeciNaZemi2);
				mapaImgViews.put(2, imgVeciNaZemi3);
				mapaImgViews.put(3, imgVeciNaZemi4);
				mapaImgViews.put(4, imgVeciNaZemi5);
				mapaImgViews.put(5, imgVeciNaZemi6);
				for(String nazev : hra.getHerniPlan().getAktualniProstor().getVeci().keySet() ){
					if (nazev == null) {break;}
					if (counter == 6) {break;}
					try {
						Image image = new Image(Paths.get("img/items/" + nazev + ".jpg").toUri().toString());
						mapaImgViews.get(counter).setDisable(false);
						mapaImgViews.get(counter).setImage(image);
						mapaImgViews.get(counter).setFitHeight(128);
						mapaImgViews.get(counter).setFitWidth(128);
						Tooltip.install(mapaImgViews.get(counter), new Tooltip(nazev));
						counter ++;
					} catch (Exception e) {
						System.exit(0);
					}
				}
			}else {
				vycistitVeciProstorViews();
			}
		}
		/**
	     *  Metoda, která do daných ImgViews přiřadí obázek
	     *  předmětů v inventáři s popiskem. Navíc každému obrázku
	     *  vytvoří contextMenu s dvěma akcemi
	     */
		//veci v inventari, to same jako pro veci v prostoru, nyni ale v inventari
		public void setObrazekVeciVInventari() {
			if (hra.getHerniPlan().getHrdinka().vratInventar() != null ) {
				int counter = 0;
				Map<Integer, ImageView> mapaImgViews = new HashMap<Integer, ImageView>();
				mapaImgViews.put(0, imgVeciInv1);
				mapaImgViews.put(1, imgVeciInv2);
				mapaImgViews.put(2, imgVeciInv3);
				mapaImgViews.put(3, imgVeciInv4);
				mapaImgViews.put(4, imgVeciInv5);
				mapaImgViews.put(5, imgVeciInv6);
				for(String nazev : hra.getHerniPlan().getHrdinka().vratInventar().keySet() ){
					if (nazev == null) {break;}
					if (counter == 6) {
						break;
					}
					try {
						Image image = new Image(Paths.get("img/items/" + nazev + ".jpg").toUri().toString());
						mapaImgViews.get(counter).setDisable(false);
						mapaImgViews.get(counter).setImage(image);
						mapaImgViews.get(counter).setFitHeight(128);
						mapaImgViews.get(counter).setFitWidth(128);
						Tooltip.install(mapaImgViews.get(counter), new Tooltip(nazev));
						
						//tvorba context menu
				        ContextMenu contextMenu = new ContextMenu();
				        MenuItem item1 = new MenuItem("zahoď " + nazev.replaceAll("_", " "));
				        item1.setOnAction(new EventHandler<ActionEvent>() {
				            @Override
				            public void handle(ActionEvent event) {
				            	odesliPrikazStringVypis("zahoď " + nazev);
				            }
				        });
				        MenuItem item2 = new MenuItem("použij " + nazev.replaceAll("_", " "));
				        item2.setOnAction(new EventHandler<ActionEvent>() {
				            @Override
				            public void handle(ActionEvent event) {
				            	odesliPrikazStringVypis("použij " + nazev);
				            }
				        });
				        // Add MenuItem to ContextMenu
				        contextMenu.getItems().addAll(item1, item2);
				        mapaImgViews.get(counter).setOnContextMenuRequested(e -> 
				        	contextMenu.show(imgPostava, e.getScreenX(), e.getScreenY())
				        );
						counter ++;
					} catch (Exception e) {
						System.exit(0);
					}
				}
			}else {
				vycistitVeciInventarViews();
			}
		}
		/**
	     *  Metoda, která umožňuje akci onAction,
	     *  otevře okno s nápovědou, která je v HTML
	     *  souboru
	     */
		public void otevriNapovedu() throws IOException {
			File file = new File ("files/napoveda.html");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}
		/**
	     *  Metoda, která umožňuje akci onAction,
	     *  otevře okno se zadáním v pdf souboru
	     */
		public void otevriZadani() throws IOException {
			File file = new File ("files/Zikl00_Adventura_Zadani.pdf");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}
		/**
	     *  Metoda, která umožňuje akci onAction,
	     *  otevře okno se manuálem
	     */
		public void otevriManual() throws IOException {
			File file = new File ("files/userMan.pdf");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);
		}
		
}
