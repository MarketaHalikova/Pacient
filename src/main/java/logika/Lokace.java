/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kĂłdovĂˇnĂ­: PĹ™Ă­liĹˇ ĹľluĹĄouÄŤkĂ˝ kĹŻĹ� ĂşpÄ›l ÄŹĂˇbelskĂ© Ăłdy. */
package logika;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Trida Lokace - popisuje jednotlivĂ© lokace (mĂ­stnosti) hry. Tato tĹ™Ă­da je
 * souÄŤĂˇstĂ­ jednoduchĂ© textovĂ© hry.
 *
 * "Lokace" reprezentuje jedno mĂ­sto (mĂ­stnost, prostor, ...) ve scĂ©nĂˇĹ™i hry.
 * Lokace mĹŻĹľe mĂ­t sousednĂ­ lokace pĹ™ipojenĂ© pĹ™es vĂ˝chody. Pro kaĹľdĂ˝ vĂ˝chod
 * si lokace uklĂˇdĂˇ odkaz na sousedĂ­cĂ­ lokace.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha, MarkĂ©ta HalĂ­kovĂˇ
 * @version    LS 2016/2017
 */
public class Lokace {

    private String nazev;
    private String popis;
    private Set<Lokace> vychody;   // obsahuje sousednĂ­ lokace
    private Map<String, Predmet> predmety;
    private boolean zamceno = false;
    private Predmet klic1;
    private Predmet klic2;
    private Nepritel nepritel;
    
    private double x;
    private double y;

