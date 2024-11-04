package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import modelo.Bola;

public class Controlador implements Initializable {
    @FXML
    Pane panel;
    @FXML
    Circle bolaAzul;

    AnimationTimer timer;
    boolean pararAzul;
    Bola miBola;
    WritableImage capturaPantalla;

    public Controlador() {
        
        pararAzul = false;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (pararAzul) {
                    miBola.getBola().setFill(Color.RED);
                    timer.stop();
                } else {
                    moverBola();
                }
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        panel.setFocusTraversable(true);
        miBola = new Bola(bolaAzul);
        timer.start();

    }

    public void teclaPulsada(KeyEvent tecla) {
        KeyCode code = tecla.getCode();
        switch (code) {
            case UP:
                miBola.setSentidoX(0);
                miBola.setSentidoY(-1);
                break;
            case RIGHT:
                miBola.setSentidoX(+1);
                miBola.setSentidoY(0);
                break;
            case DOWN:
                miBola.setSentidoX(0);
                miBola.setSentidoY(+1);
                break;
            case LEFT:
                miBola.setSentidoX(-1);
                miBola.setSentidoY(0);
                break;
            default:

                break;
        }

    }

    public void moverBola() {
        if (!colision()) {
            miBola.getBola().setLayoutX(miBola.getBola().getLayoutX() + miBola.getSentidoX());
            miBola.getBola().setLayoutY(miBola.getBola().getLayoutY() + miBola.getSentidoY());
        }
    }

    public boolean colision() {
        capturaPantalla = panel.snapshot(null, null);
        Color color = capturaPantalla.getPixelReader().getColor(
            (int)miBola.getBola().getLayoutX() + miBola.getSentidoX() + 10, 
            (int)miBola.getBola().getLayoutY() + miBola.getSentidoY() + 10
            );
        if (((miBola.getBola().getLayoutX() + miBola.getSentidoX() + 10) >= 800)
                ||
                ((miBola.getBola().getLayoutX() + miBola.getSentidoX() - 10) <= 0))
                || 
                (color == Color.WHITE)
                )
                {
            pararAzul = true;
            return true;
        }
        if (((miBola.getBola().getLayoutY() + miBola.getSentidoY() + 10) >= 600)
                ||
                ((miBola.getBola().getLayoutY() + miBola.getSentidoY() - 10) <= 0)) {
            pararAzul = true;
            return true;
        }
        return false;
    }
}
