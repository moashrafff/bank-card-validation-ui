plugins {
    `java-library`
    alias(libs.plugins.jetbrains.kotlin.jvm)
    `maven-publish`
}

group = "com.bankcardvalidator"
version = "1.0.0"

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(11)) }
    withSourcesJar()
    withJavadocJar()
}
kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

publishing {
    publications {
        create<MavenPublication>("release") {
            from(components["java"])
        }
    }
}

dependencies {
    testImplementation(libs.junit)
}