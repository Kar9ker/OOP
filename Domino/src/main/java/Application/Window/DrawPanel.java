package Application.Window;

import Application.Domino.Direction;
import Application.Domino.Dice;
import Application.Domino.Game;

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
    private List<Dice> table;

    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
        Game game = new Game(PANEL_HEIGHT, PANEL_WIDTH);
        //общая колода
        diceList = game.getDiceList();
        //начальная раздача
        playersHand = game.getPlayersHand();
        table = game.getTable();
        game.start();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (e.getButton()) {
                    case 1 :

                        repaint();
                        break;
                    case 3 :

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
        for (Dice d : table) {
            d.draw(gr);
        }
        orderPlayersDices();
        for (Dice dice : playersHand) {
            dice.draw(gr);
        }
    }

    //Выставить руку игрока внизу экрана
//    private void orderPlayersDices() {
//        int x0 = PANEL_WIDTH / 2 - Dice.getSMALL_RECT_DIAMETER();
//        int prekol = 0;
//        boolean sign = true;
//        for (Dice dice : playersHand) {
//            dice.setY(PANEL_HEIGHT - Dice.getSMALL_RECT_DIAMETER() * 2 - 50);
//            dice.setX(x0 + prekol);
//            if (sign) {
//                prekol = Math.abs(prekol) + 2 * Dice.getSMALL_RECT_DIAMETER();
//            } else {
//                prekol *= -1;
//            }
//            sign = !sign;
//        }
//    }
    private void orderPlayersDices() {
        int coefficient = 30;
        for (Dice dice : playersHand) {
            dice.setY(PANEL_HEIGHT - Dice.getSMALL_RECT_DIAMETER() * 2 - 50);
            dice.setX(coefficient);
            coefficient += Dice.getSMALL_RECT_DIAMETER() + 10;
        }
    }
    private <T> void printList(List<T> list) {
        for (T l : list) {
            System.out.print(l.toString() + " ");
        }
    }
}
