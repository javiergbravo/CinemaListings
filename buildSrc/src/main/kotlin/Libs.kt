object Libs {

    internal object Version {
        // Core
        const val coreKtx = "1.10.1"
        const val appCompat = "1.6.1"
        const val coroutines = "1.8.1"

        // Logs
        const val timber = "5.0.1"

        // UI
        const val material = "1.9.0"
        const val constraintLayout = "2.1.4"
        const val recyclerView = "1.3.2"
        const val activity = "1.7.2"
        const val lifecycle = "2.6.2"
        const val glide = "4.16.0"

        // API
        const val okHttp = "4.12.0"
        const val retrofit = "2.11.0"

        // JSO
        const val moshi = "1.15.1"
        const val converterMoshi = "2.11.0"

        // Dependency injection
        const val dagger = "2.50"
        const val hilt = "1.2.0"

        // Test
        const val truth = "1.4.4"
        const val jUnit = "4.13.2"
        const val turbine = "1.1.0"
        const val supportTest = "1.2.1"
        const val arch = "2.2.0"
        const val espresso = "3.6.1"
        const val mockito = "5.13.0"
        const val mockWebServer = "4.12.0"
    }

    object Library {
        const val androidCoreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Version.appCompat}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
        const val material = "com.google.android.material:material:${Version.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Version.recyclerView}"
        const val activityKtx = "androidx.activity:activity-ktx:${Version.activity}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
        const val daggerHilt = "com.google.dagger:hilt-android:${Version.dagger}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okHttp}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val moshiKt = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}"
    }

    object TestLibrary {
        const val truth = "com.google.truth:truth:${Version.truth}"
        const val junit = "junit:junit:${Version.jUnit}"
        const val turbine = "app.cash.turbine:turbine:${Version.turbine}"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
        const val supportJunit = "androidx.test.ext:junit:${Version.supportTest}"
        const val arch = "androidx.arch.core:core-testing:${Version.arch}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val mockito = "org.mockito:mockito-core:${Version.mockito}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
        const val daggerHiltTest = "com.google.dagger:hilt-android-testing:${Version.dagger}"
    }

    object Compiler {
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Version.glide}"
        const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        const val hiltDaggerCompiler = "com.google.dagger:hilt-android-compiler:${Version.dagger}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Version.hilt}"
    }
}