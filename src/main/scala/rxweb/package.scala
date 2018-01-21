import scala.concurrent.{ExecutionContext, Future}

package org {
  package object rxweb {
    type rxweb$NextAction = Any => Unit
    type rxweb$ExecutionContext = ExecutionContext => Future[_]
    // type rxweb$WebAction = (rxweb$ExecutionContext, rxweb$NextAction) => Unit
    type rxweb$WebAction = Any => Unit
    type rxweb$FilterFunc[A <: rxweb$Task] = A => Boolean
    // type rxweb$SubscribeFunc[A <: rxweb$Task] = A => Unit
    type rxweb$PromiseFunc[A <: rxweb$Task] = A => Future[A]
    type rxweb$OnNext[A <: rxweb$Task] = A => Unit
    type rxweb$OnError = Throwable => Unit
    type rxweb$OnCompleted = () => Unit
  }
}
