package problem_along_the_journey

/**
  * can not return MyNil even though MyNil is a subtype of MyList[Nothing]
  *
  * making MyNil a subtype of MyList[Nothing] doesn't necessarily guarantee that
  * MyNil is a subtype of MyList[U]
  *
  * to make MyNil a subtype of MyList[U], you need
  * make your List covariant, MyList[+T]
  * make MyNil a subtype of MyList[Nothing]
  *
  * for Nothing is designed to be the subtype of all classes, once your type X is covariant
  * X[Nothing] is guaranteed to be the subtype of X[U]
  */
case object MyNil extends MyList[Nothing] {
  override def isEmpty: Boolean = true
  override def head = throw new Exception("head not supported")
  override def tail = throw new Exception("tail not supported")
}
case class ::[T](val head:T, var ttail:MyList[T]) extends MyList[T] {
  override def isEmpty: Boolean = false
  override def tail = ttail
}
abstract class MyList[+T] { // only if you use +T instead of T
  def isEmpty: Boolean
  def head: T
  def tail: MyList[T]
  def map[U](f:T => U):MyList[U] = {
    this match {
      case MyNil => MyNil // problem here
    }
  }
}

object CanNotReturnMyNil {

}
