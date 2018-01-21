package org.rxweb

import rx.lang.scala._

class rxweb$Observer[A<:rxweb$Task](var o: Observable[A], var filterFunc: rxweb$FilterFunc[A], var subscribeFunc: rxweb$OnNext[A], promiseFunc: Option[rxweb$PromiseFunc[A]]) {
  val o$ = o filter filterFunc

  def observable = o$

  def subscribe() = o$.subscribe(subscribeFunc)

  def subscribe(onNext: rxweb$OnNext[A]) = o$.subscribe(onNext)

  def subscribe(onNext: rxweb$OnNext[A], onError: rxweb$OnError, onCompleted: rxweb$OnCompleted) {
    o$.subscribe(onNext, onError, onCompleted)
  }
}

object rxweb$Observer {
  def apply[A<:rxweb$Task](o: Observable[A], filterFunc: rxweb$FilterFunc[A], subscribeFunc: rxweb$OnNext[A], promiseFunc: Option[rxweb$PromiseFunc[A]]) = new rxweb$Observer(o, filterFunc, subscribeFunc, promiseFunc)
}

