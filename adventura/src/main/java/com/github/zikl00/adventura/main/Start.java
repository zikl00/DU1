/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.adventura.main;

import com.github.zikl00.adventura.logika.Hra;
import com.github.zikl00.adventura.logika.IHra;
import com.github.zikl00.adventura.ui.HomeController;
import com.github.zikl00.adventura.logika.*;
import com.github.zikl00.adventura.ui.TextoveRozhrani;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2015/2016
 */
public class Start extends Application
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
    	if (args.length == 0) {
            launch(args);
        } else {
            if (args[0].equals("-text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else {
                System.out.println("Neplatný parametr");
            }
        }
    	/*
        IHra hra = new Hra();
        TextoveRozhrani ui = new TextoveRozhrani(hra);
        if(args.length == 0){
            ui.hraj();
        }
        else{
            ui.hrajZeSouboru(args[0]);
        }*/
    	//launch(args);
    }
    /**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/com/github/zikl00/adventura/ui/MainWindow.fxml"));
    	Parent root = loader.load();

    	HomeController controller = loader.getController();
    	IHra hra = new Hra();
		controller.inicializuj(hra);
    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("Little advanced adventura");
    	//nebude se měnit velikost okna, prostě nebude
    	primaryStage.setResizable(false);
		
	}
    //private Start(){}
}