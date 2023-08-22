pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() //Shimmer
    }
}

rootProject.name = "FoodyFoodyFoody"
include (":app")
include(":network")
include(":database")
include(":model")
include(":data")
include(":test")
include(":domain")
