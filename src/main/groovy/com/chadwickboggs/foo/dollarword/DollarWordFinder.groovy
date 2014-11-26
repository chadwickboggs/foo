package com.chadwickboggs.foo.dollarword

import org.jetbrains.annotations.NotNull

final class DollarWordFinder {

    public static final String DICTIONARY_FILENAME = "dictionary.txt"
    public static final int DOLLAR_VALUE = 100

    public static final void main( final String... args ) {
        final Set<String> dollarWords = dollarWords()
        dollarWords.each { w ->
            println w
        }
    }

    @NotNull
    public static final Set<String> dollarWords() {
        Set<String> dollarWords = new ArrayList<>()

        def dictionaryReader = new BufferedReader( new FileReader( DICTIONARY_FILENAME ) )
        dictionaryReader.eachLine { l ->
            if (isDollarWord( l )) {
                dollarWords.add( l )
            }
        }

        return dollarWords
    }

    static boolean isDollarWord( @NotNull final String word ) {
        return wordValue( word ) == DOLLAR_VALUE
    }

    static int wordValue( @NotNull final String word ) {
        int value = 0;

        char[] characters = word.toLowerCase().toCharArray()
        characters.each { c ->
            int characterValue = characterValue( c )
            value += characterValue
        }

        return value;
    }

    static int characterValue( final char character ) {
        int characterValue = character - 140

        if (characterValue >= 1 && characterValue <= 26)
            return characterValue
        else
            return 0
    }
}