    /**
     * VytvoĹ™enĂ­ lokace se zadanĂ˝m popisem, napĹ™. "kuchyĹ�", "hala", 
     *
     * @param    nazev nazev lokace, jednoznaÄŤnĂ˝ identifikĂˇtor, jedno slovo nebo vĂ­ceslovnĂ˝ nĂˇzev bez mezer
     * @param    popis Popis lokace
     */
    public Lokace(String nazev, String popis, double x, double y) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        predmety = new HashMap<>();
        this.x = x;
        this.y = y;
    }
    /**
     * VytvoĹ™enĂ­ lokace se zadanĂ˝m popisem, napĹ™. "kuchyĹ�", "hala", moĹľnostĂ­ zamknout mĂ­stnost a klĂ­ÄŤi k odemknutĂ­
     *
     * @param    nazev nazev lokace, jednoznaÄŤnĂ˝ identifikĂˇtor, jedno slovo nebo vĂ­ceslovnĂ˝ nĂˇzev bez mezer
     * @param    popis Popis lokace
     * @param    zamceno boolean stanovujĂ­cĂ­ zda je mĂ­stnost zamÄŤena ÄŤi ne
     * @param    klic1 Predmet potĹ™ebnĂ˝ k odemÄŤenĂ­ mĂ­stnosti
     * @param    klic2 Predmet potĹ™ebnĂ˝ k odemÄŤenĂ­ mĂ­stnosti
     */
    public Lokace(String nazev, String popis, double x, double y, boolean zamceno, Predmet klic1, Predmet klic2) {
        this(nazev, popis, x, y);
        this.zamceno = zamceno;
        this.klic1 = klic1;
        this.klic2 = klic2;
    }

    /**
     * Definuje vĂ˝chod z lokace (sousednĂ­/vedlejsi lokace). Vzhledem k tomu,
     * Ĺľe je pouĹľit Set pro uloĹľenĂ­ vĂ˝chodĹŻ, mĹŻĹľe bĂ˝t sousednĂ­ lokace uvedena
     * pouze jednou (tj. nelze mĂ­t dvoje dveĹ™e do stejnĂ© sousednĂ­ lokace).
     * DruhĂ© zadĂˇnĂ­ stejnĂ© lokace tiĹˇe pĹ™epĂ­Ĺˇe pĹ™edchozĂ­ zadĂˇnĂ­ (neobjevĂ­
     * se ĹľĂˇdnĂ© chybovĂ© hlĂˇĹˇenĂ­). Lze zadat tĂ©Ĺľ cestu ze do sebe sama.
     *
     * @param    vedlejsi lokace, kterĂˇ sousedi s aktualnĂ­ lokacĂ­.
     *
     */
    public void setVychod(Lokace vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda equals pro porovnĂˇnĂ­ dvou lokacĂ­. PĹ™ekrĂ˝vĂˇ se metoda equals ze
     * tĹ™Ă­dy Object. DvÄ› lokace jsou shodnĂ©, pokud majĂ­ stejnĂ˝ nĂˇzev. Tato
     * metoda je dĹŻleĹľitĂˇ z hlediska sprĂˇvnĂ©ho fungovĂˇnĂ­ seznamu vĂ˝chodĹŻ (Set).
     *
     * BliĹľĹˇĂ­ popis metody equals je u tĹ™Ă­dy Object.
     *
     * @param     o object, kterĂ˝ se mĂˇ porovnĂˇvat s aktuĂˇlnĂ­m
     * @return    hodnotu true, pokud mĂˇ zadanĂˇ lokace stejnĂ˝ nĂˇzev, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnĂˇvĂˇme zda se nejednĂˇ o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnĂˇvĂˇme jakĂ©ho typu je parametr 
        if (!(o instanceof Lokace)) {
            return false;    // pokud parametr nenĂ­ typu Lokace, vrĂˇtĂ­me false
        }
        // pĹ™etypujeme parametr na typ Lokace 
        Lokace druha = (Lokace) o;

        //metoda equals tĹ™Ă­dy java.util.Objects porovnĂˇ hodnoty obou nĂˇzvĹŻ. 
        //VrĂˇtĂ­ true pro stejnĂ© nĂˇzvy a i v pĹ™Ă­padÄ›, Ĺľe jsou oba nĂˇzvy null,
        //jinak vrĂˇtĂ­ false.

       return (java.util.Objects.equals(this.nazev, druha.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object.
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * VracĂ­ nĂˇzev lokace (byl zadĂˇn pĹ™i vytvĂˇĹ™enĂ­ lokace jako parametr
     * konstruktoru)
     *
     * @return    nĂˇzev lokace
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * VracĂ­ "dlouhĂ˝" popis lokace, kterĂ˝ mĹŻĹľe vypadat nĂˇsledovnÄ›: Jsi v
     * mistnosti/lokaci vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return    dlouhĂ˝ popis lokace
     */
    public String dlouhyPopis() {
        return "Jsi v místnosti/lokaci " + popis + ".\n"
                + popisVychodu() + "\n"
                + seznamPredmetu();
    }

    /**
     * VracĂ­ textovĂ˝ Ĺ™etÄ›zec, kterĂ˝ popisuje vĹˇechny pĹ™edmÄ›ty v lokaci, napĹ™Ă­klad:
     * "predmety: zidle, stul, rum ".
     *
     * @return    popis pĹ™edmÄ›tĹŻ - nĂˇzvĹŻ vĹˇech pĹ™edmÄ›tĹŻ v lokaci
     */
    private String seznamPredmetu()
    {
        String seznam = "předměty:";
        
        for (String nazevPredmetu : predmety.keySet())
        {
            seznam += " " + nazevPredmetu;
        }
        
        return seznam;
    }
    
    public Collection<Predmet> getPredmety() {

    	return predmety.values();
    }
    
    

    /**
     * VracĂ­ textovĂ˝ Ĺ™etÄ›zec, kterĂ˝ popisuje sousednĂ­ vĂ˝chody, napĹ™Ă­klad:
     * "vychody: hala ".
     *
     * @return    popis vĂ˝chodĹŻ - nĂˇzvĹŻ sousednĂ­ch lokacĂ­
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Lokace sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
            if (sousedni.jeZamceno()) {
                 vracenyText += "(zamknuto)";
             }
        }
        return vracenyText;
    }

    /**
     * VracĂ­ lokaci, kterĂˇ sousedĂ­ s aktuĂˇlnĂ­ lokacĂ­ a jejĂ­Ĺľ nĂˇzev je zadĂˇn
     * jako parametr. Pokud lokace s udanĂ˝m jmĂ©nem nesousedĂ­ s aktuĂˇlnĂ­
     * lokacĂ­, vracĂ­ se hodnota null.
     *
     * @param     nazevSouseda JmĂ©no sousednĂ­ lokace (vĂ˝chodu)
     * @return    lokace, kterĂˇ se nachĂˇzĂ­ za pĹ™Ă­sluĹˇnĂ˝m vĂ˝chodem, nebo hodnota null, pokud lokace zadanĂ©ho jmĂ©na nenĂ­ sousedem.
     */
    public Lokace vratSousedniLokaci(String nazevSouseda) {
        List<Lokace>hledaneLokace = 
            vychody.stream()
                   .filter(sousedni -> sousedni.getNazev().equals(nazevSouseda))
                   .collect(Collectors.toList());
        if(hledaneLokace.isEmpty()){
            return null;
        }
        else {
            return hledaneLokace.get(0);
        }
    }

    /**
     * VracĂ­ kolekci obsahujĂ­cĂ­ lokace, se kterĂ˝mi ta lokace sousedĂ­.
     * Takto zĂ­skanĂ˝ seznam sousednĂ­ch lokacĂ­ nelze upravovat (pĹ™idĂˇvat,
     * odebĂ­rat vĂ˝chody) protoĹľe z hlediska sprĂˇvnĂ©ho nĂˇvrhu je to plnÄ›
     * zĂˇleĹľitostĂ­ tĹ™Ă­dy Lokace.
     *
     * @return    nemodifikovatelnĂˇ kolekce lokacĂ­ (vĂ˝chodĹŻ), se kterĂ˝mi tato lokace sousedĂ­.
     */
    public Collection<Lokace> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }
    
    public Collection<String> getVychodyAsString() {
    	List<String> list = new ArrayList<>();
    	for(Lokace lokace:vychody){
    		list.add(lokace.getNazev());
    	}
    	return list;
    }
    
    /**
     * PĹ™idĂˇ pĹ™edmÄ›t do lokace.
     * 
     * @param    predmet pĹ™edmÄ›t, kterĂ˝ mĂˇ bĂ˝t pĹ™idĂˇn do lokace
     */
    public void vlozPredmet(Predmet predmet)
    {
        predmety.put(predmet.getNazev(), predmet);

    }

    /**
     * Odebere pĹ™edmÄ›t s danĂ˝m nĂˇzvem z lokace a vrĂˇtĂ­ ho. Pokud v lokaci pĹ™edmÄ›t s danĂ˝m
     * nĂˇzvem nenĂ­, vrĂˇtĂ­ null.
     * 
     * @param      nazevPredmetu nĂˇzev pĹ™edmÄ›tu
     * @returns    pĹ™edmÄ›t, kterĂ˝ byl odebrĂˇn z lokace
     */
    public Predmet vezmiPredmet(String nazevPredmetu)
    {
    	Predmet predmet = predmety.remove(nazevPredmetu);
    	

    	return predmet;
        
    }
    
    /**
     * Najde v lokaci pĹ™edmÄ›t s danĂ˝m nĂˇzvem a vrĂˇtĂ­ ho. Pokud v lokaci pĹ™edmÄ›t s danĂ˝m
     * nĂˇzvem nenĂ­, vrĂˇtĂ­ null.
     * 
     * @param      nazevPredmetu nĂˇzev pĹ™edmÄ›tu
     * @returns    pĹ™edmÄ›t z lokace
     */
    public Predmet najdiPredmet(String nazevPredmetu)
    {
        return predmety.get(nazevPredmetu);
    }
    
    /**
     * ZjistĂ­, jestli je v lokaci pĹ™edmÄ›t s danĂ˝m nĂˇzvem.
     * 
     * @param      nazevPredmetu nĂˇzev pĹ™edmÄ›tu
     * @returns    true, pokud lokace obsahuje pĹ™edmÄ›t s danĂ˝m nĂˇzvem; jinak false
     */
    public boolean obsahujePredmet(String nazevPredmetu)
    {
        return predmety.containsKey(nazevPredmetu);
    }

    /**
     * 0demkne zamÄŤenou mistnost.
     * 
     * @param      boolean odemceno
     * 
     */
    public void odemknout(boolean odemceno){
        zamceno = !odemceno;
    }
    
    /**
     * ZjistĂ­, zda je mĂ­stnost zamÄŤena
     * 
     * 
     * @returns     boolean zamceno
     */
    public boolean jeZamceno(){
        return zamceno;
    }
    
    /**
     * Nastavi klĂ­ÄŤe k odemknutĂ­ mĂ­stnosti
     * (k odemnknutĂ­ jsou potĹ™eba klĂ­ÄŤe dva)
     * 
     * @param Predmet klic1 a klic2 PĹ™edmÄ›ty, kterĂ© odemknou mĂ­stnost    
     */
    public void nastavKlice(Predmet klic1, Predmet klic2){
        this.klic1 = klic1;
        this.klic2 = klic2;
    }
    
    /**
     * VrĂˇtĂ­ prvni pĹ™edmÄ›t, kterĂ˝ je potĹ™eba k odemknutĂ­ zamÄŤenĂ© lokace .
     * (k odemnknutĂ­ jsou potĹ™eba klĂ­ÄŤe dva)
     * 
     * @returns klic1 PĹ™edmÄ›t k odemknutĂ­ mĂ­stnosti      
     */
    public Predmet getKlic1() {
             return klic1;
    }
    
    /**
     * VrĂˇtĂ­ druhĂ˝ pĹ™edmÄ›t, kterĂ˝ je potĹ™eba k odemknutĂ­ zamÄŤenĂ© lokace .
     * (k odemnknutĂ­ jsou potĹ™eba klĂ­ÄŤe dva)
     * 
     * @returns klic2 PĹ™edmÄ›t k odemknutĂ­ mĂ­stnosti       
     */
    public Predmet getKlic2() {
             return klic2;
    }
    
    /**
     * PĹ™iĹ™adĂ­ lokaci NepĹ™Ă­tele
     * 
     * @param nepritel NepĹ™Ă­tel      
     */
    public void vlozNepritele(Nepritel nepritel){
        this.nepritel = nepritel;
    }
    
    /**
     * VrĂˇtĂ­ nepĹ™Ă­tele kterĂ˝ je lokaci pĹ™iĹ™azen
     * 
     * @returns nepritel NepĹ™Ă­tel      
     */
    public Nepritel getNepritele(){
        return nepritel;
    }
    
    public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

    
}
