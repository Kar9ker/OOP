package Application.Domino;

import java.awt.*;

public class Dice {
    private final int DIAMETER = 50;
    private int firstValue;
    private int secondValue;
    private int x, y;
    Rect firstRect, secondRect;
    private int direction; // 0 - up, 1 - right, 2 - down, 3 - left

    private class Rect {
        public int x, y;
        public int diameter, value;
        boolean isVertical;
        public final int POINT_RADIUS = 5;

        public Rect(int x, int y, int diameter, int value, boolean isVertical) {
            this.x = x;
            this.y = y;
            this.diameter = diameter;
            this.value = value;
            this.isVertical = isVertical;
        }
        public void draw(Graphics gr) {
            Graphics2D g = (Graphics2D) gr;
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(x, y, diameter, diameter);
            g.setColor(Color.BLACK);
            g.drawRect(x, y, diameter, diameter);
            switch (value) {
                case 0:
                    break;
                case 1:
                    g.setColor(Color.RED);
                    g.fillOval(x + diameter/2 - POINT_RADIUS, y + diameter/2 - POINT_RADIUS,
                            2*POINT_RADIUS, 2*POINT_RADIUS);
                    break;
                case 2:
                    g.setColor(Color.RED);
                    if (isVertical) {
                        g.fillOval(x + diameter / 4 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 + diameter / 4 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    }else {
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 4 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 + diameter/4 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    }
                    break;
            }
        }
    }

    public Dice(int firstValue, int secondValue, int x, int y, int direction) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.x = x;
        this.y = y;
        this.direction = direction;
        firstRect = new Rect(0,0, DIAMETER, firstValue, true);
        secondRect = new Rect(0,0, DIAMETER, secondValue, true);
    }


    //Доделать класс квадрата

    public void draw(Graphics gr) {
        // 0 - up, 1 - right, 2 - down, 3 - left
        switch (direction) {
            case 0:
                firstRect.x = x;
                firstRect.y = y;

                secondRect.x = x;
                secondRect.y = y + DIAMETER;

                firstRect.isVertical = true;
                secondRect.isVertical = true;
                break;
            case 1:
                secondRect.x = x;
                secondRect.y = y;

                firstRect.x = x + DIAMETER;
                firstRect.y = y;

                firstRect.isVertical = false;
                secondRect.isVertical = false;
                break;
            case 2:
                secondRect.x = x;
                secondRect.y = y;

                firstRect.x = x;
                firstRect.y = y + DIAMETER;

                firstRect.isVertical = true;
                secondRect.isVertical = true;
                break;
            case 3:
                firstRect.x = x;
                firstRect.y = y;

                secondRect.x = x + DIAMETER;
                secondRect.y = y;

                firstRect.isVertical = false;
                secondRect.isVertical = false;
                break;
        }
        firstRect.draw(gr);
        secondRect.draw(gr);
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
