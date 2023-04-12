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

class ApplicationConfig : Plugin<Project> {
    val javaVersion = JavaVersion.VERSION_17
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                //common plugins
            }
            extensions.configure<ApplicationExtension> {
//                compileSdk = 33
                compileSdkPreview = "UpsideDownCake"
                buildToolsVersion = "33.0.1"
                defaultConfig.apply {
                    targetSdk = 33
                    minSdk = 29
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
                    kotlinCompilerExtensionVersion = "1.4.4"
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
                    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.3")
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