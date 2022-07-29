package javaInAction.chapter5.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    static Trader raoul = new Trader("Raoul","Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");

    static List<Transaction> transactions = Arrays.asList(
        new Transaction(brian,2011, 300),
        new Transaction(raoul , 2012 , 1000),
        new Transaction(raoul, 2011, 400),
        new Transaction(mario,2012,710),
        new Transaction(mario,2012,700),
        new Transaction(alan,2012,950)
    );

    // 2011년에 일어난 모든 트랜잭션을 찾아 오름차순으로 정리하시오
    static void problem1(){
        transactions.stream()
                .filter(b -> b.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .forEach(System.out::println);

    }
    // 거래자가 근무하는 모든 도시를 중복없이 나열하시오
    static void problem2(){
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
    }

    //케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오
    static void problem3(){
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
    }

    //모든 거래자들의 이름을 알파벳 순으로 정렬해서 반환하시오
    static void problem4(){
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted(Comparator.naturalOrder())
                .distinct()
                .forEach(System.out::println);    //이건 진짜 첨보네

    }

    //밀라노의 거래자가 있는가?
    static void problem5(){
         transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getCity)
            .filter( c -> c.equals("Milan"))
            .findAny()
            .ifPresent(s -> System.out.println("Yes"));
    }

    //케임브리지에 거주하는 모든 거래자의 모든 트랜잭션값을 출력하시오
    static void problem6(){
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

    }

    //전체 트랜잭션 값중 최대 최소를 구하시오
    static void problem7(){
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(System.out::println);
    }

    public static void main(String[] args) {
        problem7();
    }
}
