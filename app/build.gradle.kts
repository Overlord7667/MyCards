plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
}

android {
    namespace = "com.betelgeuse.corp.mycards"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.betelgeuse.corp.mycards"
        minSdk = 27
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
    implementation("com.android.volley:volley:1.2.1")
    implementation ("androidx.compose.ui:ui:1.6.4")
    implementation ("androidx.compose.material:material:1.6.4")
    implementation ("androidx.compose.ui:ui-tooling:1.6.4")
    implementation ("androidx.activity:activity-compose:1.8.2")

    implementation ("androidx.navigation:navigation-compose:2.7.7")

    implementation ("io.coil-kt:coil-compose:1.4.0")

    implementation ("androidx.room:room-runtime:2.6.1")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation ("io.insert-koin:koin-core:3.1.2")
    implementation ("io.insert-koin:koin-android:3.1.2")

    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation ("androidx.paging:paging-runtime:3.2.1")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.chromium.net:cronet-embedded:119.6045.31")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}