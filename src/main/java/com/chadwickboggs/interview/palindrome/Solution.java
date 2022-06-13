package com.chadwickboggs.interview.palindrome;

import java.util.Optional;


class Solution {

    public static void main( String... args ) {
        String value = "chad";
        stdout( value, longestPalindrome( value ) );

        value = "chadahc";
        stdout( value, longestPalindrome( value ) );

        value = "tiffany";
        stdout( value, longestPalindrome( value ) );
    }

    public static Optional<String> longestPalindrome( String value ) {
        if ( value == null ) return Optional.empty();
        if ( value.length() <= 0 ) return Optional.empty();

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

        if ( longestFound == "" ) {
            return Optional.empty();
        }

        return Optional.of(longestFound);
    }

    private static void stdout( final String valueUnderTest, final Optional<String> longestPalindromeOpt ) {
        System.out.println( String.format(
            "longestPalindromeOpt( \"%s\" ) = \"%s\"",
            valueUnderTest,
            longestPalindromeOpt.isPresent() ? longestPalindromeOpt.get() : ""
        ) );
    }

    private static boolean isPalindrome( String substring ) {
        final StringBuilder buf = new StringBuilder( substring.length() );
        for ( int i = substring.length() - 1; i >= 0; i-- ) {
            buf.append( substring.charAt( i ) );
        }

        return buf.toString().equals( substring );
    }
}
