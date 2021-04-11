plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 19
        targetSdk = 30
        versionCode = 1
        versionName = "bilibiliHD"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    implementation("androidx.appcompat:appcompat:1.3.0-rc01")
    implementation("androidx.transition:transition:1.4.0")
    api("com.github.chrisbanes:PhotoView:2.3.0")
}