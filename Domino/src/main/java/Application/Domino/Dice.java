package Application.Domino;

import javax.swing.*;
import java.awt.*;

public class Dice extends JComponent implements Comparable<Dice>{
    private final static int SMALL_RECT_DIAMETER = 40;
    private int firstValue;
    private int secondValue;
    private int x, y;
    Rect firstRect, secondRect;
    private Direction direction;
    private boolean isAvailable;
    private boolean isChosen;

    @Override //Compare by sum
    public int compareTo(Dice o) {
        int mainSum = firstValue + secondValue;
        int comparedSum = o.firstValue + o.secondValue;
        if (mainSum > comparedSum) {
            return 1;
        } else if (mainSum < comparedSum){
            return -1;
        }
        return 0;
    }

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
        isAvailable = false;
        isChosen = false;
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
    @Override
    public String toString() {
        return "{ " + firstValue + ", " + secondValue + " }";
    }
    public static int getSMALL_RECT_DIAMETER() {
        return SMALL_RECT_DIAMETER;
    }
    public static Dice copyOf(Dice dice) {
        return new Dice(dice.getFirstValue(), dice.getSecondValue(), dice.getX(), dice.getY(), dice.getDirection());
    }
    public boolean isV1sameAsV2() {
        return firstValue == secondValue;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }
}
