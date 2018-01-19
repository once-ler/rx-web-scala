package org.rxweb

object Index {
  def main(args: Array[String]): Unit = {
    val rxServer = SkinnyServer();

    val m0 = rxweb$Middleware[WebTask]("/some/path", t => true, t => {})
    val m1 = rxweb$Middleware[WebTask]("/another/path", t => true, t => {})
    val m2 = rxweb$Middleware[WebTask]("/yet/another/path", t => true, t => {})

    rxServer.middlewares += (m0, m1, m2)
  }
}
