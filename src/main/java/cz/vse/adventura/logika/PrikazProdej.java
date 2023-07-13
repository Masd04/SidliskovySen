package cz.vse.adventura.logika;

/**
 *  Třída PrikazProdej - implementace příkazu "prodej" pro hru
 *
 *
 *@author     David Mašek
 *@version    pro školní rok 2020/2021
 */

public class PrikazProdej implements IPrikaz {

    private static final String NAZEV = "prodej";
    private HerniPlan herniPlan;
    private Igelitka igelitka;
    private String nazevProstoru = "zastavarna";

    /**
     *  Konstruktor
     *
     * @param herniPlan
     */

    public PrikazProdej (HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        this.igelitka = igelitka;

    }
    /**
     *  Provádí příkaz "prodej".
     *  Validuje uživatelský vstup (vypisuje hlášky v závislosti na správnosti napsání příkazu, parametru).
     *  správně napsaný příkaz, možnost prodeje pouze v zastavárně, stav hráčových peněz
     *  Pokud hráč prodá nesprávný item, hlásí prohru a ukončuje hru.
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Jak mám vědět, co mám prodat?? Musíš zadat název věci!";
        }
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Igelitka igelitka = herniPlan.getIgelitka();

        if (!herniPlan.getAktualniProstor().getNazev().equals("zastavarna")) {
            return "Věci můžeš prodat pouze v zastavárně.";
        } else
        if (igelitka.maVec(nazevVeci)) {
            if(!nazevVeci.equals("cizi_penezenka")){
            herniPlan.setPenezenka(herniPlan.getPenezenka() + igelitka.odhodVec(nazevVeci).getCena());

            return "Prodal jsi " + nazevVeci + "\n" + "V peněžence máš " + herniPlan.getPenezenka();

            } else {
                System.out.println("╒══════════════════════════════════════════════════════╕" +"\n" + "\n"
                        +          "                  Kdo by to byl tušil..                 " + "\n" +
                                   "                  Mercedes byl Dušana.                  "  + "\n" +
                                   "         Za tuhle krádež si posedíš v chládku.          " + "\n" +
                        "\n"  +    "                      Prohrál jsi!!                     " + "\n" +
                        "\n"  +    "╘══════════════════════════════════════════════════════╛");





            //System.exit(0);
            }
        }
        return "Tuto věc nemáš v igelitce.";
    }

    /**
     *  Getter
     *
     * @return NAZEV - název příkazu
     */

    @Override
    public String getNazev() {
        return NAZEV;
    }
}







