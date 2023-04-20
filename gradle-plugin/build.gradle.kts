plugins {
    `kotlin-dsl`
}

version = "1.0.0"

val jdkVersion = JavaVersion.VERSION_18

java {
    sourceCompatibility = jdkVersion
    targetCompatibility = jdkVersion
}

sourceSets {
    getByName("main") {
        java.srcDir("src/main/java")
    }
}
dependencies {
    implementation(gradleApi())
}