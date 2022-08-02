package javaInAction.chapter5;

import javaInAction.chapter4.Dish;
import javaInAction.chapter4.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class originalTypeStream {
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

    public void example1(){
        int calories = menu.stream()
                .map((Dish::getCalories))// Stream<Integer> 반환
                .reduce(0,Integer::sum);

        /* Integer 인터페이스 내부에 sum() 메소드가 정의되어 있지 않기 때문에
           이러한 형식으로 코드 작성이 불가

        int newCalories = menu.stream()
                .map((Dish::getCalories))
                .sum();

         */

        // mapToInt 는 기본형 특화 스트림인 intStream을 반환 하기 때문에 intStream 의 Sum 메소드 사용가능
        int newCalories = menu.stream()
                .mapToInt(Dish::getCalories) // intStream 반환
                .sum();

        IntStream intStream  = menu.stream().mapToInt(Dish::getCalories);

        // 이런식으로 다시 Stream 으로 변환가능
        Stream<Integer> stream = intStream.boxed();
    }

    public static void example2(){
        /*
        *
        */
        // range 는 a 초과 b 미만, rangeClosed a 이상 b 이하
        IntStream.rangeClosed(1,100).boxed()
                .flatMap(a -> // flatMap 은 2차원 -> 1차원 배열로 차원을 낮춤
                        IntStream.rangeClosed(a,100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> // mapToObj 지정된 오브젝트로 변형
                                new int[]{a,b,(int) Math.sqrt(a * a + b * b)})
                )
                .forEach(c -> {
                    System.out.println(Arrays.toString(c));
                });
    }

    public static void example3() {
        // stream.of 로 문자열 스트림 만드는 예제
        Stream<String> stream = Stream.of("Modern","Java","In","Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // null 이 될 수 있는 객체로 스트림 만들기
        Stream<String> homeValueStream
                = Stream.ofNullable(System.getProperty("home"));
        homeValueStream.forEach(System.out::println);

    }

    public static void main(String[] args) {
        example3();
    }
}
