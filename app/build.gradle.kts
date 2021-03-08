import appdependencies.Builds.APP_ID
import appdependencies.Builds.BUILD_TOOLS
import appdependencies.Builds.COMPILE_VERSION
import appdependencies.Builds.MIN_VERSION
import appdependencies.Builds.TARGET_VERSION
import appdependencies.Libs

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    "koin"
}


android {
    compileOptions.incremental = false
    compileSdkVersion(COMPILE_VERSION)
    buildToolsVersion = BUILD_TOOLS
    defaultConfig {
        applicationId = APP_ID
        minSdkVersion(MIN_VERSION)
        targetSdkVersion(TARGET_VERSION)
        versionCode = appdependencies.Builds.App.VERSION_CODE
        versionName = appdependencies.Builds.App.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        javaCompileOptions.annotationProcessorOptions {
            includeCompileClasspath = true
        }
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


    buildFeatures {
        dataBinding = true
        viewBinding = true
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
    api(project(":data"))
    api(project(":usecases"))


    api(Libs.Core.paging)
    api(Libs.Core.appcompat)

    kapt(Libs.Lifecycle.kapt_compiler)
    api(Libs.Lifecycle.livedataKtx)
    api(Libs.Lifecycle.viewmodelKtx)
    api(Libs.Lifecycle.savedStateViewModel)
    api(Libs.Lifecycle.extensions)
    api(Libs.Lifecycle.common)
    api(Libs.Lifecycle.runtime)

    api(Libs.toasty)
    api(Libs.Core.constraintlayout)
    api(Libs.ImageLoading.coil)

    api(Libs.Koin.koinViewModel)
    api(Libs.Koin.koinFragment)
    api(Libs.Koin.koinScope)

    api(Libs.Core.navigationFragmentKtx)
    api(Libs.Core.navigationUiKtx)
    api(Libs.Core.material)

    testImplementation(Libs.MockIO.mockio)


}
