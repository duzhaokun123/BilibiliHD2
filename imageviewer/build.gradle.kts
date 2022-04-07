plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = compile_sdk

    defaultConfig {
        minSdk = min_sdk
        targetSdk = target_sdk
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    namespace = "com.stfalcon.imageviewer"
}

dependencies {
    implementation("androidx.appcompat:appcompat:$androidx_appcompat_version")
    implementation("androidx.transition:transition:1.4.1")
    api("com.github.chrisbanes:PhotoView:2.3.0")
}