/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Jan Riha, Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazProzkoumej implements IPrikaz
{
    private static final String NAZEV = "prozkoumej";
    private HerniPlan hPlan;
    
   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    */  
    public PrikazProzkoumej(HerniPlan hPlan){
        this.hPlan = hPlan;
    }

    /**
     * Provádí příkaz "prozkoumej". Pokud nebyl zadán žádný parametr, vrátí kompletní
     * popis aktuální lokace. Pokud byl zadán jeden parametr, pokusí se v aktuální lokaci
     * a následně v batohu najít předmět s daným názvem a vrátit jeho popis. Pokud bylo
     * zadáno více parametrů, vrátí chybové hlášení.
     *
     * @param     parametry může jako parametr obsahovat název předmětu, který chce hráč prozkoumat
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry){
        if (parametry.length > 1)
        {
            return "Tomu nerozumím, nemůžeš prozkoumat více předmětů najednou";
        }
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (parametry.length > 0)
        {
            // Vypis popis predmetu
            String prozkoumavanaVec = parametry[0];
            
            if (aktLokace.obsahujePredmet(prozkoumavanaVec))
            {
                return aktLokace.najdiPredmet(prozkoumavanaVec).getPopis();
            }

            //Pokud predmet neni v aktualni lokaci, zkusime ho najit jeste v lékárničce 
            
            if (hPlan.getLekarnicka().obsahujePredmet(prozkoumavanaVec))
            {
                return hPlan.getLekarnicka().najdiPredmet(prozkoumavanaVec).getPopis();
            }
            
            if ((aktLokace.getNepritele()!= null) && (aktLokace.getNepritele().getNazev().equals(prozkoumavanaVec))&& (aktLokace.getNepritele().isZivy())){
                return aktLokace.getNepritele().getPopis();
            }
            
            
            
            return "Předmět " + prozkoumavanaVec + " není v lékárničce ani v aktuální lokaci, ani se nejedná o nepřítele";
        }
        else
        {
            // Zobraz popis aktualni lokace
            return aktLokace.dlouhyPopis();
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
    public String getNazev(){
        return NAZEV;
    }
}
