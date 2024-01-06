package Application.Window;

import Application.Direction;
import Application.Domino.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DrawPanel extends JPanel {
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private List<Dice> diceList;

    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        //общая колода
        diceList = getFilledDices();
        //начальная раздача
        List<Dice> playersHand = getRandDices(diceList, 5);
        diceList = playersHand;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case 1 :
                        Dice dice = new Dice(5, 6, e.getX(), e.getY(), Direction.UP);
                        diceList.add(dice);
                        repaint();
                        break;
                    case 3 :
                        int size = diceList.size();
                        if (size > 0) {
                            Dice tmp = diceList.get(size - 1);
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
        for (Dice d : diceList) {
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

    private List<Dice> getRandDices(final List<Dice> list, int count) {
        List<Dice> newList = new ArrayList<>(list);
        List<Dice> finalList = new ArrayList<>(count);
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            int num = rnd.nextInt(0, newList.size());
            finalList.add(newList.get(num));
            newList.remove(num);
        }
        return finalList;
    }
    private void someTest() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2;
        list1.add(10);
        list1.add(6);
        list1.add(98);
        list2 = list1;
        list2.remove(0);
        for (Integer i : list1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (Integer i : list2) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
