package com.chadwickboggs.interview.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main( String... args ) {
        final List<Integer> integerListReadOnly = Collections.unmodifiableList( Arrays.asList(
            6, 4, 5, 2, 1, 2, 3
        ) );
        System.out.print( "Integers Unsorted: " );
        System.out.println( integerListReadOnly );

        //
        // An unmodifiable list may not be sorted; so, a defensive copy must be created.
        //
        // Techniques for the Sorting of a Defensive Copy:
        //      1. Intantiate a modifiable copy, then sort it in place.
        //      2. Stream the elements, run the sort stream operated, collect in a list.
        //          a. This technique enables parallel (spliterator) processing/sorting.
        //

        //
        // Sorting Technique #1: Inplace sort a modifiable copy.
        //
        final ArrayList<Integer> integerListReadWrite = new ArrayList<>( integerListReadOnly );
        Collections.sort( integerListReadWrite );
        System.out.print( "Sort Inplace a Modifiable Defensive Copy: " );
        System.out.println( integerListReadWrite );

        //
        // Sorting Technique #2: Parallel stream sort collect into a modifiable copy.
        //
        final List<Integer> sortedByParalleStream =
            integerListReadOnly.parallelStream()
                .sorted()
                .collect( Collectors.toList() );
        System.out.print( "Sort a Parallel Stream collected into a List: " );
        System.out.println( sortedByParalleStream );

        //
        // Variation #1: A custom Comparator may be provided.
        //
        final List<Integer> sortedWithCustomComparator =
            integerListReadOnly.parallelStream()
                .sorted( Comparator.comparingInt( o -> o ) )
                .collect( Collectors.toList() );
        System.out.print( "Sorted with Custom Comparator: " );
        System.out.println( sortedWithCustomComparator );

        // ...or Custom code the custom comparator.
        final List<Integer> sortedWithCustomlyCodedCustomComparator =
            integerListReadOnly.parallelStream()
                .sorted( ( o1, o2 ) -> o1 - o2 )
                .collect( Collectors.toList() );
        System.out.print( "Sorted with Customly Coded Custom Comparator: " );
        System.out.println( sortedWithCustomlyCodedCustomComparator );
    }

}
