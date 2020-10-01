buildscript {
    val kotlin_version by extra("1.4.10")
    repositories {
        google()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }

    }
    dependencies {
        classpath(appdependencies.ClassPath.gradle)
        classpath(appdependencies.ClassPath.kotlingradle)
        classpath(appdependencies.ClassPath.google)
        classpath(appdependencies.ClassPath.navisafe)
        classpath(appdependencies.ClassPath.kotlin_gradle_plugin)
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
