plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
}

android {
    namespace = "com.zhadko.productsmanager"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zhadko.productsmanager"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(libs.core)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraint)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle)

    //Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.02.00")
    implementation(composeBom)
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(libs.activity.compose)
    implementation(libs.foundation)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.navigation.compose)

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")

    //Coil
    implementation(libs.coil.compose)

    //JetpackNavigation
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    //Dagger2
    implementation("com.google.dagger:dagger:2.50")
    kapt("com.google.dagger:dagger-compiler:2.50")

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Room
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    implementation("com.google.code.gson:gson:2.10.1")
}