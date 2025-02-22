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
include(":core:data")
include(":core:domain")
include(":core:database")
include(":core:model")

include(":feature:syllabus")
include(":feature:personnel")
include(":feature:news")
include(":feature:support")
include(":feature:dashboard")
include(":feature:web")
include(":feature:students")
include(":feature:department")
include(":core:datastore")
