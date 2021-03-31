buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        maven { url = uri(appdepdencies.ClassPath.Uri.plugins_gradle) }

    }
    dependencies {
        classpath(appdepdencies.ClassPath.gradle)
        classpath(appdepdencies.ClassPath.kotlingradle)
        classpath(appdepdencies.ClassPath.google)
        classpath(appdepdencies.ClassPath.navisafe)
        classpath(appdepdencies.ClassPath.kotlin_gradle_plugin)
        classpath(appdepdencies.ClassPath.koin_class)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri(appdepdencies.ClassPath.Uri.jitpack) }
        maven { url = uri(appdepdencies.ClassPath.Uri.plugins_gradle) }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
