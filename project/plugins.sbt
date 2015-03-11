resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.8")

// scalajs
addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.1")

addSbtPlugin("com.vmunier" % "sbt-play-scalajs" % "0.2.3")

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % "1.0.0")
