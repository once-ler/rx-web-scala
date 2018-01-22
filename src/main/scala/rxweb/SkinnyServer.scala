package org.rxweb

import skinny.micro.{AsyncWebApp, WebServer}
import skinny.micro.context.SkinnyContext
import skinny.micro.contrib.json4s.JSONSupport

trait ShadowSkinny {
  var server: SkinnyServer
  var respond: Any => Unit
}

class WebTask(var typeName: String, var server: SkinnyServer, var respond: Any => Unit, anyData: Option[Any] = None, nextFunc: Option[rxweb$NextAction] = None) extends rxweb$Task with ShadowSkinny {
  def this(typeName: String, server: SkinnyServer, respond: Any => Unit, data: Any) = { this(typeName, server, respond, Some(data), None) }

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
  def apply(typeName: String, server: SkinnyServer, respond: Any => Unit): WebTask = new WebTask(typeName, server, respond)
}

object Middleware0 {
  type $renderResponse = Any => Unit

  def test(t: Any, sub: rxweb$Subject[WebTask], respond: $renderResponse, server: SkinnyServer)(implicit ctx: SkinnyContext) {
    val resp = server.toJSONString(Map("foo" -> "bar"))
    server.contentType = "application/json"
    sub.next(WebTask("b", server, respond))
    // ctx.response.setStatus((200))

    respond(resp)
  }
}

class SkinnyServer extends AsyncWebApp with JSONSupport with rxweb$Base[WebTask] {

  def apply: SkinnyServer = new SkinnyServer

  def test2()(implicit ctx: SkinnyContext): Unit = {
    sub.next(WebTask("a", this, renderResponse))
    Middleware0.test(1, sub, renderResponse, this)
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
