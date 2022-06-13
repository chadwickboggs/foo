package com.chadwickboggs.interview.duplicates;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main( String... args ) {
        final List<Integer> integerList = Arrays.asList( 1, 1, 2, 3, 2, 1, 4, 5, 5, 6, 1 );

        // TODO: Output list of number which occur only once.

/*
        final Map<Integer, Integer> instances = new HashMap<>();
        integerList.stream().forEach( value -> {
            int countOfValue = 1;
            if ( instances.containsKey( value ) ) {
                countOfValue = instances.get( value ) + 1;
            }
            instances.put( value, countOfValue );
        } );

        final List<Integer> uniqueInts = instances.entrySet().stream()
            .filter( entity -> entity.getValue() == 1 )
            .map( entity -> entity.getKey() )
            .collect( Collectors.toList() );

        final List<Integer> uniqueInts = new ArrayList<>();
        for ( int i = 0; i < integerList.size(); i++ ) {
            final Integer value = integerList.get( i );
            if ( countOf( value, integerList ) == 1 ) {
                uniqueInts.add(  value );
            }
        }
*/

        final List<Integer> uniqueInts = integerList.stream()
            .filter( value -> countOf( value, integerList ) == 1 )
            .collect( Collectors.toList() );

        System.out.printf(
            "The uniques in %s are %s.%n", integerList, uniqueInts
        );
    }

    private static int countOf( int value, final List<Integer> integerList ) {
        return (int) integerList.stream()
            .filter( memberValue -> memberValue.equals( value ) )
            .count();
    }

}
