package rxweb

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

class rxweb$Route[A>:rxweb$WebAction](var expression: String, var action: A)

