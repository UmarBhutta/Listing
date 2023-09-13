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
    }
}

rootProject.name = "Listing"
include(":app")
include(":common")
include(":feature")
include(":feature:userlist")
include(":feature:users_list")
include(":feature:users_list:api")
include(":feature:users_list:domain")
include(":feature:users_list:presentation")
include(":domain")
include(":domain:network")
