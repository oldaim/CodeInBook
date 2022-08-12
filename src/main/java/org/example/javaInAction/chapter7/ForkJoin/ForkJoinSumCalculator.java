package org.example.javaInAction.chapter7.ForkJoin;

import java.util.concurrent.RecursiveTask;
/*
*   주어진 long[]의 원소의 합을 Fork Join 프레임워크를 통해 구현
*
*   과정: threshold 만큼 작아질 때까지 재귀적으로 분할 후 작은 배열에서는 순차적으로 계산을 한다.
*         그 과정을 하나로 합하여 결과를 도출한다.
*/
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private final long[] numbers;
    private final int start;
    private final int end;
    public static final long THRESHOLD = 10_000;

    public ForkJoinSumCalculator(long[] numbers){
        this(numbers, 0, numbers.length);
    }

    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }


    @Override
    protected Long compute() {
        int length = end - start;
        if(length <= THRESHOLD) return computeSequentially();

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);

        leftTask.fork(); // 첫번째 서브데스크를 비동기 실행

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers,start+length/2, end);

        Long rightResult = rightTask.compute(); // 두번째 서브데스크를 동기 실행

        Long leftResult = leftTask.join(); // 첫번째 서브데스크의 결과를 읽거나 기다린다.

        return leftResult + rightResult; // 두 서브데스크의 결과를 더한다.
    }

    private Long computeSequentially() {
        long sum = 0;

        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }

        return sum;
    }


}
