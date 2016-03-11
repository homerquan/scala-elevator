package com.homerquan
import scala.io.Source
import scala.util.Properties.{javaVersion, javaVmName, versionString}

/**
  * Lunch point of the APP
  * Created by homer on 3/10/16.
  */
object Main extends App {

  private def echo(msg: String, isErr: Boolean = false) {
    if(!isErr) {
      Console.println(msg)
    } else {
      Console.err.println(msg)
    }
  }
  private def checkFile(name: String): Boolean = {
    new java.io.File(name).exists
  }

  if(args.length<3) {
    echo("missing parameters, please provide 1. file path 2. src 3. dest",true)
    System.exit(1)
  } else {
    val file=args(0)
    val srcStr=args(1)
    val destStr=args(2)
    if(!this.checkFile(file)){
      echo("Can't find the file",true)
      System.exit(1)
    } else {
      try {
        val elevators=Helper.loadElevators(Source.fromFile(file).getLines.toList)
        val src=Helper.loadLocation(srcStr)
        val dest=Helper.loadLocation(destStr)
        val results=new ElevatorFinder(elevators).findSolution(src,dest)
        results.map(println)
      } catch {
        case ex: Exception => echo("Bummer, an exception happened in loading the file or read src/dest." + ex.getMessage.toString,true)
      }
    }
  }

}
