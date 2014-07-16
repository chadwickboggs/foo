package com.chadwickboggs.foo;

import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


public class CashRegisterTest {

    @Test
    public void testPayBI() {
        final int cost = 16728;

        int change = pay(1, cost);
        change = pay(16720, change);
        change = pay(5, change);
        change = pay(2, change);

        assertEquals(0, change);
    }

    private int pay(final int amount, final int cost) {
        BigInteger change = CashRegister.pay(BigInteger.valueOf(amount), BigInteger.valueOf(cost));

        assertEquals("Change differs from expected.", BigInteger.valueOf(cost - amount), change);

        return change.intValue();
    }

    @Test
    public void testPayCurrency() {
        int cost = 16728;

        Collection<CurrencyUnit> change = pay(CashRegister.currency(BigInteger.valueOf(1)), cost);
        cost -= 1;
        change = pay(CashRegister.currency(BigInteger.valueOf(16720)), cost);
        cost -= 16720;
        change = pay(CashRegister.currency(BigInteger.valueOf(5)), cost);
        cost -= 5;
        change = pay(CashRegister.currency(BigInteger.valueOf(1)), cost);

        final Collection<CurrencyUnit> expected = new ArrayList<>();
        expected.add(CurrencyUnit.PENNY);

        assertEquals(expected, change);
    }

    private Collection<CurrencyUnit> pay(final Collection<CurrencyUnit> amount, final int cost) {
        final Collection<CurrencyUnit> change = CashRegister.pay(amount, BigInteger.valueOf(cost));

        final Collection<CurrencyUnit> expected =
                CashRegister.currency(BigInteger.valueOf(cost).subtract(CashRegister.amount(amount)));

        assertEquals("Change differs from expected.", expected, change);

        return change;
    }


    @Test
    public void testCurrency() {
    }

}
