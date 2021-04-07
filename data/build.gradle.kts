import appdepdencies.Builds
import appdepdencies.Versions
import appdepdencies.Libs
import appdepdencies.Modules


plugins {
    id(appdepdencies.Plugins.id_android_library)
    kotlin(appdepdencies.Plugins.kotlin_android)
    kotlin(appdepdencies.Plugins.kotlin_android_extensions)
    kotlin(appdepdencies.Plugins.kotlin_kapt)
}

android {
    compileSdkVersion(Builds.COMPILE_VERSION)
    buildToolsVersion = Builds.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(Builds .MIN_VERSION)
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

    implementation(project(Modules.infraestructure))
    implementation(project(Modules.core))
    implementation(project(Modules.domain))

    implementation(Libs.Retrofit.core)
    implementation(Libs.Retrofit.adapter)
    implementation(Libs.Retrofit.gson)

    implementation(Libs.Okhttp.okhttp)
    implementation(Libs.Okhttp.logging)

    implementation(Libs.Room.runtime)
    implementation(Libs.Room.ktx)
    kapt(Libs.Room.kaptcompiler)

    testImplementation(Libs.Tests.junit)
}
