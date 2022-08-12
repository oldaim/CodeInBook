package org.example.javaInAction.chapter7.Spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class customSpliterator implements Spliterator<Long> { // T는 탐색하는 요소의 형식
    @Override
    public boolean tryAdvance(Consumer<? super Long> action) { // 탐색해야할 요소가 있으면 True 반환
        return false;
    }

    @Override
    public Spliterator<Long> trySplit() {  // 일부 요소 (자신이 반환한 요소를 분할 해서 두번째 Spliterator 를 생성하는 메서드
        return null;
    }

    @Override
    public long estimateSize() { //탐색해야할 요소수 정보를 제공하는 메서드
        return 0;
    }

    @Override
    public int characteristics() { // 해당 Spliterator 의 특성 정보를 보여준다.
        return 0;
    }

    /*
    *   1. 첫번째 Spliterator 에 trySplit 을 호출하면 두번째 Spliterator 가 생성됨
    *   2. 2단계 에서 두개의 Spliterator 에 trySplit 호출 -> 4개 생성
    *   3. trySplit 이 결과가 null 이 될때 까지 이 과정을 반복한다.
    *   4. null 반환 => 더 이상 분할할것이 없다! => 재귀 분할 종료
    */
}
