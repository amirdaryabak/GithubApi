plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion(Versions.buildToolsVersion)

    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode(Versions.versionCode)
        versionName(Versions.versionName)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
}

dependencies {
    implementation(Dependencies.Data.kotlinStdLib)
    implementation(Dependencies.Data.core)
    implementation(Dependencies.Data.room)
    kapt(Dependencies.Data.roomCompiler)
    implementation(Dependencies.Data.roomKtx)
    implementation(Dependencies.Data.inject)
    implementation(Dependencies.Data.retrofit)
    implementation(Dependencies.Data.retrofitConverterGson)
    implementation(Dependencies.Data.okhttpLoggingInterceptor)
    implementation(Dependencies.Data.liveDataKtx)
    implementation(Dependencies.Data.timber)
}