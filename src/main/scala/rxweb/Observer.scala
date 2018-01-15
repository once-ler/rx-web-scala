package rxweb

import rx.lang.scala._
import rxweb.{rxweb$FilterFunc, rxweb$PromiseFunc, rxweb$OnNext, rxweb$OnError, rxweb$OnCompleted}

class rxweb$Observer[A>:rxweb$Task](o: Observable[A], filterFunc: rxweb$FilterFunc[A], promiseFunc: Option[rxweb$PromiseFunc]) {
  private val o$ = o filter filterFunc

  def observable = o$

  def subscribe(onNext: rxweb$OnNext[A]) = o$.subscribe(onNext)

  def subscribe(onNext: rxweb$OnNext[A], onError: rxweb$OnError, onCompleted: rxweb$OnCompleted) {
    o$.subscribe(onNext, onError, onCompleted)
  }
}

