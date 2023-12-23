plugins {
    id("java")
    id("maven-publish")
}

group = "me.fionitos.discord"
version = "1.0.3"

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