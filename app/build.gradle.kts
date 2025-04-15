plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.evoo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.evoo"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)


    // OpenStreetMap
    //implementation (libs.osmdroid.osmdroid.android)
    //implementation (libs.accompanist.permissions.v0320)

    implementation (libs.core.ktx.v1120)
    implementation (libs.play.services.location)
    implementation (libs.osmdroid.osmdroid.android)
    implementation (libs.accompanist.permissions.v0320)

    // Play Services Location mit backward compatibility
    implementation ("com.google.android.gms:play-services-location:21.0.1")

    // OSMDroid
    implementation ("org.osmdroid:osmdroid-android:6.1.16")

    // Accompanist Permissions
    implementation ("com.google.accompanist:accompanist-permissions:0.32.0")

    // Fügen Sie diese Zeile hinzu für AndroidX Core
    implementation ("androidx.core:core-ktx:1.12.0")



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}