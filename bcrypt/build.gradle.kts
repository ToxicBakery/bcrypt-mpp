import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("maven-publish")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("signing")
}

kotlin {
    jvm {}
    js {
        nodejs {}
    }
    sourceSets {
        sourceSets["commonMain"].dependencies {
            implementation(kotlin("stdlib-common"))
        }
        sourceSets["commonTest"].dependencies {
            implementation(kotlin("test-common"))
            implementation(kotlin("test-annotations-common"))
        }
        sourceSets["jvmMain"].dependencies {
            implementation(kotlin("stdlib-jdk8"))
            api("at.favre.lib:bcrypt:0.9.0")
        }
        sourceSets["jvmTest"].dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit"))
        }
        sourceSets["jsMain"].dependencies {
            implementation(kotlin("stdlib-js"))
            api(npm("bcrypt", "^3.0.6"))
        }
        sourceSets["jsTest"].dependencies {
            implementation(kotlin("test-js"))
        }
    }
}

detekt {
    failFast = true
    buildUponDefaultConfig = true
    config = files("$rootDir/config/detekt.yml")
    input = files(
        kotlin.sourceSets
            .flatMap { sourceSet -> sourceSet.kotlin.srcDirs }
            .map { file: File -> file.relativeTo(projectDir) }
    )

    reports {
        html.enabled = true
        xml.enabled = false
        txt.enabled = false
    }
}

tasks {
    val dokka by getting(DokkaTask::class) {
        outputFormat = "html"
        outputDirectory = "$buildDir/dokkaHtml"
        multiplatform {
            val common by creating {
                includeNonPublic = false
            }
            val js by creating {
                includeNonPublic = false
            }
            val jvm by creating {
                includeNonPublic = false
            }
        }
    }
}

val dokkaJavadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
    dependsOn("dokka")
    from("$buildDir/dokkaHtml")
}

val POM_DESCRIPTION: String by project
val POM_NAME: String by project
val POM_URL: String by project
val POM_SCM_URL: String by project
val POM_SCM_CONNECTION: String by project
val POM_SCM_DEV_CONNECTION: String by project
val POM_LICENCE_NAME: String by project
val POM_LICENCE_URL: String by project
val POM_LICENCE_DIST: String by project
val POM_DEVELOPER_ID: String by project
val POM_DEVELOPER_NAME: String by project
val POM_DEVELOPER_EMAIL: String by project
val POM_DEVELOPER_ORGANIZATION: String by project
val POM_DEVELOPER_ORGANIZATION_URL: String by project

publishing {
    publications.withType<MavenPublication>().all {
        artifact(dokkaJavadocJar.get())
        pom {
            description.set(POM_DESCRIPTION)
            name.set(POM_NAME)
            url.set(POM_URL)
            scm {
                url.set(POM_SCM_URL)
                connection.set(POM_SCM_CONNECTION)
                developerConnection.set(POM_SCM_DEV_CONNECTION)
            }
            licenses {
                license {
                    name.set(POM_LICENCE_NAME)
                    url.set(POM_LICENCE_URL)
                    distribution.set(POM_LICENCE_DIST)
                }
            }
            developers {
                developer {
                    id.set(POM_DEVELOPER_ID)
                    name.set(POM_DEVELOPER_NAME)
                    email.set(POM_DEVELOPER_EMAIL)
                    organization.set(POM_DEVELOPER_ORGANIZATION)
                    organizationUrl.set(POM_DEVELOPER_ORGANIZATION_URL)
                }
            }
        }
    }

    repositories {
        val releaseUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
        val snapshotUrl = uri("https://oss.sonatype.org/content/repositories/snapshots")
        val sonatypeUsername = System.getenv("SONATYPE_USERNAME") ?: ""
        val sonatypePassword = System.getenv("SONATYPE_PASSWORD") ?: ""
        maven {
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotUrl else releaseUrl
            credentials {
                username = if (sonatypeUsername.isBlank()) "" else sonatypeUsername
                password = if (sonatypePassword.isBlank()) "" else sonatypePassword
            }
        }
    }
}

if (!version.toString().endsWith("SNAPSHOT")) {
    signing {
        isRequired = false
        sign(publishing.publications)
    }
}
