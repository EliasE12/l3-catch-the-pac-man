package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeLineJoin;
import model.Game;
import javafx.scene.control.MenuItem;
import thread.PacManThread;
import thread.UpdateGUIThread;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CatchPacManController implements Initializable {

    @FXML private MenuItem miCargarJuego;
    @FXML private MenuItem miGuardarJuego;
    @FXML private MenuItem miSalir;
    @FXML private MenuItem miCargarNivel;
    @FXML private MenuItem miMejoresPuntajes;
    @FXML private MenuItem miAcercaJuego;
    @FXML private Pane apPane;

    private Bounds bounds;
    private Game game;
    private File file;
    private ArrayList<Arc> pacManArcs;
    private ArrayList<Circle> eyes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game();
        bounds = apPane.getBoundsInLocal();
        pacManArcs = new ArrayList<Arc>();
        eyes = new ArrayList<Circle>();

        UpdateGUIThread guiThread = new UpdateGUIThread(this);
        guiThread.setDaemon(true);
        guiThread.start();

    }

    @FXML
    void controlMiSalir(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void controlMiAcercaJuego(ActionEvent event) { }

    @FXML
    void controlMiCargarNivel(ActionEvent event) {

        JFileChooser chooser = new JFileChooser("data/levels");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int resultadoSeleccion = chooser.showOpenDialog(chooser);
        if(resultadoSeleccion == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
        }
        try {
            game.importLevel(file);
            drawPacMans();
        } catch (IOException e) {
            Alert men = new Alert(Alert.AlertType.WARNING);
            men.setTitle("Avertencia !!!");
            men.setHeaderText("Archivo invalido");
            men.setContentText("Debe selccionar un archivo válido.");
            men.showAndWait();
        }
    }

    public void drawPacMans(){
        for (int i = 0; i <game.getListPacMans().size(); i++) {

            Arc pacMan = new Arc();
            Circle eye = new Circle();

            pacManArcs.add(pacMan);
           // eyes.add(eye);

            pacMan.setRadiusX(game.getListPacMans().get(i).getRadius());
            pacMan.setRadiusY(game.getListPacMans().get(i).getRadius());
            pacMan.setStartAngle(45);
            pacMan.setLength(270);
            pacMan.setLayoutX(game.getListPacMans().get(i).getPosX());
            pacMan.setLayoutY(game.getListPacMans().get(i).getPosY());
            pacMan.setFill(Color.YELLOW);
            pacMan.setType(ArcType.ROUND);
            pacMan.setStroke(Color.BLACK);
            pacMan.setStrokeWidth(5);
            pacMan.setStrokeLineJoin(StrokeLineJoin.ROUND);

            eye.setRadius(5);
            eye.setLayoutX(pacManArcs.get(i).getLayoutX()+5);
            eye.setLayoutY(pacManArcs.get(i).getLayoutY()-20);
            eye.setFill(Color.BLACK);

            apPane.getChildren().addAll( pacMan );


            PacManThread pacManThread = new PacManThread(game.getListPacMans().get(i),this);
            pacManThread.setDaemon(true);
            pacManThread.start();


             /**
            pacManArcs.get(i).setRadiusX(game.getListPacMans().get(i).getRadius());
            pacManArcs.get(i).setRadiusY(game.getListPacMans().get(i).getRadius());
            pacManArcs.get(i).setStartAngle(45);
            pacManArcs.get(i).setLength(270);
            pacManArcs.get(i).setLayoutX(game.getListPacMans().get(i).getPosX());
            pacManArcs.get(i).setLayoutY(game.getListPacMans().get(i).getPosY());
            pacManArcs.get(i).setFill(Color.YELLOW);
            pacManArcs.get(i).setType(ArcType.ROUND);
            pacManArcs.get(i).setStroke(Color.BLACK);
            pacManArcs.get(i).setStrokeWidth(5);
            pacManArcs.get(i).setStrokeLineJoin(StrokeLineJoin.ROUND);
            eyes.get(i).setRadius(5);
            eyes.get(i).setLayoutX(pacManArcs.get(i).getLayoutX()+5);
            eyes.get(i).setLayoutY(pacManArcs.get(i).getLayoutY()-20);
            eyes.get(i).setFill(Color.BLACK);

             **/



        }
    }

    public void updatePacMans() {
        for (int i = 0; i < pacManArcs.size(); i++) {
             pacManArcs.get(i).setLayoutX(game.getListPacMans().get(i).getPosX());
             pacManArcs.get(i).setLayoutY(game.getListPacMans().get(i).getPosY());
        }
    }


    @FXML
    void controlMiGuardarJuego(ActionEvent event) {
        try {
            game.saveGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void controlMiMejoresPuntajes(ActionEvent event) {}

    public Bounds getBounds() {
        return bounds;
    }

    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
