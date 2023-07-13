package cz.vse.adventura.logika;

import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.PrikazJdi;
import cz.vse.adventura.logika.PrikazKonec;
import cz.vse.adventura.logika.SeznamPrikazu;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída SeznamPrikazuTest slouží ke komplexnímu otestování třídy  
 * SeznamPrikazu
 *
 * @author    David Mašek (předloha: Luboš Pavlíček)
 * @version  pro školní rok 2020/21
 */

public class SeznamPrikazuTest
{
    private Hra hra;
    private PrikazKonec prKonec;
    private PrikazJdi prJdi;
    private PrikazIgelitka prikazIgelitka;
    private PrikazKoupit prKoupit;
    private PrikazOdhod prOdhod;
    private PrikazProdej prProdej;
    private PrikazSeber prSeber;

    
    @Before
    public void setUp() {
        hra = new Hra();
        prKonec = new PrikazKonec(hra);
        prJdi = new PrikazJdi(hra.getHerniPlan());
        prikazIgelitka = new PrikazIgelitka(hra.getHerniPlan());
        prKoupit = new PrikazKoupit(hra.getHerniPlan());
        prOdhod = new PrikazOdhod(hra.getHerniPlan());
        prProdej = new PrikazProdej(hra.getHerniPlan());
        prSeber = new PrikazSeber(hra.getHerniPlan());
    }

    @Test
    public void testVlozeniVybrani() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazIgelitka);
        seznPrikazu.vlozPrikaz(prKoupit);
        seznPrikazu.vlozPrikaz(prOdhod);
        seznPrikazu.vlozPrikaz(prProdej);
        seznPrikazu.vlozPrikaz(prSeber);
        assertEquals(prKonec, seznPrikazu.vratPrikaz("konec"));
        assertEquals(prJdi, seznPrikazu.vratPrikaz("jdi"));
        assertEquals(null, seznPrikazu.vratPrikaz("napoveda"));
        assertEquals(prikazIgelitka, seznPrikazu.vratPrikaz("igelitka"));
        assertEquals(prKoupit, seznPrikazu.vratPrikaz("koupit"));
        assertEquals(prOdhod, seznPrikazu.vratPrikaz("odhod"));
        assertEquals(prProdej, seznPrikazu.vratPrikaz("prodej"));
        assertEquals(prSeber, seznPrikazu.vratPrikaz("seber"));
    }
    @Test
    public void testJePlatnyPrikaz() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazIgelitka);
        seznPrikazu.vlozPrikaz(prKoupit);
        seznPrikazu.vlozPrikaz(prOdhod);
        seznPrikazu.vlozPrikaz(prProdej);
        seznPrikazu.vlozPrikaz(prSeber);
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("jdi"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("nápověda"));
        assertEquals(false, seznPrikazu.jePlatnyPrikaz("Konec"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("igelitka"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("koupit"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("odhod"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("prodej"));
        assertEquals(true, seznPrikazu.jePlatnyPrikaz("seber"));
    }
    
    @Test
    public void testNazvyPrikazu() {
        SeznamPrikazu seznPrikazu = new SeznamPrikazu();
        seznPrikazu.vlozPrikaz(prKonec);
        seznPrikazu.vlozPrikaz(prJdi);
        seznPrikazu.vlozPrikaz(prikazIgelitka);
        seznPrikazu.vlozPrikaz(prKoupit);
        seznPrikazu.vlozPrikaz(prOdhod);
        seznPrikazu.vlozPrikaz(prProdej);
        seznPrikazu.vlozPrikaz(prSeber);
        String nazvy = seznPrikazu.vratNazvyPrikazu();
        assertEquals(true, nazvy.contains("konec"));
        assertEquals(true, nazvy.contains("jdi"));
        assertEquals(false, nazvy.contains("nápověda"));
        assertEquals(false, nazvy.contains("Konec"));
        assertEquals(true, nazvy.contains("igelitka"));
        assertEquals(true, nazvy.contains("koupit"));
        assertEquals(true, nazvy.contains("odhod"));
        assertEquals(true, nazvy.contains("prodej"));
        assertEquals(true, nazvy.contains("seber"));
        assertEquals(false, nazvy.contains("inventar"));
    }
    
}
