import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    war
    id("org.springframework.boot") version "2.4.4"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("plugin.spring") version "1.4.31"
    kotlin("plugin.jpa") version "1.4.31"
    kotlin("jvm") version "1.4.31"
}

repositories {
    jcenter()
    mavenCentral()
}

val warName = "hosp.war"


group = "com.armyenjoyers.hospital"
version = "0.0.1-SNAPSHOT"

tasks.withType<JavaCompile> {
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}



dependencies {
    implementation("com.armyenjoyers:doc-generator")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")

    implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    // for RSASSA-PSS (PS256, PS384, PS512) algorithms:
    //'org.bouncycastle:bcprov-jdk15on:1.60'
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2") // or 'io.jsonwebtoken:jjwt-gson:0.11.2' for gson

    implementation("org.springframework.boot:spring-boot-starter-security:2.4.4")

    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation("io.springfox:springfox-swagger-ui:3.0.0")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    runtimeOnly("mysql:mysql-connector-java")
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootWar{
    archiveFileName.set(warName)
}