plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(appdependencies.Builds.COMPILE_VERSION)
    buildToolsVersion = appdependencies.Builds.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(appdependencies.Builds .MIN_VERSION)
        targetSdkVersion(appdependencies.Builds.TARGET_VERSION)
        versionCode = appdependencies.Builds.Core.VERSION_CODE
        versionName = appdependencies.Builds.Core.VERSION_NAME
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
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin("stdlib-jdk8", appdependencies.Versions.kotlin))
    api(project(":CORE"))
    api(project(":infraestructure"))
    api(project(":domain"))

    api(appdependencies.Libs.Retrofit.core)
    api(appdependencies.Libs.Retrofit.adapter)
    api(appdependencies.Libs.Retrofit.gson)

    api(appdependencies.Libs.Okhttp.okhttp)
    api(appdependencies.Libs.Okhttp.logging)

    api(appdependencies.Libs.Room.runtime)
    api(appdependencies.Libs.Room.ktx)
    kapt(appdependencies.Libs.Room.kaptcompiler)

    testImplementation(appdependencies.Libs.Tests.junit)
}
