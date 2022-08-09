package org.example.javaInAction.chapter5;

import java.util.Arrays;
import java.util.List;

public class reducing {
    static List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,77,123,432,56234,324,32);
    // 하나의 정답이 나올떄 까지 반복작업을 계속하는 과정 => reducing
    // 초깃값이 존재하지 않으면 Optional 객체를 반환함
    static int reduceSum(){
        return numbers.stream().reduce(0, Integer::sum);
    }

    static int reduceMax(){
        return numbers.stream().reduce(1,Integer::max);
    }

    static int reduceMin(){
        return numbers.stream().reduce(1, Integer::min);
    }

    public static void main(String[] args) {
        System.out.println(reduceMax());
        System.out.println(reduceMin());
        System.out.println(reduceSum());
    }
}
