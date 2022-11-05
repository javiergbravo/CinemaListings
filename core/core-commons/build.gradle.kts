plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ProjectConfig.compileVersion
    namespace = "${ProjectConfig.mainPackage}.core.commons"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

dependencies {
    implementation(Libs.Library.timber)
    implementation(Libs.Library.coroutinesCore)
}