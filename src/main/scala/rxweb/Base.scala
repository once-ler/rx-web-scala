package rxweb

import scala.collection.mutable._

trait rxweb$Base[A>:rxweb$Task] {
  val sub = new rxweb$Subject
  var middlewares = MutableList[rxweb$Middleware[A]]()
  var routes = MutableList[rxweb$Route[A]]()

  def next(value: rxweb$Task) { sub.next(value) }

  def makeObserversAndSubscribeFromMiddlewares {
    for (m <- middlewares) {
      val o = new rxweb$Observer(sub observable, m.filterFunc, m.promiseFunc)
      o.subscribe(m.subscribeFunc)
    }
  }
}
