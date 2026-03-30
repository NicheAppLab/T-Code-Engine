val scala3Version = "3.8.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "T-Code-Engine",

    version := "0.3.0",

    organization := "io.github.nicheapplab",

    scalaVersion := scala3Version,

    publishMavenStyle := true,

    libraryDependencies += "org.scalameta" %% "munit" % "1.2.4" % Test
  )

ThisBuild / publishTo := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  if (isSnapshot.value) Some("central-snapshots" at centralSnapshots)
  else localStaging.value
}
ThisBuild / organizationName := "Niche App Lab"
ThisBuild / organizationHomepage := None

ThisBuild / scmInfo := Some(
  ScmInfo(
    url("https://github.com/NicheAppLab/T-Code-Engine"),
    "scm:git@github.com:your-account/your-project.git"
  )
)
ThisBuild / developers := List(
  Developer(
    id = "kazuf3",
    name = "Kazuhiro Funakoshi",
    email = "steelheart.wolverine@gmail.com",
    url = url("http://your.url")
  )
)

ThisBuild / description := "Generate Japanese characters based on T-Code input method"
ThisBuild / licenses := List(
  "GPL2" -> new URL("https://www.gnu.org/licenses/old-licenses/gpl-2.0.html")
)
ThisBuild / homepage := Some(url("https://github.com/NicheAppLab/T-Code-Engine"))

// Remove all additional repository other than Maven Central from POM
ThisBuild / pomIncludeRepository := { _ => false }

ThisBuild / versionScheme := Some("early-semver")
