plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.core.data.remote"

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

    debugImplementation(Libs.TestLibrary.coroutinesTest)
    debugImplementation(Libs.TestLibrary.arch)
    debugImplementation(Libs.TestLibrary.mockWebServer)
}