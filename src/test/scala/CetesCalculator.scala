package dev.gonz

import org.scalatest.funsuite.AnyFunSuite

import scala.collection.mutable

class CetesCalculator extends AnyFunSuite {

  test("Interés compuesto mensual") {
    // monthly: 5.49
    // 3 months: 5.87
    // 6 months: 6.30
    // year: 6.95

    val years = 4
    val initialQuantity = 200_000.0
    val monthlyMoney = 15_000

    val interest = new mutable.HashMap[Int, Double]()

    val res = (0 until (years * 12)).map(_.toDouble).scan(initialQuantity) { (currentTotal, index) =>
      val tax = (0.97 / 100) / 12.0
      val interestRate = (5.49 / 100) / 12.0

      val monthlyQuantity = if (index == 0) currentTotal else currentTotal + monthlyMoney
      val newTotal = monthlyQuantity * (1 + interestRate - tax)

      interest(index.toInt) = newTotal - monthlyQuantity
      newTotal
    }

    println(res.toList.map(i => f"$i%.2f").mkString(sep = "\n"), "\n")
    println(interest.toList.map(i => f"Month: ${i._1+1}%d => ${i._2}%.2f").mkString(sep = "\n"))
    println(f"Total interest: ${interest.values.sum}%.2f")
  }

  test("Interes compuesto anual fijo") {
    // monthly: 5.49
    // 3 months: 5.87
    // 6 months: 6.30
    // year: 6.95

    val years = 5
    val quantity = 200_000.0

    val res = (0 until years).map(_.toDouble).scan(quantity) { (item, total) =>
      val tax = 0.97 / 100
      val interestRate = 6.95 / 100
      total + item * (1 + interestRate - tax)
    }

    println(res.toList.map(i => f"$i%.2f").mkString(sep = "\n"))
    println()
    println(res.toList.map(i => f"${i - quantity}%.2f").mkString(sep = "\n"))
  }
}
