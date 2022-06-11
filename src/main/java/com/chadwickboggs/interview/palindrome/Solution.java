package com.chadwickboggs.interview.palindrome;

class Solution {

    public static void main( String... args ) {
        String value = "chad";
        stdout( value, longestPalindrome( value ) );

        value = "chadahc";
        stdout( value, longestPalindrome( value ) );

        value = "tiffany";
        stdout( value, longestPalindrome( value ) );
    }

    private static void stdout( final String value, final String longestPalindrome ) {
        System.out.println( String.format(
            "longestPalindrome( \"%s\" ) = \"%s\"", value, longestPalindrome
        ) );
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
                if ( isPalindrome( substring ) && substring.length() > longestFound.length() ) {
                    longestFound = substring;
                }
            }
        }

        return longestFound;
    }

    private static boolean isPalindrome( String substring ) {
        final StringBuilder buf = new StringBuilder( substring.length() );
        for ( int i = substring.length() - 1; i >= 0; i-- ) {
            buf.append( substring.charAt( i ) );
        }

        return buf.toString().equals( substring );
    }
}
