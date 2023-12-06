import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("maven-publish")
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
}

group = "com.legendaries"
version = "0.0.1-SNAPSHOT"

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.legendaries"
            artifactId = "board"
            version = "0.0.1-SNAPSHOT"
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/jinseon-dev/packages-test")
            credentials {
                username = project.findProperty("GITHUB_USERNAME").toString()
                password = project.findProperty("GITHUB_TOKEN").toString()
            }
        }
    }
}


java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
