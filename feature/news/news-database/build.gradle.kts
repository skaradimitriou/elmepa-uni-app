plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
}

android {
    namespace = "com.elmepa.news.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":feature:news:news-domain"))
}
