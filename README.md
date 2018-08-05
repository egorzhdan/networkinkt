# networkinkt [![Build Status](https://travis-ci.org/egorzhdan/networkinkt.svg?branch=master)](https://travis-ci.org/egorzhdan/networkinkt)

This is a lightweight HTTP client for Kotlin. It relies on coroutines on both JS & JVM platforms.

Here is a simple `GET` request:

```kotlin
val text = HTTP.get("http://httpbin.org/status/200").getText() // suspending call
```

...and a `POST` request with HTTP headers and body:
```kotlin
val text = HTTP.post("http://httpbin.org/headers",
                     headers = mapOf("MyLibraryHeader" to "networkinkt"),
                     body = "param=value")
               .getText()
```

See the [usage section](#usage) for more examples.

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

## Usage

How to make a request:
1. Obtain an instance of `HTTPRequest` from an `HTTPClient` (the default one is `DefaultHTTPClient` or simply `HTTP`)
1. Send the request and receive an `HTTPResponse`, which encapsulates the response code and text
1. Use `code` and `text` properties of a response object

```kotlin
import com.egorzh.networkinkt.*

// 1:
val request /* : DefaultHTTPRequest */ = HTTP.get(url = "http://httpbin.org/status/200")

// 2: (from a suspend function or coroutine)
val response /* : DefaultHTTPResponse */ = HTTP.sendRequest(request)

// 3:
val code = response.code
val text = response.text
```

### Customization

You can easily create your own implementations of `HTTPClient`, 
for example, to provide caching or error handling functionality.