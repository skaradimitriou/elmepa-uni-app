plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
}

android {
    namespace = "com.elmepa.home.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))
}
