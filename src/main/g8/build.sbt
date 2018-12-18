name := """$name$"""
organization := "$organization$"

version :=  "$version$"

lazy val root = (project in file(".")).enablePlugins(PlayScala, DockerPlugin,EcrPlugin,SwaggerPlugin)

scalaVersion := "2.12.6"

libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies ++= Seq( jdbc , ehcache , ws , specs2 % Test , guice )

libraryDependencies += "com.github.stijndehaes" %% "play-prometheus-filters" % "0.4.0"


libraryDependencies += "org.webjars" % "swagger-ui" % "3.20.3"
// https://mvnrepository.com/artifact/net.sourceforge.jtds/jtds
libraryDependencies += "net.sourceforge.jtds" % "jtds" % "1.3.1"


maintainer in Docker := "$dockerMaintainer$"
packageName in Docker := "$dockerPackagename$"
version in Docker := "latest"
dockerBaseImage := "anapsix/alpine-java"

stage := ((stage in Docker) dependsOn swagger).value

region           in Ecr := Region.getRegion(Regions.US_EAST_1)
repositoryName   in Ecr := (packageName in Docker).value
localDockerImage in Ecr := (packageName in Docker).value + ":" + (version in Docker).value

// Create the repository before authentication takes place (optional)
login in Ecr := ((login in Ecr) dependsOn (createRepository in Ecr)).value

// Authenticate and publish a local Docker image before pushing to ECR
push in Ecr := ((push in Ecr) dependsOn (publishLocal in Docker, login in Ecr)).value


//Swagger Plugin Configuration
swaggerPrettyJson := true
swaggerOutputTransformers := Seq(envOutputTransformer)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "$organization$.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "$organization$.binders._"
