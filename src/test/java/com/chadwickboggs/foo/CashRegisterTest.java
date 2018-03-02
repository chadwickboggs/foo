package com.chadwickboggs.foo;

import cucumber.api.CucumberOptions;
import cucumber.api.java8.En;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = { "classpath:features/cash_register.feature" }
)
public class CashRegisterTest implements En {
}
