resolvers += "Web plugin repo" at "http://siasia.github.com/maven2"    

//Following means libraryDependencies += "com.github.siasia" %% "xsbt-web-plugin" % "0.1.1-<sbt version>""
libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % ("0.1.1-"+v))

resolvers += ScalaToolsSnapshots

resolvers += "remeniuk repo" at "http://remeniuk.github.com/maven"

libraryDependencies += "org.netbeans" %% "sbt-netbeans-plugin" % "0.1.2"

resolvers += {
  val typesafeRepoUrl = new java.net.URL("http://repo.typesafe.com/typesafe/releases")
  val pattern = Patterns(false, "[organisation]/[module]/[sbtversion]/[revision]/[type]s/[module](-[classifier])-[revision].[ext]")
  Resolver.url("Typesafe Repository", typesafeRepoUrl)(pattern)
}

libraryDependencies <<= (libraryDependencies, sbtVersion) { (deps, version) => 
  deps :+ ("com.typesafe.sbteclipse" %% "sbteclipse" % "1.2" extra("sbtversion" -> version))
}

