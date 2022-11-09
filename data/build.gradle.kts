plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.data"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    implementation(project(":core:core-commons"))
    implementation(project(":data:data-remote"))

    implementation(DataDependencies.libs)
    kapt(DataDependencies.compilers)
    testImplementation(DataDependencies.testLibs)
    androidTestImplementation(DataDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}