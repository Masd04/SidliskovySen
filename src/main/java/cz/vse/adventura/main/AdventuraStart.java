package cz.vse.adventura.main;

import cz.vse.adventura.gui.Mapa;
import cz.vse.adventura.gui.PanelIgelitka;
import cz.vse.adventura.gui.PanelVychodu;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import cz.vse.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class AdventuraStart extends Application {

    private IHra hra = Hra.getSingleton();
    private TextArea konzole = new TextArea();

    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */

    public static void main(String... args) {
        IHra hra = new Hra();
        if (args.length > 0) {
            String prepinac = args[0];
            if ("text".equals(prepinac)) {
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else if ("graphic".equals(prepinac)) {
                launch(args);
            } else {
                throw new IllegalArgumentException("Vstupní parametr " + prepinac + " neznám.");
            }
        } else {
            launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();


        TextArea konzole = new TextArea();
        konzole.setText(hra.vratUvitani());
        konzole.setEditable(false);
        borderPane.setCenter(konzole);
        konzole.setFont(Font.font("Times New Roman", FontPosture.ITALIC, 15));


        PanelVychodu panelVychodu = new PanelVychodu(hra.getHerniPlan());
        borderPane.setRight(panelVychodu.getListview());

        Mapa mapa = new Mapa(hra.getHerniPlan());
        AnchorPane veciAPa = (AnchorPane) mapa.getAnchorPane();

        borderPane.setTop(pridejVeci(veciAPa));
        pridejVeci(veciAPa);

        PanelIgelitka panelIgelitka = new PanelIgelitka(hra);
        borderPane.setLeft(panelIgelitka.getComponentNode());

        Label poleLabel = new Label("Sem piš -> ");
        poleLabel.setFont(Font.font("Comic SANS MS", FontWeight.EXTRA_BOLD, 20));

        TextField prikazPole = new TextField();

        HBox spodniHBox = new HBox();
        spodniHBox.setAlignment(Pos.BASELINE_CENTER);
        spodniHBox.getChildren().addAll(poleLabel, prikazPole);

        borderPane.setBottom(spodniHBox);

        prikazPole.setOnAction(event -> {
            String prikaz = prikazPole.getText();
            String navratHodnota = hra.zpracujPrikaz(prikaz);


            if (hra.isGameWon()) {
                konzole.appendText("╒══════════════════════════════════════════════════════╕" +"\n" + "\n"
                        + "                                                        " + "\n" +
                        "                      VYHRÁL JSI!!!                     "  + "\n" +
                        "      Gratuluji! Na mapě se objevil bonusový item.      " + "\n" +
                        "      Po jeho nalezení si můžeš plně vychutnat sídliskový sen!      " + "\n" +
                     //   "\n"  +    "                                                        " + "\n" +
                        "\n"  +    "╘══════════════════════════════════════════════════════╛");

            }
            konzole.appendText("\n" + navratHodnota + "\n");
            prikazPole.clear();

            if (hra.konecHry()) {
                prikazPole.setEditable(false);
            }
        });

        MenuBar menuBar = pripravMenu(primaryStage);
        VBox menuHraVBox = new VBox();
        menuHraVBox.getChildren().addAll(menuBar, borderPane);

        Scene scene = new Scene(menuHraVBox, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sídliskový sen");
        primaryStage.setFullScreen(false);
        prikazPole.requestFocus();
        primaryStage.show();
    }
        private MenuBar pripravMenu(Stage primaryStage) {
           MenuBar menuBar = new MenuBar();
           Menu souborMenu = new Menu("Soubor");
           Menu napovedaMenu= new Menu("Nápověda");

           ImageView nGButton = new ImageView(new Image(AdventuraStart.class.getResourceAsStream("/zdroje/newGame.png"), 200, 200, true, true));
            MenuItem ngMenuItem = new MenuItem("Nová Hra", nGButton);
            ngMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl + N"));
            ngMenuItem.setOnAction(event -> {
                primaryStage.close();
                hra = new Hra();

                konzole.setEditable(true);
                start(new Stage());
            });

            MenuItem endMenu = new MenuItem("Konec");
            endMenu.setOnAction(event -> System.exit(0));
            
            MenuItem aboutMenu = new MenuItem("O Aplikaci");
            MenuItem helpMenu = new MenuItem("Nápověda");



           SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
           souborMenu.getItems().addAll(ngMenuItem, separatorMenuItem, endMenu);
           napovedaMenu.getItems().addAll(aboutMenu,helpMenu);

           aboutMenu.setOnAction(event -> {
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Java FX Adventura");
               alert.setHeaderText("Sídliskový sen - Java FX Adventura");
               alert.setContentText("Verze ZS 2021");
               alert.showAndWait();
           });
            helpMenu.setOnAction(event -> {
                Stage stage = new Stage();
                stage.setTitle("Nápověda k aplikaci");
                WebView webView = new WebView();
                webView.getEngine().load(AdventuraStart.class.getResource
                        ("/zdroje/napovedaManual.html").toExternalForm());
           stage.setScene(new Scene(webView, 600, 600));
            stage.show();
            });
            menuBar.getMenus().addAll(souborMenu, napovedaMenu);
            return menuBar;
        }


    private AnchorPane pridejVeci(AnchorPane veciAP) {

        ImageView autobus = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/autobus.png"), 150, 150, false, false));

        AnchorPane.setLeftAnchor(autobus, 505.0);
        AnchorPane.setTopAnchor(autobus, 70.0);


        ImageView strom = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/strom.png"), 150, 150, false, false));
        AnchorPane.setLeftAnchor(strom, 420.0);
        AnchorPane.setTopAnchor(strom, 360.0);


        ImageView ker = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/ker.png"), 50, 50, false, false));
        AnchorPane.setLeftAnchor(ker, 340.0);
        AnchorPane.setTopAnchor(ker, 450.0);


        ImageView pAutomat = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/pAutomat.png"), 100, 100, false, false));
        AnchorPane.setLeftAnchor(pAutomat, 630.0);
        AnchorPane.setTopAnchor(pAutomat, 90.0);


        ImageView kos = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/kos.png"), 65, 65, false, false));
        AnchorPane.setLeftAnchor(kos, 903.0);
        AnchorPane.setTopAnchor(kos, 225.0);

        ImageView kos1 = new ImageView(new Image(Mapa.class.getResourceAsStream("/zdroje/kos.png"), 65, 65, false, false));
        AnchorPane.setLeftAnchor(kos1, 903.0);
        AnchorPane.setTopAnchor(kos1, 300.0);


        veciAP.getChildren().addAll(autobus, strom, ker, pAutomat, kos, kos1);
        return veciAP;
    }
}