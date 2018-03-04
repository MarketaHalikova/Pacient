/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída PrikazZautoc implementuje pro hru příkaz zautoc
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class PrikazZautoc implements IPrikaz
{
    private static final String NAZEV = "zautoc";
    
    private HerniPlan hPlan;
    private Hra hra;
    
    private static final String PREDMET_HLIDANY = "paralen2";
    
   /**
    * Konstruktor třídy
    *
    * @param    plan herní plán, ve kterém se bude ve hře "chodit" 
    * @param    hra ,která bude moci být ukončena pokud nás nepřítel zabije
    */  
    public PrikazZautoc(HerniPlan hPlan, Hra hra){
        this.hPlan = hPlan;
        this.hra = hra;
    }

    /**
     * Provádí příkaz "prozkoumej". Pokud nebyl zadán žádný parametr, vrátí chybné hlášení
     * Pokud byl zadán jeden parametr, zjistí, zda se jedná o nepřítele
     * a následně zjišťuje zda jsou v lékárničce potřebné předměty k zabití nepřítele. Pokud ne
     * hra končí - hráč byl zabit. Pokud ano
     * Nepřítel je zabit a status jím hlídaného předmětu se změní na přenositelný. Pokud bylo
     * zadáno více parametrů, vrátí chybové hlášení.
     *
     * @param     parametry může jako parametr obsahovat název nepřítele, na kterého chce hráč zaútočit
     * @return    zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String proved(String... parametry)
    {
        if (parametry.length > 1)
        {
            return "Nemůžeš zaútočit na víc věcí najednou.";
        }
        
        Lokace aktLokace = hPlan.getAktualniLokace();
        
        if (parametry.length > 0)
        {
            
            String prozkoumavanaVec = parametry[0];
            
            if (aktLokace.getNepritele()== null){
                return prozkoumavanaVec + " není tvůj nepřítel, v této místnosti žádného nepřítele nemáš, takže klid. ";
            
            }
            
            if (!aktLokace.getNepritele().isZivy()){
                return prozkoumavanaVec + " už je mrtvý, vždyť už si ho zabil/a. ";
            
            }
            
            if (aktLokace.getNepritele().getNazev().equals(prozkoumavanaVec)){
                
                if (hPlan.getLekarnicka().obsahujePredmet(aktLokace.getNepritele().getZbranProtiMe1())) {
                    
                    if(hPlan.getLekarnicka().obsahujePredmet(aktLokace.getNepritele().getZbranProtiMe2())){
                    
                        aktLokace.getNepritele().setZivy(false);
                        aktLokace.najdiPredmet(PREDMET_HLIDANY).setPrenositelny(true);
                        
                        return "HURÁÁÁ!!! Zabil/a si nepřítele, nyní si můžeš vzít předmět, který hlídal";

                    }
                    
                    
                    hra.setKonecHry(true);
                    return "Ale néé!! Nepřítel tě zabil, hra skončila. V lékárničce si měl/a jen jeden z potřebných léků pro jeho zabití. Zkus to znovu!";
                }
                hra.setKonecHry(true);
                return "Nepřítel tě zabil, což znamená konec hry. Ale můžeš si za to sám/a. Zaútočil/a jsi na něj bez potřebných léků.";
                
            } else {
                 return prozkoumavanaVec + " není tvůj nepřítel, proč na to útočíš?!? ";
            }
        }
        else
        {
            return "Neřekl/a si na co chceš zaútočit.";
        }
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání).
     *
     * @return    název příkazu
     */
    @Override
    public String getNazev()
    {
        return NAZEV;
    }
}