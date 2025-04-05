package com.elmepa.convention.plugins

import com.elmepa.convention.ext.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class AndroidFeatureConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("elmepa.android.library")
            }

            dependencies {
                //add("implementation", project(":core:design-system"))
                add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-runtime.compose").get())
                add("implementation", libs.findLibrary("androidx-lifecycle-viewModel.compose").get())

                add("implementation", libs.findLibrary("kotlinx-collections-immutable").get())
            }
        }
    }
}
