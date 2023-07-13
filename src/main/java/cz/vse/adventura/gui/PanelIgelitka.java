package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.IHra;
import cz.vse.adventura.logika.Igelitka;
import cz.vse.adventura.logika.Vec;
import cz.vse.adventura.util.Observer;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import java.io.InputStream;
import java.util.ArrayList;

public class PanelIgelitka implements Component, Observer {

    private Igelitka igelitka;
    private VBox vBox = new VBox();
    private final FlowPane panelVeciFlowPane = new FlowPane();
    private HerniPlan herniPlan;

    public PanelIgelitka(IHra hra) {
        this.igelitka = hra.getHerniPlan().getIgelitka();
        this.herniPlan = hra.getHerniPlan();

        init();

        igelitka.registerObserver(this);
        herniPlan.registerObserver(this);
    }

    private void init() {
        vBox.getChildren().clear();
        vBox.setPrefWidth(180);


        Label penize = new Label(herniPlan.penezenkaToString());
        penize.setFont(Font.font("Comic SANS MS", FontWeight.BOLD, 20));
        penize.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Label label = new Label("OBSAH IGELITKY:");
        label.setFont(Font.font("Comic SANS MS", FontWeight.BOLD, 15));
        label.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(penize);
        vBox.getChildren().addAll(hBox, label, panelVeciFlowPane);
    }

    @Override
    public Node getComponentNode() {
        return this.vBox;
    }

    @Override
    public void update() {
        nactiObrazky();
        init();
    }

    private void nactiObrazky() {
        panelVeciFlowPane.getChildren().clear();
        String nazev;
        ArrayList<Vec> mnozinaVeci = igelitka.getMnozinaVeci();
        for (Vec nazevVeci : mnozinaVeci) {
            nazev = "/zdroje/" + nazevVeci.getNazev() + ".png";
            InputStream inputStream =
                    PanelIgelitka.class.getResourceAsStream(nazev);
            Image image = new Image(inputStream, 80, 80, false, false);
            ImageView imageView = new ImageView(image);
            panelVeciFlowPane.getChildren().add(imageView);
        }
    }
}
