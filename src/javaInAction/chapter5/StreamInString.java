package javaInAction.chapter5;

import java.util.Arrays;
import java.util.List;

public class StreamInString {
    static String[] array = {"Hello","World"};
    public static void main(String[] args) {
        List<String> stringList =
                Arrays.stream(array)
                        .map(s -> s.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .toList();
    }
}
