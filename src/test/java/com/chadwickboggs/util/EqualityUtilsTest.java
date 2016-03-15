package com.chadwickboggs.util;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualityUtilsTest {

    @Test
    public void testEqualsDeepSinglePrimitive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assertTrue(EqualityUtils.areEqual(137, 137));
        assertFalse(EqualityUtils.areEqual(137, 139));
    }

    @Test
    public void testEqualsDeepSingleObject() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assertTrue(EqualityUtils.areEqual("137", "137"));
        assertFalse(EqualityUtils.areEqual("137", "139"));
    }

    @Test
    public void testEqualsDeepArrayPrimitive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        byte[] expected = {(byte) 137};
        byte[] actual = {(byte) 137};
        assertTrue(EqualityUtils.areEqual(expected, actual));
        actual = new byte[] {(byte) 139};
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testEqualsDeepArrayObject() {
        String[] expected = {"137"};
        String[] actual = {"137"};
        assertTrue(EqualityUtils.areEqual(expected, actual));
        actual = new String[] {"139"};
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testEqualsDeepCollection() {
/*
        Collection expected =
        assertTrue(EqualityUtils.areEqual(expected, actual));
*/
    }

    @Test
    public void testEqualsDeepMap() {
    }

    @Test
    public void testEqualsDeep2DArrayPrimitive() {
    }

    @Test
    public void testEqualsDeep2DArrayObject() {
    }

    @Test
    public void testEqualsDeep2DCollection() {
    }

    @Test
    public void testEqualsDeep2DMap() {
    }

    @Test
    public void testEqualsDeepAll() {
    }

}
