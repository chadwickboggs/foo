package com.chadwickboggs.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;


public final class EqualityUtils {

    public static <F, S> boolean areEqual(@Nullable F first, @Nullable S second) {
        if (first == second) {
            return true;
        }

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

            CollectionUtils.isEqualCollection(((Map) first).entrySet(), ((Map) second).entrySet());
        }

        return first.equals(second);
    }

}
