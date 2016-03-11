package com.homerquan

/**
  * Customize types
  * Created by homer on 3/10/16.
  */
final case class Location(floor:Int,time:Int)
final case class Link(from:Location,to:Set[Location])
final case class Elevator(id:Char,states:List[Int])

