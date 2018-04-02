/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kĂłdovĂˇnĂ­: PĹ™Ă­liĹˇ ĹľluĹĄouÄŤkĂ˝ kĹŻĹ� ĂşpÄ›l ÄŹĂˇbelskĂ© Ăłdy. */
package logika;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * TestovacĂ­ tĹ™Ă­da NepritelTest slouĹľĂ­ ke komplexnĂ­mu otestovĂˇnĂ­ tĹ™Ă­dy Nepritel 
 *
 * @author    MarkĂ©ta HalĂ­kovĂˇ
 * @version   LS 2016/17
 */
public class NepritelTest{
    private final static String NAZEV = "nepritel";
    private final static String POPIS = "popis nepritele";
    private final static String POPIS_2 = "popis nepritele2";
    private final static boolean ZIVY = true;
    private final static String ZBRAN_PROTI_ME_1 = "zbran1";
    private final static String ZBRAN_PROTI_ME_2 = "zbran2";
    
    private Nepritel nepritel;
    @Before
    public void setUp(){
        nepritel = new Nepritel(NAZEV, POPIS, ZIVY, ZBRAN_PROTI_ME_1, ZBRAN_PROTI_ME_2);
    }

    /**
     * TestovacĂ­ metoda metody getNazev tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testGetNazev() {
        assertEquals(NAZEV, nepritel.getNazev());
    }

    /**
     * TestovacĂ­ metoda metody getPopis tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testGetPopis() {
        assertEquals(POPIS, nepritel.getPopis());
    }

    /**
     *  TestovacĂ­ metoda metody isZivy tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testIsZivy() {
        assertEquals(ZIVY, nepritel.isZivy());
    }

    /**
     *  TestovacĂ­ metoda metody setPopis tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testSetPopis() {
        assertEquals(POPIS, nepritel.getPopis());
        nepritel.setPopis(POPIS_2);
        assertEquals(POPIS_2, nepritel.getPopis());
    }

    /**
     *  TestovacĂ­ metoda metody setZivy tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testSetZivy() {
        assertEquals(ZIVY, nepritel.isZivy());
        nepritel.setZivy(false);
        assertFalse(nepritel.isZivy());
    }

     /**
     *  TestovacĂ­ metoda metody getZbranProtiMe2 tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testGetZbranProtiMe1() {
        assertEquals(ZBRAN_PROTI_ME_1, nepritel.getZbranProtiMe1());
    }
    
     /**
     *  TestovacĂ­ metoda metody getZbranProtiMe2 tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testGetZbranProtiMe2() {
        assertEquals(ZBRAN_PROTI_ME_2, nepritel.getZbranProtiMe2());
    }
    
    /**
     *  TestovacĂ­ metoda metody toString tĹ™Ă­dy Nepritel.
     */
    @Test
    public void testToString() {
        assertEquals("Nepřítel: " + NAZEV, nepritel.toString());

    }

}
