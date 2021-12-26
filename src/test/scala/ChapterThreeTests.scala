package dev.gonz

import ChapterThreeTests.{TestFileName, TestFilesPath}

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable
import scala.io.Source

class ChapterThreeTests extends AnyFunSuite {

  test("Listing 3.5 - Creating, initializing, and using immutable set") {
    var jetSet = Set("Boeing", "Airbus")
    jetSet += "Lear" // This creates a new set! Shorthand for: jetSet = jetSet + "Lear"
    println("Does set contain \"Cessna\" element? -> " + jetSet.contains("Cessna"))

    assert(!jetSet.contains("Cessna"))
  }

  test("Listing 3.6 - Creating, initializing, and using mutable set") {
    val movieSet = mutable.Set("Hitch", "Poltergeist")
    movieSet += "Shrek"
    println(movieSet)

    assert(movieSet.size == 3)
  }

  test("Listing 3.7 - Creating, initializing and using mutable map") {
    val treasureMap = mutable.Map[Int, String]()
    treasureMap += (1 -> "Go to island.")
    treasureMap += (2 -> "Find big X on ground.")
    treasureMap += (3 -> "Dig")
    println(treasureMap(2))

    assert(treasureMap.size == 3)
  }

  test("Listing 3.8 - Creating, initializing, and using immutable map") {
    val romanNumeral = Map(
      1 -> "I",
      2 -> "II",
      3 -> "III",
      4 -> "IV",
      5 -> "V",
    )
    // Compilation error: romanNumeral is a val and of type immutable.Map
    // romanNumeral += (6 -> "VI")
    println(romanNumeral(4))

    assert(romanNumeral(4) == "IV")
  }

  test("Listing 3.9 - A function without side effects and vars") {

    /**
     * Function built in imperative style since there are var declarations
     * and a while loop to iterate over an array.
     */
    def printArgsImperative(args: Array[String]) = {
      var i = 0
      while (i < args.length) {
        println(args(i))
        i += 1
      }
    }

    /**
     * Getting rid of var declarations: making it more functional!
     */
    def printArgsFunctional(args: Array[String]) = {
      for (arg <- args) {
        println(arg)
      }
    }

    /**
     * Even more functional: using functions as top-level properties
     */
    def printArgsMoreFunctional(args: Array[String]) = {
      args.foreach(println)
    }

    /**
     * MORE! Side effects should be out of our functions
     */
    def formatArgs(args: Array[String]) = args.mkString(sep = "\n")
    val args = Array("My", "name", "is", "Gonz!")

    val formattedArgs = formatArgs(args)
    // Side effect out of the function
    println(formattedArgs)

    assert(formattedArgs == "My\nname\nis\nGonz!")
  }

  // Test ignore since its high memory consumption
  ignore("Listing 3.10 - Reading lines from a file.") {
    val fileName = TestFileName
    val file = Source.fromFile(fileName)

    for (line <- file.getLines()) {
      val lineLength = line.length
      if (lineLength > 100) throw new IllegalArgumentException("")
      println(s"${if (lineLength < 10) s" $lineLength" else lineLength} | $line")
    }
  }

  // Test ignore since its high memory consumption
  ignore("Listing 3.11 - Printing formatted character counts for the lines of a file") {
    val fileName = TestFileName
    def widthOfLength(s: String) = s.length.toString.length

    val lines = Source.fromFile(fileName).getLines().toList
    val longestLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b
    )
    val maxWidth = widthOfLength(longestLine)

    for (line <- lines) {
      val numSpaces = maxWidth - widthOfLength(line)
      val padding = " " * numSpaces
      println(padding + line.length + " | " + line)
    }
  }
}

object ChapterThreeTests {
  val TestFilesPath = "./src/test/scala"
  val TestFileName = s"$TestFilesPath/ChapterThreeTests.scala"
}