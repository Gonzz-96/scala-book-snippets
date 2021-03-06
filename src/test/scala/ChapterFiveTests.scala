package dev.gonz

import org.scalatest.funsuite.AnyFunSuite

// If this flag is not present, postfix operations
// are not allowed by the compiled...
import scala.language.postfixOps

class ChapterFiveTests extends AnyFunSuite {

  test("Integer literals") {
    // Hexadecimals starts with "0x" prefix
    val hex = 0x5
    val hex2 = 0x00FF
    val magic = 0xCAFEBABE
    assert(hex.isInstanceOf[Int])
    assert(hex2.isInstanceOf[Int])
    assert(magic.isInstanceOf[Int])

    // If the literal starts with a non-zero digit, it's a decimal
    val dec1 = 31
    val dec2 = 255
    val dec3 = 20
    assert(dec1.isInstanceOf[Int])
    assert(dec2.isInstanceOf[Int])
    assert(dec3.isInstanceOf[Int])

    // Long literals ends with an "L"
    val prog = 0xCAFEBABEL
    val tower = 35L
    val of = 31L
    assert(prog.isInstanceOf[Long])
    assert(tower.isInstanceOf[Long])
    assert(of.isInstanceOf[Long])

    // Types are flexible
    val little: Short = 367 // Int literal assigned to Short value
    val littler: Byte = 38 // Int literal assigned to Byte value
    assert(little.isInstanceOf[Short])
    assert(littler.isInstanceOf[Byte])
  }

  test("Floating point literals") {
    val big = 1.2345
    val bigger = 1.2345e1
    val biggerStill = 123e45
    assert(big.isInstanceOf[Double])
    assert(bigger.isInstanceOf[Double])
    assert(biggerStill.isInstanceOf[Double])

    // Float type ends with an F
    val little = 1.2345F
    val littleBigger = 3e5F
    assert(little.isInstanceOf[Float])
    assert(littleBigger.isInstanceOf[Float])

    // Optionally, Double value can end with a D
    val anotherDouble = 3e5
    val yetAnother = 3e5D
    assert(anotherDouble.isInstanceOf[Double])
    assert(yetAnother.isInstanceOf[Double])
  }

  test("Character literals") {
    // Characters literals are compose of any Unicode character
    val a = 'A'

    // Using Unicode code
    val d = '\u0041'
    val f = '\u0044'
    assert(d == 'A')
    assert(f == 'D')

    // Special scape sequences
    val backslash = '\\'
    assert(backslash.isInstanceOf[Char])
  }

  test("String literals") {
    val hello = "hello"

    val escapes = "\\\"\'"
    assert(escapes == """    \"'     """.trim)
  }

  // IMPORTANT: Symbol literals were deprecated in Scala 2.13!!!!!
  // The correct way of instantiating a Symbol is by using the
  // factory method: Symbol("symbolName")
  test("Symbol literals") {

    def updateRecordByName(r: Symbol, value: Any): Unit = { }

    updateRecordByName(Symbol("favoriteAlbum"), "OK computer")

    val s = 'aSymbol // DEPRECATED CONSTRUCTION!!!!
    val nm = s.name
    assert(nm == "aSymbol")
  }

  test("Boolean literals") {
    val bool = true
    val fool = false

    // They are just booleans lol
  }

  test("String interpolation") {
    val name = "reader"
    println(s"Hello, $name!")

    val answer = s"The answer is: ${6 * 7}"

    // raw interpolator doesn't recognize escape literals
    println(raw"No\\\\escape!")

    // f interpolator let create printf-like formatting
    println(f"${math.Pi}%.5f")

    // if no formatting instructions are given to f interpolator,
    // it will behave as s interpolator
    val pi = "Pi"
    println(f"$pi is approximately ${math.Pi}%.8f.")
  }

  test("Operators are methods!") {
    val sum = 1 + 2 // Scala invokes 1.+(2)
    val sumMore = 1.+(2) // Same as 1 + 2
    assert(sum == sumMore)

    val longSum = 1 + 2L // Scala invokes 1.+(2L)
    assert(longSum.isInstanceOf[Long])
    assert(longSum == 3L)

    val s = "Hello, world!"
    assert((s indexOf 'o') == 4)
    assert((s indexOf ('o', 5)) == 8)

    // WHen a method has the "unary_" prefix,
    // it can be called as a prefix operator
    // Also, only +, -, ! and ~ simbols can be turned
    // into prefix operators!!!!
    assert(-2.0 == (2.0).unary_-)

    // Scala convention says that if a method has
    // side effects, it should include parenthesis.
    // If it's not the case, you can omit them
    assert((s toLowerCase) == "hello, world!")
  }

  test("Objects equality") {
    case class Person(age: Int, name: String)

    val p1 = Person(25, "Gonz")
    val p2 = Person(25, "Gonz")

    assert(p1 == p2)
  }
}
