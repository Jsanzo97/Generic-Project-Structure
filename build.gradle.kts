import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(Plugins.gradleVersions) version Versions.gradleVersions
}

buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(BuildTools.androidGradlePlugin)
        classpath(BuildTools.kotlinGradlePlugin)
        classpath(BuildTools.gradleVersions)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = Versions.targetCompatibility.toString()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

