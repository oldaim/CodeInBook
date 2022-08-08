package javaInAction.chapter6;

import javaInAction.chapter4.Dish;
import javaInAction.chapter4.Type;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class Collector {

    public enum CaloricLevel{
        DIET,NORMAL,FAT
    }

    static List<Dish> menu = Arrays.asList(
            new Dish("pork",false,800, Type.MEAT),
            new Dish("beef",false,700,Type.MEAT),
            new Dish("chicken",false,400,Type.MEAT),
            new Dish("french fries",true,530,Type.OTHER),
            new Dish("rice",true,350,Type.OTHER),
            new Dish("season fruit",true,120,Type.OTHER),
            new Dish("pizza",true,550,Type.OTHER),
            new Dish("prawns",false,300,Type.FISH),
            new Dish("salmon",false,450,Type.FISH)
    );

    public static void example1() {
        // 스트림 값에서 최댓값과 최솟값 검색
        // Calories 의 Int 값을 기준으로 비교함
        Comparator<Dish> dishCaloriesComparator =
                comparingInt(Dish::getCalories);

        //Comparator 를 인수로 받는 Collector.maxBy() -> max() 컬렉터
        Optional<Dish> mostCalorieDish =
                menu.stream().max(dishCaloriesComparator);

        //합계, 평균등을 반환하는 연산에도 리듀싱 기능이 자주 사용됨
        //이러한 연산을 요약 연산이라고 함

        //합계 연산
        int totalCalories =
                menu.stream().mapToInt(Dish::getCalories).sum();

        //평균 연산
        double avgCalories =
                menu.stream().collect(averagingInt(Dish::getCalories));

        // 합계, 숫자, 최대, 최소, 평균전부를 요약하는 연산
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));



    }

    public static void example2() {
        //문자열 연결
        String shortMenu =
                menu.stream().map(Dish::getName).collect(joining());

        //문자열 구분되게 연결
        String shortMenu2 =
                menu.stream().map(Dish::getName).collect(joining(","));

        System.out.println(shortMenu);
        System.out.println(shortMenu2);

        /*
        * porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon  -> 1번 출력
        * pork,beef,chicken,french fries,rice,season fruit,pizza,prawns,salmon -> 2번 출력
        */

        /*
        * stream 의 reduce 와 collect 는 비슷한 기능을 하지만
        * 두 값을 하나로 도출하는 불변형 연산이라는 reduce 와 결과를 도출하는 컨테이너를 바꾸는 collect 라는 의미론적 차이와
        * 병렬연산에서의 용이성 때문에 reducing 연산은 collect 메서드로 진행하는 것이 바람직하다.
        */

        /*
        * 여러 자료를 살펴본 결과 내가 이해한 reduce 와 collector 의 차이는 input 과 output 이 달라도 되는가 인것같다.
        * reduce 의 경우 T 형태 하나만 지원을 하고 collect 는 T 와 S 두개를 지원하게 된다.
        * 즉! Collect 는 미리 변환할 컬랙션을 만들고 거기에 누적을 진행한다.
        * 그러므로 List<Integer> -> Int 식으로 의미 변경이 될때에는 Collect 를 쓰는 것이 더욱 바람직 한것 같다.
        */

    }

    public static void example3() {
        //함수를 기준으로 스트림이 그룹화 되므로 이를 분류 함수라고 부른다.
        Map<Type,List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));

        System.out.println(dishesByType);

        Map<CaloricLevel,List<Dish>> dishesByCaloricLevel =
                menu.stream().collect(
                        groupingBy(dish -> {
                            if(dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        })
                );

        System.out.println(dishesByCaloricLevel);

    }

    public static void example4() {

        //stream filter 로 구성할수도 있지만 groupingBy 내에 predicate 인수를 받을수 있게 overloading 되있다.
        Map<Type,List<Dish>> caloricDishesByType =
                menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        //리팩터링 후
        Map<Type,List<Dish>> caloricDishesByType2 =
                menu.stream().collect(
                        groupingBy(Dish::getType,
                                filtering(dish -> dish.getCalories() > 500,toList()
                                )
                        )
                );

        // filtering 한 element 를 어떻게 저장 할것인가? 를 인수로 받는다는 것을 알고 있자!

        // 그룹으로 분류된 모든 요소에 리듀싱 작업을 수행할 때는 groupingBy 에 두 번째 인수로 전달한 컬랙터를 사용한다.
        Map<Type,Dish> mostCaloricByType =
                menu.stream().collect(groupingBy(Dish::getType,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),Optional::get
                        )
                    )
                );

    }

    public static void example5() {
        //분할 함수 true,false 로 구분해서 컬랙션 생성
        Map<Boolean,Map<Type,List<Dish>>> vegetarianDishesByType =
                menu.stream().collect(
                        partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType))
                );

        System.out.println(vegetarianDishesByType);
    }

    public static void main(String[] args) {
        example3();
    }
}
