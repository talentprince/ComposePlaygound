plugins {
    `kotlin-dsl`
}

version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

sourceSets {
    getByName("main") {
        java.srcDir("src/main/java")
    }
}
dependencies {
    implementation(gradleApi())
}