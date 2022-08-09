package org.example.javaInAction.chapter2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.example.javaInAction.chapter2.Color.GREEN;
import static org.example.javaInAction.chapter2.Color.RED;

public class Main {


    //동작 파라미터화 이전의 메서드!
    public static List<Apple> filterApples(List<Apple> inventory){
        List<Apple> result = new ArrayList<>();
        for (Apple apple:inventory) {
            if(GREEN.equals(apple.getColor())) result.add(apple);
        }

        return result;
    }


    // 동작을 파라미터 완료! 그러나 코드가 너무 길어짐
    public static List<Apple> filterGreenApples(List<Apple> inventory,ApplePredicate p){
        List<Apple> result = new ArrayList<>();
        for (Apple apple:inventory) {
            if(p.test(apple)) result.add(apple);
        }

        return result;
    }

    // 제네릭 프로그래밍을 통해 메서드를 추상화 할수 있음! -> 재사용 가능성 크게 올라감
    public static<T> List<T> filterRedApples(List<T> inventory, Predicate<T> p){
        List<T> result = new ArrayList<>();
        for (T e:inventory) {
            if(p.test(e)) result.add(e);
        }

        return result;
    }


    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            if(i < 10) apples.add(new Apple(i,GREEN));
            else apples.add(new Apple(i,RED));
        }

        //동작파라미터화 완료후 만들어 놓은 클래스를 파라미터화
        List<Apple> greenApple = filterGreenApples(apples,new AppleColorPredicate());

        //익명 클래스 사용! -> 클래스를 따로 생성 하지 않아도됨 그러나 코드가 지저분함!
        List<Apple> redApple = filterGreenApples(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(RED);
            }
        });

        // 람다식 사용! -> 익명클래스의 코드가 지저분하다는 단점을 보완해줄수 있음!
        redApple = filterGreenApples(apples,(Apple apple) -> apple.getColor().equals(RED));
    }


}
