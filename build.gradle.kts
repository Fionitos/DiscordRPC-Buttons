plugins {
    id("java")
    id("maven-publish")
}

group = "me.fionitos.discord"
version = "1.0.4"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
}