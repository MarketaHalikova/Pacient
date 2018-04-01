package ui;

import logika.IHra;
import logika.Predmet;

import java.util.Observable;
import java.util.Observer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
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
	@FXML private ListView<String> vychody;
	@FXML private ListView<Predmet> veci;

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
		
		this.hra = hra;
		hra.getHerniPlan().addObserver(this);
		update(null, null);
		
	}
	

	@Override
	public void update(Observable arg0, Object arg1) {
		
		uzivatel.setX(hra.getHerniPlan().getAktualniLokace().getX());
		uzivatel.setY(hra.getHerniPlan().getAktualniLokace().getY());
	
		ObservableList<String> vychodyList = FXCollections.observableArrayList();
		vychodyList.addAll(hra.getHerniPlan().getAktualniLokace().getVychodyAsString());
		vychody.setItems(vychodyList);
		
		ObservableList<Predmet> predmety = FXCollections.observableArrayList();
		predmety.addAll(hra.getHerniPlan().getAktualniLokace().getPredmety());
		veci.setItems(predmety);
		veci.setCellFactory(param -> new ListCell<Predmet>() {	
			 private ImageView imageView = new ImageView();
	            @Override
	            public void updateItem(Predmet predmet, boolean empty) {
	                super.updateItem(predmet, empty);
	                if (empty) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                    if(predmet.getNazev().equals("jazyk"))
	                        imageView.setImage(new Image("/jazyk.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("penicilin"))
	                        imageView.setImage(new Image("/penicilin.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("prasek-zeleny"))
	                        imageView.setImage(new Image("/prasekZeleny.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("prasek-modry"))
	                        imageView.setImage(new Image("/prasekModry.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("srdce"))
	                        imageView.setImage(new Image("/srdce.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("mozek"))
	                        imageView.setImage(new Image("/mozek.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("oxacilin"))
	                        imageView.setImage(new Image("/oxacilin.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("paralen1"))
	                        imageView.setImage(new Image("/paralen1.png", 30, 30, false, false));
	                    else if(predmet.getNazev().equals("paralen2"))
	                        imageView.setImage(new Image("/paralen2.png", 30, 30, false, false));
	                    //setText(predmet.getNazev());
	                    setGraphic(imageView);
	                }
	            }
	    });
		

	}

}

