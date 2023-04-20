package org.weyoung.gradle.plugin

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.weyoung.gradle.plugin.dsl.libs

class ApplicationConfig : Plugin<Project> {
    val javaVersion = JavaVersion.VERSION_18
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                //common plugins
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.kapt")
            }
            extensions.configure<ApplicationExtension> {
//                compileSdk = libs.versions.compileVersion.get().toInt()
                compileSdkPreview = libs.versions.compileVersionPreview.get()
                buildToolsVersion = libs.versions.androidBuildTool.get()
                defaultConfig.apply {
                    targetSdk = libs.versions.androidTarget.get().toInt()
                    minSdk = libs.versions.androidMinSdk.get().toInt()
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    vectorDrawables {
                        useSupportLibrary = true
                    }
                }
                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                    sourceCompatibility = javaVersion
                    targetCompatibility = javaVersion
                }

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                    release {
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
                }
                packaging {
                    resources.excludes.addAll(
                        listOf(
                            "META-INF/AL2.0",
                            "META-INF/LGPL2.1"
                        )
                    )
                }
                dependencies {
                    coreLibraryDesugaring(libs.desugar)
                    implementation(libs.kotlin.std)
                }
            }
            tasks.withType<KotlinCompile<KotlinJvmOptions>> {
                kotlinOptions {
                    jvmTarget = javaVersion.toString()
                }
            }
        }
    }
}