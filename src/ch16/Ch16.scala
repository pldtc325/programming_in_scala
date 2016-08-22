package ch16

object CreatingList extends App {
  // empty list
  val emptyList = List.empty // Nil

  // a list of String
  val xs = List("a", "quick", "brown")

  // using ::, List("quick")::("a")
  // note that under the hood, :: is in fact a type < List, just like Nil, to see this:
  // def ::[B >: A] (x: B): List[B] =
  //   new scala.collection.immutable.::(x, this)
  val ys = "a" :: List("quick")
}

object BasicOperation extends App {
  val xs = List("hello", "world")
  /**
    * def isEmpty: Boolean
    * def head: A
    * def tail: List[A]
    *
    * these operations all called 'Basic' because all the others can be expressed by
    * the some combinations of them
    */
  xs.isEmpty
  xs.head
  xs.tail
  case object MyNil extends MyList[Nothing] {
    override def isEmpty: Boolean = true
    override def head = throw new Exception("head not supported")
    override def tail = throw new Exception("tail not supported")
  }

  case class ::[T](val head:T, var ttail:MyList[T]) extends MyList[T] {
    override def isEmpty: Boolean = false
    override def tail = ttail
  }

  abstract class MyList[+T] {
    def isEmpty: Boolean
    def head: T
    def tail: MyList[T]
    def ::[U >: T](x:U) = ch16.BasicOperation.::(x, this)

    /**
      * get first n elements of the list
      * this function only use head, tail, Nil and ::
      * @param n
      * @return
      */
    def take(n:Int):MyList[T] = {
      this match {
        case MyNil => MyNil
        case h :: t if n > 0 => h :: t.take(n - 1)
        case _ =>  MyNil
      }
    }
    override def toString = {
      this match {
        case MyNil => ""
        case h :: t => h.toString + " " + t.toString
      }
    }
  }

  val myList = ::(2, ::(1, MyNil))
  println(myList.take(2))
  println(myList.take(1))
}

object Ch16 {

}
