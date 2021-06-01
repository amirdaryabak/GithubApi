plugins {
    id("com.android.application")
    id("androidx.navigation.safeargs")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        applicationId("com.amirdaryabak.githubapi")
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode(Versions.versionCode)
        versionName(Versions.versionName)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(Dependencies.App.kotlinStdLib)
    implementation(Dependencies.App.core)
    implementation(Dependencies.App.appCompat)
    implementation(Dependencies.App.material)
    implementation(Dependencies.App.constraintLayout)
    implementation(Dependencies.App.coordinatorLayout)
    implementation(Dependencies.App.navigationFragment)
    implementation(Dependencies.App.navigationUi)
    implementation(Dependencies.App.hilt)
    kapt(Dependencies.App.hiltCompiler)
    implementation(Dependencies.App.hiltViewModel)
    kapt(Dependencies.App.hiltJetpackCompiler)
    implementation(Dependencies.App.room)
    implementation(Dependencies.App.viewModel)
    implementation(Dependencies.App.liveDataKtx)
    implementation(Dependencies.App.timber)
    implementation(Dependencies.App.eventbus)
    implementation(Dependencies.App.securityCrypto)

}