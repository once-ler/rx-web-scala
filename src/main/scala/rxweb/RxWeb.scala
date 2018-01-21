package org.rxweb

trait rxweb$Task {
  var typeName: String
  var data: Any
  var next: rxweb$NextAction
  // var context: rxweb$ExecutionContext
}

class rxweb$Middleware[A<:rxweb$Task](var filterFunc: rxweb$FilterFunc[A], var subscribeFunc: rxweb$OnNext[A], var promiseFunc: Option[rxweb$PromiseFunc[A]]) {
  def this(filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$OnNext[A]) {
    this(filterFunc, subscribeFunc, None)
  }
}

object rxweb$Middleware {
  def apply[A<:rxweb$Task](filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$OnNext[A], promiseFunc: Option[rxweb$PromiseFunc[A]])
    = new rxweb$Middleware[A](filterFunc, subscribeFunc, promiseFunc)
  def apply[A<:rxweb$Task](filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$OnNext[A])
    = new rxweb$Middleware[A](filterFunc, subscribeFunc, None)
}

class rxweb$Route(var expression: String, var action: rxweb$WebAction)

object rxweb$Route {
  def apply(expression: String, action: rxweb$WebAction) = new rxweb$Route(expression, action)
}
