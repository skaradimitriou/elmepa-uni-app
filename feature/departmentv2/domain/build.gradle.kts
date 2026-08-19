plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
}

android {
    namespace = "com.elmepa.departmentv2.domain"
}

dependencies {
    implementation(project(":core:domain"))
}
