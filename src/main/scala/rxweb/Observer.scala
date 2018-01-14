package rxweb

import rx.lang.scala.{Observable}
import rxweb.{rxweb$FilterFunc, rxweb$PromiseFunc, rxweb$OnNext, rxweb$OnError, rxweb$OnCompleted}

class rxweb$Observer[A>:rxweb$Task](o: Observable[A], filterFunc: rxweb$FilterFunc, promiseFunc: Option[rxweb$PromiseFunc]) {
  private var observer = new Observable[A]

  private val o$ = o.filter(filterFunc)

  if (rxweb$PromiseFunc.isEmpty) observer = o$
  else {
    observer = o$
      .flatMap(task: A => {
        // Await on the Future[rxweb$Task]
      })
  }

  def observable = observer

  def subscribe(onNext: rxweb$OnNext, onError: Option[rxweb$OnError], onCompleted: Option[rxweb$OnCompleted]) {
    observer.subscribe(onNext, onError, onCompleted)
  }
}

