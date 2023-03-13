plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.hilt)
    id(Plugins.secrets)
}

android {
    compileSdk = ProjectConfig.compileVersion

    namespace = "${ProjectConfig.mainPackage}.data.remote"

    defaultConfig {
        minSdk = ProjectConfig.minVersion
    }
}

secrets {
    propertiesFileName = "secrets.properties"
    defaultPropertiesFileName = "secrets.defaults.properties"
}

dependencies {
    implementation(project(":core:core-commons"))
    implementation(project(":core:core-data-remote"))

    implementation(RemoteDependencies.libs)
    kapt(RemoteDependencies.compilers)

    testImplementation(RemoteDependencies.testLibs)
}

kapt {
    correctErrorTypes = true
}