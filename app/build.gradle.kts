import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(BuildTools.navigationSafeArgs)
    }
}

apply(plugin = Plugins.navigationKotlin)

plugins {
    id(Plugins.android)
    id(Plugins.kotlinAndroid)
    id(Plugins.kapt)
}

val VERSION_MAJOR: String by project
val VERSION_MINOR: String by project
val VERSION_PATCH: String by project
val versionMajor = VERSION_MAJOR.toInt()
val versionMinor = VERSION_MINOR.toInt()
val versionPatch = VERSION_PATCH.toInt()

val appVersionCode = versionMajor * 1_000_000 + versionMinor * 1_000 + versionPatch
val appVersionName = "$versionMajor.$versionMinor.$versionPatch"

configure<BaseAppModuleExtension> {
    compileSdk = Versions.targetSdk
    lintOptions.isAbortOnError = false
    testOptions.unitTests.apply {
        isIncludeAndroidResources = true
    }

    defaultConfig.apply {
        applicationId = "com.example.movielist"
        minSdkVersion(Versions.minSdk)
        targetSdkVersion(Versions.targetSdk)
        versionCode = appVersionCode
        versionName = appVersionName
        resValue("string", "app_name", "@string/application_name")
        resourceConfigurations.add("en")
        resourceConfigurations.add("es")
        multiDexEnabled = true
        buildConfigField("String", "SERVER_ENDPOINT", "\"https://api.themoviedb.org/3/movie/\"")
        buildConfigField("String", "SERVER_API_KEY", "\"3ce5fa18330f82a0e8c84eea49508b46\"")
    }

    signingConfigs.apply {
        val KEYSTORE_PASSWORD: String by project
        val KEY_PASSWORD: String by project
        getByName("debug") {
            try {
                storeFile = file("${project.rootDir}/keystore.jks")
                storePassword = KEYSTORE_PASSWORD
                keyAlias = "debug"
                keyPassword = KEY_PASSWORD
            } catch (e: Exception) {
                throw Throwable("You should define KEYSTORE_PASSWORD and KEY_PASSWORD in gradle.properties.")
            }
        }
        register("release") {
            try {
                storeFile = file("${project.rootDir}/keystore.jks")
                storePassword = KEYSTORE_PASSWORD
                keyAlias = "release"
                keyPassword = KEY_PASSWORD
            } catch (e: Exception) {
                throw Throwable("You should define KEYSTORE_PASSWORD and KEY_PASSWORD in gradle.properties.")
            }
        }
    }

    buildTypes.apply {
        getByName("debug") {
            applicationIdSuffix = ".debug"
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    sourceSets.apply {
        forEach {
            it.java.srcDir("src/${it.name}/kotlin")
        }
        getByName("main") {
            val addResources: (Array<File>) -> Unit = { files: Array<File> ->
                files.filter { it.exists() }
                    .mapNotNull { it.listFiles { file: File -> file.isDirectory } }
                    .forEach { folders -> res.srcDirs(*folders) }
            }
            val resScreens = file("src/main/res-screens")

            addResources(arrayOf(resScreens))
        }
    }

    packagingOptions.apply {
        exclude("META-INF/com.android.tools/proguard/coroutines.pro")
    }

}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":database"))
    implementation(project(":common"))
    implementation(project(":remote"))
    implementation(Libs.kotlinStdlib)
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesAndroid)
    implementation(Libs.appCompat)
    implementation(Libs.coreKtx)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.liveDataKtx)
    implementation(Libs.viewModelKtx)
    implementation(Libs.navigationFragmentKtx)
    implementation(Libs.navigationUiKtx)
    implementation(Libs.koin)
    implementation(Libs.koinAndroid)
    implementation(Libs.koinAndroidScope)
    implementation(Libs.koinAndroidViewModel)
    implementation(Libs.arrowCore)
    implementation(Libs.roomKtx)
    implementation(Libs.okhttp)
    implementation(Libs.retrofit)
    implementation(Libs.retrofitMoshi)

    debugImplementation(DebugLibs.debugDb)
    debugImplementation(DebugLibs.chucker)

    testImplementation(Test.mockitoCore)
    testImplementation(Test.mockitoKotlin)
    testImplementation(Test.mockitoInline)
    testImplementation(Test.robolectric)
    testImplementation(Test.coroutinesTest)
    testImplementation(Test.jUnit)

    androidTestImplementation(Test.jUnitExt)

    kapt(Kapt.lifecycle)
}
