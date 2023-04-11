plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.jvm)
}

gradlePlugin {
    plugins {
        register("appConfig") {
            id = "org.weyoung.gradle.app"
            implementationClass = "org.weyoung.gradle.plugin.ApplicationConfig"
        }
//        register("libConfig") {
//
//        }
    }
}

dependencies {
    implementation(gradleApi())
//    implementation(libs.plugins.android.application)
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

}
