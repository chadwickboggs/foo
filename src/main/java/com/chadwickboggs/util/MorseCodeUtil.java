package com.chadwickboggs.util;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeUtil {

    public static final Map<Character, String> MORSE_CODE_MAP = new HashMap<>();

    static {
        MORSE_CODE_MAP.put('a', ".-");
        MORSE_CODE_MAP.put('b', "-.....");
        MORSE_CODE_MAP.put('c', "-.-.");
        MORSE_CODE_MAP.put('d', "-..");
        MORSE_CODE_MAP.put('e', ".");
        MORSE_CODE_MAP.put(' ', "/");
    }

    public static String morseCode(final String input) {
        assert input != null : "Parameter input is null.";

        final StringBuilder morseCodeBuf = new StringBuilder();
        for (final char aChar : input.toCharArray()) {
            final String morse = morseCode(aChar);
            morseCodeBuf.append(morse);
        }

        return morseCodeBuf.toString();
    }

    private static String morseCode(char aChar) {
        final String morse = MORSE_CODE_MAP.get(aChar);
        if (morse == null) {
            throw new RuntimeException(String.format("No morse code known for character.  Character: %s", aChar));
        }

        return morse;
    }

}
