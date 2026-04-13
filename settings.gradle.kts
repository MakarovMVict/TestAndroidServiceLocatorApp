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

rootProject.name = "TestAppPattern"
include(":app")
include(":core:core-di")
include(":core:core-ui")
include(":core:core-navigation")
include(":features:main-api")
include(":features:main-impl")
include(":features:payments-api")
include(":features:payments-impl")
include(":features:settings-api")
include(":features:settings-impl")
include(":base-component-lifecycle")
include(":app-navigation")
