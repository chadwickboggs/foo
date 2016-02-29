package com.chadwickboggs.util;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import rx.Observable;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public final class EqualityUtils {

    @NotNull
    public static <E, R> EqualsDeepResult equalsDeep(@Nullable E expected, @Nullable R actual)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        if (expected == null) {
            if (actual != null) {
                return new EqualsDeepResult(false, "Not equal.  Expected is null and actual non-null.");
            }
        } else if (actual == null) {
            return new EqualsDeepResult(false, "Not equal.  Expected is non-null and actual null.");
        }

        Map<String, Object> gameProperties = PropertyUtils.describe(expected);
        Set<Map.Entry<String, Object>> entries = gameProperties.entrySet();

        Observable<Map.Entry<String, Object>> processed = Observable.from(entries).flatMap(entry -> {
            String name = entry.getKey();
            if ("class".equals(name)) {
                return Observable.just(entry);
            }

            Object value = entry.getValue();

            Object valueReturned = null;
            try {
                valueReturned = PropertyUtils.getProperty(actual, name);
            } catch (Throwable ignored) {
            }

            String message = String.format(
                    "Not equal.  Property Name: \"%s\", Value Expected: \"%s\", Value Actual: \"%s\", Object: \"%s\"",
                    name, value, valueReturned, expected
            );
            if (!areEqual(value, valueReturned)) {
                return Observable.error(new Exception(message));
            }

            if (value == null || valueReturned == null || value.equals(expected)) {
                return Observable.just(entry);
            }

            try {
                if (!equalsDeep(value, valueReturned).isEqual()) {
                    return Observable.error(new Exception(message));
                }
            } catch (Throwable t) {
                return Observable.error(t);
            }

            return Observable.just(entry);
        });

        List<Map.Entry<String, Object>> processedList = null;
        try {
            processedList = processed.toList().toBlocking().single();
        } catch (Throwable t) {
            return new EqualsDeepResult(false, t.getMessage());
        }

        if (entries.size() == processedList.size()) {
            return EqualsDeepResult.TRUE;
        } else {
            return new EqualsDeepResult(false, "Not equal.  Differing counts of properties found.");
        }
    }

    public static boolean areEqual(@Nullable Object first, @Nullable Object second) {
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

            boolean equals =
                    first instanceof byte[] && second instanceof byte[]
                            ? StringUtils.equals(Arrays.toString((byte[]) first), Arrays.toString((byte[]) second))
                            : first instanceof char[] && second instanceof char[]
                            ? StringUtils.equals(Arrays.toString((char[]) first), Arrays.toString((char[]) second))
                            : first instanceof int[] && second instanceof int[]
                            ? StringUtils.equals(Arrays.toString((int[]) first), Arrays.toString((int[]) second))
                            : first instanceof long[] && second instanceof long[]
                            ? StringUtils.equals(Arrays.toString((long[]) first), Arrays.toString((long[]) second))
                            : first instanceof float[] && second instanceof float[]
                            ? StringUtils.equals(Arrays.toString((float[]) first), Arrays.toString((float[]) second))
                            : first instanceof double[] && second instanceof double[]
                            ? StringUtils.equals(Arrays.toString((double[]) first), Arrays.toString((double[]) second))
                            : StringUtils.equals(Arrays.toString((Object[]) first), Arrays.toString((Object[]) second));

            return equals;
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

    public static class EqualsDeepResult {

        public static final EqualsDeepResult TRUE = new EqualsDeepResult(true);
        public static final EqualsDeepResult FALSE = new EqualsDeepResult(false);

        private boolean equal;
        private Optional<String> message = Optional.empty();

        public EqualsDeepResult(boolean equal) {
            this.equal = equal;
        }

        public EqualsDeepResult(boolean equal, @NotNull String message) {
            this.equal = equal;
            this.message = Optional.of(message);
        }

        public boolean isEqual() {
            return equal;
        }

        public boolean isNotEqual() {
            return !equal;
        }

        @NotNull
        public Optional<String> getMessage() {
            return message;
        }
    }
}
