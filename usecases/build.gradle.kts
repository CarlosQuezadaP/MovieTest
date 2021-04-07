import appdepdencies.Libs
import appdepdencies.Versions
import appdepdencies.Modules
import appdepdencies.Builds

plugins {
    id(appdepdencies.Plugins.id_android_library)
    kotlin(appdepdencies.Plugins.kotlin_android)
    kotlin(appdepdencies.Plugins.kotlin_android_extensions)
    kotlin(appdepdencies.Plugins.kotlin_kapt)
    appdepdencies.Plugins.koin
}

android {
    compileSdkVersion(Builds.COMPILE_VERSION)
    buildToolsVersion = Builds.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(Builds.MIN_VERSION)
        targetSdkVersion(Builds.TARGET_VERSION)
        versionCode = Builds.Core.VERSION_CODE
        versionName = Builds.Core.VERSION_NAME
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin(Libs.jdk8, Versions.kotlin))

    api(project(Modules.data))
    api(project(Modules.core))
    api(project(Modules.domain))

    testImplementation(Libs.MockIO.mockio)
    testImplementation(Libs.Koin.koinTest)
    testImplementation(Libs.Tests.junit)
}
