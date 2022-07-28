package javaInAction.chapter2;

public class AppleWeightPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() < 10;
    }
}
