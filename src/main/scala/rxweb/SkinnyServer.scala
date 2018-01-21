package org.rxweb

import skinny.micro.{AsyncWebApp, WebServer}
import skinny.micro.context.SkinnyContext
import skinny.micro.contrib.json4s.JSONSupport

import scala.concurrent.Future

class WebTask(var typeName: String, anyData: Option[Any] = None, nextFunc: Option[rxweb$NextAction] = None) extends rxweb$Task {
  def this(typeName: String, data: Any) = { this(typeName, Some(data), None) }

  override var data: Any = anyData match {
    case Some(f) => f
    case None => ""
  }

  override var next: rxweb$NextAction = nextFunc match {
    case Some(f) => f
    case None => _ => Unit
  }
}

object WebTask {
  def apply(typeName: String): WebTask = new WebTask(typeName)
}

object Middleware0 {
  type $renderResponseBody = Any => Unit

  def test(t: Any, sub: rxweb$Subject[WebTask], respond: $renderResponseBody)(implicit ctx: SkinnyContext) {
    sub.next(WebTask("b"))

    respond("ABCDEFGHIJK")
  }
}

class SkinnyServer extends AsyncWebApp with JSONSupport with rxweb$Base[WebTask] {

  def apply: SkinnyServer = new SkinnyServer

  def test2()(implicit ctx: SkinnyContext): Unit = {
    sub.next(WebTask("a"))
    Middleware0.test(1, sub, renderResponseBody)
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
