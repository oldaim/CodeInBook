package javaInAction.chapter2;

public class Apple {

    int weight;

    Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public int getWeight() {
        return this.weight;
    }
}
