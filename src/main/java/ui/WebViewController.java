package ui;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebViewController
{
    @FXML
    private WebView webView;

    @FXML
    private void initialize()
    {
        WebEngine engine = webView.getEngine();
        
        final String hellohtml = "/Uzivatelska_prirucka.htm"; //HTML file to view in web view

	    URL urlHello = getClass().getResource(hellohtml);
	    engine.load(urlHello.toExternalForm());
    }
}