package ch23

/**
  * for comprehension makes hard read code easy to read
  */

/**
  * hard read code example
  */
case class Person(name: String,
                  isMale: Boolean,
                  children: Person*)

object HardReadCode extends App {
  val lara = Person("Lara", isMale = false)
  val bob = Person("Bob", isMale = true)
  val julie = Person("Julie", isMale = false, lara, bob)
  val persons = List(lara, bob, julie)

  persons filter (p => !p.isMale) flatMap (p =>
    p.children map (c => (p.name, c.name)))
}

object Ch23 {

}
