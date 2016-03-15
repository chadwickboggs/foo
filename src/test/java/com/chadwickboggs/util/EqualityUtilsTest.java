package com.chadwickboggs.util;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EqualityUtilsTest {

    @Test
    public void testareEqualsSinglePrimitive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assertTrue(EqualityUtils.areEqual(137, 137));
        assertFalse(EqualityUtils.areEqual(137, 139));
    }

    @Test
    public void testareEqualsSingleObject() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        assertTrue(EqualityUtils.areEqual("137", "137"));
        assertFalse(EqualityUtils.areEqual("137", "139"));
    }

    @Test
    public void testareEqualsArrayPrimitive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        byte[] expected = {(byte) 137};
        byte[] actual = {(byte) 137};
        assertTrue(EqualityUtils.areEqual(expected, actual));

        actual = new byte[] {(byte) 139};
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEqualsArrayObject() {
        String[] expected = {"137"};
        String[] actual = {"137"};
        assertTrue(EqualityUtils.areEqual(expected, actual));

        actual = new String[] {"139"};
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEqualsCollection() {
        Collection<String> expected = new ArrayList<>();
        expected.add("one");
        expected.add("two");
        Collection<String> actual = new HashSet<>();
        actual.add("one");
        actual.add("two");
        assertTrue(EqualityUtils.areEqual(expected, actual));

        actual.add("three");
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEqualsMap() {
        Map<String, Integer> expected = new HashMap<>();
        expected.put("key1", 1);
        expected.put("key2", 2);
        Map<String, Integer> actual = new HashMap<>();
        actual.put("key1", 1);
        actual.put("key2", 2);
        assertTrue(EqualityUtils.areEqual(expected, actual));

        actual.put("key3", 3);
        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEquals2DArrayPrimitive() {
    }

    @Test
    public void testareEquals2DArrayObject() {
    }

    @Test
    public void testareEquals2DCollection() {
    }

    @Test
    public void testareEquals2DMap() {
    }

    @Test
    public void testareEqualsAll() {
    }

}
