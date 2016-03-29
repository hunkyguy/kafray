name := "kafray"

version := "1.0"

scalaVersion := "2.11.7"

resolvers += "spray repo" at "http://repo.spray.io"


libraryDependencies ++= {
    val akkaVersion  = "2.3.6"
    val sprayVersion = "1.3.3"
    Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion,
        "io.spray" %% "spray-can" % sprayVersion,
        "io.spray" %% "spray-routing" % sprayVersion,
        "io.spray" %% "spray-json" % "1.3.2" exclude ("org.scala-lang" , "scala-library"),
        "org.apache.kafka" % "kafka_2.11" % "0.9.0.0"
    )
}

scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-Xlint",
    "-Ywarn-dead-code",
    "-language:_",
    "-target:jvm-1.7",
    "-encoding", "UTF-8"
)
