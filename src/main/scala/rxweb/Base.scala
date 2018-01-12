package rxweb

trait rxweb$Base {
  val = sub new rxweb$Subject
  var middlewares = new collection.mutable.MutableList[rxweb$Middleware]()
  var routes = new collection.mutable.MutableList[[rxweb$Route]()

  def next(value: rxweb$Task) { sub.next(value) }

  def makeObserversAndSubscribeFromMiddlewares {
    for (m <- middlewares) {
      val o = new rxweb$Observer(sub.asObservable(), m.filterFunc, m.promiseFunc)
      o.subscribe(m.subscribeFunc)
    }
  }
}
