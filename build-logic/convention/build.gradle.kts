import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.elmepa.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.gradle)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "elmepa.android.library"
            implementationClass = "com.elmepa.convention.plugins.AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "elmepa.android.hilt"
            implementationClass = "com.elmepa.convention.plugins.AndroidHiltConventionPlugin"
        }
        register("androidCompose") {
            id = "elmepa.android.compose"
            implementationClass = "com.elmepa.convention.plugins.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "elmepa.android.feature"
            implementationClass = "com.elmepa.convention.plugins.AndroidFeatureConventionPlugin"
        }

        register("androidRoom") {
            id = "elmepa.android.room"
            implementationClass = "com.elmepa.convention.plugins.AndroidRoomConventionPlugin"
        }
    }
}
