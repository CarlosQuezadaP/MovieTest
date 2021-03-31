import appdepdencies.Builds
import appdepdencies.Versions


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
        jvmTarget = "1.8"
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin("stdlib-jdk8", Versions.kotlin))
}
