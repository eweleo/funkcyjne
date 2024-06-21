package org.example;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> differenceBetweenSumOfSquaresAndSquareOfSum = a ->
                IntStream.rangeClosed(1, a).sum() * IntStream.rangeClosed(1, a).sum()
                        - IntStream.rangeClosed(1, a).map(i -> i * i).sum();
        

        Function<Double, Double> cubeRoot = new Function<>() {
            @Override
            public Double apply(Double a) {
                return cubeRootHelper(a, a);
            }

            private Double cubeRootHelper(Double a, Double xk) {
                if (a - (xk * xk * xk) == 0) {
                    return xk;
                } else {
                    return cubeRootHelper(a, (2 * xk + a / (xk * xk)) / 3);
                }
            }
        };


        BiFunction<Double, Double, Double> heronEpsilon = new BiFunction<>() {
            @Override
            public Double apply(Double a, Double epsilon) {
                return heronEpsilonHelper(a, a, epsilon);
            }

            private Double heronEpsilonHelper(Double a, Double x, Double epsilon) {
                Double x1 = 0.5 * (x + a / x);
                if (x - x1 <= epsilon) {
                    return x1;
                }
                return heronEpsilonHelper(a, x1, epsilon);
            }
        };


        BiFunction<Double, Integer, Double> heronStep = new BiFunction<>() {
            @Override
            public Double apply(Double a, Integer step) {
                return heronStepHelper(a,a,step);
            }

            private Double heronStepHelper(Double a, Double x, Integer step) {
                Double x1 = 0.5 * (x + a / x);
                if (step == 1) {
                    return x1;
                }
                return heronStepHelper(a,x1,--step);
            }
        };

        Function<Integer,Long> fibRecur = n -> {
            if (n <= 1) return Long.valueOf(n);

            long a = 0, b = 1, c;
            for (int i = 2; i <= n; i++) {
                c = a + b;
                a = b;
                b = c;
            }
            return b ; // b = 817770325994397771
        };

        Function<Integer,Long> fib = new Function<>() {
            @Override
            public Long apply(Integer n) {
                if(n<=1){
                    return Long.valueOf(n);
                }
                return apply(n-1) + apply(n-2);
            }
        };

        Function<Set<Integer>,Set<Set<Integer>>> powerSet = integers -> integers.stream()
                        .reduce(
                                Stream.of(Collections.<Integer>emptySet()).collect(Collectors.toSet()),
                                (powSet, element) -> powSet.stream()
                                        .flatMap(set -> Stream.of(set, Stream.concat(Stream.of(element), set.stream()).collect(Collectors.toSet())))
                                        .collect(Collectors.toSet()),
                                (left, right) -> {
                                    left.addAll(right);
                                    return left;
                                }
                        );


        System.out.println(fibRecur.apply(1000));
    }

}

