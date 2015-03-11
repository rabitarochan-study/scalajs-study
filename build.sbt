import sbt.Project.projectToRef

lazy val _scalaVersion = "2.11.5"
lazy val _jsProjects = Seq(client)

lazy val server =
  (project in file("server")).settings(
    scalaVersion := _scalaVersion,
    scalaJSProjects := _jsProjects,
    pipelineStages := Seq(scalaJSProd, gzip),
    libraryDependencies ++= Seq(
      jdbc,
      anorm,
      cache,
      ws,
      "com.vmunier" %% "play-scalajs-scripts" % "0.1.0"
    )
  )
  .enablePlugins(PlayScala)
  .aggregate(_jsProjects.map(projectToRef): _*)
  .dependsOn(jvm)

lazy val client =
  (project in file("client")).settings(
    scalaVersion := _scalaVersion,
    persistLauncher := true,
    persistLauncher in Test := false,
    sourceMapsDirectories += js.base / "..",
    unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "0.8.0"
    )
  )
  .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
  .dependsOn(js)

lazy val shared =
  (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(
    scalaVersion := _scalaVersion
  )
  .jsConfigure(_.enablePlugins(ScalaJSPlay))
  .jsSettings(
    sourceMapsBase := baseDirectory.value / ".."
  )

lazy val jvm = shared.jvm
lazy val js  = shared.js

onLoad in Global := (Command.process("project server", _: State)) compose (onLoad in Global).value
