package org.example.javaInAction.chapter8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class CollectionMethod {
    /*
    *   removeIf : 프레디케이트를 만족하는 요소를 제거한다 ( List,Set 에서 사용)
    *   replaceAll : UnaryOperator 함수를 이용해 요소를 바꾼다.
    *   -> UnaryOperator 는 Function 을 확장해서 사용하는 함수
    *   Sort : 리스트를 정렬한다. ( List 에서 사용)
    */

    public static void example1() {

        List<Integer> integerList = new java.util.ArrayList<>(IntStream.rangeClosed(1, 100).boxed().toList());

        for (Integer i:integerList) System.out.print(i);

        integerList.removeIf(i -> i % 2 == 0); // 해당 조건에 부합되는 (짝수) 요소를 모두 제거 할 수 있음

        System.out.println();

        for (Integer i:integerList) System.out.print(i);

    }

    public static void example2() {
        List<String> referenceCodes = new ArrayList<>(Arrays.asList("a12","c14","b13"));

        referenceCodes.stream()
                .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
                .toList()
                .forEach(System.out::println);

        // 위 코드를 replaceAll 을 통해 간단히 표현 할 수 있다.

        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));

        referenceCodes.forEach(System.out::println);

    }

    public static void example3() {
        Map<String,Integer> ageOfFriends =
                Map.ofEntries(
                        entry("kim", 11),
                        entry("Lee", 14),
                        entry("cha", 18));

        ageOfFriends.forEach((name,age) ->
                System.out.println(name + " " + age )); // 이건 순서 안지켜서 나오네



        ageOfFriends
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue()) // Key - Value 값으로 정렬 가능
                .forEach(System.out::println);

        // Key 값이 존재하면 value 값 가져옴 , 없으면 defaultValue 값을 가져옴
        System.out.println(ageOfFriends.getOrDefault("kim",34));
        System.out.println(ageOfFriends.getOrDefault("sd",222));

    }

    public static void example4() {
        /*
        *   computeIfAbsent :  키 & 값이 존재하지 않으면 값을 추가해준다.
        *   computeIfPresent : 키 값이 존재하면 (NULL 이 아니라면) 값을 수정해 준다.
        *   compute : 제공된 키로 새 값을 계산하고 맵에 저장한다.
        */

        Map<String,Integer> ageOfFriends = // 이 코드는 변경 불가라 이렇게 하면 안됨
                new java.util.HashMap<>(Map.ofEntries(
                        entry("kim", 11),
                        entry("Lee", 14),
                        entry("cha", 18)));

        ageOfFriends.computeIfAbsent("ksi",num -> ageOfFriends.put("ksi",15));

        ageOfFriends.computeIfPresent("ksi",(name,age) -> ageOfFriends.put("ksi",17));

        ageOfFriends.forEach((key, value) -> {
            System.out.println(key + " " + value);
        });
    }

    public static void example5() {
        /*
        * remove 와 replace / replaceAll (삭제와 교체)
        */

        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("kim","hello");
        hashmap.put("Lee","My");
        hashmap.put("cha","name");
        hashmap.put("tang","is");

       // hashmap.remove("kim"); 삭제
        hashmap.replace("kim",hashmap.get("kim"),"Bye"); // 교체

        hashmap.forEach((name,key) ->System.out.println(name + " " + key));


    }

    public static void example6() {

        Map<String,String> hashmap = new HashMap<>();
        hashmap.put("kim","hello");
        hashmap.put("Lee","My");
        hashmap.put("cha","name");
        hashmap.put("tang","is");

        Map<String,String> hashmap2 = Map.of("ss","ll");

        hashmap.putAll(hashmap2);

        hashmap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .map((name) -> {
                    Map<String,String> newMap = new HashMap<>();
                    newMap.put(name.getKey(), name.getValue());

                    return newMap;
                })
                .forEach(System.out::println);

    }

    public static void example7() {
        /*
        * 동시성 친화적이며 최신기술을 반영한 HashMap
        * 음 뭔소리인지 모르겠음
        * 동시성 친화적이다 => 동시에 실행되는 병렬처리에 오류가 적다?
        *
        * forEach : 각 쌍에 주어진 액션을 실행
        * reduce :  모둔 쌍을 제공된 리듀스 함수를 통해 결과로 합침
        * search :  null 이 아닌 값을 반환 할때 까지 각 쌍에 함수를 적용
        *
        * 키, 값 , (키,값) , Entry 를 이용한 연산 지원
        * 상태를 잠그지 않고 연산을 수행한다 -> 계산이 진행되는 동안 바뀔수 있는 객체 값 순서에 의존 하지 않아야 한다.
        * 병렬성 기준값을 통해 병렬 수준을 정할 수 있다.
         */

        ConcurrentHashMap<Long,Long> map = new ConcurrentHashMap<>();

        long threshold = 10; // 동시성 기준값 1 이 병렬성 최대 Long.MAX_VALUE 가 동시성 최소 이다.

        LongStream.rangeClosed(1L,100L)
                .forEach(num -> map.put(num,num));

        map.forEach(threshold, (key,value) -> System.out.println(key+value));



    }

    public static void main(String[] args) {
        example7();
    }
}
