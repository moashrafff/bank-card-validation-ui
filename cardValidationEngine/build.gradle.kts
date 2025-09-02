plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
}

group = "com.bankcardvalidator"
version = "1.0.0"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "cardvalidatorengine"

            from(components["java"])

            pom {
                name.set("cardvalidatorengine")
                description.set("Card validation engine.")
            }
        }
    }
}

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


java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
kotlin {
    compilerOptions {
        jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11
    }
}
dependencies {
    testImplementation(libs.junit)
}