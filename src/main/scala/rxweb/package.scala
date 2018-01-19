import scala.concurrent.{ExecutionContext, Future}

package org {
  package object rxweb {
    type rxweb$NextAction = Any => Unit
    type rxweb$ExecutionContext = ExecutionContext => Future[_]
    type rxweb$WebAction = (rxweb$ExecutionContext, rxweb$NextAction) => Unit
    type rxweb$FilterFunc[A >: rxweb$Task] = A => Boolean
    type rxweb$SubscribeFunc = rxweb$Task => Unit
    type rxweb$PromiseFunc = rxweb$Task => Future[rxweb$Task]
    type rxweb$OnNext[A >: rxweb$Task] = A => Unit
    type rxweb$OnError = Throwable => Unit
    type rxweb$OnCompleted = () => Unit
  }
}
