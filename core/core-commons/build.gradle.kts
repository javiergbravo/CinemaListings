plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
}

android {
    compileSdk = ProjectConfig.compileVersion
    namespace = "${ProjectConfig.mainPackage}.core.commons"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(Libs.Library.timber)
    implementation(Libs.Library.coroutinesCore)

    debugImplementation(Libs.TestLibrary.junit)
    debugImplementation(Libs.TestLibrary.coroutinesTest)

    testImplementation(Libs.TestLibrary.truth)
}