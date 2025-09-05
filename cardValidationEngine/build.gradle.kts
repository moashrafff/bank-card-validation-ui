plugins {
    `java-library`
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
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
publishing {
    publications {
        withType<MavenPublication> {
            groupId = "com.bankcardvalidator"
            artifactId = "cardvalidator-engine"
            version = "1.0.0"
        }
    }
}