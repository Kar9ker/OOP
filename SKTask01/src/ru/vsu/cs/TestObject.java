package ru.vsu.cs;

import java.util.ArrayList;
import java.util.List;

public class TestObject {
    private String name;
    private int number;
    public List<TestObject> links = new ArrayList<>();

    public TestObject(String name, int number) {
        this.name = name;
        this.number = number;
    }

    @Override
    public String toString() {
        return '\'' + name + " " + number + '\'';
    }
}
