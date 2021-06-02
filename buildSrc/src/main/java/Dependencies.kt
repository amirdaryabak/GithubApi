object Dependencies {

    private const val kotlinVersion = "1.4.10"
    private const val navigationVersion = "2.3.0"
    private const val hiltVersion = "2.35"
    private const val hiltJetpackVersion = "1.0.0-alpha03"
    private const val roomVersion = "2.2.5"
    private const val retrofitVersion = "2.9.0"
    private const val lifeCycleVersion = "2.2.0"
    private const val coroutinesVersion = "1.3.9"

    private object Common {
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val core = "androidx.core:core-ktx:1.3.1"
        const val room = "androidx.room:room-runtime:$roomVersion"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifeCycleVersion"
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object App {
        const val kotlinStdLib = Common.kotlinStdLib
        const val core = Common.core
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val material = "com.google.android.material:material:1.3.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:1.1.0"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:$navigationVersion"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:$navigationVersion"
        const val hilt = "com.google.dagger:hilt-android:$hiltVersion"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$hiltJetpackVersion"
        const val hiltJetpackCompiler = "androidx.hilt:hilt-compiler:$hiltJetpackVersion"
        const val room = Common.room
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifeCycleVersion"
        const val viewModelRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:$lifeCycleVersion"
        const val activityViewModel = "androidx.activity:activity-ktx:1.1.0"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
        const val liveDataKtx = Common.liveDataKtx
        const val timber = Common.timber
        const val eventbus = "org.greenrobot:eventbus:3.2.0"
        const val securityCrypto = "androidx.security:security-crypto:1.1.0-alpha03"
    }

    object Data {
        const val kotlinStdLib = Common.kotlinStdLib
        const val core = Common.core
        const val room = Common.room
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
        const val inject = "javax.inject:javax.inject:1"
        const val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
        const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
        const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
        const val liveDataKtx = Common.liveDataKtx
        const val timber = Common.timber
    }

    object Login {
        const val kotlinStdLib = Common.kotlinStdLib
        const val core = Common.core
    }

}