import java.io.FileInputStream
import java.util.Properties

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

        buildConfigField("String", "API_KEY", getApiKey())
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

fun getApiKey(): String {
    val propFile = rootProject.file("./././secrets.properties")
    val properties = Properties()
    properties.load(FileInputStream(propFile))
    return properties["THEMOVIEDB_API_KEY"] as? String ?: ""
}