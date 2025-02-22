plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
}

android {
    namespace = "com.stathis.domain"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core::data"))
    implementation(project(":core::model"))

    implementation(libs.paging)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
