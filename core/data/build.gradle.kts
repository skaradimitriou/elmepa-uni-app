plugins {
    alias(libs.plugins.elmepa.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.elmepa.android.hilt)
    alias(libs.plugins.elmepa.android.room)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.googleServices)
}

android {
    namespace = "com.stathis.data"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:datastore"))
    implementation(project(":core:model"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    implementation(libs.firebase.firestore)
    implementation(libs.firebase.storage)

    implementation(libs.jsoup)

    implementation(libs.timber)
    implementation(libs.paging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
