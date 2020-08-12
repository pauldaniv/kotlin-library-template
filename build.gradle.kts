plugins {
  base
  java
  idea
  `maven-publish`
  id("io.freefair.lombok") version "5.1.1"
  id("com.jfrog.bintray") version "1.8.4" apply false
  kotlin("jvm") version "1.3.50" apply false
}

val githubUsr: String = findParam("gpr.usr", "USERNAME") ?: ""
val githubKey: String? = findParam("gpr.key", "TOKEN", "GITHUB_TOKEN")

subprojects {
  group = "com.pauldaniv.library.template"
  apply(plugin = "java")
  apply(plugin = "maven-publish")
  val sourcesJar by tasks.creating(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
  }

  publishing {
    repositories {
      maven {
        name = "GitHub-Publish-Repo"
        url = uri("https://maven.pkg.github.com/pauldaniv/${rootProject.name}")
        credentials {
          username = githubUsr
          password = githubKey
        }
      }
    }

    publications {
      register<MavenPublication>("gpr") {
        from(components["java"])
        artifact(sourcesJar)
      }
    }
  }
}

allprojects {
  apply(plugin = "java")
  apply(plugin = "idea")
  apply(plugin = "groovy")
  apply(plugin = "io.freefair.lombok")

  repositories {
    jcenter()
    mavenCentral()
    maven {
      name = "GitHub-Maven-Repo"
      url = uri("https://maven.pkg.github.com/pauldaniv/bom-template")
      credentials {
        username = githubUsr
        password = githubKey
      }
    }
  }

  dependencies {
    implementation(platform("com.paul:bom-template:0.0.+"))
    implementation("org.codehaus.groovy:groovy")
    testImplementation("org.assertj:assertj-core")
    testImplementation("org.junit.jupiter:junit-jupiter")
  }

  configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
  }

  idea {
    module {
      excludeDirs.addAll(listOf(
          file(".idea"),
          file(".gradle"),
          file("gradle"),
          file("build"),
          file("out")
      ))
    }
  }

  tasks.withType<Test> {
    useJUnitPlatform()
  }

  configurations.all {
    resolutionStrategy.cacheDynamicVersionsFor(1, "minutes")
  }
}

fun findParam(vararg names: String): String? {
  for (name in names) {
    val param = project.findProperty(name) as String? ?: System.getenv(name)
    if (param != null) {
      return param
    }
  }
  return null
}
