object Plugins {

    object Versions {
        const val android = "8.1.0"
        const val kotlin = "1.9.21"
        const val secrets = "2.0.1"
        const val daggerHilt = Libs.Version.dagger
    }

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "org.jetbrains.kotlin.android"
    const val kotlinKapt = "org.jetbrains.kotlin.kapt"
    const val hilt = "dagger.hilt.android.plugin"
    const val secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"

    object ClassPath {
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
        const val secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.secrets}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }
}