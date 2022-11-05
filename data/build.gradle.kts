plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.appId}.data"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    implementation(project(":core:core-commons"))
    implementation(project(":core:core-data"))
    implementation(project(":data:data-remote"))

    implementation(DataDependencies.libs)
    kapt(DataDependencies.compilers)
    testImplementation(DataDependencies.libs)
    androidTestImplementation(DataDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}