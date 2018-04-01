package ui;

import logika.IHra;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

/**
 * Kontroler, který zprostředkovává komunikaci mezi grafikou
 * a logikou adventury
 * 
 * @author Marketa Halikova
 *
 */
public class HomeController extends GridPane implements Observer{
	
	@FXML private TextField vstupniText;
	@FXML private TextArea vystup;
	@FXML private ImageView uzivatel;
	private IHra hra;
	
	/**
	 * metoda čte příkaz ze vstupního textového pole
	 * a zpracuje ho
	 */
	@FXML public void odesliPrikaz() {
		String vystupPrikazu = hra.zpracujPrikaz(vstupniText.getText());
		vystup.appendText("\n----------\n"+vstupniText.getText()+"\n----------\n");
		vystup.appendText(vystupPrikazu);
		vstupniText.setText("");
		if(hra.konecHry()) {
			vstupniText.setDisable(true);
			}
	}
	
	/**
	 * Metoda bude soužit pro předání objektu se spuštěnou hrou
	 * kontroleru a zobrazí stav hry v grafice.
	 * @param objekt spuštěné hry
	 */
	public void inicializuj(IHra hra) {
		vystup.setText(hra.vratUvitani());
		vystup.setEditable(false);
		uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
		this.hra = hra;
		hra.getHerniPlan().addObserver(this);
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
	uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
	Double xx = hra.getHerniPlan().getAktualniLokace().getY();
	System.out.println(xx);
	uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
		
	}

}

