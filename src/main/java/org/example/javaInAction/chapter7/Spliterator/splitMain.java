package org.example.javaInAction.chapter7.Spliterator;

import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class splitMain {

    static final String SENTENCE = "nel mezzo del cammin di nostra vita "
            +"mi ritrovai per una selva oscura "
            +"ché la diritta via era smarrita";

    public static int countWordsIteratively(String s){ // 문자를 탐색하다 공백 문자를 만나면 체크해서 단어수를 결정하는 메서드
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }
            else{
                if(lastSpace) counter++;
                lastSpace = false;
            }
        }

        return counter;
    }

    static Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
    static Stream<Character> stream = StreamSupport.stream(spliterator,true);

    public static int countWords (Stream<Character> stream){ // 위 메서드를 스트림으로 구현

        WordCounter wordCounter = stream.reduce(new WordCounter(0,true),
                WordCounter::accumulate,
                WordCounter::combine);

        return wordCounter.getCounter();

    }



    public static void main(String[] args) {
        System.out.println("Found "+ countWords(stream) + " words");


    }
}
