plugins {
    application
    kotlin("jvm") version "1.4.21"
    id("com.justai.jaicf.jaicp-build-plugin") version "0.1.1"
    kotlin("plugin.serialization") version "1.4.21"
}

group = "com.justai.jaicf"
version = "1.0.0"

val jaicf = "1.1.3"
val logback = "1.2.3"
val ktor = "1.5.1"
val serializationRuntime = "1.0.1"
val jUnit = "5.6.0"
val kotlin = "1.4.21"

// Main class to run application on heroku. Either JaicpPollerKt, or JaicpServerKt. Will propagate to .jar main class.
application {
    mainClassName = "com.justai.jaicf.template.connections.JaicpServerKt"
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("ch.qos.logback:logback-classic:$logback")

    implementation("com.just-ai.jaicf:core:$jaicf")
    implementation("com.just-ai.jaicf:jaicp:$jaicf")
    implementation("com.just-ai.jaicf:caila:$jaicf")

    api("io.ktor:ktor-client-core:$ktor")
    implementation("io.ktor:ktor-client-auth-jvm:$ktor")
    api("io.ktor:ktor-client-cio:$ktor")
    api("io.ktor:ktor-client-serialization-jvm:$ktor")

    implementation("org.junit.jupiter:junit-jupiter-api:$jUnit")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:$jUnit")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationRuntime")
    implementation("org.jetbrains.kotlin:kotlin-serialization:$kotlin")
    implementation("com.squareup.okhttp3:okhttp:3.2.0")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

tasks.create("stage") {
    dependsOn("shadowJar")
}

tasks.withType<com.justai.jaicf.plugins.jaicp.build.JaicpBuild> {
    mainClassName.set(application.mainClassName)
}
