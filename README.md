# networkinkt [![Build Status](https://travis-ci.org/egorzhdan/networkinkt.svg?branch=master)](https://travis-ci.org/egorzhdan/networkinkt)

This is a simple HTTP client for Kotlin. It relies on coroutines on both JS & JVM platforms.

```kotlin
import com.egorzh.networkinkt.*

val text = HTTP.get("http://httpbin.org/status/200").getText() // suspending call
```

A request with HTTP headers and body:
```kotlin
val text = HTTP.post("http://httpbin.org/headers",
                    headers = mapOf("MyLibraryHeader" to "networkinkt"),
                    body = "param=value")
                    .getText()
```

## Getting started

The project uses Gradle to manage dependencies.

**Common JVM & JS `build.gradle`**:
```gradle
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    // ...
    compile "com.egorzh.networkinkt:networkinkt:$networkinkt_version"
}
```

**`yourproject-jvm/build.gradle`**:
```gradle
dependencies {
    // ...
    compile "com.egorzh.networkinkt:networkinkt-jvm:$networkinkt_version"
}
```

**`yourproject-js/build.gradle`**:
```gradle
dependencies {
    // ...
    compile "com.egorzh.networkinkt:networkinkt-js:$networkinkt_version"
}
```

You can always check which version is up-to-date on the [releases page](https://github.com/egorzhdan/networkinkt/releases)
