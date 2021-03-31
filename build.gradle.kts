buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }

    }
    dependencies {
        classpath(appdepdencies.ClassPath.gradle)
        classpath(appdepdencies.ClassPath.kotlingradle)
        classpath(appdepdencies.ClassPath.google)
        classpath(appdepdencies.ClassPath.navisafe)
        classpath(appdepdencies.ClassPath.kotlin_gradle_plugin)
        classpath(appdepdencies.ClassPath.koin_class)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://www.jitpack.io") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
