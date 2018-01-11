import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.eztier",
      scalaVersion := "2.12.4",
      version      := "0.1.0"
    )),
    name := "rx-web-scala",
    libraryDependencies += scalaTest % Test
    libraryDependencies += "io.reactivex" %% "rxscala" % "x.y.z"
  )

lazy val commonSettings = Seq(
  version := "0.1.0",
  organization := "com.eztier",
  scalaVersion := "2.12.4",
  test in assembly := {}
)

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
    mainClass in assembly := Some("com.eztier.Main")
  )

lazy val utils = (project in file("utils")).
  settings(commonSettings: _*).
  settings(
    assemblyJarName in assembly := "utils.jar"
  )