plugins {
    `java-library`
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
}

group = "io.github.moashrafff.bankCardValidator"
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
            artifactId = "cardValidationEngine"
        }
    }
}

dependencies {
    testImplementation(libs.junit)
}
