import Configurations.COMPILE_SDK_VERSION
import Configurations.MIN_SDK_VERSION
import Configurations.TARGET_SDK_VERSION
import Configurations.VERSION_CODE
import Configurations.VERSION_NAME

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.elmepa.android.hilt)
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
        android.buildFeatures.buildConfig = true

        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }

    buildTypes {
        viewBinding.enable = true
        dataBinding.enable = true
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidxComposeCompiler.get()
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:model"))

    implementation(project(":feature:home:data"))
    implementation(project(":feature:home:domain"))
    implementation(project(":feature:home:ui"))

    implementation(project(":feature:support:support-data"))
    implementation(project(":feature:support:support-domain"))
    implementation(project(":feature:support:support-ui"))

    implementation(project(":feature:personnel:personnel-data"))
    implementation(project(":feature:personnel:personnel-database"))
    implementation(project(":feature:personnel:personnel-domain"))
    implementation(project(":feature:personnel:personnel-ui"))

    implementation(project(":feature:syllabus"))
    implementation(project(":feature:syllabusv2:syllabus-ui"))

    implementation(project(":feature:news"))
    implementation(project(":feature:students"))
    implementation(project(":feature:department"))
    implementation(project(":feature:web"))

    implementation(libs.viewModelLifecycle)
    implementation(libs.liveDataLifecycle)
    implementation(libs.lifecycle.common)

    implementation(libs.fragment.navigation)
    implementation(libs.ui.navigation)
    implementation(libs.navigation.fragment.compose)

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
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
