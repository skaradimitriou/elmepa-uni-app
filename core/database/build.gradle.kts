plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.stathis.database"
}

dependencies {
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
