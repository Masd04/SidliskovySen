package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;



public class PanelVychodu implements Observer {

    HerniPlan herniPlan;
    ListView<String> listview = new ListView<>();
    ObservableList<String> vychody = FXCollections.observableArrayList();

    public PanelVychodu(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;

        init();

        herniPlan.registerObserver(this);
    }

    private void init() {
        Prostor aktProstor = herniPlan.getAktualniProstor();
        for (Prostor p : aktProstor.getVychody()) {
            vychody.add(p.getNazev());
        }

        listview.setItems(vychody);
        listview.setPrefWidth(200);

    }

    public ListView<String> getListview() {
        return listview;
    }

    @Override
    public void update() {
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        vychody.clear();
        for(Prostor p : aktualniProstor.getVychody()) {
            vychody.add(p.getNazev());
        }
    }
}
