plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
}

android {
    namespace = "com.elmepa.students.domain"
}

dependencies {
    implementation(project(":core:domain"))
}
