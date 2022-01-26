import java.io.ByteArrayOutputStream
import java.nio.file.Paths

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-android")
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
            signingConfig = signingConfigs.getAt("release")
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
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")

    implementation("androidx.core:core-ktx:$androidx_core_ktx_version")
    implementation("androidx.appcompat:appcompat:$androidx_appcompat_version")
    implementation("com.google.android.material:material:$material_version")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.3")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    //bilibili-api
    implementation("com.hiczp", "bilibili-api", "bilibiliHD")

    //preferencex
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("com.takisoft.preferencex:preferencex:1.1.0")
    implementation("com.takisoft.preferencex:preferencex-simplemenu:1.1.0")

    //kotlinx-coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")

    //AnnotationProcessor
    kapt(project(":annotation-processor"))
    compileOnly(project(":annotation-processor"))

    //lifecycle
    val lifecycleVersion = "2.3.0"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")

    //gson
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.github.salomonbrys.kotson:kotson:2.5.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.12.0")
    kapt("com.github.bumptech.glide:compiler:4.12.0")

    //nav
    val navVersion = "2.4.0-alpha01"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")
    implementation("androidx.drawerlayout:drawerlayout:1.1.1")

    //dataBinding
    kapt("com.android.databinding:compiler:3.2.0-alpha10")

    //SmartRefreshLayout
    val srlVersion = "2.0.3"
    implementation("com.scwang.smart:refresh-layout-kernel:$srlVersion")
    implementation("com.scwang.smart:refresh-header-material:$srlVersion")
    implementation("com.scwang.smart:refresh-footer-classics:$srlVersion")

    //browser
    implementation("androidx.browser:browser:1.4.0")

    //StfalconImageViewer
    implementation(project(":imageviewer"))

    //BiliPlayer
    implementation(project(":bili-player"))

    //grpc
    implementation(project(":grpc"))

    implementation("androidx.multidex:multidex:2.0.1")

    //qr
    implementation("com.github.kenglxn.QRGen:android:2.6.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
}

afterEvaluate {
    tasks.getByName("minifyReleaseWithR8").doLast {
        optimizeReleaseRes()
    }
}

fun optimizeReleaseRes() {
    val aapt2 = Paths.get(
        project.android.sdkDirectory.path,
        "build-tools", project.android.buildToolsVersion, "aapt2"
    )
    val zip = Paths.get(
        project.buildDir.path, "intermediates",
        "shrunk_processed_res", "destroy", "resources-destroy-stripped.ap_"
    )
    val optimized = File("${zip}.opt")
    val cmd = exec {
        commandLine(
            aapt2, "optimize", "--collapse-resource-names",
            "--shorten-resource-paths", "-o", optimized, zip
        )
        isIgnoreExitValue = true
    }
    if (cmd.exitValue == 0) {
        zip.toFile().delete()
        optimized.renameTo(zip.toFile())
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
