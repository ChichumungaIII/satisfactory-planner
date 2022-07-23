plugins {
    kotlin("js") version "1.7.10"
}

group = "me.chris"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

dependencies {
    testImplementation(kotlin("test"))

    // Helps to ensure consistency between other Kotlin wrappers
    implementation(enforcedPlatform(kotlinw("wrappers-bom:1.0.0-pre.359")))

    implementation(kotlinw("emotion"))
    implementation(kotlinw("mui"))
    implementation(kotlinw("mui-icons"))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
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
}
