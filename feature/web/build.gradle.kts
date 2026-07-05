plugins {
    alias(libs.plugins.elmepa.android.feature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.stathis.web"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:design-system"))
}
