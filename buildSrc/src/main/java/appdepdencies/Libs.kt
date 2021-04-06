package appdepdencies


object Libs {

    const val jdk8 = "stdlib-jdk8"
    const val toasty = "com.github.GrenderG:Toasty:${Versions.toasty}"

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val kaptcompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object MockIO{
        const val mockio = "io.mockk:mockk:${Versions.mockio}"
    }

    object Koin {
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
        const val koinTest = "org.koin:koin-test:${Versions.koin}"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val kapt_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
        const val savedStateViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.savedstate}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object Okhttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }


    object Core {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatX}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.fragment}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.ui}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    }

    object ImageLoading {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }


}