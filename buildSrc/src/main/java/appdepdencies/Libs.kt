package appdependencies


object Libs {

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "com.android.support.test:runner:${Versions.runner}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Koin {
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinCore = "org.koin:koin-core:${Versions.koin}"
        const val koinScope = "org.koin:koin-android-scope:${Versions.koin}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val kapt = "androidx.room:room-compiler:${Versions.room}"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"

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

    object Core {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatX}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.appCoreX}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
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


}