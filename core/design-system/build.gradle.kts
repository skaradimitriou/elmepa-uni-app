plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.elmepa.android.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.elmepa.designsystem"
}

dependencies {
    implementation(project(":core:common"))
}
