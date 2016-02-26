package com.chadwickboggs.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import rx.Observable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by cboggs1 on 2/26/16.
 */
public final class EqualityUtils {

    private static <E, R> void equalsDeep(E expected, R actual)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Map<String, Object> gameProperties = PropertyUtils.describe(expected);
        Set<Map.Entry<String, Object>> entries = gameProperties.entrySet();

        Observable<Map.Entry<String, Object>> processed = Observable.from(entries).map(entry -> {
            String name = entry.getKey();
            if ("class".equals(name)) {
                return entry;
            }

            Object value = entry.getValue();

            Object valueReturned = null;
            try {
                valueReturned = PropertyUtils.getProperty(actual, name);
            } catch (Throwable ignored) {
            }

            assertTrue(
                    String.format(
                            "Not equal.  Property Name: \"%s\", Value Expected: \"%s\", Value Actual: \"%s\", Object: \"%s\"",
                            name, value, valueReturned, expected
                    ),
                    areEqual(value, valueReturned)
            );

            if (value == null || valueReturned == null || value.equals(expected)) {
                return entry;
            }

            try {
                equalsDeep(value, valueReturned);
            } catch (Throwable t) {
                Observable.error(t);
            }

            return entry;
        });

        assertEquals(entries.size(), processed.toList().toBlocking().single().size());
    }

    private static boolean areEqual(Object first, Object second) {
        if (first == null && second == null) {
            return true;
        }

        if (first == null || second == null) {
            return false;
        }

        if (first.getClass().isArray()) {
            if (!second.getClass().isArray()) {
                return false;
            }

            return StringUtils.equals(Arrays.toString((Object[]) first), Arrays.toString((Object[]) second));
        }

        if (Collection.class.isAssignableFrom(first.getClass())) {
            if (!Collection.class.isAssignableFrom(second.getClass())) {
                return false;
            }

            return CollectionUtils.isEqualCollection((Collection) first, (Collection) second);
        }

        if (Map.class.isAssignableFrom(first.getClass())) {
            if (!Map.class.isAssignableFrom(second.getClass())) {
                return false;
            }

            boolean allMatch = ((Map) first).entrySet().stream().allMatch(entry -> {
                Object valueSecond = ((Map) second).get(((Map.Entry) entry).getKey());

                boolean areEqual = areEqual(((Map.Entry) entry).getValue(), valueSecond);

                return areEqual;
            });

            return allMatch;
        }

        return first.toString().equals(second.toString());
    }

}
