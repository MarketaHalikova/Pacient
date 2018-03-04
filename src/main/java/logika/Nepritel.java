/* Soubor je ulozen v kodovani UTF-8.
  * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package logika;

/**
 * Třída Nepřítel reprezentuje postavu nepřítele kterou je možné ve hře potkat
 * 
 * Nepřítele je možné zabít příkazem zautoc se správnou kombinací léků v lékárničce
 * Nepřítel má název, popis, status zda je živý, a dvě zbraně kterými se dá zabít
 *
 * @author     Markéta Halíková
 * @version    LS 2016/2017
 */
public class Nepritel{
    private String nazev;
    private String popis;
    private boolean zivy;
    
    private String zbranProtiMe1;
    private String zbranProtiMe2;
    
    /**
     * Vytvoří nového nepřítele se zadaným názvem, popisem a statusem zivy a zbraněmi proti němu.
     * 
     * @param    nazev          název nepřítele (jedno slovo)
     * @param    popis          popis nepřítele (může se jednat o text libovolné délky)
     * @param    zivy           true, pokud je živý ; jinak false pokud jsme ho již zabili
     */
    public Nepritel(String nazev, String popis, boolean zivy, String zbranProtiMe1, String zbranProtiMe2){
        this.nazev = nazev;
        this.popis = popis;
        this.zivy = zivy;
        this.zbranProtiMe1 = zbranProtiMe1;
        this.zbranProtiMe2 = zbranProtiMe2;
    }

    
    /**
     * Vrátí název nepřítele.
     * 
     * @returns    název nepřítele
     */
    public String getNazev(){
        return nazev;
    }
    
    /**
     * Vrátí popis nepřítele.
     * 
     * @returns    popis nepřítele
     */
    public String getPopis(){
        return popis;
    }
    
    /**
     * Vrátí status, zda je předmět živý, nebo ne.
     * 
     * @returns    true, pokud je nepřítel živý; jinak false
     */
    public boolean isZivy(){
        return zivy;
    }
    
    /**
     * Nastaví nový popis nepřítele.
     * 
     * @param    popis nový popisnepřítele
     */
    public void setPopis(String popis){
        this.popis = popis;
    }
    
    /**
     * Nastaví status živý nepřítele.
     * 
     * @param    živý   true, pokud má být nepřítel živý; jinak false
     */
    public void setZivy(boolean zivy){
        this.zivy = zivy;
    }

    /**
     * Vrátí název prvního předmětu který je nutný použít proti nepříteli jako zbraň
     * 
     * @returns    název předmětu(zbraně)
     */
    public String getZbranProtiMe1(){
        return zbranProtiMe1;
    }
    
    /**
     * Vrátí název druhého předmětu který je nutný použít proti nepříteli jako zbraň
     * 
     * @returns    název předmětu(zbraně)
     */
    public String getZbranProtiMe2(){
        return zbranProtiMe2;
    }
    
    @Override
    public String toString(){
        return "Nepritel: " + nazev;
    }
}
