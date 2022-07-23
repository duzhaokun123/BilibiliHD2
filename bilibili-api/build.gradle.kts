import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjvm-default=enable", "-opt-in=kotlin.RequiresOptIn", /* "-Xuse-k2" */)
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("io.github.microutils:kotlin-logging:2.1.23")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.github.salomonbrys.kotson:kotson:2.5.0")
    api("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    api("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("io.ktor:ktor-client-websockets:1.6.8")
    implementation("io.ktor:ktor-client-cio:1.6.8")
    implementation("com.hiczp:crc32-crack:1.0")
}