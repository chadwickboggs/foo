package com.chadwickboggs.foo;

public enum CurrencyUnit {

    PENNY(1, TYPE.COIN, "PENNY"),
    NICKLE(5, TYPE.COIN, "NICKLE"),
    DIME(10, TYPE.COIN, "DIME"),
    QUARTER(25, TYPE.COIN, "QUARTER"),
    FIFTY_CENT(50, TYPE.COIN, "FIFTY_CENT"),
    DOLLAR(100, TYPE.COIN, "DOLLAR"),

    ONE(100, TYPE.BILL, "ONE"),
    TWO(200, TYPE.BILL, "TWO"),
    FIVE(500, TYPE.BILL, "FIVE"),
    TEN(1000, TYPE.BILL, "TEN"),
    TWENTY(2000, TYPE.BILL, "TWENTY"),
    FIFTY(5000, TYPE.BILL, "FIFTY"),
    HUNDRED(10000, TYPE.BILL, "HUNDRED");


    private int value;
    private TYPE type;
    private String name;

    CurrencyUnit(final int value, final TYPE type, final String name) {
        this.value = value;
        this.type = type;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public TYPE getType() {
        return type;
    }

    public String getName() {
        return name;
    }


    public static enum TYPE {
        BILL, COIN
    }
}
