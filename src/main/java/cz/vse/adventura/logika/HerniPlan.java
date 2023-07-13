package cz.vse.adventura.logika;


import cz.vse.adventura.util.Observer;
import cz.vse.adventura.util.SubjectOfChange;
import java.util.HashSet;
import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     David Mašek (předloha: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova)
 *@version    pro školní rok 2020/2021
 */
public class HerniPlan implements SubjectOfChange, Observer {
    private Vec joint;
    private Prostor aktualniProstor;
    private Igelitka igelitka;
    public int penezenka = 0;
    private Set<Observer> pozorovatele = new HashSet<>();
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     */
    public HerniPlan() {
        zalozProstoryHry();
        igelitka = new Igelitka();
    }
    /**
     *  Metoda vrací igelitku (inventář) a věci v ní obsažené.
     *
     * @return igelitka
     */
    public Igelitka getIgelitka() {
        return igelitka;
    }



    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví byt.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
         Prostor byt = new Prostor("byt", "ve svém zablešeném bytě v paneláku", 90, 345 );
         Prostor lavicka = new Prostor("lavicka", "u lavičky před vchodem do paneláku, kde pravidelně sedáváš," + "\n" + "než jsi po opici schopný chůze po schodech", 298, 225);
         Prostor parkoviste = new Prostor("parkoviste", "parkoviste\",\"na parkovišti s funkčním kamerovým systémem", 560, 76 );
         Prostor park = new Prostor("park", "park\",\"v parku bez nočního osvětlení. Tady se za tmy dějí věci..", 420, 395 );
         Prostor obchodak = new Prostor("obchodak", "v jediném obchodním domě ve městě", 580, 250 );
         Prostor zastavarna = new Prostor("zastavarna", "v zastavárně, kterou vlastní tvůj nevlastní strýc Dušan", 735, 395 );
         Prostor rolex = new Prostor("ROLEX", "v obchodě se značkovými hodinkami", 815, 80 );
         Prostor mParkoviste = new Prostor("oParkoviste", "v odlehlé části parkoviště. Sem kamery nevidí..", 335, 85 );
         Prostor pZachody = new Prostor("panske_zachody", "na pánských záchodech v obchoďáku", 845, 196 );
         Prostor dZachody = new Prostor("damske_zachody", "na dámských záchodech v obchoďáku", 845, 290 );
         Prostor mercedes = new Prostor("mercedes", "u starého mercedesu stojícího v odlehlé části parkoviště. Skrz okno rozeznáváš věc na sedačce.", 55, 65 );
        // vytváření nových věcí

        Vec autobus = new Vec("autobus" , true, false , 5, 300, 300);
        Vec strom = new Vec("strom" , true,false , 5, 0, 0);
        Vec kos = new Vec("koš" , true , false , 0, 0, 0);
        Vec pAutomat = new Vec("parkovací_automat" , true , false , 0,  0, 0);
        Vec ker = new Vec("keř" , true , false , 0, 0, 0);


        Vec sroubovak = new Vec("sroubovak" , true, true , 3, 0, 0);
        Vec rohlik = new Vec("rohlik", true , true , 1, 0, 0);
        Vec cigarety = new Vec("cigarety", true , true , 2, 0, 0);
        Vec pFlaska = new Vec("prazdna_flaska", true , true , 0, 0, 0);
        Vec sluchatka = new Vec("sluchatka", true , true , 1, 0, 0);
        Vec vodka = new Vec("vodka" , true, true , 3, 0, 0);
        Vec kladivo = new Vec("kladivo" , true, true , 5, 0, 0);
        Vec telefon = new Vec("telefon" , true, true , 10, 0, 0);
        Vec mikrovlnka = new Vec("mikrovlnka" , true, true , 7, 0, 0);
        Vec ciziPenezenka = new Vec("cizi_penezenka" , true, true , 18, 0, 0);
        Vec joint = new Vec("joint",false, true, 420, 0, 0);
        this.joint = joint;
        Vec rolexky = new Vec("rolexky" , true , true, 20, 0, 0);

        //vytváření nových postav
        NPC dusan = new NPC("Dušan" , "'Tak co pro mě máš?'");
        NPC hlidac = new NPC("Hlídač" , "'Dej si na mě bacha!'");



        // přiřazují se průchody mezi prostory (sousedící prostory)
        byt.setVychod(lavicka);
        lavicka.setVychod(byt);
        lavicka.setVychod(parkoviste);
        lavicka.setVychod(park);
        parkoviste.setVychod(lavicka);
        parkoviste.setVychod(obchodak);
        parkoviste.setVychod(mParkoviste);
        mParkoviste.setVychod(parkoviste);
        mParkoviste.setVychod(mercedes);
        mercedes.setVychod(mParkoviste);
        park.setVychod(lavicka);
        park.setVychod(obchodak);
        park.setVychod(zastavarna);
        obchodak.setVychod(park);
        obchodak.setVychod(parkoviste);
        obchodak.setVychod(zastavarna);
        obchodak.setVychod(rolex);
        obchodak.setVychod(pZachody);
        obchodak.setVychod(dZachody);
        pZachody.setVychod(obchodak);
        dZachody.setVychod(obchodak);
        zastavarna.setVychod(park);
        zastavarna.setVychod(obchodak);
        rolex.setVychod(obchodak);



        //přidání věcí do prostoru
        byt.addVec(pFlaska);
        park.addVec(strom);
        park.addVec(sroubovak);
        park.addVec(mikrovlnka);
        park.addVec(ker);
        parkoviste.addVec(autobus);
        parkoviste.addVec(pAutomat);
        lavicka.addVec(vodka);
        lavicka.addVec(cigarety);
        mercedes.addVec(ciziPenezenka);
        pZachody.addVec(kladivo);
        pZachody.addVec(kos);
        dZachody.addVec(telefon);
        dZachody.addVec(kos);
        pZachody.addVec(sluchatka);
        mParkoviste.addVec(rohlik);

        zastavarna.getVeciKProdeji().pridejVec(rolexky);

        rolex.addVec(joint);

        //přidání postav do prostoru
        zastavarna.pridejNPC(dusan);
        obchodak.pridejNPC(hlidac);

                
        aktualniProstor = byt;  // hra začíná v bytě
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     *  Metoda vrací peněženku (do které jsou ukádány peníze z prodeje věcí)
     *
     * @return penezenka
     */
    public int getPenezenka() {
        return penezenka;
    }

    /**
     * Setter - pro peněženku
     *
     * @return penezenka
     */

    public void setPenezenka(int penezenka) {
        this.penezenka = penezenka;
        notifyObservers();
    }

    /**
     * Setter - nastavuje viditelnost bonusového itemu
     *
     * @param hodnota
     */

    public void setJointViditelna(boolean hodnota) {
        this.joint.setViditelna(hodnota);
    }

    public String penezenkaToString() {
        return "Peníze: " + penezenka;
    }

    @Override
    public void registerObserver(Observer observer) {
        pozorovatele.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        pozorovatele.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : pozorovatele) {
            o.update();
        }
    }

    @Override
    public void update() {

    }
}

