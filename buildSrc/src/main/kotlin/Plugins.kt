object Plugins {

    object Versions {
        const val android = "7.3.1"
        const val kotlin = "1.7.20"
        const val secrets = "2.0.1"
        const val daggerHilt = Libs.Version.dagger
    }

    object ClassPath {
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
        const val secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.secrets}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
}