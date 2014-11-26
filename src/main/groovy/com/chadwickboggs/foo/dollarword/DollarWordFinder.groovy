package com.chadwickboggs.foo.dollarword

//import org.jetbrains.annotations.NotNull

final class DollarWordFinder {

    public static final String DICTIONARY_FILENAME = "dictionary.txt"
    public static final int DOLLAR_VALUE = 100

    public static final void main( final String... args ) {
        final Set<String> dollarWords = dollarWords()
        dollarWords.each { w ->
            println w
        }
    }

//    @NotNull
    public static final Set<String> dollarWords() {
        Set<String> dollarWords = new ArrayList<>()

        def dictionaryReader = new BufferedReader( new FileReader( DICTIONARY_FILENAME ) )
        dictionaryReader.eachLine { l ->
            if (isDollarWord( l )) {
                dollarWords.add( l )
            }
        }

        println "Count of Dollar Words: ${dollarWords.size(  )}"

        return dollarWords
    }

    static boolean isDollarWord( /*@NotNull*/ final String word ) {
        int wordValue = wordValue( word )
//        println( "${wordValue}\t${word}" )

        return wordValue == DOLLAR_VALUE
    }

    static int wordValue( /*@NotNull*/ final String word ) {
        int value = 0;

        byte[] bytes = word.toLowerCase().bytes
        bytes.each { b ->
            int characterValue = characterValue( b )
//            println "${characterValue}\t${b}"

            value += characterValue
        }

        return value;
    }

    static int characterValue( final byte character ) {
        int characterValue = ((int) character) - 96

        if (characterValue >= 1 && characterValue <= 26)
            return characterValue
        else
            return 0
    }
}
