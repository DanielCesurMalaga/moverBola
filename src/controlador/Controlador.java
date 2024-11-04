package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class Controlador implements Initializable {
    @FXML
    Pane panel;
    @FXML
    Circle bolaAzul;

    AnimationTimer timer;
    int sentidoAzul;
    boolean pararAzul;

    public Controlador() {

        sentidoAzul = 1;
        pararAzul = false;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                
                    if (!pararAzul) {
                        if ((bolaAzul.getLayoutX() + sentidoAzul + 10) >= 800) {
                            sentidoAzul = sentidoAzul * -1;
                        } else if ((bolaAzul.getLayoutX() + sentidoAzul - 10) <= 0) {
                            sentidoAzul = sentidoAzul * -1;
                        }
                        bolaAzul.setLayoutX(bolaAzul.getLayoutX() + sentidoAzul);
                    } else {
                        sentidoAzul = 0;
                    }
            }

        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel.setFocusTraversable(true);
        timer.start();

    }

    public void teclaPulsada(KeyEvent tecla) {
        KeyCode code = tecla.getCode();
        switch (code) {
            case UP:
                
                break;
            
            default:

                break;
        }

    }
}
