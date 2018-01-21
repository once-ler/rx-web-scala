package org.rxweb

import skinny.micro.{AsyncWebApp, WebServer}
import skinny.micro.context.SkinnyContext
import skinny.micro.contrib.json4s.JSONSupport

class WebTask extends rxweb$Task {
  override var next: rxweb$NextAction = _
  override var data: Any = _
  override var context: rxweb$ExecutionContext = _
  override var typeName: String = _
}

object WebTask {
  def apply(): WebTask = new WebTask()
}

class SkinnyServer extends AsyncWebApp with JSONSupport with rxweb$Base[WebTask] {

  def apply: SkinnyServer = new SkinnyServer

  def test(t: Any)(implicit ctx: SkinnyContext) {
    renderResponseBody("ABCDEFG")
  }

  def test2()(implicit ctx: SkinnyContext): Unit = {
    test(1)
  }

  get("/foo") {
    implicit ctx => "bar"
  }

  override def applyRoutes {
    for (r <- rxweb$Routes) {
      // post(r.expression) { implicit ctx => r.action }
      post(r.expression) {
        implicit ctx => {

          test2

          Unit
        }
      }
    }
  }

  def start {
    makeObserversAndSubscribeFromMiddlewares
    applyRoutes
    WebServer.mount(this).port(4567).start()
  }
}

object SkinnyServer {
  def apply(): SkinnyServer = new SkinnyServer
}
