package com.chadwickboggs.foo.scala

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class MainTest extends FunSuite {

  test("ExpressionCB.show") {
    assert(NumberCB(0).show(NumberCB(1)) === "1")
    assert(NumberCB(0).show(NumberCB(2)) === "2")
    assert(NumberCB(0).show(VariableCB("x")) === "x")
    assert(NumberCB(0).show(SummationCB(VariableCB("x"), NumberCB(1))) === "x + 1")
    assert(NumberCB(0).show(ProductCB(VariableCB("y"), NumberCB(2))) === "y * 2")
    assert(NumberCB(0).show(
      ProductCB(VariableCB("x"), SummationCB(VariableCB("y"), NumberCB(2)))) === "x * (y + 2)"
    )
  }

  test("FooListUtils.insert") {
    // Head.
    var result: List[Int] = FooListUtils.insert(1, List(2, 3, 4))
    assert(result.equals(List(1, 2, 3, 4)))

    // Middle.
    result = FooListUtils.insert(2, List(1, 3, 4))
    assert(result.equals(List(1, 2, 3, 4)))

    // Tail.
    result = FooListUtils.insert(4, List(1, 2, 3))
    assert(result.equals(List(1, 2, 3, 4)))
  }

}
