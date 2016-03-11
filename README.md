# Elevator
The goal is to write a program which satisfies the following spec and email back the source code for it once you are done.

You can ask questions.

Implement in scala 2.11.x only using standard libraries. Solution should compile and run on a box that includes sbt 0.13.5+. Expected time to completion should be up to about 3 hours. If you reach 4 hours, please finish what you're working on and send in whatever code you have.

Priorities:
 1. Correctness
 1. Implementation time
 1. Elegance of code
 1. Anything else you think we might care about in a coding problem.

The following diagram is an elevator state--- the state of an elevator system at a given point in time. Dots represent an elevator shaft. Captial letters represent an elevator. For example, elevator A is on the 1st floor (1-indexed), and the following elevator state has 5 floors:

```
xxxxxxxxxxxx
xx.x.x.x.xxx
xx.x.x.x.xxx
xx.xBx.x.xxx
xx.x.xCx.xxx
xxAx.x.xDxxx
```

Input: Take a series of elevator states from a file, representing successive states of the elevator system from t = {1,2,3...} Each elevator state will be separated by an empty line. Valid elevators are specified by the characters A-Z. Lines may be broken by \n or by \r\n (unix and dos line endings respectively).

An elevator system with elevator states for t=1, 2, and 3 might look like:

```
xxxxxxxxxxxx
xx.x.x.x.xxx
xx.x.x.x.xxx
xx.xBx.x.xxx
xx.x.xCx.xxx
xxAx.x.xDxxx

xxxxxxxxxxxx
xx.x.x.x.xxx
xx.xBx.x.xxx
xx.x.x.x.xxx
xxAx.x.x.xxx
xx.x.xCxDxxx

xxxxxxxxxxxx
xxAx.x.x.xxx
xx.x.x.xDxxx
xx.xBx.x.xxx
xx.x.xCx.xxx
xx.x.x.x.xxx
```

Write a command line program. It should take exactly three args as specified:

```
sbt "run <elevator system filename> <starting elevator> <final destination>"
```

Where `<final destination>` is specified as `<floor>-<time>`, e.g. 3-2, indicating that the final destination is the 3rd floor at time t=2.

At t=1, you begin in the elevator specified by `<starting elevator>`, e.g. "A".

In a given time period, you can board an elevator or stay on the elevator you are on. In any time period, you can only board any elevator on the same floor as the elevator that you are currently on (including t=1).
The goal is to find a series of legal actions that lead to you being at the final destination--- the right floor at the right time.
The set of actions should be written to stdout as a series of actions in a single string. An action is defined as the elevator you board (or stay on) at time t, eg:

```
AABDD
```

Indicates that you were on (or switched to) A at t=1, stayed on A at t=2, switched to B at t=3, and switched to D at t=4, and stayed on D at t=5. If the final destination time is T, your actions should have T-1 actions in it (the elevator you are on at time T-1 must transition to the correct floor at time T for your solution to be valid). The elevator at time T should *not* be in the solution. Similarly, the start elevator may not be in the solution; it would only be there if you opted to stay on it at t=1.

If there is a solution, the solution string is the *only* thing that should be printed to stdout.

If there is no solution, print something appropriate to stderr, and *nothing* to stdout.


## Solution

### Create two map

1. location->elevation
(Location(5,2),List(A))
(Location(4,1),List(B))
(Location(2,0),List(C))
(Location(3,0),List(B))
(Location(1,1),List(C, D))
(Location(3,2),List(B))
(Location(2,2),List(C))
(Location(4,2),List(D))
(Location(2,1),List(A))
(Location(1,0),List(A, D))

2. adjucency graph
(Location(2,1),Set(Location(5,2)))
(Location(1,0),Set(Location(2,1), Location(1,1)))
(Location(2,0),Set(Location(1,1)))
(Location(1,1),Set(Location(2,2), Location(4,2)))
(Location(4,1),Set(Location(3,2)))
(Location(3,0),Set(Location(4,1)))