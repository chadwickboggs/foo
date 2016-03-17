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
        byte[][] expected = {{(byte) 137}, {(byte) 139}};
        byte[][] actual = {{(byte) 137}, {(byte) 139}};
        assertTrue(EqualityUtils.areEqual(expected, actual));

        byte[][] actualFalse = {{(byte) 2}, {(byte) 3}};
        assertFalse(EqualityUtils.areEqual(expected, actualFalse));
    }

    @Test
    public void testareEquals2DArrayObject() {
        String[][] expected = {{"137"}, {"139"}};
        String[][] actual = {{"137"}, {"139"}};
        assertTrue(EqualityUtils.areEqual(expected, actual));

        String[][] actualFalse = {{"2"}, {"3"}};
        assertFalse(EqualityUtils.areEqual(expected, actualFalse));
    }

    @Test
    public void testareEquals2DCollection() {
        Collection<Collection<String>> expected = new ArrayList<>();
        Collection<String> tmp = new ArrayList<>();
        expected.add(tmp);
        tmp.add("one");
        tmp = new ArrayList<>();
        expected.add(tmp);
        tmp.add("two");

        Collection<Collection<String>> actual = new HashSet<>();
        tmp = new ArrayList<>();
        actual.add(tmp);
        tmp.add("one");
        tmp = new ArrayList<>();
        actual.add(tmp);
        tmp.add("two");

        assertTrue(EqualityUtils.areEqual(expected, actual));

        actual = new HashSet<>();
        tmp = new ArrayList<>();
        actual.add(tmp);
        tmp.add("three");
        tmp = new ArrayList<>();
        actual.add(tmp);
        tmp.add("four");

        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEquals2DMap() {
        Map<String, Map<String, Integer>> expected = new HashMap<>();
        Map<String, Integer> tmp = new HashMap<>();
        expected.put("first", tmp);
        tmp.put("key1", 1);
        tmp = new HashMap<>();
        expected.put("second", tmp);
        tmp.put("key2", 2);

        Map<String, Map<String, Integer>> actual = new HashMap<>();
        tmp = new HashMap<>();
        actual.put("first", tmp);
        tmp.put("key1", 1);
        tmp = new HashMap<>();
        actual.put("second", tmp);
        tmp.put("key2", 2);

        assertTrue(EqualityUtils.areEqual(expected, actual));

        tmp = new HashMap<>();
        actual.put("third", tmp);
        tmp.put("key3", 3);

        assertFalse(EqualityUtils.areEqual(expected, actual));
    }

    @Test
    public void testareEqualsAll() {
        // TODO: Implement.
    }

}
