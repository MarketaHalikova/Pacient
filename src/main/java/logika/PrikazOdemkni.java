package logika;


/**
 * Třída implementuje příkaz odemkni pro odemceni zamcene mistnosti pomoci klicu
 * 
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazOdemkni implements IPrikaz{
    private static final String NAZEV = "odemkni";
    private HerniPlan hPlan;

    /**
    *  Konstruktor třídy
    *  
    *  @param hPlan herní plán, je potřeba zjistit, zda jsem v místnosti
    *                    vedle místnosti, která se má odemknout
    */    
    public PrikazOdemkni(HerniPlan hPlan) {
        this.hPlan = hPlan;
    }

    /**
     *  Provádí příkaz "odemkni". Zjišťuji, zda z aktuální místnosti je
     *  východ do zadané místnosti. Pokud místnost existuje a je zamčená,
     *  tak se zjistí, zda v lékárničce je potřebný klíč. Pokud ano, odemknu
     *  místnost.
     *
     * @param parametry - jako  parametr obsahuje jméno místnosti,
     *                         do které se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */ 
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím co mám odemknout? Musíš zadat název lokace";
        }
        
        if (parametry.length > 1) {
            return "Nemůžeš odemknout víc věcí najednou";
        }
        String lokace = parametry[0];

        Lokace sousedniLokace = hPlan.getAktualniLokace().vratSousedniLokaci(lokace);

        if (sousedniLokace == null) {
            return "Odsud se do lokace "+lokace+" nedá jít!";
        }
        else {
            if (sousedniLokace.jeZamceno()) {
                if ((hPlan.getLekarnicka().obsahujePredmet(sousedniLokace.getKlic1().getNazev()))&& (hPlan.getLekarnicka().obsahujePredmet(sousedniLokace.getKlic2().getNazev()))) {
                    sousedniLokace.odemknout(true);
                    return "Podařilo se ti odemknout lokaci "
                           + lokace + ". Správné prášky tedy máš. Stačí jen vstoupit a máš vyhráno";
                }
                else {
                    return "Pro odemčení lokace "+lokace+" potřebuješ mít "
                        + "u sebe "+sousedniLokace.getKlic1().getNazev()+ "  a "+sousedniLokace.getKlic2().getNazev();
                }
            }
            else {
                return "Lokaci "+lokace+" je už odemčená!!!";
            }
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    public String getNazev() {
        return NAZEV;
    }

}
