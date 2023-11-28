package Application.Domino;

import java.awt.*;

public class Dice {
    private final int HEIGHT = 100;
    private final  int WIDTH = 200;
    private int firstValue;
    private int secondValue;
    private int x;
    private int y;

    public Dice(int firstValue, int secondValue, int x, int y) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x,y ,WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);
    }

    public int getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(int firstValue) {
        this.firstValue = firstValue;
    }

    public int getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(int secondValue) {
        this.secondValue = secondValue;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
