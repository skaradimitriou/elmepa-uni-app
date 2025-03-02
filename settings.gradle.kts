pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "ElmepaUniApp"
include(":app")
include(":core:common")
include(":core:design-system")
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:model")

//Home
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:ui")

//Support
include(":feature:supportv2:support-data")
include(":feature:supportv2:support-database")
include(":feature:supportv2:support-domain")
include(":feature:supportv2:support-ui")

include(":feature:syllabus")
include(":feature:personnel")
include(":feature:news")
include(":feature:support")
include(":feature:web")
include(":feature:students")
include(":feature:department")
include(":core:datastore")
