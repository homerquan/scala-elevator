package com.homerquan


/**
  * Created by homer on 3/10/16.
  */
object Helper {

  /**
    * Create each elevator's states in time order
    * Build a maps for quick lookup
    * the elevator name -> states
    * e.g., A -> Elevator(A,List(1, 2, 5))
    */
  def loadElevators(lines:List[String]) = {
    val statesLengh= lines.count(_.trim.isEmpty)+1
    val floorHeight = lines.indexWhere(_.trim.isEmpty)
    val states = lines.sliding(floorHeight,floorHeight+1).toList
    val elevatorIds = states(0).flatMap(_.toCharArray).filter(x => x>='A' && x<='Z').sorted
    val elevators = elevatorIds.map(id=>Elevator(id,states.map(state=>state.reverse.indexWhere(floor=>floor.contains(id))+1)))
    elevatorIds.zip(elevators).toMap
  }


  def loadLocation(input:String) : Location = {
    val floorAndTime=input.split(':')
    Location(floorAndTime(0).toInt,floorAndTime(1).toInt)
  }
}
