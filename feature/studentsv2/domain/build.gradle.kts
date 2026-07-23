plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
}

android {
    namespace = "com.students.domain"
}

dependencies {
    implementation(project(":core:domain"))
}
