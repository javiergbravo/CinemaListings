object TestDependencies {
    val testLibs = arrayListOf<String>().apply {
        add(Libs.TestLibrary.truth)
        add(Libs.TestLibrary.junit)
        add(Libs.TestLibrary.turbine)
        add(Libs.TestLibrary.coroutinesTest)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        add(Libs.TestLibrary.truth)
        add(Libs.TestLibrary.supportJunit)
        add(Libs.TestLibrary.espresso)
        add(Libs.TestLibrary.coroutinesTest)
        add(Libs.TestLibrary.daggerHiltTest)
    }
}

object PresentationDependencies {

    val libs = arrayListOf<String>().apply {
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

    val compilers = arrayListOf<String>().apply {
        add(Libs.Compiler.glideCompiler)
        add(Libs.Compiler.hiltCompiler)
        add(Libs.Compiler.hiltDaggerCompiler)
    }

    val testLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.testLibs)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.androidTestLibs)
    }
}

object DomainDependencies {

    val libs = arrayListOf<String>().apply {
        add(Libs.Library.coroutinesCore)
        add(Libs.Library.coroutinesAndroid)
        add(Libs.Library.daggerHilt)
    }

    val compilers = arrayListOf<String>().apply {
        add(Libs.Compiler.hiltCompiler)
        add(Libs.Compiler.hiltDaggerCompiler)
    }

    val testLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.testLibs)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.androidTestLibs)
    }
}

object DataDependencies {

    val libs = arrayListOf<String>().apply {
        add(Libs.Library.coroutinesCore)
        add(Libs.Library.coroutinesAndroid)
        add(Libs.Library.retrofit)
        add(Libs.Library.daggerHilt)
    }

    val compilers = arrayListOf<String>().apply {
        add(Libs.Compiler.hiltCompiler)
        add(Libs.Compiler.hiltDaggerCompiler)
    }

    val testLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.testLibs)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.androidTestLibs)
    }
}

object RemoteDependencies {
    val libs = arrayListOf<String>().apply {
        add(Libs.Library.coroutinesCore)
        add(Libs.Library.coroutinesAndroid)
        add(Libs.Library.moshiKt)
        add(Libs.Library.moshiConverter)
        add(Libs.Library.okHttp)
        add(Libs.Library.interceptor)
        add(Libs.Library.retrofit)
        add(Libs.Library.daggerHilt)
    }

    val compilers = arrayListOf<String>().apply {
        add(Libs.Compiler.moshiCodegen)
        add(Libs.Compiler.hiltCompiler)
        add(Libs.Compiler.hiltDaggerCompiler)
    }

    val testLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.testLibs)
        add(Libs.TestLibrary.mockito)
    }

    val androidTestLibs = arrayListOf<String>().apply {
        addAll(TestDependencies.androidTestLibs)
    }
}