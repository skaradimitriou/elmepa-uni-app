import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.elmepa.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
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
            implementationClass = "com.elmepaconvention.plugins.AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "elmepa.android.koin"
            implementationClass = "com.elmepaconvention.plugins.AndroidKoinConventionPlugin"
        }
        register("androidCompose") {
            id = "elmepa.android.compose"
            implementationClass = "com.elmepaconvention.plugins.AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature") {
            id = "elmepa.android.feature"
            implementationClass = "com.elmepaconvention.plugins.AndroidFeatureConventionPlugin"
        }
        
        register("androidRoom") {
            id = "custom.android.room"
            implementationClass = "com.elmepaconvention.plugins.AndroidRoomConventionPlugin"
        }
    }
}
