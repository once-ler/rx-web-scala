package rxweb

type rxweb$OnNext = rxweb$Task => Unit
type rxweb$OnError = Any => Unit
type rxweb$OnCompleted = () => Unit

class rxweb$Observer(o: Observable[rxweb$Task], filterFunc: rxweb$FilterFunc, promiseFunc: Option[rxweb$PromiseFunc]) {
  private var observer = new Observable[rxweb$Task]

  private val o$ = o.filter(filterFunc)

  if (rxweb$PromiseFunc.isEmpty) observer = o$
  else {
    observer = o$
      .mergeMap(task: rxweb$Task => {
        // Await on the Future[rxweb$Task]
      })
  }

  def observable = observer

  def subscribe(rxweb$OnNext, Option[rxweb$OnError], Option[rxweb$OnCompleted]) {
    observer.subscribe(onNext, onError, onCompleted)
  }
}
