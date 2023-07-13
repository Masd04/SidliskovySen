package cz.vse.adventura.logika;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 *
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author David Mašek (předloha: Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova)
 * @version pro školní rok 2020/21
 */

public class Prostor {

    private String nazev;
    private String popis;
    private Set<Prostor> vychody;   // obsahuje sousední místnosti
    private Set<Vec> seznamVeci;
    private Set<NPC> seznamNpc;
    private Igelitka veciKProdeji;
    private double posLeft;
    private double posTop;


    /**
     * Vytvoření prostoru se zadaným popisem.
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor,
     * jedno slovo nebo víceslovný název bez mezer.
     *
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new HashSet<>();
        seznamVeci = new HashSet<Vec>();
        seznamNpc = new HashSet<NPC>();
        veciKProdeji = new Igelitka();
        this.posLeft = posLeft;
        this.posTop = posTop;

    }

    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
        vychody.add(vedlejsi);
    }

    /**
     * Metoda přidává věci do prostoru
     *
     * @param vec - přidávaná věc
     */

    public void addVec(Vec vec) {
        seznamVeci.add(vec);
    }

    /**
     * Getter
     *
     * @return veciKProdeji - věci na prodej v zastavárně
     */

    public Igelitka getVeciKProdeji() {
        return veciKProdeji;
    }


    /**
     *  Vrací název věci
     *
     *  return
     */

    public Vec vratVec(String jmenoVeci) {
        Vec vec = null;
        for (Vec vec1 : seznamVeci) {
            if (vec1.getNazev().equals(jmenoVeci)) {
                vec = vec1;
                break;
            }
        }
        if (vec != null && vec.isSebratelna()) {
            seznamVeci.remove(vec);
            return vec;
        }
        else {
            return null;
        }


    }

    /**
     * Odebírá věc z prostoru
     *
     * @param jmenoVeci - název odebírané věci
     */

    public void removeVec(String jmenoVeci) {
        seznamVeci.remove(jmenoVeci);
    }

    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     *
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */

      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů.
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }

    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }



    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev() {
        return nazev;       
    }

    /**
     * Přidá postavu do prostoru
     *
     * @param npc - název postavy
     */

    public void pridejNPC (NPC npc) {
        seznamNpc.add(npc);
    }


    /**
     * Vrací "dlouhý" popis prostoru, který může vypadat následovně: Jsi v
     * mistnosti/prostoru vstupni hala budovy VSE na Jiznim meste. vychody:
     * chodba bufet ucebna
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        return "Jsi " + popis + ".\n"
                + "   " + popisVychodu() + "\n"
                + "   " + popisVeci() + "\n" + "   "  + popisNPC() + "\n";

    }

    /**
     * Vrací textový řetězec, který popisuje sousední východy, například:
     * "vychody: hala ".
     *
     * @return Popis východů - názvů sousedních prostorů
     */
    private String popisVychodu() {
        String vracenyText = "východy:";
        for (Prostor sousedni : vychody) {
            vracenyText += " " + sousedni.getNazev();
        }
        return vracenyText;
    }

    /**
     * Vrací výčet věcí v prostoru.
     *
     * @return názvy věcí v aktuálním prostoru
     */

    private String popisVeci() {
        String vracenyText = "věci:";
        for (Vec vec : seznamVeci) {
            if(vec.getViditelna()){
                vracenyText += " " + vec.getNazev();
            }
        }
        return vracenyText;
    }

    /**
     * Vrací výčet postav v aktuálním prostoru
     *
     * @return jména postav v aktuálním prostoru
     */

    private String popisNPC() {
        String vracenyText = "postavy:";
        for (NPC npc : seznamNpc) {
            vracenyText += " " + npc.getJmeno() + "\n" + npc.getJmeno() + ": " + npc.getProslov();
        }
        return vracenyText + "\n" + "══════════════════════════════════════════════════════";
    }

    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */

    public Prostor vratSousedniProstor(String nazevSouseda) {
        if (nazevSouseda == null) {
            return null;
        }
        for (Prostor sousedni : vychody) {
            if (sousedni.getNazev().equals(nazevSouseda)) {
                return sousedni;
            }
        }
        return null;
    }

        public double getPosLeft() {
            return posLeft;
        }

        public double getPosTop() {
            return posTop;
        }
    }


