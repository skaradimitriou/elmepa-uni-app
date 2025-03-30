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
include(":feature:support:support-data")
include(":feature:support:support-database")
include(":feature:support:support-domain")
include(":feature:support:support-ui")

// Personnel
include(":feature:personnelv2:personnel-data")
include(":feature:personnelv2:personnel-database")
include(":feature:personnelv2:personnel-domain")
include(":feature:personnelv2:personnel-ui")


include(":feature:syllabus")
include(":feature:news")
include(":feature:web")
include(":feature:students")
include(":feature:department")
include(":core:datastore")
