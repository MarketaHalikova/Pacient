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
	@FXML private ListView<Predmet> lekarnicka;

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
		ObservableList<Predmet> vLekarnicce = FXCollections.observableArrayList();
		vLekarnicce.addAll(hra.getHerniPlan().getLekarnicka().obsahLekarnicky());
		lekarnicka.setItems(vLekarnicce);
		veci.setCellFactory(param -> new ListCell<Predmet>() {	
			 private ImageView imageView = new ImageView();
	            @Override
	            public void updateItem(Predmet predmet, boolean empty) {
	                super.updateItem(predmet, empty);
	                if (empty) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                	imageView.setImage(getImage(predmet));
	                    //setText(predmet.getNazev());
	                    setGraphic(imageView);
	                }
	            }	
	    });
		lekarnicka.setCellFactory(param -> new ListCell<Predmet>() {	
			 private ImageView imageView = new ImageView();
	            @Override
	            public void updateItem(Predmet predmet, boolean empty) {
	                super.updateItem(predmet, empty);
	                if (empty) {
	                    setText(null);
	                    setGraphic(null);
	                } else {
	                	imageView.setImage(getImage(predmet));
	                    //setText(predmet.getNazev());
	                    setGraphic(imageView);
	                }
	            }	
	    });
		
		

	}
	
	private Image getImage(Predmet predmet) {
		Image image = null;
		if(predmet.getNazev().equals("jazyk"))
            image = new Image("/jazyk.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("penicilin"))
        	image = new Image("/penicilin.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("prasek-zeleny"))
        	image = new Image("/prasekZeleny.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("prasek-modry"))
        	image = new Image("/prasekModry.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("srdce"))
        	image = new Image("/srdce.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("mozek"))
        	image = new Image("/mozek.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("oxacilin"))
        	image = new Image("/oxacilin.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("paralen1"))
        	image = new Image("/paralen1.png", 40, 40, false, false);
        else if(predmet.getNazev().equals("paralen2"))
        	image = new Image("/paralen2.png", 40, 40, false, false);
		return image;
	}

}

