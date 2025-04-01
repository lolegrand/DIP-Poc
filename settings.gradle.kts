@file:Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories.apply {
        google()
        mavenCentral()
    }
}

rootProject.name = "DIP-Poc"

include(":application")

include(":library:api")
include(":library:prod")
include(":library:dev")
include(":library:demo")
