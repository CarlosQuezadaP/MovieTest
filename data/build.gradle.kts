import appdepdencies.Builds
import appdepdencies.Versions
import appdepdencies.Libs


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
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin("stdlib-jdk8", Versions.kotlin))

    api(project(appdepdencies.Modules.infraestructure))
    api(project(appdepdencies.Modules.core))
    api(project(appdepdencies.Modules.domain))


    api(Libs.Retrofit.core)
    api(Libs.Retrofit.adapter)
    api(Libs.Retrofit.gson)

    api(Libs.Okhttp.okhttp)
    api(Libs.Okhttp.logging)

    api(Libs.Room.runtime)
    api(Libs.Room.ktx)
    kapt(Libs.Room.kaptcompiler)

    testImplementation(Libs.Tests.junit)
}
