pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CinemaListings"
include(":app")
include(":core")
include(":data")
include(":data:data-remote")
include(":domain")
include(":core:core-presentation")
include(":core:core-commons")
include(":core:core-data-remote")
