plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
}

android {
    namespace = "com.elmepa.support.database"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":feature:support:support-domain"))
}
