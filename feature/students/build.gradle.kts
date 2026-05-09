plugins {
    alias(libs.plugins.elmepa.android.feature)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.stathis.students"

    buildTypes {
        viewBinding.enable = true
        dataBinding.enable = true
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.viewModelLifecycle)
    implementation(libs.liveDataLifecycle)
    implementation(libs.lifecycle.common)

    implementation(libs.fragment.navigation)
    implementation(libs.ui.navigation)

    implementation(libs.timber)
    implementation(libs.swipeToRefresh)
    implementation(libs.shimmer)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
