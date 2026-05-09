package com.elmepa.convention.plugins

import com.elmepa.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                withPlugin("com.android.library") {
                    apply("com.google.dagger.hilt.android")
                }

                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("implementation", (libs.findLibrary("hilt.android").get()))
                add("ksp", (libs.findLibrary("hilt.compiler").get()))
            }
        }
    }
}
