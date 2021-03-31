import appdepdencies.Builds.APP_ID
import appdepdencies.Builds.BUILD_TOOLS
import appdepdencies.Builds.COMPILE_VERSION
import appdepdencies.Builds.MIN_VERSION
import appdepdencies.Builds.TARGET_VERSION
import appdepdencies.Libs

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
        versionCode = appdepdencies.Builds.App.VERSION_CODE
        versionName = appdepdencies.Builds.App.VERSION_NAME
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
    implementation(kotlin("stdlib-jdk8", appdepdencies.Versions.kotlin))

    implementation(project(appdepdencies.Modules.core))
    implementation(project(":infraestructure"))
    implementation(project(":data"))
    implementation(project(":usecases"))


    implementation(Libs.Core.paging)
    implementation(Libs.Core.appcompat)

    kapt(Libs.Lifecycle.kapt_compiler)
    implementation(Libs.Lifecycle.livedataKtx)
    implementation(Libs.Lifecycle.viewmodelKtx)
    implementation(Libs.Lifecycle.savedStateViewModel)
    implementation(Libs.Lifecycle.extensions)
    implementation(Libs.Lifecycle.common)
    implementation(Libs.Lifecycle.runtime)

    implementation(Libs.toasty)
    implementation(Libs.Core.constraintlayout)
    implementation(Libs.ImageLoading.coil)

    implementation(Libs.Koin.koinViewModel)
    implementation(Libs.Koin.koinFragment)

    implementation(Libs.Core.navigationFragmentKtx)
    implementation(Libs.Core.navigationUiKtx)
    implementation(Libs.Core.material)

    testImplementation("io.mockk:mockk:1.10.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.3.0")
    androidTestImplementation("androidx.test:core-ktx:1.3.0")
    androidTestImplementation("androidx.test:rules:1.3.0")
    androidTestImplementation("io.mockk:mockk-android:1.10.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
}
