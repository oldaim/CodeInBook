package javaInAction.chapter6;
/*
* 컬랙터를 직접 구현 해보자
*
*/


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

public class CollectorSelfMade {

    /*
    *   T는 수집될 스트림 항목의 제네릭 형식 (input)
    *   A는 누적자 즉 수집과정에서 중간 결과를 누적하는 객체의 형식 (box)
    *   R은 수집 연산 결과 객체의 형식 (output)
    *   public interface Collector<T,A,R>{
            Supplier<A> supplier();
            * 결과 컨테이너 생성
            BiConsumer<A,T> accumulator();
            * 결과 컨테이너에 요소 추가하기
            Function<A,R> finisher();
            * 최종 변환 값을 결과 컨테이너로 적용하기
            BinaryOperator<A> combiner();
            * 병렬 연산을 진행할때 두 결과 컨테이너 병합
            Set<Characteristics> characteristics();
            * 컬렉터의 연산을 정의해주는 힌트를 제공한다.
            * UNORDERED : 결과는 요소의 순서에 영향을 받지 않는다.
            * CONCURRENT : 이 컬랙터는 병렬로 연산을 수행할 수 있다.
            * IDENTITY_FINISH : 리듀싱 과정의 최종 결과로 누적자 객체를 바로 사용할 수 있다.
            *                   또한 누적자 A를 결과 R로 안전하게 형변환 할 수 있다.
        }
    */
    public static Map<Boolean, List<Integer>> partitionPrimeOld(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(partitioningBy(CollectorSelfMade::isPrimeOld));
    }

    public static boolean isPrimeOld(Integer candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2,candidateRoot)
                .noneMatch(i -> candidate % i ==0);
    }


    public static Map<Boolean,List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2,n).boxed()
                .collect(new PrimeNumbersCollector());
    }


    // 기본과 커스텀 속도 비교
    // 244 < 238 로 커스텀이 더 우수
    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_0000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;
        }

        System.out.println(fastest);
    }
}
