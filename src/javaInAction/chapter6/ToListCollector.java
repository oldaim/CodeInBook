package javaInAction.chapter6;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

// Collectors.toList() 를 Collector 인터페이스를 통해 직접 구현
public class ToListCollector<T> implements Collector<T,List<T>, List<T>> {
    @Override
    public Supplier<List<T>> supplier() { // 반환할 객체 생성
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>,T> accumulator() { // 누적
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() { //병렬 계산 합
        return (list1,list2) ->
        {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>,List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH,Characteristics.CONCURRENT));
    }
}
