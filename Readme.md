# Poker Hands

## Instructions

A solution for [poker card game](http://codingdojo.org/cgi-bin/index.pl?KataPokerHands). This solution focuses on 
type system and uses ScalaCheck for testing.
 
* **Type System - Soundness and Completeness** - A `sound` jury will never send a guilty person free. A `complete` jury 
will never send an innocent person to jail.
  * [Static Types in Scala Basics](https://twitter.github.io/scala_school/type-basics.html)
 
* **ScalaCheck** - ScalaCheck is a tool for testing Scala and Java programs, based on property specifications and 
automatic test data generation. The basic idea is that you define a property that specifies the behaviour of a method
 or some unit of code, and ScalaCheck checks that the property holds. All test data are generated automatically in a 
 random fashion, so you don't have to worry about any missed cases. 
  * [ScalaCheck github](https://github.com/rickynils/scalacheck/wiki/User-Guide)
  
## Running the project  
You can run it with`sbt test` 
