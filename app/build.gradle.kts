plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp) // KSP 플러그인 사용
}

android {
    namespace = "com.choo.ex_aac"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.choo.ex_aac"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.room.runtime) // Room Runtime
    implementation(libs.room.ktx)     // Room KTX
    ksp(libs.room.compiler)           // Room Compiler (KSP)

    implementation(libs.lifecycle.viewmodel.ktx) // ViewModel KTX
    implementation(libs.lifecycle.livedata.ktx)  // LiveData 사용
    implementation(libs.lifecycle.runtime.ktx)  // Lifecycle Runtime KTX
    implementation(libs.paging.runtime)  // Add this line
}