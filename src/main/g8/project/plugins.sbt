import sbt.Resolver

logLevel := Level.Warn

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.20")

addSbtPlugin("com.iheart" %% "sbt-play-swagger" % "0.7.5")


addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")
// for autoplugins
addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.3.2")

resolvers += Resolver.url("bintray-sbilinski", url("http://dl.bintray.com/sbilinski/maven"))(Resolver.ivyStylePatterns)
addSbtPlugin("com.mintbeans" % "sbt-ecr" % "0.8.0")

