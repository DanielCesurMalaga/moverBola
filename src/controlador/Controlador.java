package controlador;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
                    moverBola();
                } else {
                    moverBola();
                }
            }
        };
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Canvas lienzo = new Canvas(800, 600);
        GraphicsContext gc = lienzo.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(200, 200, 50, 50);

        panel.getChildren().add(lienzo);

        Rectangle obstaculo = new Rectangle(500, 400, 60, 60);
        panel.getChildren().addAll(obstaculo);
        panel.setFocusTraversable(true);
        panel.setStyle("-fx-background-color: grey");
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

        if (esFrontera(miBola.getBola().getLayoutX(),
                miBola.getBola().getLayoutY(),
                miBola.getSentidoX(),
                miBola.getSentidoY())) {
            pararAzul = true;
            return true;
        }

        if (!colorIgual(miBola.getBola().getLayoutX(),
                miBola.getBola().getLayoutY(),
                miBola.getSentidoX(),
                miBola.getSentidoY())) {
            pararAzul = true;
            return true;
        }

        return false;
    }

    public boolean colorIgual(double centroX, double centroY, double sentidoX, double sentidoY) {
        Color colorCentro = Color.GREY;
        Color colorU1 = Color.GREY;
        Color colorU2 = Color.GREY;
        Color colorD1 = Color.GREY;
        Color colorD2 = Color.GREY;
        // cálculo común

        if (sentidoX > 0) {
            colorCentro = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 * sentidoX + sentidoX),
                    (int) (centroY + 10 * sentidoY + sentidoY));
            colorU1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX),
                    (int) (centroY - 10 - 1));
            colorU2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + (10 * Math.cos(45)) + 1),
                    (int) (centroY - (10 * Math.sin(45)) - 1));
            colorD1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX),
                    (int) (centroY + 10 + 1));
            colorD2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + (10 * Math.cos(45)) + 1),
                    (int) (centroY + (10 * Math.sin(45)) + 1));

        } else if (sentidoX < 0) {
            colorCentro = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 * sentidoX + sentidoX),
                    (int) (centroY + 10 * sentidoY + sentidoY));
            colorU1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX),
                    (int) (centroY + 10 + 1));
            colorU2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - (10 * Math.cos(45)) - 1),
                    (int) (centroY - (10 * Math.sin(45)) - 1));
            colorD1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX),
                    (int) (centroY - 10 - 1));
            colorD2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - (10 * Math.cos(45)) - 1),
                    (int) (centroY + (10 * Math.sin(45)) + 1));

        } else if (sentidoY > 0) {
            colorCentro = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 * sentidoX + sentidoX),
                    (int) (centroY + 10 * sentidoY + sentidoY));
            colorU1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - 10 - 1),
                    (int) (centroY));
            colorU2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + (10 * Math.cos(45)) + 1),
                    (int) (centroY + (10 * Math.sin(45)) + 1));
            colorD1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 + 1),
                    (int) (centroY));
            colorD2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - (10 * Math.cos(45)) - 1),
                    (int) (centroY + (10 * Math.sin(45)) + 1));

        } else if (sentidoY < 0) {
            colorCentro = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 * sentidoX + sentidoX),
                    (int) (centroY + 10 * sentidoY + sentidoY));
            colorU1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - 10 - 1),
                    (int) (centroY));
            colorU2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX - (10 * Math.cos(45)) - 1),
                    (int) (centroY - (10 * Math.sin(45)) - 1));
            colorD1 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + 10 + 1),
                    (int) (centroY));
            colorD2 = capturaPantalla.getPixelReader().getColor(
                    (int) (centroX + (10 * Math.cos(45)) + 1),
                    (int) (centroY - (10 * Math.sin(45)) - 1));
        }

        return (colorCentro.equals(Color.GREY) &&
                colorU1.equals(Color.GREY) &&
                colorU2.equals(Color.GREY) &&
                colorD1.equals(Color.GREY) &&
                colorD2.equals(Color.GREY));
    }

    public boolean esFrontera(double centroX, double centroY, double sentidoX, double sentidoY) {

        if (sentidoX > 0) {
            if ((centroX + 10 + 1) > 799) {
                return true;
            }
            return false;
        } else if (sentidoX < 0) {
            if ((centroX - 10 - 1) <= 0) {
                return true;
            }
            return false;
        }

        if (sentidoY > 0) {
            if ((centroY + 10 + 1) > 599) {
                return true;
            }
            return false;
        } else if (sentidoY < 0) {
            if ((centroY - 10 - 1) <= 0) {
                return true;
            }
            return false;
        }
        return false;
    }
}
