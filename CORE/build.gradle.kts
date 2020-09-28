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
        minSdkVersion(appdependencies.Builds.MIN_VERSION)
        targetSdkVersion(appdependencies.Builds.TARGET_VERSION)
        versionCode = appdependencies.Builds.Core.VERSION_CODE
        versionName = appdependencies.Builds.Core.VERSION_NAME
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "IMAGES_URL", "\"https://image.tmdb.org/t/p/w500\"")
        buildConfigField("String", "SERVER_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "ApiKey", "\"026a257e7842ac9cac1fa627496b1468\"")

    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
        kotlinOptions.suppressWarnings = true
        kotlinOptions.jvmTarget = "1.8"
        kotlinOptions.noReflect = true
        kotlinOptions.freeCompilerArgs += listOf(
            "-XXLanguage:+InlineClasses"
        )
    }

    dataBinding { isEnabled = true }
    androidExtensions {
        isExperimental = true
        defaultCacheImplementation =
            org.jetbrains.kotlin.gradle.internal.CacheImplementation.HASH_MAP
    }

    buildFeatures {
        dataBinding = true
    }

}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin("stdlib-jdk8", appdependencies.Versions.kotlin))

    api(appdependencies.Libs.Core.paging)
    api(appdependencies.Libs.Core.constraintlayout)
    api(appdependencies.Libs.Core.appcompat)

    kapt(appdependencies.Libs.Lifecycle.kapt_compiler)
    api(appdependencies.Libs.Lifecycle.livedataKtx)
    api(appdependencies.Libs.Lifecycle.viewmodelKtx)
    api(appdependencies.Libs.Lifecycle.savedStateViewModel)
    api(appdependencies.Libs.Lifecycle.extensions)
    api(appdependencies.Libs.Lifecycle.common)
    api(appdependencies.Libs.Lifecycle.runtime)


    api(appdependencies.Libs.ImageLoading.coil)

    api(appdependencies.Libs.Core.navigationFragmentKtx)
    api(appdependencies.Libs.Core.navigationUiKtx)

    api(appdependencies.Libs.Retrofit.core)
    api(appdependencies.Libs.Retrofit.adapter)
    api(appdependencies.Libs.Retrofit.gson)

    api(appdependencies.Libs.Okhttp.okhttp)
    api(appdependencies.Libs.Okhttp.logging)

    api(appdependencies.Libs.Koin.koin)
    api(appdependencies.Libs.Koin.koinViewModel)
    api(appdependencies.Libs.Koin.koinFragment)
    api(appdependencies.Libs.Koin.koinScope)

    testImplementation(appdependencies.Libs.Tests.junit)
    androidTestImplementation(appdependencies.Libs.Tests.runner)
    androidTestImplementation(appdependencies.Libs.Tests.espresso)
}
