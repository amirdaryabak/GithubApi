buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.33-beta")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

task ("clean") {
    delete(rootProject.buildDir)
}