package com.homerquan

import org.specs2.mutable.Specification

import scala.io.Source

class ElevatorFinderSpec extends Specification {

  val testCase = Helper.loadElevators(Source.fromURL(getClass.getResource("/states_example.txt")).getLines.toList)

  "An ElevatorFinder" should {
    "return a solution if direct reachable" in {
      new ElevatorFinder(testCase).findSolution(Location(2,0),Location(1,1)) must not beEmpty
    }
  }

  "An ElevatorFinder" should {
    "return a solution if keep take A" in {
      val result=new ElevatorFinder(testCase).findSolution(Location(1,0),Location(5,2))
      result.map(println)
      result must not beEmpty
    }
  }

  "An ElevatorFinder" should {
    "return a solution if rally C,D" in {
      val result=new ElevatorFinder(testCase).findSolution(Location(2,0),Location(4,2))
      result.map(println)
      result must not beEmpty
    }
  }

  "An ElevatorFinder" should {
    "return no solution if unreachable" in {
      val result=new ElevatorFinder(testCase).findSolution(Location(1,0),Location(4,3))
      result.map(println)
      result must beEmpty
    }
  }
}
