package javaInAction.chapter4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800,Type.MEAT),
            new Dish("beef",false,700,Type.MEAT),
            new Dish("chiecken",false,400,Type.MEAT),
            new Dish("french fries",true,530,Type.OTHER),
            new Dish("rice",true,350,Type.OTHER),
            new Dish("season fruit",true,120,Type.OTHER),
            new Dish("pizza",true,550,Type.OTHER),
            new Dish("prawns",false,300,Type.FISH),
            new Dish("salmon",false,450,Type.FISH)
    );

    public static void main(String[] args) {
        List<String> test =
                menu.stream()
                        .filter(dish -> dish.getCalories() < 500)
                        .map(Dish::getName)
                        .toList();

        test.forEach(System.out::println);
    }

}
