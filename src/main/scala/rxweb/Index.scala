package org.rxweb

import scala.concurrent.ExecutionContext
import scala.concurrent.{Future}

object Index extends App {
  // def main(args: Array[String]): Unit = {
    val rxServer = SkinnyServer()

    val m0 = rxweb$Middleware[WebTask]((t: WebTask) => true, (t: WebTask) => {})
    val m1 = rxweb$Middleware[WebTask]((t: WebTask) => true, (t: WebTask) => {})
    val m2 = rxweb$Middleware[WebTask]((t: WebTask) => true, (t: WebTask) => {})

    val a = (t: String) => "a"

    def handler0(t: WebTask)(implicit ctx: ExecutionContext): Future[Seq[String]] = Future {
      Seq.empty
    }

    val r0 = rxweb$Route("/a/path", _ => {})

    rxServer.rxweb$Middlewares += (m0, m1, m2)
    rxServer.rxweb$Routes += (r0)

    rxServer.start

    println("Started...")
  // }
}
