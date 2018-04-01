/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

import java.util.Observable;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny lokace, propojuje je vzájemně pomocí východů 
 * a pamatuje si aktuální lokaci, ve které se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, Markéta Halíková
 * @version    LS 2016/2017
 */
public class HerniPlan extends Observable{

    private static final String NAZEV_VITEZNE_LOKACE = "hlava";
    
    private Lokace aktualniLokace;
    
    private Lekarnicka lekarnicka;
    
    /**
     * Konstruktor který vytváří jednotlivé lokace a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozLokaceHry();

        this.lekarnicka = new Lekarnicka();
    }

    /**
     * Vytváří jednotlivé lokace a propojuje je pomocí východů.
     * Jako výchozí aktuální lokaci nastaví domeček.
     */
    private void zalozLokaceHry() {
        // vytvářejí se jednotlivé lokace
        Lokace pusa = new Lokace("pusa","pusa pacienta",140.0,70.0);
        Lokace krk = new Lokace("krk","krk pacienta",140.0,85.0);
        Lokace telo = new Lokace("telo","tělo pacienta",140.0,120.0);
        Lokace pravaRuka = new Lokace("ruka-prava","pravá ruka pacienta",200.0,153.0);
        Lokace levaRuka = new Lokace("ruka-leva","levá ruka pacienta",80.0,153.0);
        Lokace pravaNoha = new Lokace("noha-prava","pravá noha pacienta",156.0,238.0);
        Lokace levaNoha = new Lokace("noha-leva","levá noha pacienta",123.0,238.0);
        
        // přiřazují se průchody mezi lokacemi (sousedící lokace)
        pusa.setVychod(krk);
        krk.setVychod(telo);
        krk.setVychod(pusa);
        telo.setVychod(krk);
        telo.setVychod(pravaRuka);
        telo.setVychod(levaRuka);
        telo.setVychod(pravaNoha);
        telo.setVychod(levaNoha);
        pravaRuka.setVychod(telo);
        levaRuka.setVychod(telo);
        pravaNoha.setVychod(telo);
        levaNoha.setVychod(telo);

        // vytvoříme několik věcí
        
        Predmet jazyk = new Predmet("jazyk", "jazyk vypadá v pořádku", false);
        Predmet srdce = new Predmet("srdce", "srdce pacienta je naštěstí zdravé", false);
        Predmet mozek = new Predmet("mozek", "mozek pacienta je naštěstí zdravý", false);
        Predmet penicilin = new Predmet ("penicilin", "dávka antibiotik, kterou jsi dříve do ruky aplikoval/a");
        Predmet oxacilin = new Predmet ("oxacilin", "dávka antibiotik, kterou jsi dříve do ruky aplikoval/a");
        Predmet prasekModry = new Predmet ("prasek-modry", "jeden z prášků, který si pacientovi dal/a");
        Predmet prasekZeleny = new Predmet ("prasek-zeleny", "jeden z prášků, který si pacientovi dal/a");
        Predmet paralen1 = new Predmet ("paralen1", "jeden z prášků na hlavu, který potřebuješ");
        Predmet paralen2 = new Predmet ("paralen2", "druhý z prášků na hlavu, který potřebuješ", false);
        
        // vytvořím nepřítele
        Nepritel parazit = new Nepritel("parazit", "Parazita můžeš zabít tím, že na něj zaútočíš, když u sebe budeš mít správnou kombinaci antibiotika + prášek" + "\n"
                                        + "Pozor! když zaútočíš s nesprávnou kombinací, parazita akorát naštveš a zabije tě.", true, "oxacilin", "prasek-zeleny");
        
        // umístíme věci do prostorů
        pusa.vlozPredmet(jazyk);
        telo.vlozPredmet(srdce);
        levaRuka.vlozPredmet(penicilin);
        levaRuka.vlozPredmet(oxacilin);
        pravaRuka.vlozPredmet(prasekModry);
        pravaRuka.vlozPredmet(prasekZeleny);        
        pravaNoha.vlozPredmet(paralen1);
        levaNoha.vlozPredmet(paralen2);
        
        levaNoha.vlozNepritele(parazit);
        
        Lokace hlava = new Lokace(NAZEV_VITEZNE_LOKACE, "hlava pacienta",140.0,50.0, true, paralen1, paralen2);
        krk.setVychod(hlava);
        hlava.setVychod(krk);
        hlava.vlozPredmet(mozek);
        aktualniLokace = pusa;  // hra začíná v puse      
    }

    /**
     * Metoda vrací odkaz na aktuální lokaci, ve které se hráč právě nachází.
     *
     * @return    aktuální lokace
     */
    
    public Lokace getAktualniLokace() {
        return aktualniLokace;
    }

    /**
     * Metoda nastaví aktuální lokaci, používá se nejčastěji při přechodu mezi lokacemi
     *
     * @param    lokace nová aktuální lokace
     */
    public void setAktualniLokace(Lokace lokace) {
       aktualniLokace = lokace;
       setChanged();
       notifyObservers();
    }
    
    /**
     * Metoda vrací informaci, zda hráč vyhrál (zda dorazil do lokace chaloupka).
     * 
     * @return    true, pokud hráč vyhrál; jinak false
     */
    public boolean hracVyhral() {
        return aktualniLokace.getNazev().equals(NAZEV_VITEZNE_LOKACE);
    }

    /**
     * Metoda vrací lékárničku
     *
     * @return   Lekarnicka lekarnica
     */
    public Lekarnicka getLekarnicka() {
       return lekarnicka;
    }
}
