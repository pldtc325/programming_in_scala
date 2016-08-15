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


}

object Ch21 {

}
