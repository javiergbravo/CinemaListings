plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.core.domain"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    implementation(project(":core:core-commons"))
}