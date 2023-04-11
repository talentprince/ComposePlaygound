plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    id("org.weyoung.gradle.app")
}
group = "org.weyoung.navigation-playground"

android {
    namespace = "org.weyoung.example"
    defaultConfig {
        applicationId = "org.weyoung.example"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    implementation (libs.androidx.core.ktx)
    implementation (libs.androidx.appcompat)
    implementation (libs.bundles.androidx.compose)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.lifecycle.viewModelCompose)
    implementation (libs.androidx.navigation.compose)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
