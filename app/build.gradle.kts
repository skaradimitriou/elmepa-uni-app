plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
    id("kotlin-kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.stathis.elmepaunivapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stathis.elmepaunivapp"
        minSdk = 27
        targetSdk = 34
        versionCode = 9
        versionName = "9.0"

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

    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":model"))
    implementation(project(":feature"))

    implementation(libs.viewModelLifecycle)
    implementation(libs.liveDataLifecycle)
    implementation(libs.lifecycle.common)

    implementation(libs.fragment.navigation)
    implementation(libs.ui.navigation)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.timber)

    //old dependencies

    implementation(libs.shimmer)
    implementation(libs.jsoup)
    implementation(libs.glide)
    implementation(libs.circleImgView)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.swipeToRefresh)
    implementation(libs.gson)
    implementation(libs.preference.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}