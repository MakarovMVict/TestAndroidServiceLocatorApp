plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.testapppattern"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.testapppattern"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":app-navigation"))
    implementation(project(":core:core-navigation"))
    implementation(project(":core:core-ui"))
    implementation(project(":core:core-di"))
    implementation(project(":base-component-lifecycle"))
    // features
    implementation(project(":features:main-api"))
    implementation(project(":features:payments-api"))
    implementation(project(":features:settings-api"))

    implementation(project(":features:main-impl"))
    implementation(project(":features:payments-impl"))
    implementation(project(":features:settings-impl"))

    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended")
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
