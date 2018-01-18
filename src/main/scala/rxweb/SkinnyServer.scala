package rxweb

import skinny.micro.AsyncWebApp
import skinny.micro.contrib.json4s.JSONSupport

class WebTask extends rxweb$Task {
  override var next: rxweb$NextAction = _ => {

  }
  override var data: Any = _
  override var context: rxweb$ExecutionContext = _
  override var typeName: String = _
}

class SkinnyServer extends AsyncWebApp with JSONSupport with rxweb$Base[WebTask] {
  def apply: SkinnyServer = new SkinnyServer()

  def applyRoutes {
    for (r <- routes) {
      post(r.expression) { implicit ctx => r.action }
    }
  }

  def start {
    makeObserversAndSubscribeFromMiddlewares
    applyRoutes
  }
}
