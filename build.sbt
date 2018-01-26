import Dependencies._

lazy val skinnyMicroVersion = "1.3.+"
lazy val rxScalaVersion = "0.26.5"

/*
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.eztier",
      scalaVersion := "2.12.4",
      version      := "0.1.9"
    )),
    name := "rx-web-scala",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "io.reactivex" %% "rxscala" % "0.26.5",
      "org.skinny-framework" %% "skinny-micro"             % skinnyMicroVersion,
      "org.skinny-framework" %% "skinny-micro-json4s"      % skinnyMicroVersion,
      "org.skinny-framework" %% "skinny-micro-server"      % skinnyMicroVersion
    )
  )
*/

lazy val commonSettings = Seq(
  version := "0.1.10",
  organization := "com.eztier",
  scalaVersion := "2.12.4",
  test in assembly := {}
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    name := "rx-web-scala",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "io.reactivex" %% "rxscala" % rxScalaVersion
    ),
    assemblyJarName in assembly := "rx-web-scala.jar"
  )

// sbt "project app" package

lazy val app = (project in file("app")).
  settings(commonSettings: _*).
  settings(
    name := "app",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "org.skinny-framework" %% "skinny-micro"             % skinnyMicroVersion,
      "org.skinny-framework" %% "skinny-micro-json4s"      % skinnyMicroVersion,
      "org.skinny-framework" %% "skinny-micro-server"      % skinnyMicroVersion
    ),
    mainClass in assembly := Some("org.rxweb.Main")
  ).
  dependsOn(root)

lazy val utils = (project in file("utils")).
  settings(commonSettings: _*).
  settings(
    name := "utils",
    assemblyJarName in assembly := "utils.jar"
  )