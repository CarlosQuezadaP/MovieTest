plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    "koin"
}

android {
    compileSdkVersion(appdepdencies.Builds.COMPILE_VERSION)
    buildToolsVersion = appdepdencies.Builds.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(appdepdencies.Builds.MIN_VERSION)
        targetSdkVersion(appdepdencies.Builds.TARGET_VERSION)
        versionCode = appdepdencies.Builds.Core.VERSION_CODE
        versionName = appdepdencies.Builds.Core.VERSION_NAME
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
    implementation(kotlin("stdlib-jdk8", appdepdencies.Versions.kotlin))

    api(project(":data"))
    api(project(":CORE"))
    api(project(":domain"))

    testImplementation(appdepdencies.Libs.MockIO.mockio)
    testImplementation(appdepdencies.Libs.Koin.koinTest)
    testImplementation(appdepdencies.Libs.Tests.junit)
}
