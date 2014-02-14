package com.chadwickboggs.foo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

public class CashRegister {

  public static final String ERROR_MESSAGE = "ERROR";


  public static BigInteger pay(final BigInteger amount, final BigInteger price) {
    final BigInteger change = price.subtract(amount);

    return change;
  }


  public static Collection<CurrencyUnit> pay(final Collection<CurrencyUnit> amount, final BigInteger price) {
    final BigInteger amountBI = amount(amount);

    final BigInteger changeBI = pay(amountBI, price);
    if (changeBI.compareTo(BigInteger.ZERO) < 0) {
      System.err.println(ERROR_MESSAGE);
    }

    final Collection<CurrencyUnit> change = currency(changeBI);

    return change;
  }


  public static BigInteger amount(Collection<CurrencyUnit> currencyUnitCollection) {
    BigInteger amount = BigInteger.ZERO;

    for (final CurrencyUnit next : currencyUnitCollection) {
      amount = amount.add(BigInteger.valueOf(next.getValue()));
    }

    return amount;
  }


  public static Collection<CurrencyUnit> currency(BigInteger amount) {
    amount = amount.abs();

    final CurrencyUnit[] values = CurrencyUnit.values();
    final Collection<CurrencyUnit> currencyUnit = new ArrayList<>();

    for (int i = values.length - 1; i >= 0; i--) {
      final CurrencyUnit oneCurrencyUnit = values[i];
      final BigInteger oneCurrBI = BigInteger.valueOf(oneCurrencyUnit.getValue());

      final BigInteger[] divideAndRemainder = amount.divideAndRemainder(oneCurrBI);

      BigInteger count = divideAndRemainder[0];
      while (count.compareTo(BigInteger.ZERO) > 0) {
        currencyUnit.add(oneCurrencyUnit);

        count = count.subtract(BigInteger.ONE);
      }

      amount = divideAndRemainder[1];
    }

    return currencyUnit;
  }

}
