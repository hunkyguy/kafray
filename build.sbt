enablePlugins(JavaServerAppPackaging)

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

mainClass in Compile := Some("org.hank.kafray.Main")
maintainer in Linux := "Hank Wang <hunky.wang@gmail.com>"
packageSummary in Linux := "Restful kafka service, demo of Spray."
packageDescription := "Receive message, check and send it to kafka cluster."

rpmLicense := Some("MIT License")
rpmVendor := "Hank Wang"

// daemonUser := normalizedName.value  // user which will execute the application
daemonUser := "root"    // user which will execute the application
daemonGroup := daemonUser.value     // group which will execute the application

mappings in Universal <++= sourceDirectory map {
    src => {
        // we are using the imora.conf as default application.conf
        // the user can override settings here
        val conf = src / "main" / "resources" / "application.conf"
        Seq(conf -> "conf/application.conf")
    }
}

bashScriptConfigLocation := Some("${app_name}/../conf/" + name.value.toString + ".ini")

javaOptions in Universal ++= Seq(
    // JVM memory tuning
    "-J-Xmx1024m",
    "-J-Xms512m",

    // Since play uses separate pidfile we have to provide it with a proper path
    //s"-Dpidfile.path=/var/run/${packageName.value}/play.pid",
    s"-Dpidfile.path=/var/run/${packageName.value}.pid",

    // ## Use separate configuration file for production environment
    s"-Dconfig.file=/usr/share/${packageName.value}/conf/application.conf"
)

scalacOptions ++= Seq(
    "-unchecked",
    "-deprecation",
    "-Xlint",
    "-Ywarn-dead-code",
    "-language:_",
    "-target:jvm-1.7",
    "-encoding", "UTF-8"
)
