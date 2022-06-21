package com.chadwickboggs.interview.roman_numerals;

import java.util.HashMap;
import java.util.Map;


public class Main {

    public static final Map<Character, Integer> INDEX = new HashMap<>();

    static {
        INDEX.put( 'I', 1 );
        INDEX.put( 'V', 5 );
        INDEX.put( 'X', 10 );
        INDEX.put( 'L', 50 );
        INDEX.put( 'C', 100 );
        INDEX.put( 'D', 500 );
        INDEX.put( 'M', 1000 );
    }

    public static void main( final String... args ) {
        // Test adding values.
        testTranslationOf("MDL", 1550);

        // Test subtracting values.
        testTranslationOf("IV", 4);


        // Test composite adding and subtracting values.

        // Test error handling of invalid roman numeral values.

    }

    private static void testTranslationOf( final String romanNumeralValue, int expectedValue ) {
        System.out.printf(
            "translate(\"%s\") = %d (expected %d)%n",
            romanNumeralValue, translate( romanNumeralValue ), expectedValue
        );
    }

    private static int translate( final String romanNumeralValue ) {
        if ( romanNumeralValue == null ) {
            throw new IllegalArgumentException(
                "Invalid roman numeral.  Roman numeral values most be non-null."
            );
        }

        if ( romanNumeralValue.length() == 0 ) {
            return 0;
        }
        else if ( romanNumeralValue.length() == 1 ) {
            return getIntValueFor( romanNumeralValue.charAt( 0 ) );
        }

        int intValue = 0;
        for ( int i = 1; i < romanNumeralValue.length(); i += 2 ) {
            char priorChar = romanNumeralValue.charAt( i - 1 );
            char currentChar = romanNumeralValue.charAt( i );

            if ( currentChar == priorChar ) {
                throw new IllegalArgumentException(
                    "Invalid roman numeral.  Successive roman numeral values must not equal each other."
                );
            }

            if ( priorChar < currentChar ) {
                // Subtract.
                intValue += getIntValueFor(currentChar) - getIntValueFor(priorChar);
            }
            else {
                // Add.
                intValue += getIntValueFor(currentChar) + getIntValueFor(priorChar);
            }
        }

        return intValue;
    }

    private static Integer getIntValueFor( final char romanNumeralValue ) {
        if ( !INDEX.containsKey( romanNumeralValue ) ) {
            throw new IllegalArgumentException( String.format(
                "Invalid roman numeral.  Roman numeral values must equal one of %S.",
                INDEX.values()
            ) );
        }

        return INDEX.get( romanNumeralValue );
    }

}
