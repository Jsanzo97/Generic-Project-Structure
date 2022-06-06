import org.gradle.api.JavaVersion

object Plugins {
    const val android = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val javaLibrary = "java-library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlin = "kotlin"
    const val kapt = "kotlin-kapt"
    const val gradleVersions = "com.github.ben-manes.versions"
    const val navigationKotlin = "androidx.navigation.safeargs.kotlin"
}

object Versions {
    const val kotlin = "1.5.30"
    const val coroutines = "1.3.2"
    const val androidGradlePlugin = "7.0.2"
    const val gradleVersions = "0.25.0"

    const val minSdk = 23
    const val targetSdk = 31

    val sourceCompatibility = JavaVersion.VERSION_1_8
    val targetCompatibility = JavaVersion.VERSION_1_8

    const val appCompat = "1.1.0"
    const val coreKtx = "1.1.0"
    const val material = "1.1.0-alpha10"
    const val constraintLayout = "2.0.0-beta3"
    const val lifecycle = "2.2.0"
    const val room = "2.4.0-rc01"
    const val navigation = "2.4.0"

    const val arrow = "0.10.0"
    const val timber = "4.7.1"
    const val koin = "2.0.1"

    const val moshi = "1.12.0"
    const val retrofit = "2.6.2"
    const val okhttp = "4.2.1"
    const val chucker = "3.0.1"

    const val gson = "2.8.2"
    const val glide = "4.9.0"

    const val debugDb = "1.0.6"

    const val mockitoCore = "4.6.0"
    const val mockitoKotlin = "3.2.0"
    const val mockitoInline = "2.21.0"
    const val robolectric = "4.8"
    const val coroutinesTest = "1.6.2"
    const val jUnit = "4.13.2"
    const val jUnitExt = "1.1.3"
}

object BuildTools {
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val gradleVersions = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleVersions}"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Libs {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val arrowCore = "io.arrow-kt:arrow-core-data:${Versions.arrow}"
    const val koin = "org.koin:koin-core:${Versions.koin}"
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidScope = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object Kapt {
    const val lifecycle = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val room = "androidx.room:room-compiler:${Versions.room}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
}

object DebugLibs {
    const val debugDb = "com.amitshekhar.android:debug-db:${Versions.debugDb}"
    const val chucker = "com.github.chuckerteam.chucker:library:${Versions.chucker}"
}

object Test {
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockitoCore}"
    const val mockitoKotlin = "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val jUnitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
}
