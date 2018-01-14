package rxweb

import scala.concurrent.{ExecutionContext, Future}


trait rxweb$Task {
  typeName: String
  data: Any
  next: rxweb$NextAction
  context: rxweb$Context
}

class rxweb$Middleware(typeName: String, filterFunc: rxweb$FilterFunc, subscribeFunc: rxweb$SubscribeFunc, promiseFunc: Option[rxweb$PromiseFunc]) {
  def this(typeName: String, filterFunc: rxweb$FilterFunc, subscribeFunc: rxweb$SubscribeFunc) = {}
}

class rxweb$Route[A>:rxweb$WebAction](expression: String, action: A)

package object rxweb {
  type rxweb$NextAction = Any => Unit
  type rxweb$Context = ExecutionContext => Future[ActionResult]
  type rxweb$WebAction = (rxweb$Context, rxweb$NextAction) => Unit
  type rxweb$FilterFunc = rxweb$Task => Boolean
  type rxweb$SubscribeFunc = rxweb$Task => Unit
  type rxweb$PromiseFunc = rxweb$Task => Future[rxweb$Task]
  type rxweb$OnNext = rxweb$Task => Unit
  type rxweb$OnError = Any => Unit
  type rxweb$OnCompleted = () => Unit
}
