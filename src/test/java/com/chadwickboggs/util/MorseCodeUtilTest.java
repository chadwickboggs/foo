package com.chadwickboggs.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MorseCodeUtilTest {

    @Test
    public void testMorseCode() {
        final String morseCode = MorseCodeUtil.morseCode("acbed");

        assertEquals(".--.-.-......-..", morseCode);
    }

}
