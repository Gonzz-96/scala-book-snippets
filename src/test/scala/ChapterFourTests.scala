package dev.gonz

import scala.collection.mutable

import org.scalatest.funsuite.AnyFunSuite

class ChapterFourTests extends AnyFunSuite {

  class ChecksumAccumulator {
    private var sum = 0
    def add(b: Byte): Unit = { sum += b }
    def checksum(): Int = ~(sum & 0xFF) + 1
  }

  object ChecksumAccumulator {

    private val cache = mutable.Map.empty[String, Int]

    def apply() = new ChecksumAccumulator

    def calculate(s: String) =
      if (cache.contains(s))
        cache(s)
      else {
        val acc = ChecksumAccumulator()
        for (c <- s) {
          acc.add(c.toByte)
        }
        val cs = acc.checksum()
        cache += (s -> cs)
        cs
      }

  }

  test("Listing 4.1 - Final version of class CheksumAccumulator") {
    val acc = new ChecksumAccumulator
    acc.add(0x01)

    assert(acc.checksum() == -1)
  }

  test("Listing 4.2 - Companion object for class ChecksumAccumulator") {
    val cs = ChecksumAccumulator.calculate("Every value is an object.")
    println(cs)
  }

  test("Listing 4.3 - The Summer application") {
    object Summer {
      def main(args: Array[String]): Unit = {
        for (arg <- args) {
          println(arg + ": " + ChecksumAccumulator.calculate(arg))
        }
      }
    }

    // This is intended to run as a standalone app, not a test
    Summer.main(Array("Hello", "World", "My", "Name", "Is", "Gonz"))
  }

  test("Listing 4.4 - Using the App traig") {
    // This is intended to run as a standalone app, not a test
    object FallWinterSpringSummer extends  App {
      for (season <- List("fall", "winter", "spring")) {
        println(season + ": " + ChecksumAccumulator.calculate(season))
      }
    }
  }
}
