plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.domain"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    // Modules
    implementation(project(":core:core-commons"))
    implementation(project(":data"))

    // Libraries
    implementation(DomainDependencies.libs)
    kapt(DomainDependencies.compilers)
    testImplementation(DomainDependencies.testLibs)
    androidTestImplementation(DomainDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}