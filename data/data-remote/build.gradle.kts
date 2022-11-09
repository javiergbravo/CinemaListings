plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.data.remote"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    implementation(project(":core:core-commons"))

    implementation(RemoteDependencies.libs)
    kapt(RemoteDependencies.compilers)
    testImplementation(RemoteDependencies.testLibs)
    androidTestImplementation(RemoteDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}