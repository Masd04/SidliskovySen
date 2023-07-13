package cz.vse.adventura.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     David Mašek (předloha: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova)
 *@version    pro školní rok 2020/2021
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private boolean rolexky = false;
    private Igelitka igelitka;
    private boolean isGameWon;

    private static Hra singleton = new Hra();

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber (herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazIgelitka(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdhod(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProdej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKoupit(herniPlan));


    }

//    ╒══════════════════════════════════════════════════════╕
//
//
//
//    ╘══════════════════════════════════════════════════════╛


    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "╒════════════════════════════════════════════════════╕" + "\n" + "\n" +
               "                                     Vítej v Sídliskovém snu!\n" + "\n" +
               "   Naskytla se ti možnost realizace Sídliskového snu a je jen na tobě, jak se k tomu postavíš.\n" +
               "   Nevíš si rady? Zkus příkaz 'napoveda'.\n" +
               "\n" + "   " +
               herniPlan.getAktualniProstor().dlouhyPopis() + "\n";
    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Hra je u konce, mír s tebou!";
    }

    public boolean isGameWon() {
        return isGameWon;
    }

    public void setGameWon(boolean gameWon) {
        isGameWon = gameWon;
    }


    /**
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Neexistující příkaz.. Zkus to znovu. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     *  Metoda vrátí odkaz na igelitku.
     *
     *  @return     odkaz na igelitku
     */

    public  Igelitka getIgelitka() {
        return igelitka;
    }

    public static Hra getSingleton() {
        return singleton;
    }
}


