plugins {
    `kotlin-dsl`
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    getByName("main") {
        java.srcDir("src/main/java")
    }
}
dependencies {
    implementation(gradleApi())
}