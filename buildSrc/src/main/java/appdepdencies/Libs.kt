package appdependencies


object Libs {

    object Tests {
        const val junit = "junit:junit:${Versions.junit}"
        const val runner = "com.android.support.test:runner:${Versions.runner}"
        const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Koin{
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinCore = "org.koin:koin-core:${Versions.koin}"
        const val koinScope = "org.koin:koin-android-scope:${Versions.koin}"
    }

}