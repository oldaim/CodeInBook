package org.example.javaInAction.chapter7.ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumMain {

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();

        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);

        return new ForkJoinPool().invoke(task); //invoke 메서드의 반환값 = ForkJoinSumCalculator 에서 정의한 테스크의 결과
    }

    public static void main(String[] args) {
        System.out.println(forkJoinSum(10_000_000));

    }
}
