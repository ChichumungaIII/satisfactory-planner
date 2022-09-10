plugins {
    kotlin("multiplatform") version "1.7.10"
    application
}

group = "me.chris"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val jsMain by getting {
            fun kotlinw(target: String): String =
                "org.jetbrains.kotlin-wrappers:kotlin-$target"

            dependencies {
                implementation(project.dependencies.enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.359")))

                implementation(kotlinw("emotion"))
                implementation(kotlinw("mui"))
                implementation(kotlinw("mui-icons"))
                implementation(kotlinw("react"))
                implementation(kotlinw("react-dom"))
                implementation(kotlinw("react-router-dom"))
            }
        }
        val jsTest by getting
    }
}
