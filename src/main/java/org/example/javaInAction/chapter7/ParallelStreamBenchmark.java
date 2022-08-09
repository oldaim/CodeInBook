package org.example.javaInAction.chapter7;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G","-Xmx4G"})
public class ParallelStreamBenchmark {
    private static final long N = 10_000_000L;



    /*
    * Benchmark                                                         Mode  Cnt    Score   Error  Units
    * javaInAction.chapter7.ParallelStreamBenchmark.sequentialSumBench  avgt   20  333.304 ± 6.259  ms/op

    public static long sequentialSumBench(){ // 무한 스트림을 통해 1부터 n 까지의 합을 구한다.
        return Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .reduce(0L,Long::sum);
    }

    */

    /*
    * Benchmark                                                  Mode  Cnt    Score   Error  Units
    * javaInAction.chapter7.ParallelStreamBenchmark.parallelSum  avgt   20  365.983 ± 6.492  ms/op
    */

    @Benchmark
    public static long parallelSum(){ // 합을 병렬 처리로 구현
        return Stream.iterate(1L, i -> i + 1)
                .limit(N)
                .parallel()
                .reduce(0L,Long::sum);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ParallelStreamBenchmark.class.getSimpleName())
                .warmupIterations(10)
                .measurementIterations(20)
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
