plugins {
  kotlin("multiplatform") version "1.8.10"
  kotlin("plugin.serialization") version "1.8.10"
  application
}

group = "me.chris"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
  maven("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven")
}

kotlin {
  js(IR) {
    binaries.executable()
    browser {
      commonWebpackConfig {
        cssSupport {
          enabled.set(true)
        }
      }
    }
  }

  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "18"
    }
    withJava()
    testRuns["test"].executionTask.configure {
      useJUnit()
    }
  }

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test"))
      }
    }

    val jsMain by getting {
      fun kotlinw(target: String): String =
        "org.jetbrains.kotlin-wrappers:kotlin-$target"

      dependencies {
        implementation(project.dependencies.enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.575")))

        implementation(kotlinw("cssom-core"))
        implementation(kotlinw("emotion"))
        implementation(kotlinw("mui"))
        implementation(kotlinw("mui-icons"))
        implementation(kotlinw("react"))
        implementation(kotlinw("react-dom"))
        implementation(kotlinw("react-router"))
        implementation(kotlinw("react-router-dom"))
      }
    }
    val jsTest by getting

    val jvmMain by getting {
      dependencies {
        implementation("io.ktor:ktor-server-html-builder-jvm:2.0.1")
        implementation("io.ktor:ktor-server-netty:2.0.1")
        implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.2")
      }
    }
    val jvmTest by getting
  }
}

application {
  mainClass.set("com.chichumunga.satisfactory.ServerKt")
}

tasks.named<Copy>("jvmProcessResources") {
  val jsBrowserDistribution = tasks.named("jsBrowserDistribution")
  from(jsBrowserDistribution)
}

tasks.named<JavaExec>("run") {
  dependsOn(tasks.named<Jar>("jvmJar"))
  classpath(tasks.named<Jar>("jvmJar"))
}

task("compileScss") {
  doLast {
    exec {
      val root = """src\jsMain\kotlin\app\main.scss"""
      val resource = """build\distributions\satisfactory-planner.css"""
      commandLine("cmd", "/c", "sass", root, resource)
    }
  }
}

task("runWithCss") {
  dependsOn("compileScss", "run")
}
