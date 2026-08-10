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
include(":feature:personnel:personnel-data")
include(":feature:personnel:personnel-database")
include(":feature:personnel:personnel-domain")
include(":feature:personnel:personnel-ui")

//Syllabus
include(":feature:syllabus:syllabus-ui")

//Students
include(":feature:students:presentation")
include(":feature:students:data")
include(":feature:students:domain")

// News
include(":feature:news:news-data")
include(":feature:news:news-database")
include(":feature:news:news-domain")
include(":feature:news:news-ui")

include(":feature:web")
include(":feature:department")
include(":core:datastore")
