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
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.duzhaokun123.biliplayer"
}

dependencies {
    implementation("androidx.core:core-ktx:$androidx_core_ktx_version")
    implementation("androidx.appcompat:appcompat:$androidx_appcompat_version")
    implementation("com.google.android.material:material:$material_version")

    val exoplayerVersion = "2.13.3"
    api("com.google.android.exoplayer:exoplayer-core:$exoplayerVersion")
    compileOnly("org.checkerframework:checker-qual:3.22.1")
    api("androidx.media:media:1.6.0")

    api("com.github.duzhaokun123:DanmakuView:ed76ba7ad5")
}
