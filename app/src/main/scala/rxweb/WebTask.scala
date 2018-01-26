package org.rxweb

class WebTask(var typeName: String, anyData: Option[Any] = None, var server: SkinnyServer, var respond: Any => Unit, nextFunc: Option[rxweb$NextAction] = None) extends rxweb$Task with ShadowSkinny {
  def this(typeName: String, data: Any, server: SkinnyServer, respond: Any => Unit) = { this(typeName, Some(data), server, respond, None) }

  override var data: Any = anyData match {
    case Some(f) => f
    case None => ""
  }

  override var next: rxweb$NextAction = nextFunc match {
    case Some(f) => f
    case None => _ => server.sub.next(_)
  }
}

object WebTask {
  def apply(typeName: String, data: Any, server: SkinnyServer, respond: Any => Unit): WebTask = new WebTask(typeName, data, server, respond)
}

