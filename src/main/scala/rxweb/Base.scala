package rxweb

trait rxweb$Base {
  val sub = new rxweb$Subject
  var middlewares = new scala.collection.mutable.MutableList[rxweb$Middleware]()
  var routes = new scala.collection.mutable.MutableList[rxweb$Route]()

  def next(value: rxweb$Task) { sub.next(value) }

  def makeObserversAndSubscribeFromMiddlewares {
    for (m <- middlewares) {
      val o = new rxweb$Observer(sub observable, m.filterFunc, m.promiseFunc)
      o.subscribe(m.subscribeFunc)
    }
  }
}
