import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

val localProperties = gradleLocalProperties(rootDir)

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
    id("com.google.devtools.ksp") version "1.7.10-1.0.6"
}

android {
    val buildTime = System.currentTimeMillis()
    val baseVersionName = "0.1-wip"

    compileSdk = compile_sdk

    defaultConfig {
        applicationId = "com.duzhaokun123.bilibilihd2"
        minSdk = min_sdk
        targetSdk = target_sdk
        versionCode = 1
        versionName = "$baseVersionName-git.$gitHash"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("long", "BUILD_TIME", buildTime.toString())
        buildConfigField(
            "String",
            "PROJECT_HOME",
            "\"https://github.com/duzhaokun123/BilibiliHD2\""
        )
        buildConfigField(
            "String",
            "DONATE_LINK",
            "\"https://duzhaokun123.github.io/donate.html\""
        )

        var appSecret = System.getenv("APP_SECRET")?.takeIf { it.isNotEmpty() }
                ?: localProperties.getProperty("app.secret")?.takeIf { it.isNotEmpty() }
                ?: ""
        if (localProperties.getProperty("analytics.enabled", "true") != "true")
            appSecret = ""
        buildConfigField("String", "APP_SECRET", "\"$appSecret\"")
    }
    packagingOptions {
        resources.excludes.addAll( arrayOf(
            "META-INF/**",
            "kotlin/**",
            "okhttp3/**",
            "google/**",
            "bilibili/**",
            "github.com/**"
        ))
    }
    signingConfigs {
        create("release") {
            storeFile = file("../releaseKey.jks")
            storePassword = System.getenv("REL_KEY")
            keyAlias = "key0"
            keyPassword = System.getenv("REL_KEY")
            enableV1Signing = true
            enableV2Signing = true
            enableV3Signing = true
            enableV4Signing = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = if (System.getenv("REL_KEY") != null) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug")
            }
            sourceSets.getByName("main").java.srcDir(File("build/generated/ksp/release/kotlin"))
        }
        getByName("debug") {
            val minifyEnabled = localProperties.getProperty("minify.enabled", "false")
            isMinifyEnabled = minifyEnabled.toBoolean()
            isShrinkResources = minifyEnabled.toBoolean()
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            sourceSets.getByName("main").java.srcDir(File("build/generated/ksp/debug/kotlin"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
//        freeCompilerArgs = listOf(
//            "-Xuse-k2"
//        )
    }
    buildFeatures {
        dataBinding = true
    }
    lint {
        abortOnError = false
    }
    namespace = "com.duzhaokun123.bilibilihd2"
}

dependencies {
    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.appcompat:appcompat:1.4.2")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //bilibili-api
    implementation(project(":bilibili-api"))

    //preferencex
    implementation("androidx.preference:preference-ktx:1.2.0")
    implementation("com.takisoft.preferencex:preferencex:1.1.0")
    implementation("com.takisoft.preferencex:preferencex-simplemenu:1.1.0")

    //kotlinx-coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //AnnotationProcessor
    compileOnly(project(":annotation-processor"))
    ksp(project(":annotation-processor"))

    //lifecycle
    val lifecycleVersion = "2.5.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    //gson
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.github.salomonbrys.kotson:kotson:2.5.0")

    //nav
    val navVersion = "2.5.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")

    //SmartRefreshLayout
    val srlVersion = "2.0.5"
    implementation("io.github.scwang90:refresh-layout-kernel:$srlVersion")
    implementation("io.github.scwang90:refresh-header-material:$srlVersion")
    implementation("io.github.scwang90:refresh-footer-classics:$srlVersion")

    //browser
    implementation("androidx.browser:browser:1.4.0")

    //StfalconImageViewer
    implementation(project(":imageviewer"))

    //grpc
    implementation(project(":grpc"))

    //qr
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")

    //appcenter
    val appCenterSdkVersion = "4.4.5"
    implementation("com.microsoft.appcenter:appcenter-analytics:${appCenterSdkVersion}")
    implementation("com.microsoft.appcenter:appcenter-crashes:${appCenterSdkVersion}")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    //core-splashscreen
    implementation("androidx.core:core-splashscreen:1.0.0-rc01")

    //picasso
    implementation("com.squareup.picasso:picasso:2.71828")

    //player
    val exoplayerVersion = "2.18.1"
    implementation("com.google.android.exoplayer:exoplayer-core:$exoplayerVersion")
    implementation("com.google.android.exoplayer:exoplayer-ui:$exoplayerVersion")
    implementation("com.github.duzhaokun123:DanmakuView:ed76ba7ad5")
}

val optimizeReleaseRes = task("optimizeReleaseRes").doLast {
    val aapt2 = Paths.get(
        project.android.sdkDirectory.path,
        "build-tools", project.android.buildToolsVersion, "aapt2"
    )
    val zip = Paths.get(
        project.buildDir.path, "intermediates",
        "optimized_processed_res", "release", "resources-release-optimize.ap_"
    )
    val optimized = File("${zip}.opt")
    val cmd = exec {
        commandLine(aapt2, "optimize", "--collapse-resource-names", "-o", optimized, zip)
        isIgnoreExitValue = true
    }
    if (cmd.exitValue == 0) {
        delete(zip)
        optimized.renameTo(zip.toFile())
    }
}
tasks.whenTaskAdded {
    when (name) {
        "optimizeReleaseResources" -> {
            finalizedBy(optimizeReleaseRes)
        }
    }
}

val gitHash: String
    get() {
        val out = ByteArrayOutputStream()
        val cmd = exec {
            commandLine("git", "rev-parse", "--short", "HEAD")
            standardOutput = out
            isIgnoreExitValue = true
        }
        return if (cmd.exitValue == 0)
            out.toString().trim()
        else
            "(error)"
    }
