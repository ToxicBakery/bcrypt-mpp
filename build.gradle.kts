plugins {
    id("org.jetbrains.kotlin.multiplatform") version "1.3.71" apply false
    id("io.gitlab.arturbosch.detekt") version "1.7.1" apply false
    id("org.jetbrains.dokka") version "0.10.1" apply false
}

allprojects {
    val tagName = System.getenv("CIRCLE_TAG")
    val isTag = tagName != null && !tagName.isEmpty()
    val buildNumber = System.getenv("CIRCLE_BUILD_NUM") ?: "0"

    group = "com.ToxicBakery.library.bcrypt"
    version = "1.0.$buildNumber${if(isTag) "" else "-SNAPSHOT"}"
    println(version.toString())

    repositories {
        mavenCentral()
        jcenter()
        maven { setUrl("http://dl.bintray.com/kotlin/kotlinx.html/") }
    }
}
