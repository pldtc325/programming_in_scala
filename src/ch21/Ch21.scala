package ch21

import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.JButton


/**
  * A java Swing example, use inner class to represent a callback
  * which is tedious, lengthy
  */
object ImplicitConversionExample extends App {
  val button = new JButton
  button.addActionListener(
    new ActionListener {
      def actionPerformed(event: ActionEvent) {
        println("pressed!")
      }
    } )


  /**
    * scala implicit conversion, turn a function:ActionEvent => Unit
    * to ActionListener
    * @param f
    * @return
    */
  implicit def function2ActionListener(f:ActionEvent => Unit) = {
    new ActionListener {
      override def actionPerformed(e: ActionEvent): Unit = f(e)
    }
  }

  /**
    * scala-way of callback, much more concise
    * need function -> ActionListener implicit conversion to work
    */
  button.addActionListener( // Type mismatch!
    (_: ActionEvent) => println("pressed!")
  )

  class A
  class B
  implicit def A2B(a:A) = new B()
  val someB:B = new A() // would have been a type mismatch if there was not A2B

  class C
  class D
  implicit def D2C(s:D):C = new C()
  def passMeC(c:C) = {}
  passMeC(new D()) // would have been a type mismatch if there was not D2C

  class E {def m() {}}
  class F
  implicit  def F2E(f:F) = new E()
  val f = new F()
  f.m() // would have been a type mismatch if there was not F2E

}

object Ch21 {

}
