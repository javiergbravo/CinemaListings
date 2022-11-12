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
}

dependencies {
    implementation(project(":core:core-commons"))

    debugImplementation(Libs.TestLibrary.coroutinesTest)
    debugImplementation(Libs.TestLibrary.arch)
    debugImplementation(Libs.TestLibrary.mockWebServer)
}