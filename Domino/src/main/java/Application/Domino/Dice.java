package Application.Domino;

import Application.Direction;

import java.awt.*;

public class Dice {
    private final int SMALL_RECT_DIAMETER = 50;
    private int firstValue;
    private int secondValue;
    private int x, y;
    Rect firstRect, secondRect;
    private Direction direction;

    private class Rect {
        public int x, y;
        public int diameter, value;
        boolean isVertical;
        public final int POINT_RADIUS = SMALL_RECT_DIAMETER / 10;

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
                    g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    break;
                case 2:
                    g.setColor(Color.RED);
                    if (isVertical) {
                        g.fillOval(x + diameter / 4 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 + diameter / 4 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    } else {
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 4 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 + diameter / 4 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    }
                    break;
                case 3:
                    g.setColor(Color.RED);
                    if (isVertical) {
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    } else {
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    }
                    break;
                case 4 :
                    g.setColor(Color.RED);
                    g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    break;
                case 5 :
                    g.setColor(Color.RED);
                    g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                            2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    break;
                case 6 :
                    g.setColor(Color.RED);
                    if (isVertical) {
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 2 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    } else {
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + 4 * diameter / 5 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                        g.fillOval(x + diameter / 2 - POINT_RADIUS, y + 4 * diameter / 5 - POINT_RADIUS,
                                2 * POINT_RADIUS, 2 * POINT_RADIUS);
                    }
                    break;
            }
        }
    }

    public Dice(int firstValue, int secondValue, int x, int y, Direction direction) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.x = x;
        this.y = y;
        this.direction = direction;
        firstRect = new Rect(0, 0, SMALL_RECT_DIAMETER, firstValue, true);
        secondRect = new Rect(0, 0, SMALL_RECT_DIAMETER, secondValue, true);
    }


    //Доделать класс квадрата

    public void draw(Graphics gr) {
        switch (direction) {
            case UP:
                firstRect.x = x;
                firstRect.y = y;

                secondRect.x = x;
                secondRect.y = y + SMALL_RECT_DIAMETER;

                firstRect.isVertical = true;
                secondRect.isVertical = true;
                break;
            case RIGHT:
                secondRect.x = x;
                secondRect.y = y;

                firstRect.x = x + SMALL_RECT_DIAMETER;
                firstRect.y = y;

                firstRect.isVertical = false;
                secondRect.isVertical = false;
                break;
            case DOWN:
                secondRect.x = x;
                secondRect.y = y;

                firstRect.x = x;
                firstRect.y = y + SMALL_RECT_DIAMETER;

                firstRect.isVertical = true;
                secondRect.isVertical = true;
                break;
            case LEFT:
                firstRect.x = x;
                firstRect.y = y;

                secondRect.x = x + SMALL_RECT_DIAMETER;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
