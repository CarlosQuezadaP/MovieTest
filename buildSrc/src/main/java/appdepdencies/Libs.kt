package appdependencies


object Libs {


    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "com.android.support.test:runner:${Versions.runner}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val kaptcompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Koin {
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
        const val koinScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val kapt_compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

        //const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle}"
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        // kotlin live data extensions
        const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"

        // alternately - if using Java8, use the following instead of lifecycle-compiler
        const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"

        // view model saved state handler
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
        const val legacy_ = "androidx.legacy:legacy-support-v4:1.0.0:${Versions.legacy}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.appCoreX}"
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.fragment}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.ui}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
        const val swipeRefreshLayout =
            "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
        const val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    }

    object ImageLoading {
        const val coil = "io.coil-kt:coil:${Versions.coil}"
    }


}