package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //sample1();
        sample2();
    }

    public static void sample1() {
        TestObject robot1 = new TestObject("Borya", 10);
        TestObject robot2 = new TestObject("James", 5);
        TestObject robot3 = new TestObject("Vovva", 6);

        robot1.links.add(robot2);
        robot2.links.add(robot1);

        List<TestObject> testObjectList = new ArrayList<>();
        testObjectList.add(robot1);
        testObjectList.add(robot2);
        testObjectList.add(robot3);

        Graph graph = new Graph(testObjectList);
        graph.showAdjacencies();

        graph.collectGarbage();
        graph.showAdjacencies();
    }

    public static void sample2() {
        List<TestObject> testObjectList = new ArrayList<>();
        for (int i = 1; i <= 6; i ++) {
            testObjectList.add(new TestObject("Obj", i));
        }
        for(int i = 1; i < 4; i++) {
            testObjectList.get(0).links.add(testObjectList.get(i));
        }
        testObjectList.get(1).links.add(testObjectList.get(5));
        testObjectList.get(4).links.add(testObjectList.get(5));
        Graph graph = new Graph(testObjectList);
        graph.showAdjacencies();

        graph.collectGarbage();
        graph.showAdjacencies();
    }
}