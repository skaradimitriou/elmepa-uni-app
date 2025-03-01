package com.elmepa.convention.plugins

import com.android.build.gradle.LibraryExtension
import com.elmepa.convention.ext.configureKotlinAndroid
import com.elmepa.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            dependencies.apply {
                add("implementation", (libs.findLibrary("timber").get()))
                add("testImplementation", kotlin("test"))
            }
        }
    }
}
