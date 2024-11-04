package modelo;

import javafx.scene.shape.Circle;

public class Bola {
    private Circle bola;
    private int sentidoX;
    private int sentidoY;
    
    public Bola(Circle bola) {
        this.bola = bola;
        sentidoX = 0;
        sentidoY = 0;
    }
    public Circle getBola() {
        return bola;
    }
    public void setBola(Circle bola) {
        this.bola = bola;
    }
    public int getSentidoX() {
        return sentidoX;
    }
    public void setSentidoX(int sentidoX) {
        this.sentidoX = sentidoX;
    }
    public int getSentidoY() {
        return sentidoY;
    }
    public void setSentidoY(int sentidoY) {
        this.sentidoY = sentidoY;
    }
}
