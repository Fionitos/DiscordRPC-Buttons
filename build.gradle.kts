plugins {
    id("java")
    id("maven-publish")
}

group = "me.fionitos"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:23.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}


publishing {
    publications {
        create<MavenPublication>("lib") {
            from(components["java"])
            groupId = "com.github.Fionitos"
            artifactId = "DiscordRPC-Buttons"
            version = "v1.0.1"
        }
    }
}