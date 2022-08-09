package org.example.javaInAction.chapter3;
import org.example.javaInAction.chapter2.Color;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    static List<Apple> apples = new ArrayList<>();

    public static void makeAppleList(){
        Random random = new Random(10000);
        for (int i = 0; i < 100; i++) {
            apples.add(new Apple(random.nextInt(), Color.GREEN));
        }
    }

    public static void preSort(){
        makeAppleList();
        for (Apple apple:apples) {
            System.out.print(apple.weight);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        preSort();

        //Comparator<> 직접 구현해서 정렬 구현
        apples.sort(new AppleComparator());
        // 익명 클래스 사용! 직접 구현 안함
        apples.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.weight - o2.weight;
            }
        });
        // 람다 표현식 사용
        apples.sort((Apple a1,Apple a2) -> a1.weight - a2.weight);
        // 람다 표현식 간소화 가능!
        apples.sort(Comparator.comparing(apple -> apple.getWeight()));
        // 이제 메서드 참조까지 가능하다! -> 더 코드가 간소화 된다
        apples.sort(Comparator.comparing(Apple::getWeight));

    }
}
