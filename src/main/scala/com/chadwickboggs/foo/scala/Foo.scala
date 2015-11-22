package com.chadwickboggs.foo.scala


abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat = new Successor(this)

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

class Successor(val nat: Nat) extends Nat {
  override def isZero: Boolean = false

  override def predecessor: Nat = nat

  override def +(that: Nat): Nat = new Successor(nat + that)

  override def -(that: Nat): Nat = if (that.isZero) this else nat - that.predecessor
}

case class NumberCB(n: Int) extends ExpressionCB {
}

case class VariableCB(name: String) extends ExpressionCB {
}

case class SummationCB(e1: ExpressionCB, e2: ExpressionCB) extends ExpressionCB {
}

case class ProductCB(e1: ExpressionCB, e2: ExpressionCB) extends ExpressionCB {
}

trait ExpressionCB {
  def show(e: ExpressionCB): String = {
    e match {
      case NumberCB(n) => n.toString
      case VariableCB(name) => name.toString
      case SummationCB(l, r) => show(l) + " + " + show(r)
      case ProductCB(l, r) =>
        val le: String = l match {
          case SummationCB(e1, e2) => "(" + show(e1) + " + " + show(e2) + ")"
          case ProductCB(e1, e2) => show(e1) + " * " + show(e2)
          case NumberCB(n) => n.toString
          case VariableCB(name) => name.toString
        }

        val re: String = r match {
          case SummationCB(e1, e2) => "(" + show(e1) + " + " + show(e2) + ")"
          case ProductCB(e1, e2) => show(e1) + " * " + show(e2)
          case NumberCB(n) => n.toString
          case VariableCB(name) => name.toString
        }

        le + " * " + re
    }
  }
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def predecessor: Nat = throw new Error

  override def +(that: Nat): Nat = that

  override def -(that: Nat): Nat = if (that.isZero) this else throw new Error
}

object FooListUtils {
  def insert(x: Int, xs: List[Int]): List[Int] = {
    xs match {
      case List() => List[Int](x)
      case y :: ys => {
        if (x <= y) x :: xs
        else y :: insert(x, ys)
      }
    }
  }
}
