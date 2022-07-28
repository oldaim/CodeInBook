package javaInAction.chapter3;

import javaInAction.chapter2.Color;

public class Apple {

    int weight;

    Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }
}
