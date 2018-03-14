package com.chadwickboggs.foo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;


public class CashRegisterStepsDef implements En {

    private Integer price;
    private Integer payment;
    private Integer change;

/*
    public void CashRegisterStepsDef() {

        Given("^Price (\\d+)$", (Integer price) -> {
            System.out.println(String.format("Price %s", price));
        });

        When("^Paid (\\d+)$", (Integer payment) -> {
            System.out.println(String.format("Paid %s.", payment));
//            throw new PendingException();
        });

        Then("^Change (\\d+)$", (Integer change) -> {
            System.out.println(String.format("Change %s", change));
            assertTrue(true);
        });
    }
*/


    @Given( "^Price (\\d+)$" )
    public void price(final Integer price) {
        System.out.println(String.format("Price %s", price));
        this.price = price;
    }


    @When( "^Paid (\\d+)$" )
    public void paid(final Integer payment) {
        System.out.println(String.format("Paid %s", payment));
        this.payment = payment;
        change = CashRegister.pay(BigInteger.valueOf(this.payment), BigInteger.valueOf(price)).intValue();
    }


    @Then( "^Change (\\d+)$" )
    public void change(final Integer change) {
        System.out.println(String.format("Expected change: %s", this.change));
        System.out.println(String.format("Received change: %s", change));

        assertEquals(this.change, change);
    }

}
