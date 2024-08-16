import Configurations.COMPILE_SDK_VERSION
import Configurations.MIN_SDK_VERSION
import Configurations.TARGET_SDK_VERSION
import Configurations.VERSION_CODE
import Configurations.VERSION_NAME

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.perf)
}

android {
    namespace = "com.stathis.elmepaunivapp"
    compileSdk = COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.stathis.elmepaunivapp"
        minSdk = MIN_SDK_VERSION
        targetSdk = TARGET_SDK_VERSION
        versionCode = VERSION_CODE
        versionName = VERSION_NAME

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