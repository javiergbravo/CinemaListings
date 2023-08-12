plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.data"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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