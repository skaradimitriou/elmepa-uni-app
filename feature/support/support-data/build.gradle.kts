plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
}

android {
    namespace = "com.elmepa.support.data"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:datastore"))

    implementation(project(":feature:support:support-database"))
    implementation(project(":feature:support:support-domain"))

    implementation(libs.firebase.firestore)

    implementation(libs.jsoup)
}
