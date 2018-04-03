/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.zikl00.DU1.main;

import com.github.zikl00.DU1.logika.*;
import com.github.zikl00.DU1.ui.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou obrázkovou aplikaci
 * k hádání anglických slovíček
 *
 * @author    Libor Zíka
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
                System.out.println("Tato aplikace je bez parametrů");
        }
    }
    /**
	 * Metoda, ve které se konstruuje okno, kontroler a hra,
	 * která se předává kontroleru
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getResource("/com/github/zikl00/DU1/ui/MainWindow.fxml"));
    	Parent root = loader.load();

    	HomeController controller = loader.getController();
    	Hra hra = new Hra();
		controller.inicializuj(hra);
    	
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    	primaryStage.setTitle("DU1");
    	//nebude se měnit velikost okna, prostě nebude
    	primaryStage.setResizable(false);
		
	}
}