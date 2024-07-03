plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
}

/**
 * For each release, update these values based on the type of changes:
 *
 * Major Version: For significant or non-backward-compatible changes.
 * Minor Version: For backward-compatible new features or improvements.
 * Patch Version: For small bug fixes or minor updates.
 */

val majorVersion = 4
val minorVersion = 0
val patchVersion = 2

android {
    namespace = "com.stathis.elmepaunivapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stathis.elmepaunivapp"
        minSdk = 27
        targetSdk = 34
        versionCode = majorVersion * 10000 + minorVersion * 100 + patchVersion
        versionName = "$majorVersion.$minorVersion.$patchVersion"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildTypes {
        viewBinding.enable = true
        dataBinding.enable = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(project(":feature:dashboard"))
    implementation(project(":feature:news"))
    implementation(project(":feature:personnel"))
    implementation(project(":feature:syllabus"))
    implementation(project(":feature:students"))
    implementation(project(":feature:department"))
    implementation(project(":feature:support"))
    implementation(project(":feature:web"))

    implementation(libs.viewModelLifecycle)
    implementation(libs.liveDataLifecycle)
    implementation(libs.lifecycle.common)

    implementation(libs.fragment.navigation)
    implementation(libs.ui.navigation)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.perf)
    implementation(platform(libs.firebase.bom))

    implementation(libs.timber)

    implementation(libs.shimmer)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}