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

  /**
    * implicit parameter
    */
  case class MuchMoney()
  case class LoveYourJob()
  implicit val loveYourJob = new LoveYourJob
  def beingHappy(muchMoney: MuchMoney)(implicit loveYourJob: LoveYourJob) { println(muchMoney + " " + loveYourJob)}
  beingHappy(MuchMoney()) // print MuchMoney() LoveYourJob()

  /**
    * try to provide information on the type
    * that is given by previous parameter
    */
  trait Detector[T] { def detect(t:T):Boolean}
  implicit object DetectorInt extends Detector[Int] {
    override def detect(t: Int): Boolean = t > 100000
  }
  def Arrest[T](suspects:List[T])(implicit detector: Detector[T]): List[T] = {
    suspects filter(detector detect _)
  }
  println(Arrest(List(1,100,10000000))) // arrest List(10000000)


}

object Ch21 {

}
