buildscript {
    dependencies {
        classpath(Plugins.ClassPath.kotlin)
        classpath(Plugins.ClassPath.secrets)
        classpath(Plugins.ClassPath.daggerHilt)
    }
    repositories {
        mavenCentral()
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.androidApplication) version Plugins.Versions.android apply false
    id(Plugins.androidLibrary) version Plugins.Versions.android apply false
    id(Plugins.kotlinAndroid) version Plugins.Versions.kotlin apply false
    id(Plugins.kotlinKapt) version Plugins.Versions.kotlin apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}