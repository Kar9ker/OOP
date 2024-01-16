package Application;

import Application.Domino.Game;
import Application.Window.MainWindow;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        MainWindow mainWindow = new MainWindow();
        someTest();
    }
    private static void someTest() {
        Game game = new Game();
        game.start();
    }
}
