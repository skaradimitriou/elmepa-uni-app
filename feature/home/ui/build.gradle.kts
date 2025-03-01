plugins {
    alias(libs.plugins.elmepa.android.feature)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.elmepa.android.compose)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.elmepa.home.ui"
}

dependencies {
    implementation(project(":feature:home:domain"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:design-system"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    implementation(libs.fragment.navigation)

    implementation(libs.viewModelLifecycle)
    implementation(libs.lifecycle.common)
}
