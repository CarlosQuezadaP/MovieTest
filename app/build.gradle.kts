import appdependencies.Builds.APP_ID
import appdependencies.Builds.BUILD_TOOLS
import appdependencies.Builds.COMPILE_VERSION
import appdependencies.Builds.MIN_VERSION
import appdependencies.Builds.TARGET_VERSION
import appdependencies.Libs
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

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

    flavorDimensions("version")
    productFlavors {
        create("dev") {
            applicationId = APP_ID
            versionCode = appdependencies.Builds.App.VERSION_CODE
            versionName = appdependencies.Builds.App.VERSION_NAME
            setDimension("version")
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"
            multiDexEnabled = true
        }

        create("prod") {
            applicationId = APP_ID
            versionCode = appdependencies.Builds.App.VERSION_CODE
            versionName = appdependencies.Builds.App.VERSION_NAME
            setDimension("version")
            multiDexEnabled = true
        }
    }

    dexOptions {
        javaMaxHeapSize = "4g"
    }

    applicationVariants.forEach { variant ->
        variant.outputs.forEach { output ->
            val outputImpl = output as BaseVariantOutputImpl
            val project = project.name
            val sep = "_"
            val flavor = variant.flavorName
            val buildType = variant.buildType.name
            val version = variant.versionName

            val newApkName = "$project$sep$flavor$sep$buildType$sep$version.apk"
            outputImpl.outputFileName = newApkName
        }
    }

    bundle {
        abi {
            enableSplit = false
        }
    }

    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    configurations.all {
        resolutionStrategy {
            failOnVersionConflict()
            preferProjectModules()
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    androidExtensions {
        isExperimental = true
        defaultCacheImplementation =
            org.jetbrains.kotlin.gradle.internal.CacheImplementation.HASH_MAP
    }


}

dependencies {
    implementation(fileTree(mapOf("include" to listOf("*.jar"), "dir" to "libs")))
    implementation(kotlin("stdlib-jdk8", appdependencies.Versions.kotlin))

    api(project(":CORE"))
    api(project(":infraestructure"))
    api(project(":data"))
    api(project(":usecases"))


    testImplementation(Libs.Tests.junit)
    androidTestImplementation(Libs.Tests.test_ext_junit)
    androidTestImplementation(Libs.Tests.test_rules)
    androidTestImplementation(Libs.Tests.test_runner)
    androidTestImplementation(Libs.Tests.espresso_espresso)
    androidTestImplementation(Libs.Tests.test_espresso)
    testImplementation(Libs.Mockito.mockito_inline)
    testImplementation(Libs.Mockito.mockito_core)
}
