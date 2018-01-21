package org.rxweb

import scala.concurrent.ExecutionContext
import scala.concurrent.{Future}

object Index extends App {
  val rxServer = SkinnyServer()

  val m0 = rxweb$Middleware[WebTask]((t: WebTask) => t.typeName == "a", (t: WebTask) => println(t.typeName) )
  val m1 = rxweb$Middleware[WebTask]((t: WebTask) => t.typeName == "b", (t: WebTask) => println(t.typeName) )
  val m2 = rxweb$Middleware[WebTask]((t: WebTask) => t.typeName == "c", (t: WebTask) => println(t.typeName) )

  val a = (t: String) => "a"

  def handler0(t: WebTask)(implicit ctx: ExecutionContext): Future[Seq[String]] = Future {
    Seq.empty
  }

  val r0 = rxweb$Route("/a/path", _ => {})

  rxServer.rxweb$Middlewares += (m0, m1, m2)
  rxServer.rxweb$Routes += (r0)

  rxServer.start

  println("Started...")
}
