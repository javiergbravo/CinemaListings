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
    implementation(PresentationDependencies.libs)
    kapt(PresentationDependencies.compilers)
    testImplementation(PresentationDependencies.testLibs)
    androidTestImplementation(PresentationDependencies.androidTestLibs)
}

kapt {
    correctErrorTypes = true
}