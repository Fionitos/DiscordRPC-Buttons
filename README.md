# DiscordRPC with Buttons
This library is for interfacing your games with a locally running Discord. Among all other libraries, this is the presence of additional functions for customizing functions.

Requirements: Java 17

The library is tested on Windows, Linux and macOS.

## Gradle
<details open>
<summary>Groovy</summary>

```groovy
repositories {
    maven {
        name = "arlekin"
        url = "https://repo.arlekin.space/releases"
    }
}

dependencies {
    implementation "me.fionitos.discord:discordrpc-buttons:1.0.3"
}
```

</details>
<details>
<summary>Kotlin</summary>

```kotlin
repositories {
    maven {
        name = "arlekin"
        url = uri("https://repo.arlekin.space/releases")
    }
}

dependencies {
    implementation("me.fionitos.discord:discordrpc-buttons:1.0.3")
}
```

</details>
