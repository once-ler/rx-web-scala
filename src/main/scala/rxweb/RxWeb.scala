package rxweb

import skinny.micro._

type rxweb$NextAction = Any => Unit

type rxweb$Context = implicit ExecutionContext => Future[ActionResult]

type rxweb$WebAction = (rxweb$Context, rxweb$NextAction) => Unit

trait rxweb$Task {
  type: String
  data: Any
  next: rxweb$NextAction
  context: rxweb$Context
}

type rxweb$FilterFunc = rxweb$Task => Boolean;

type rxweb$SubscribeFunc = rxweb$Task => Unit;

type rxweb$PromiseFunc = rxweb$Task => Future[rxweb$Task];

class rxweb$Middleware(type: String, filterFunc: rxweb$FilterFunc, subscribeFunc: rxweb$SubscribeFunc, promiseFunc: rxweb$PromiseFunc) {
  def this(type: String, filterFunc: rxweb$FilterFunc, subscribeFunc: rxweb$SubscribeFunc)
}

class rxweb$Route(expression: String, action rxweb$WebAction)
