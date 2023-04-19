plugins {
    `kotlin-dsl`
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
    implementation(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}
