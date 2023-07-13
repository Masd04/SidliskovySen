package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.util.Observer;
import javafx.scene.Node;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

public class Mapa implements Observer {

    private AnchorPane anchorPane = new AnchorPane();
    private ImageView postava = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/postava2.png"), 100, 100, false, false));
    private final HerniPlan herniPlan;

    public Mapa(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;

        init();
    }

    private void init() {
        herniPlan.registerObserver(this);

        nactiMapu();
    }

    private void nactiMapu() {
        ImageView vMap = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/map.png"), 1000, 750, false, true));
        postava.setBlendMode(BlendMode.SRC_ATOP);
                anchorPane.getChildren().addAll(vMap, postava);

                updatePostava();
    }

    @Override
    public void update() {
        updatePostava();
    }

    private void updatePostava() {
        Prostor aktProstor = herniPlan.getAktualniProstor();
        AnchorPane.setLeftAnchor(postava, aktProstor.getPosLeft());
        AnchorPane.setTopAnchor(postava, aktProstor.getPosTop());
    }

        public Node getAnchorPane() {
        return anchorPane;
    }
}
