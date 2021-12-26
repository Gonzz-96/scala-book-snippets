package dev.gonz

object ChapterThree extends App {

  // More explicit:
  // val greetStrings: Array[String] = new Array[String](3)
  val greetStrings = new Array[String](3)
  greetStrings(0) = "Hello"
  greetStrings(1) = ", "
  greetStrings(2) = "world!\n"

  for (i <- 0 to 2) {
    print(greetStrings(i))
  }

  // Arrays are mutable
  val numNames = Array("zero", "one", "two")

  // Lists are immutable
  val oneTwoThree = List(1, 2, 3)

  // List concatenation
  val oneTwo = List(1, 2)
  val threeFour = List(3, 4)
  val oneTwoThreeFour = oneTwo ::: threeFour
  println(oneTwo + " and " + threeFour + " were not mutated!")
  println("Thus " + oneTwoThreeFour + " is a new List[Int]!")

  // Prepend element to a list
  val twoThree = List(2, 3)
  val oneTwoThree2 = 1 :: twoThree
  println("Prepending 1 to " + oneTwoThree2)

  // Nil is an empty list
  val newList = 1 :: 2 :: 3 :: Nil
  println("List built with Nil: " + newList)

  // Tuples
  val pair = (99, "Luftballons")
  println(pair._1)
  println(pair._2)

  // Sets
  var jetSet = Set("Boeing", "Airbus")
  jetSet += "Lear"
  println(jetSet.contains("Cessna"))
}
