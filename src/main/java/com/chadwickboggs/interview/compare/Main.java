package com.chadwickboggs.interview.compare;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main( String... args ) {
        List<Integer> listOne = Arrays.asList( 2, 3, 4 );
        List<Integer> listTwo = Arrays.asList( 4, 9, 7 );

        List<Integer> intersection = listOne.stream()
            .filter( value -> listTwo.contains( value ) )
            .collect( Collectors.toList() );

        System.out.println( String.format(
            "The intersection between %s and %s is %s.",
            listOne, listTwo, intersection
        ) );
    }

}
