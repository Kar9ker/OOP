package Application.Window;

import Application.Direction;
import Application.Domino.Dice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class DrawPanel extends JPanel {
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int TIMER_DELAY;
    private Timer timer;
    private int ticksFromStart = 0;
    private java.util.List<Dice> diceList = new ArrayList<>();

    public DrawPanel(final int width, final int height, final int timerDelay) {
        this.PANEL_WIDTH = width;
        this.PANEL_HEIGHT = height;
        this.TIMER_DELAY = timerDelay;
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
}
