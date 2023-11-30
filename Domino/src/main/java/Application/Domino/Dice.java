package Application.Domino;

import java.awt.*;

public class Dice {
    private final int HEIGHT = 100;
    private final int WIDTH = 50;
    private int firstValue;
    private int secondValue;
    private int x, y;
    private int direction; // 0 - up, 1 - right, 2 - down, 3 - left


    public Dice(int firstValue, int secondValue, int x, int y, int direction) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    private class Rect {
        public int x, y;
        public int rectWidth, rectHeight, value;

        public Rect(int x, int y, int rectWidth, int rectHeight, int value) {
            this.x = x;
            this.y = y;
            this.rectWidth = rectWidth;
            this.rectHeight = rectHeight;
            this.value = value;
        }
        public void draw(Graphics gr) {
            Graphics2D g = (Graphics2D) gr;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, rectWidth, rectHeight);
        }
    }
    //Доделать класс квадрата

    public void draw(Graphics gr) {
        Graphics2D g = (Graphics2D) gr;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x, y, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);

        g.drawLine(x, y + HEIGHT / 2, x + WIDTH, y + HEIGHT / 2);

        int pointDiameter = HEIGHT / 10;
        switch (firstValue) {
            case 0:
                break;
            case 1:
                g.setColor(Color.RED);
                g.fillOval(x + WIDTH/2 - pointDiameter/2, y + HEIGHT/4 - pointDiameter/2, pointDiameter, pointDiameter);
                break;
            case 2:

                break;
            case 3:

                break;
        }
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
