plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-android")
    id ("kotlin-kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.example.evoo"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.evoo"
        minSdk = 24
        //noinspection OldTargetApi
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

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v270)
    implementation(libs.androidx.runtime)


    implementation (libs.gson)


    // OpenStreetMap
    //implementation (libs.osmdroid.osmdroid.android)
    //implementation (libs.accompanist.permissions.v0320)

    implementation (libs.core.ktx.v1120)
    implementation (libs.play.services.location)
    implementation (libs.osmdroid.osmdroid.android)
    implementation (libs.accompanist.permissions.v0320)

    // Play Services Location mit backward compatibility
    implementation (libs.play.services.location)

    // OSMDroid
    implementation (libs.osmdroid.osmdroid.android)

    // Accompanist Permissions
    implementation (libs.accompanist.permissions.v0320)

    // Fügen Sie diese Zeile hinzu für AndroidX Core
    implementation (libs.androidx.core.ktx.v1160)

    // Room mit TOML-Referenzen
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose.v291)
    implementation(libs.androidx.runtime.livedata.v183)

    // Ktor Client
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Ktor Client Logging Abhängigkeit
    implementation("io.ktor:ktor-client-logging:3.2.3")

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Extended Icons
    implementation(libs.androidx.material.icons.extended)

    // Koin
    implementation("io.insert-koin:koin-android:4.1.0")
    implementation("io.insert-koin:koin-androidx-navigation:4.1.0")
    implementation("io.insert-koin:koin-androidx-compose:4.1.0")
    testImplementation("io.insert-koin:koin-test-junit4:4.1.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")

    implementation("androidx.compose.material3:material3:1.3.2")

    implementation("io.coil-kt:coil-compose:2.7.0")

    // Ktor Client Logging Abhängigkeit
    implementation("io.ktor:ktor-client-logging:3.2.3")

    // Kotlinx Serialization
    implementation(libs.kotlinx.serialization.json)

    // Extended Icons
    implementation(libs.androidx.material.icons.extended)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}