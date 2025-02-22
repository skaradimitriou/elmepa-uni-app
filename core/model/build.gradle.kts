plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
    alias(libs.plugins.kapt)
    alias(libs.plugins.parcelize)
}

android {
    namespace = "com.stathis.model"
}
