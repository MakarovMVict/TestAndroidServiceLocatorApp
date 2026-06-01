pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
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
include(":features:dfeature-api")
include(":features:dfeature-impl")
include(":base-component-lifecycle")
include(":app-navigation")
