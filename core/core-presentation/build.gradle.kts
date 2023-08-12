plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.core.presentation"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":core:core-commons"))

    implementation(Libs.Library.timber)
    implementation(Libs.Library.androidCoreKtx)
    implementation(Libs.Library.appCompat)
    implementation(Libs.Library.activityKtx)
    implementation(Libs.Library.coroutinesCore)
    implementation(Libs.Library.coroutinesAndroid)
    implementation(Libs.Library.runtimeKtx)
    implementation(Libs.Library.material)
    implementation(Libs.Library.recyclerView)
    implementation(Libs.Library.glide)
    implementation(Libs.Library.daggerHilt)

    kapt(Libs.Compiler.glideCompiler)
    kapt(Libs.Compiler.hiltCompiler)
    kapt(Libs.Compiler.hiltDaggerCompiler)
}

kapt {
    correctErrorTypes = true
}