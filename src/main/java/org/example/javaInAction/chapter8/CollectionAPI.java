package org.example.javaInAction.chapter8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Map.entry;

public class CollectionAPI {


    public static void example1() {

        List<String> friends = new ArrayList<>();

        friends.add("Raphael");
        friends.add("Olivia");
        friends.add("Thibaut"); // 기존 List 에 추가하는 방법

        List<String> newFriends = Arrays.asList("Raphael", "Olivia"); // 이런식으로 간단하게 코드를 줄일 수 있음
        /*
        * 요소를 변경하는 것은 허용된다.
        * 그러나 고정된 리스트이므로 크기를 늘릴수 없다. (배열 처럼)
        */
    }

    public static void example2() {
        // 집합을 만드는 방법1 리스트를 요소로 추가한다.
        Set<String> friends = new HashSet<>(Arrays.asList("Raphael","Olivia","Thibaut"));
        // 집합을 만드는 방법2 스트림에 추가하고 컬렉터를 통해 집합으로 바꾼다.
        Set<String> newFriends = Stream.of("Raphael","Olivia","Thibaut")
                .collect(Collectors.toSet());
        // 요소를 바꿀수 있다.
    }

    public static void example3() {
        /*
        * 리스트 팩토리
        * List.of() 메서드를 통해 리스트를 만들 수 있다.
        * 대신 크기 변경을 비롯해 요소 변경까지 불가하다.
       */

        List<String> list = List.of("Hello","my","name");
        System.out.println(list);
        list.add("is"); //UnsupportedOperationException 발생
        System.out.println(list);

        /*
        * 오버로딩 버전 List.of는 가변인수를 사용 x
        */

    }

    public static void example4() {

        // 10개 이하에서는 Map.of 를 사용하고 (값 변하지 않음)

        Map<String,Integer> ageOfFriends
                = Map.of("Raphael",30,
                "Olivia",25,
                "Thibaut",26);

        System.out.println(ageOfFriends);

        /*
        * map.entry 는 key - value 쌍을 묷어서 표현하는 건가?
        * 자바 공식 문서에서는 키와 값으로 구성되는 데이터를 매핑(mapping) 또는 엔트리(entry)라고 기술하고 있습니다.
        */

        // 10개 이상에서는 가변 인수로 구현된 Map.ofEntries 사용
        Map<String, Integer> ageOfFriendsEntry = Map.ofEntries(entry("Raphael", 30),
                                                        entry("Olivia", 25),
                                                        entry("Thibaut",26));
    }

    public static void main(String[] args) {
        example3();
    }
}
