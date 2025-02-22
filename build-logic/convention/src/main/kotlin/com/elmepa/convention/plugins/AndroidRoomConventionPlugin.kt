package com.elmepa.convention.plugins

import androidx.room.gradle.RoomExtension
import com.elmepa.convention.ext.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("androidx.room")
                apply("com.google.devtools.ksp")
            }

            extensions.configure<KspExtension> {
                arg("room.generateKotlin", "true")
            }

            extensions.configure<RoomExtension> {
                schemaDirectory("$projectDir/schemas")
            }

            dependencies {
                add("implementation", libs.findLibrary("room.runtime").get())
                add("implementation", libs.findLibrary("room.ktx").get())
                add("implementation", libs.findLibrary("room.paging").get())
                add("ksp", libs.findLibrary("room.compiler").get())
            }
        }
    }
}
