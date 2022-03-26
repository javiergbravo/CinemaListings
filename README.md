# [ActiaSystemsMobile](https://github.com/javiergbravo/ActiaSystemsMobile)

## 🌟 About

It simply app that loads a billboard from TheMovieDatabase Api and display inside a RecyclerView as
a GridLayout. This repository shows a clean code with MVVM architecture and use professional
libraries with Kotlin language. Different models are used for each architecture layer with mappers
whose work is transforming the objects to the next layer. Each layer is represented by a module and
also exist a core module to be the base of app modules.

![OS](https://img.shields.io/badge/OS-Android-3DDC84?logo=Android) ![Language](https://img.shields.io/badge/Language-Kotlin-0095D5?logo=kotlin) ![Environment](https://img.shields.io/badge/Environment-Android_Studio-3DDC84?logo=android-studio)
![Architecture](https://img.shields.io/badge/Architecture-MVVM-brightgreen) ![View](https://img.shields.io/badge/View-ViewBinding-00B0EA) ![Observable](https://img.shields.io/badge/Observable-StateFlow-CF202E)

## 📜 Project requirements

### Instructions

To launch the application you need to get an API key from [TheMovieDb](https://www.themoviedb.org),
registration is required. You need to add this line inside your *local.properties*:

`THEMOVIEDB_API_KEY = "your_api_key"`

### Versions

![JavaVersion](https://img.shields.io/badge/Java-1.8-%2325c2c6) ![Gradle](https://img.shields.io/badge/Gradle-7.2-%23%2351db71) ![AndroidGradle](https://img.shields.io/badge/AndroidGradle-7.1.2-%230ed490) ![GradleJDK](https://img.shields.io/badge/GradleJDK-11-%13386b)
![CompileSdk](https://img.shields.io/badge/CompileSdk-31-%230095D5) ![TarjetSdk](https://img.shields.io/badge/TarjetSdk-31-%23f0758f) ![minSdk](https://img.shields.io/badge/minSdk-24-CF202E) ![AndroidSdk](https://img.shields.io/badge/AndroidSdk-33.0.0-%23ec3266) ![AndroidCompileSdk](https://img.shields.io/badge/AndroidCompileSdk-30-green)

## 📚 Libraries used

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android
  development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous
  and more.
- [Glide](https://bumptech.github.io/glide) - An image loading library for Android.
- [Material Components for Android](https://github.com/material-components/material-components-android)
    - Modular and customizable Material Design UI components for Android.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) -
  Collection of libraries that help you design robust, testable, and maintainable apps.
    - [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - Data objects
      that notify views when the underlying database changes.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores
      UI-related data that isn't destroyed on UI changes.
    - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a
      binding class for each XML layout file present in that module and allows you to more easily
      write code that interacts with views.
- [Moshi](https://github.com/square/moshi) - Library that can be used to convert Java Objects into
  their JSON representation and JSON string to an equivalent Java object.
- [OkHttpClient](https://square.github.io/okhttp) - HTTP client that allows all requests to the same
  host to share a socket and response caching avoids the network completely for repeat request.
- [Retrofit](https://square.github.io/retrofit) - Turns HTTP API into a Java interface.
- [Dagger 2](https://dagger.dev/) - Dependency Injection Framework.
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Jetpack
  Dependency Injection Framework.

## 📁 Modules

Modules have been created to respect the layers of the architecture. First there are 3 main modules:
data, domain and presentation*. At the same time, the project has 4 more modules, with prefix '
core', where provide the base of app layers, and there are one 'commons' module that provide some
features to the rest of modules.

*Note: Presentation module is called 'App'

| Module name | Type | Modules visibility | Description |
| --- | --- | --- | --- |
| app | Android Application | :domain :core-presentation :core-commons | Ui and base application features |
| domain | Java/Kotlin Library | :data :core-domain :core-commons | Use-cases |
| data | Java/Kotlin Library | :core-data :core-commons | Data sources (network) | 
| core-commons | Java/Kotlin Library | - | Commons resources and classes that can be used in different applications |
| core-presentation | Java/Kotlin Library | :core-commons | Base activity, adapters, managers to presentation layer |
| core-domain | Java/Kotlin Library | :core-commons | Base classes to domain layer (now is empty) |
| core-data | Java/Kotlin Library | :core-commons | Base repository |

## 📝 Test

Currently there are two unit tests. They have been created out of necessity when the application was
not working as expected.

- **MoshiAdapterTest** inside *:data* module to check if the date is parsed correctly.
- **MovieFiltersTest** inside *:app* module to check if the release year of movies is filtered
  correctly.

## Extras

There are some extras implemented

- [x] Add Dark/Light mode support
- [x] Search by name
- [x] Filter by year
- [x] Remove movies from the list
- [x] Proguard obfuscation in release build *(but not tested)*
