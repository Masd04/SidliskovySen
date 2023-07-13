package cz.vse.adventura.logika;

/**
 *  Třída PrikazNapoveda implementuje pro hru příkaz napoveda.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     David Mašek(podle předlohy: Jarmila Pavlickova, Luboš Pavlíček)
 *@version    pro školní rok 2016/2017
 *  
 */
class PrikazNapoveda implements IPrikaz {
    
    private static final String NAZEV = "napoveda";
    private SeznamPrikazu platnePrikazy;
    
    
     /**
    *  Konstruktor třídy
    *  
    *  @param platnePrikazy seznam příkazů,
    *                       které je možné ve hře použít,
    *                       aby je nápověda mohla zobrazit uživateli. 
    */    
    public PrikazNapoveda(SeznamPrikazu platnePrikazy) {
        this.platnePrikazy = platnePrikazy;
    }
    
    /**
     *  Vrací základní nápovědu po zadání příkazu "napoveda".
     *  
     *  @return napoveda ke hre
     */
    @Override
    public String provedPrikaz(String... parametry) {
        return "ZÍSKEJ ZLATÉ ROLEXKY! ZA KAŽDOU CENU!\n"
        + "Nemáš ani vindru, ale ty hodinky prostě musíš mít..\nKaždá věc má svoji cenu, zkus třeba něco prodat v zastavárně..\nTy hodinky budou stát minimálně 15 penízků(ale možná kecám)"
            + "\n" + "Rolexky má Dušan v trezoru, prodá ti je teprve, až přijdeš s prachama v kapse!" +
        "\n" + "Zakoupíš je pouze v zastavárně příkazem 'koupit rolexky'" + "\n"
        + "Platné příkazy:\n"
        + platnePrikazy.vratNazvyPrikazu()
        + "\n"
        + "\n"
        + "Při psaní názvu prostoru/věci nezapomeň na velikost písmen!\n";
    }
    
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @ return nazev prikazu
     */
    @Override
      public String getNazev() {
        return NAZEV;
     }

}
