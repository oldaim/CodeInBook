package org.example.javaInAction.chapter7.Spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string){
        this.string = string;
    }
    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        return currentChar < string.length(); // 소비할 문자가 있으면 true를 반환 한다.;
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        if(currentSize < 10){
            return null; //  null 을 반환 할때 까지 분할
        }
        for(int splitPos = currentSize / 2 + currentChar;
        splitPos < string.length(); splitPos++){
          if (Character.isWhitespace(string.charAt(splitPos))) {
              Spliterator<Character> spliterator = new WordCounterSpliterator
                      (string.substring(currentChar,splitPos)); // 문자를 분할
              currentChar = splitPos;
              return spliterator;
          }
        }
        return  null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + NONNULL + IMMUTABLE;
    } // T는 탐색하는 요소의 형식


    /*
    *   1. 첫번째 Spliterator 에 trySplit 을 호출하면 두번째 Spliterator 가 생성됨
    *   2. 2단계 에서 두개의 Spliterator 에 trySplit 호출 -> 4개 생성
    *   3. trySplit 이 결과가 null 이 될때 까지 이 과정을 반복한다.
    *   4. null 반환 => 더 이상 분할할것이 없다! => 재귀 분할 종료
    */
}
