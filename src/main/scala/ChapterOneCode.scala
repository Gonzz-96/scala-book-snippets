package dev.gonz

import java.math.BigInteger

object ChapterOneCode extends App {

  val javaResult = javaFactorial(BigInteger.valueOf(30L))
  val scalaResult = scalaFactorial(30)

  println(s"Factorial: $javaResult")
  println(s"Scala Factorial: $scalaResult")

  def javaFactorial(x: BigInteger): BigInteger =
    if (x == BigInteger.ZERO)
      BigInteger.ONE
    else
      x.multiply(javaFactorial(x.subtract(BigInteger.ONE)))

  def scalaFactorial(x: BigInt): BigInt =
    if (x == 0) 1
    else x * scalaFactorial(x - 1)
}
