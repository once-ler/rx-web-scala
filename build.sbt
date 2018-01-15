import Dependencies._

lazy val skinnyMicroVersion = "1.3.+"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.eztier",
      scalaVersion := "2.12.4",
      version      := "0.1.3"
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

lazy val commonSettings = Seq(
  version := "0.1.3",
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