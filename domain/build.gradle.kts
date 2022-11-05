plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.domain"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    // Modules
    implementation(project(":core:core-commons"))
    implementation(project(":data"))

    // Libraries
    implementation(DomainDependencies.libs)
    kapt(DomainDependencies.compilers)
    testImplementation(DomainDependencies.libs)
    androidTestImplementation(DomainDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}