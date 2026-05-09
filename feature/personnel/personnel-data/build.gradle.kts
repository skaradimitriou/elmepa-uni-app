plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
}

android {
    namespace = "com.elmepa.personnel.data"
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":core:domain"))
    implementation(project(":core:datastore"))

    implementation(project(":feature:personnel:personnel-database"))
    implementation(project(":feature:personnel:personnel-domain"))

    implementation(libs.firebase.firestore)

    implementation(libs.jsoup)
}
