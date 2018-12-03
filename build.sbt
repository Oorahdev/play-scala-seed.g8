lazy val root = (project in file(".")).
  settings(
    test in Test := {
      val _ = (g8Test in Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-XX:MaxPermSize=256m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("http://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns)
  ).enablePlugins(ScriptedPlugin,PlayScala, DockerPlugin,EcrPlugin,SwaggerPlugin)

maintainer in Docker := "name"
packageName in Docker := "name"
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
// giter8.ScaffoldPlugin.scaffoldSettings
