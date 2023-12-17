plugins {
    id("java-library")
    id("maven-publish")
}

group = "me.fionitos.discord"
version = "1.0.2"

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
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "arlekin-repo"
            url = uri("https://repo.arlekin.space/releases")

            credentials {
                username = System.getenv("MAVEN_ARLEKIN_ALIAS")
                password = System.getenv("MAVEN_ARLEKIN_TOKEN")
            }
        }
    }
}