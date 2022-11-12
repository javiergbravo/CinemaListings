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