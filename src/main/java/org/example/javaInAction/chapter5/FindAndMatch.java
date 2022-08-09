package org.example.javaInAction.chapter5;


import org.example.javaInAction.chapter4.Dish;
import org.example.javaInAction.chapter4.Type;

import java.util.Arrays;
import java.util.List;

public class FindAndMatch {
    static List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800, Type.MEAT),
            new Dish("beef",false,700,Type.MEAT),
            new Dish("chiecken",false,400,Type.MEAT),
            new Dish("french fries",true,530,Type.OTHER),
            new Dish("rice",true,350,Type.OTHER),
            new Dish("season fruit",true,120,Type.OTHER),
            new Dish("pizza",true,550,Type.OTHER),
            new Dish("prawns",false,300,Type.FISH),
            new Dish("salmon",false,450,Type.FISH)
    );

    // 적어도 한 요소와 일치 할때 사용하는 anyMatch, || 와 유사함

    static void anyMatch(){
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("Welcome vegetarian!!");
        }
    }

    // 모든 요소와 일치할때 사용하는 allMatch, && 와 유사함
    static void allMatch(){
        if(menu.stream().allMatch(dish -> dish.getCalories() < 1000)){
            System.out.println("this food is health");
        }
    }

    // 어떤 요소도 이것과 일치 하지 않을때 true를 반환 anyMatch의 반대라고 볼수 있음
    static void noneMatch(){
        if(menu.stream().noneMatch(dish -> dish.getCalories() >= 1000)){
            System.out.println("this food also health");
        }
    }
    /*
    * 쇼트 서킷 평가는
    * noneMatch,anyMatch,findFirst,findAny 등의 연산은 모든 스트림의 요소를 순회하지 않았어도 원하는 결과를 찾으면
    * 즉시 반환하는것을 말한다.
    * */

    // filter를 통해 element를 찾고 존재하면 출력하는 메서드 정확하게 첫번쨰를 알고 싶다면 findFirst() 를 사용한다.
    // findAny 와 findFirst 의 경우 병렬성 때문에 사용한다. (이유는 추후에 더 공부를 진행하면 알게 될듯)
    static void findAny(){
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        anyMatch();
        allMatch();
        noneMatch();
        findAny();
    }




}
