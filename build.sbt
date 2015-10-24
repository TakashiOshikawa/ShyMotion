organization  := "tokyo.shymotion"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Ylog-classpath")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"      % sprayV,
    "io.spray"            %%  "spray-routing"  % sprayV,
    "io.spray"            %%  "spray-testkit"  % sprayV  % "test",
    "com.typesafe.akka"   %%  "akka-actor"     % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"   % akkaV   % "test",
    "org.twitter4j"       %   "twitter4j-core" % "4.0.3",
    "org.scalikejdbc"     %%  "scalikejdbc"    % "2.2.+",
    "org.slf4j"           %   "slf4j-simple"   % "1.7.+",
    "mysql"               %   "mysql-connector-java" % "5.1.29",
    "com.typesafe.play"   %   "play-json_2.11" % "2.4.2",
    "org.specs2"          %%  "specs2-core"    % "2.3.11" % "test"
  )
}

ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }

Revolver.settings

