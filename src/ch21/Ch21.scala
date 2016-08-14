package ch21


abstract class ZList[+T] {
  def isEmpty: Boolean

  def tail: ZList[T]

  def head: T

  def length: Int = if (isEmpty) 0 else 1 + tail.length

  /**
    * note that this is recursive function
    * both 'tail' in the function and n:Int parameter change in each call
    */
  def drop(n: Int): ZList[T] =
  if (isEmpty) ZNil
  else if (n <= 0) this
  else tail.drop(n - 1) // for each call, tail changes, the sequence would be {tail, tail.tail, ...}

  def map[U](f: T => U): ZList[U] = {
    if (isEmpty) ZNil
    else XX(f(head), tail.map(f))
  }

  def ::[U >: T](x: U): ZList[U] = {
    XX(x, this)
  }

}

object ZNil extends ZList[Nothing] {
  override def isEmpty: Boolean = true

  def tail = throw new NoSuchElementException("tail of empty list")

  def head = throw new NoSuchElementException("head of empty list")
}

final case class XX[T](h: T, t: ZList[T]) extends ZList[T] {
  def isEmpty: Boolean = false

  def tail: ZList[T] = t

  def head: T = h
}

class Fruit

class Apple extends Fruit

class Orange extends Fruit

object Ch21 extends App {
  //val l:ZList[Int] = XX(1, XX(2, ZNil))
  //val r = l.drop(2)
  //println("r:" + r.length)

  val apples = new Apple :: ZNil
  val o: Orange = new Orange
  val f1 = new Fruit
  val f = o :: apples.::(f1)
}
