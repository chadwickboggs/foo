package com.chadwickboggs.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class EqualityUtilsTest {

    @Test
    public void testEqualsDeepSinglePrimitive() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        EqualityUtils.EqualsDeepResult equalsDeepResult = EqualityUtils.equalsDeep(
                new SinglePrimitive(137), new SinglePrimitive(137)
        );
        printMessage(equalsDeepResult.isNotEqual(), equalsDeepResult, StringUtils.EMPTY);
        assertTrue(getMessage(equalsDeepResult, StringUtils.EMPTY), equalsDeepResult.isEqual());

        equalsDeepResult = EqualityUtils.equalsDeep(new SinglePrimitive(137), new SinglePrimitive(139));
        printMessage(equalsDeepResult.isNotEqual(), equalsDeepResult, StringUtils.EMPTY);
        assertTrue(getMessage(equalsDeepResult, StringUtils.EMPTY), equalsDeepResult.isNotEqual());
    }

    @Test
    public void testEqualsDeepSingleObject() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        EqualityUtils.EqualsDeepResult equalsDeepResult = EqualityUtils.equalsDeep("137", "137");
        printMessage(equalsDeepResult.isNotEqual(), equalsDeepResult, StringUtils.EMPTY);
        assertTrue(getMessage(equalsDeepResult, StringUtils.EMPTY), equalsDeepResult.isEqual());

        equalsDeepResult = EqualityUtils.equalsDeep("137", "139");
        printMessage(equalsDeepResult.isNotEqual(), equalsDeepResult, StringUtils.EMPTY);
        assertTrue(getMessage(equalsDeepResult, StringUtils.EMPTY), equalsDeepResult.isNotEqual());
    }

    @Test
    public void testEqualsDeepArrayPrimitive() {
    }

    @Test
    public void testEqualsDeepArrayObject() {
    }

    @Test
    public void testEqualsDeepCollection() {
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

    private void printMessage(boolean condition, EqualityUtils.EqualsDeepResult equalsDeepResult, String defaultValue) {
        if (condition) {
            System.err.println(getMessage(equalsDeepResult, defaultValue));
        }
    }

    private String getMessage(EqualityUtils.EqualsDeepResult equalsDeepResult, String defaultValue) {
        @NotNull Optional<String> message = equalsDeepResult.getMessage();

        return message.isPresent() ? message.get() : defaultValue;
    }

    public static class SinglePrimitive {
        private int value;

        public SinglePrimitive(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            SinglePrimitive rhs = (SinglePrimitive) obj;
            return new EqualsBuilder()
                    .append(this.value, rhs.value)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(value)
                    .toHashCode();
        }
    }
}
