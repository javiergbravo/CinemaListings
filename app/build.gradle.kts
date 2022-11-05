plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = ProjectConfig.compileVersion
    buildToolsVersion = ProjectConfig.buildToolsVersion

    namespace = ProjectConfig.appId

    defaultConfig {
        multiDexEnabled = true
        applicationId = ProjectConfig.appId
        minSdk = ProjectConfig.minVersion
        targetSdk = ProjectConfig.targetVersion
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = ProjectConfig.testInstrumentationRunner
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
            isMinifyEnabled = false
            //applicationIdSuffix(".debug")
            //versionNameSuffix("-debug")
        }

        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), ProjectConfig.proguardRules)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
        }
    }
}

dependencies {
    // Modules
    implementation(project(":core:core-commons"))
    implementation(project(":core:core-presentation"))
    implementation(project(":domain"))

    // Libraries
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

    testImplementation(Libs.TestyLibrary.truth)
    testImplementation(Libs.TestyLibrary.junit)
    testImplementation(Libs.TestyLibrary.turbine)
    testImplementation(Libs.TestyLibrary.coroutinesTest)

    androidTestImplementation(Libs.TestyLibrary.truth)
    androidTestImplementation(Libs.TestyLibrary.supportJunit)
    androidTestImplementation(Libs.TestyLibrary.espresso)
    androidTestImplementation(Libs.TestyLibrary.coroutinesTest)
    androidTestImplementation(Libs.TestyLibrary.daggerHiltTest)
}

kapt {
    correctErrorTypes = true
}