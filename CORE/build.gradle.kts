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
        buildConfigField("String", "IMAGES_URL", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "IMAGES_BACKDROP_URL", "\"https://image.tmdb.org/t/p/original\"")
        buildConfigField("String", "SERVER_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "ApiKey", "\"026a257e7842ac9cac1fa627496b1468\"")
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

    api(appdepdencies.Libs.Koin.koin)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}
