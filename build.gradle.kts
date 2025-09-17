plugins {
    id("java")
    id("maven-publish")
}

group = "me.fionitos.discord"
version = "1.0.6"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "com.github.Fionitos"
            artifactId = "discordrpc-buttons"
            version = "1.0.5"
        }
    }
}
