import appdepdencies.BuildConfig
import appdepdencies.Versions
import appdepdencies.Libs

plugins {
    id(appdepdencies.Plugins.id_android_library)
    kotlin(appdepdencies.Plugins.kotlin_android)
    kotlin(appdepdencies.Plugins.kotlin_android_extensions)
    kotlin(appdepdencies.Plugins.kotlin_kapt)
    id(appdepdencies.Plugins.id_navigation_safeargs)
}

android {
    compileSdkVersion(appdepdencies.Builds.COMPILE_VERSION)
    buildToolsVersion = appdepdencies.Builds.BUILD_TOOLS
    defaultConfig {
        minSdkVersion(appdepdencies.Builds.MIN_VERSION)
        targetSdkVersion(appdepdencies.Builds.TARGET_VERSION)
        versionCode = appdepdencies.Builds.Core.VERSION_CODE
        versionName = appdepdencies.Builds.Core.VERSION_NAME
        buildConfigField("String", BuildConfig.images_url_key, BuildConfig.Urls.images_url)
        buildConfigField("String", BuildConfig.images_backdrop_url_key, BuildConfig.Urls.images_backdrop_url)
        buildConfigField("String", BuildConfig.server_url_key, BuildConfig.Urls.server_url)
        buildConfigField("String", BuildConfig.apikey_key, BuildConfig.Urls.apikey)
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

    api(Libs.Koin.koin)
    api(Libs.Core.kotlin_coroutines)
}
