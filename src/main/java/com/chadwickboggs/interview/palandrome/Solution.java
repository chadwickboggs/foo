package com.chadwickboggs.interview.palandrome;

class Solution {

    public static void main( String... args ) {
        System.out.println( longestPalindrome( "chadahc" ) );
    }

    public static String longestPalindrome( String value ) {
        if ( value == null ) return null;
        if ( value.length() == 0 ) return "";
        if ( value.length() == 1 ) return value.substring( 0, 1 );

        //
        // Loop through every substring, if a polidtrome save
        // if longer than last found palidrome.
        //
        String longestFound = "";
        for ( int i = 0; i < value.length(); i++ ) {
            for ( int j = i + 2; j <= value.length(); j++ ) {
                String substring = value.substring( i, j );
                if ( isPalendrome( substring ) && substring.length() > longestFound.length() ) {
                    longestFound = substring;
                }
            }
        }

        return longestFound;
    }

    private static boolean isPalendrome( String substring ) {
        final StringBuilder buf = new StringBuilder( substring.length() );
        for ( int i = substring.length() - 1; i >= 0; i-- ) {
            buf.append( substring.charAt( i ) );
        }

        return buf.toString().equals( substring );
    }
}
