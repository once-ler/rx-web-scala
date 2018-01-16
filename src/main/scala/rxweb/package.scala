import skinny.micro.ActionResult

import scala.concurrent.{ExecutionContext, Future}

package object rxweb {
  type rxweb$NextAction = Any => Unit
  type rxweb$Context = ExecutionContext => Future[ActionResult]
  type rxweb$WebAction = (rxweb$Context, rxweb$NextAction) => Unit
  type rxweb$FilterFunc[A>:rxweb$Task] = A => Boolean
  type rxweb$SubscribeFunc = rxweb$Task => Unit
  type rxweb$PromiseFunc = rxweb$Task => Future[rxweb$Task]
  type rxweb$OnNext[A>:rxweb$Task] = A => Unit
  type rxweb$OnError = Throwable => Unit
  type rxweb$OnCompleted = () => Unit
}
