package com.chadwickboggs.foo;

import java.util.Arrays;


public final class Foo {

    private Foo() {
    }

    public static void main(final String... args) {
        System.out.println(Arrays.toString(args));
    }

}
