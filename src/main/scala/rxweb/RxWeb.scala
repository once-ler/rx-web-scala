package org.rxweb

trait rxweb$Task {
  var typeName: String
  var data: Any
  var next: rxweb$NextAction
  var context: rxweb$ExecutionContext
}

class rxweb$Middleware[A>:rxweb$Task](var typeName: String, var filterFunc: rxweb$FilterFunc[A], var subscribeFunc: rxweb$SubscribeFunc, var promiseFunc: Option[rxweb$PromiseFunc]) {
  def this(typeName: String, filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$SubscribeFunc) {
    this(typeName, filterFunc, subscribeFunc)
  }
}

object rxweb$Middleware {
  def apply[A>:rxweb$Task](typeName: String, filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$SubscribeFunc, promiseFunc: Option[rxweb$PromiseFunc])
    = new rxweb$Middleware[A](typeName, filterFunc, subscribeFunc, promiseFunc)
  def apply[A>:rxweb$Task](typeName: String, filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$SubscribeFunc)
    = new rxweb$Middleware[A](typeName, filterFunc, subscribeFunc)
}

class rxweb$Route[A>:rxweb$WebAction](var expression: String, var action: A)

object rxweb$Route {
  def apply[A>:rxweb$WebAction](expression: String, action: A) = new rxweb$Route[A](expression, action)
}
