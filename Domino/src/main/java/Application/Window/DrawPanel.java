package Application.Window;

import Application.Domino.Direction;
import Application.Domino.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private List<Dice> diceList;
    private List<Dice> playersHand;

    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        //общая колода
        diceList = getFilledDices();
        //начальная раздача
        playersHand = getRandDices(diceList, 5);
        orderPlayersDices();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case 1 :
                        Dice dice = new Dice(5, 6, e.getX(), e.getY(), Direction.UP);
                        playersHand.add(dice);
                        repaint();
                        break;
                    case 3 :
                        int size = playersHand.size();
                        if (size > 0) {
                            Dice tmp = playersHand.get(size - 1);
                            tmp.setDirection(Direction.getNext(tmp.getDirection()));
                        }
                        repaint();
                        break;
                }
            }
        });

    }

    @Override
    public void paint(final Graphics gr) {
        super.paint(gr);
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
        for (Dice d : playersHand) {
            d.draw(gr);
        }
    }
    //возвращает список из 28 игральных костей
    private List<Dice> getFilledDices() {
        List<Dice> list = new ArrayList<>();
        int x = 50;
        int y = 20;
        for (int i = 0; i <= 6; i++) {
            for (int j = 0; j <= 6; j++) {
                if (!isSameValue(i, j, list)) {
                    list.add(new Dice(i, j, x, y, Direction.UP));
                    x += 60;
                }
            }
            x = 50;
            y += 110;
        }
        return list;
    }
    private boolean isSameValue(int firstV, int secondV, List<Dice> list) {
        if (list == null) {
            System.err.println("List is null");
            return false;
        }
        for (Dice d : list) {
            if (d.getFirstValue() == firstV && d.getSecondValue() == secondV
                || d.getFirstValue() == secondV && d.getSecondValue() == firstV) {
                return true;
            }
        }
        return false;
    }

    private List<Dice> getRandDices(List<Dice> list, int count) {
        List<Dice> finalList = new ArrayList<>(count);
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            int num = rnd.nextInt(0, list.size());
            finalList.add(list.get(num));
            list.remove(num);
        }
        return finalList;
    }
    //Получить индекс кости с наибольшей суммой очков из списка
    private int getHighestDiceIndex(List<Dice> list) {
        int index = 0;
        int maxSum = 0;
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            maxSum = Math.max(currSum, maxSum);
        }
        for (Dice d : list) {
            int currSum = d.getFirstValue() + d.getSecondValue();
            if (currSum == maxSum) {
                return index;
            }
            index++;
        }
        return index;
    }
    //Выставить руку игрока внизу экрана
    private void orderPlayersDices() {
        int x0 = PANEL_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER();
        int prekol = 0;
        boolean sign = true;
        for (Dice dice : playersHand) {
            dice.setY(PANEL_HEIGHT - Dice.getSMALL_RECT_DIAMETER() * 2 - 50);
            dice.setX(x0 + prekol);
            if (sign) {
                prekol = Math.abs(prekol) + 2 * Dice.getSMALL_RECT_DIAMETER();
            } else {
                prekol *= -1;
            }
            sign = !sign;
        }
    }
    private <T> void printList(List<T> list) {
        for (T l : list) {
            System.out.print(l.toString() + " ");
        }
    }
}
