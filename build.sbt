name := "snake"

version := "0.1"

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  "com.badlogicgames.gdx" % "gdx-backend-lwjgl" % "1.9.10",
  "com.badlogicgames.gdx" % "gdx-platform" % "1.9.10" classifier "natives-desktop",
  "org.typelevel" %% "cats-effect" % "2.1.3",
  "org.scalatest" %% "scalatest" % "3.2.5" % Test
)
