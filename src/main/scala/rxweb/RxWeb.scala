package rxweb

trait rxweb$Task {
  type: string
  data: Any
  next: rxweb$NextAction
  request: rxweb$Request
  response: rxweb$Response
}
