package org.rxweb

import scala.collection.mutable._

trait rxweb$Base[A<:rxweb$Task] {
  val sub = rxweb$Subject[A]()
  var rxweb$Middlewares = ArrayBuffer[rxweb$Middleware[A]]()
  var rxweb$Routes = ArrayBuffer[rxweb$Route]()

  def next(value: A) { sub.next(value) }

  def makeObserversAndSubscribeFromMiddlewares {
    for (m <- rxweb$Middlewares) {
      val o = rxweb$Observer[A](sub.observable, m.filterFunc, m.subscribeFunc, m.promiseFunc)
      o.subscribe(m.subscribeFunc)
    }
  }

  def applyRoutes: Unit
}
