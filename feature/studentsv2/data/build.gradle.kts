plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
}

android {
    namespace = "com.students.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))

    implementation(project(":feature:studentsv2:domain"))

    implementation(libs.firebase.firestore)
    implementation(libs.jsoup)
}
