package ui;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Kontroler, který v fxml souboru zobrazule htm soubor
 * 
 * @author Marketa Halikova
 *
 */
public class WebViewController
{
    @FXML
    private WebView webView;

    
    /**
     * Metoda načítá htm soubor
     * z vlastního zdroje
     */
    @FXML
    private void initialize()
    {
        WebEngine engine = webView.getEngine();
        
        final String hellohtml = "/Uzivatelska_prirucka.htm";

	    URL urlHello = getClass().getResource(hellohtml);
	    engine.load(urlHello.toExternalForm());
    }
}