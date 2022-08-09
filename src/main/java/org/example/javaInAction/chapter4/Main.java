package org.example.javaInAction.chapter4;

import java.util.Arrays;
import java.util.List;

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
                        .filter(dish -> dish.getCalories() < 500) // 조건문을 활용해서 filter 진행
                        .map(Dish::getName)// 새로운 가치를 추출해낸다 수정이 아닌 재창조 느낌으로 map 사용
                        .toList();//리스트로 변경

        // 이때 중간 연산은 filter와 map으로 다음 연산에게 스트림을 그대로 건내준다.
        // 중간연산은 lazy 해서 실제 필요한 순간에 계산을 진행한다
        // collect toList 의 경우 최종연산으로 stream이 아닌 최종 결과물 (리스트 ,Integer)등을 반환한다.

        test.forEach(System.out::println);
    }

}
