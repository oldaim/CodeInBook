package org.example.javaInAction.chapter7.Parallel;

import java.util.stream.LongStream;

public class wrongParallel {
    public static class Accumulator{
        public long total = 0;
        public void add(long value){ total += value; }
    }

    /*
    * 해당 코드를 병렬로 실행하면 큰 문제가 발생한다.
    * 다수의 스레드에서 동시에 데이터에 접근하는 데이터 레이스 문제가 일어난다.
    * 동기화로 문제를 해결하다 보면 결국 병렬화라는 특성이 사라지게 된다.
    */
    public long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;
    }

    /*
    404544419096
    449652593974
    286567883647
    446575974741
    387202318217
    237505229355
    368116038274
    459390543025
    387056979958
    276519650574 정답은 5000050000 이지만 다 다른 결과가 나와버렸다.

    병렬 스트림이 올바로 동작하려면 공유된 가변상태(여러 스레드에서 자원을 공유하는 상태) 를 피해야 한다!

    */

    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }


    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            System.out.println(sideEffectParallelSum(1_000_000));
            long duration = (System.nanoTime() - start) / 1_000_000;
            if(duration < fastest) fastest = duration;

        }
    }

}
