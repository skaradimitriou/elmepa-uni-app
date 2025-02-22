plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.kapt)
}

android {
    namespace = "com.stathis.datastore"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.dataStore)
    implementation(libs.gson)
}
