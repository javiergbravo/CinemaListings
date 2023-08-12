plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = ProjectConfig.appId

    buildFeatures {
        buildConfig = true
    }

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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        viewBinding = true
    }
    packaging {
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