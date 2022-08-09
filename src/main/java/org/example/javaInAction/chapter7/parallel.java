package org.example.javaInAction.chapter7;

import java.util.stream.Stream;

public class parallel {
    /*
    * 자바8 이전 병렬 처리의 어려움이 있었음
    * 포크 - 조인 프레임워크를 통해 병렬처리를 구현함
    * 자바8 스트림을 사용하면 병렬처리를 더욱 쉽게 구현 할 수 있음
    * 병렬 스트림이 내부적으로 어떻게 처리되는지 알아야만 스트림을 잘못 사용하는 상황을 피할 수 있다.
    */

    public static long sequentialSum(long n){ // 무한 스트림을 통해 1부터 n 까지의 합을 구한다.
        return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .reduce(0L,Long::sum);
    }

    public long iterativeSum(long n){ // 전통적인 자바의 1부터 n 까지의 합 구하는 방법
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n){ // 합을 병렬 처리로 구현
       return Stream.iterate(1L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L,Long::sum);
    }

    /*
    * 병렬처리 과정 : n개의 부분으로 나눠서 연산을 진행한 후 연산을 모두 모아서 합친다
    * 1파트: 10 2파트: 11
    * 결과 : 1파트 + 2파트 = 21
    *
    */

    public static void main(String[] args) {

    }
}
