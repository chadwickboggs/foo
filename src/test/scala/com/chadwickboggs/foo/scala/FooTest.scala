package com.chadwickboggs.foo.scala

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FooTest extends FunSuite {

    test("show") {
        assert(NumberCB(1).show() === "1")
        assert(NumberCB(2).show() === "2")
        assert(VariableCB("x").show() === "x")
        assert(SummationCB(VariableCB("x"), NumberCB(1)).show() === "x + 1")
        assert(SummationCB(VariableCB("y"), NumberCB(2)).show() === "y * 2")
        assert(ProductCB(VariableCB("x"), SummationCB(VariableCB("y"), NumberCB(2))).show() === "y * (x + 1)")
    }

}
