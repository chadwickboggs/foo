package com.chadwickboggs.interview.paypal;

import java.util.Arrays;


public class Main {

    public static void main( String... args ) {
        String input = "pwwkew";
        String outputExpected = "wke";
        String output = longestNoDups( input );
        System.out.println( output + ", " + outputExpected );
    }

    private static String longestNoDups( String input ) {
        if ( input == null || input.length() == 0 ) {
            return "";
        }

        String longest = "";
        byte[] bytes = input.getBytes();
        int startIndex = 0;
        byte currentChar;
        for ( int i = 0; i < bytes.length; i++ ) {
            currentChar = bytes[i];
            byte[] substring = Arrays.copyOfRange( bytes, startIndex, i );
            if ( new String( substring ).contains( new String( new byte[]{currentChar} ) ) ) {
                startIndex = i;
                continue;
            }
            if ( substring.length + 1 > longest.length() ) {
                longest = new String( Arrays.copyOfRange( bytes, startIndex, i + 1) );
            }

        }

        return longest;
    }

}
