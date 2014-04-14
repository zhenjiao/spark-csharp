import AssemblyKeys._ // put this at the top of the file

name := "spark-csharp"

version := "0.0.1"

scalaVersion := "2.10.2"

resolvers ++= Seq(
  "Maven Repository"     at "http://repo.maven.apache.org/maven2",
  "Apache Repository"    at "https://repository.apache.org/content/repositories/releases",
  "JBoss Repository"     at "https://repository.jboss.org/nexus/content/repositories/releases/",
  "MQTT Repository"      at "https://repo.eclipse.org/content/repositories/paho-releases/",
  "Cloudera Repository"  at "http://repository.cloudera.com/artifactory/cloudera-repos/",
  Resolver.mavenLocal
)

libraryDependencies ++= Seq(
	"org.apache.spark"           % "spark-assembly_2.10"   % "0.9.1"
)

jarName in assembly := "spark-csharp.jar"

test in assembly := {}
