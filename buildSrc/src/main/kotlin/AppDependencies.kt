object AppDependencies {

    val appLibraries = arrayListOf<String>().apply {
        add(Libs.Library.timber)
        add(Libs.Library.androidCoreKtx)
        add(Libs.Library.appCompat)
        add(Libs.Library.activityKtx)
        add(Libs.Library.coroutinesCore)
        add(Libs.Library.coroutinesAndroid)
        add(Libs.Library.runtimeKtx)
        add(Libs.Library.material)
        add(Libs.Library.recyclerView)
        add(Libs.Library.glide)
        add(Libs.Library.daggerHilt)
    }

    val appKapt = arrayListOf<String>().apply {
        add(Libs.Compiler.glideCompiler)
        add(Libs.Compiler.hiltCompiler)
        add(Libs.Compiler.hiltDaggerCompiler)
    }

    val appTest = arrayListOf<String>().apply {
        add(Libs.TestyLibrary.truth)
        add(Libs.TestyLibrary.junit)
        add(Libs.TestyLibrary.turbine)
        add(Libs.TestyLibrary.coroutinesTest)
    }

    val androidTest = arrayListOf<String>().apply {
        add(Libs.TestyLibrary.truth)
        add(Libs.TestyLibrary.supportJunit)
        add(Libs.TestyLibrary.espresso)
        add(Libs.TestyLibrary.coroutinesTest)
        add(Libs.TestyLibrary.daggerHiltTest)
    }
}