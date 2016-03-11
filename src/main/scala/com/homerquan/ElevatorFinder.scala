package com.homerquan

/**
  * The core algorithm
  *
  * The problem can be abstract as a directed graph, and find all paths from A to B
  * Build the adjacency matrix: vertex is the location (floor + time), edge is the movement of elevator (from location A to location B)
  * Then using a graph search, such as DFS to solve it
  *
  * Created by homer on 3/10/16.
  */
class ElevatorFinder(elevators:Map[Char,Elevator]) {

  // Use a map to lookup location to [elevator]
  // This will help to lookup by location
  // e.g., Location(1,2) -> List[A]
  val locationMap = elevators.map{
    case (k, v) => v.states.zipWithIndex.map{{
      case (time, floor) => (Location(time,floor),k)
    }}
  }.flatten.groupBy(_._1).map{case (k,v) => (k,v.map(_._2))}

  // Create the adjacency matrix to present the graph
  // Location A -> [Location B, Location C]
  var adjMatrix = scala.collection.mutable.HashMap.empty[Location,Set[Location]]
  elevators.map{
    case (k,v) => v.states.zipWithIndex
  }.map(_.sliding(2,1).foreach{
    pair => {
      val from = Location(pair.head._1,pair.head._2)
      val to = Location(pair.last._1,pair.last._2)
      if (adjMatrix.getOrElse(from,Map.empty).isEmpty) {
        adjMatrix += (from -> Set(to))
      } else {
        adjMatrix.put(from, adjMatrix.getOrElse(from,Set.empty) + to)
      }
    }
  })

  /**
    * Using DFS to solve the problem
    * Using tail-Recursion to make it resource efficient
    * One possible improvement is selecting DFS BFS according to sparse of matrix
    */
  def findSolution(from:Location,to:Location):Set[String] = {
    var stack = new scala.collection.mutable.Stack[Location]
    val fromSet=locationMap.getOrElse(from,List.empty[Char]).toSet
    val toSet=locationMap.getOrElse(to,List.empty[Char]).toSet
    val intersection=fromSet.intersect(toSet).map(_.toString)
    var result = Set.empty[String]
    if(from==to || elevators.size==0 || from.time >= to.time) {
      result = Set.empty
    } else if(fromSet.isEmpty || toSet.isEmpty) {
      result = Set.empty
    } else if(!intersection.isEmpty) {
      result = intersection
    } else {
      stack.pushAll(adjMatrix.getOrElse(from, Set.empty))
      //Then pop a location X and create solution of src to X, X to dest
      while (!stack.isEmpty) {
        val interLocation = stack.pop()
        result = result ++ combine(findSolution(from, interLocation), findSolution(interLocation, to))
      }
    }
    result
  }

  /**
    * Combine ['A','B'] ['C'] to ['AB','AC']
    */
  private def combine(head:Set[String],tail:Set[String]):Set[String] = {
    var result = Set.empty[String]
    head.foreach(x=>{
      tail.foreach(y=>{
        val mutation = x+y
        result = result + mutation
      })
    })
    result
  }

}
