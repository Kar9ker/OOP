package Application;

import Application.Window.MainWindow;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
//        someTest();
    }
    private static void someTest() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(6);
        list1.add(98);
        List<Integer> list2 = new ArrayList<>(list1);
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
