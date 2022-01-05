package dev.gonz

import org.scalatest.funsuite.AnyFunSuite

import scala.annotation.tailrec
import scala.collection.mutable
import scala.language.implicitConversions

class ChapterSixTests extends AnyFunSuite {

  class Rational(n: Int, d: Int) {
    // all the code that is not a method nor a field definition,
    // will be compiled as part of the constructor
    // println(s"Created: $toString")
    require(d != 0)

    private val g = greatestCommonDivisor(n.abs, d.abs)
    val numer: Int = n / g
    val denom: Int = d / g

    def this(n: Int) = this(n, 1)

    override def toString: String = s"$numer/$denom"

    override def equals(obj: Any): Boolean =
      if (obj == null || !obj.isInstanceOf[Rational]) {
        false
      } else {
        val that = obj.asInstanceOf[Rational]
        numer == that.numer && denom == that.denom
      }

    def +(that: Rational): Rational =
      new Rational(
        (numer * that.denom) + (that.numer * denom),
        (denom * that.denom)
      )

    def +(i: Int): Rational =
      new Rational(numer + (i * denom), denom)

    def *(that: Rational): Rational =
      new Rational(numer * that.numer, denom * that.denom)

    def *(i: Int): Rational =
      new Rational(numer * i, denom)

    def -(that: Rational): Rational =
      new Rational(
        (numer * that.denom) - (that.numer * denom),
        (denom * that.denom),
      )

    def -(i: Int): Rational =
      new Rational(numer - i * denom, denom)

    def /(that: Rational): Rational =
      new Rational(numer * that.denom, denom * that.numer)

    def /(i: Int): Rational =
      new Rational(numer, denom * i)

    @tailrec
    private def greatestCommonDivisor(a: Int, b: Int): Int =
      if (b == 0) a else greatestCommonDivisor(b, a % b)
  }

  test("given a valid Rational object, when toString is called, then its string value is ok") {
    val rational = new Rational(1, 5).toString

    assert(rational == "1/5")
  }

  test("given a new Rational object, when denominator is zero, then exception must be thrown") {
    assertThrows[IllegalArgumentException] {
      new Rational(5, 0)
    }
  }

  test("given two Rational objects, when + is called, return a new Rational object") {
    val oneHalf = new Rational(1, 2)
    val twoThirds = new Rational(2, 3)

    val newRational = oneHalf + twoThirds

    assert(newRational == new Rational(7, 6))
  }

  test("given a instantiation of Rational object, when passing one argument, then auxiliar constructor is called") {
    val five = new Rational(5)

    assert(five == new Rational(5, 1))
  }

  test("given a new Rational object, when recently created, then the object will be in its normalized form") {
    val sixtySixFourtyTwo = new Rational(66, 42)

    assert(sixtySixFourtyTwo == new Rational(11, 7))
  }

  test("given two Rational objects, when * is called, then return a new correct Rational object") {
    val twoThirds = new Rational(2, 3)
    val fourHalves = new Rational(4, 2)

    assert(twoThirds * fourHalves == new Rational(4, 3))
  }

  test("given two Rational objects, when * and + is called, then expect * to be called before +") {
    val x = new Rational(1, 2)
    val y = new Rational(2, 3)

    assert((x + x * y) == new Rational(5, 6))
    assert((x + x) * y == new Rational(2, 3))
    assert(x + (x * y) == new Rational(5, 6))
  }

  test("given two Rational and Int objects, when + is called, expect the correct result") {
    val rational = new Rational(1, 2)
    val int = 2

    assert(rational + int == new Rational(5, 2))
  }

  test("given two Rational and Int objects, when * is called, expect the correct result") {
    val rational = new Rational(1, 2)
    val int = 2

    assert(rational * int == new Rational(1, 1))
  }

  test("given two Rational and Int objects, when +: is called, expect the correct result") {
    implicit def intToRational(x: Int): Rational = new Rational(x)
    val rational = new Rational(1, 2)
    val int = 2

    assert(int + rational == new Rational(5, 2))
  }
}
