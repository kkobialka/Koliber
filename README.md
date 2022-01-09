# Donate

If you like this app please donate

Bitcoin:

```
bc1qwqqpuy54qfja7h5dzpd9swgrnkql02t8cacn62
```

# Installation

Download or clone this repository

```
$ git clone https://github.com/kkobialka/Koliber
```

# Setup

In cloned repository go to the directory:

```
$ cd app/src/main/java/com/krzysztof/koliber
```

In above directory create `Common.kt` file:

```
$ touch Common.kt
```

In created `Common.kt` file add API keys from below websites:

1. www.openweathermap.org
2. www.aqicn.org

`Common.kt` file should look like this:

```kotlin
package com.krzysztof.koliber

class Common {
    companion object{
        const val OPENWEATHERMAP_API_KEY = "YOUR_API_KEY"
        const val AQICN_API_KEY = "YOUR_API_KEY"
    }
}
```

In your Google Developer console create Google Maps API key. 

```
https://developers.google.com/maps/documentation/android-sdk/start#api-key
```

Then go to the directory:

```
$ cd app/src/main/res/values
```

In this directory create `google_maps_api.xml` file

```
$ touch google_maps_api.xml
```

Your `google_maps_api.xml` file should look like this


```xml
<resources>
     <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">Your_API_key</string>
</resources>
```

# About this app

Koliber is an Android application written in Kotlin. I created this app to have clear information for pollution local status. This info helps to know when you need to take more care about health, especially for allergic people.
Data provided is fully from above mentioned services (openweathermap.org, aqicn.org).

# License 

Copyright © 2021 Krzysztof Kobiałka 

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# Screenshots

![Webp net-resizeimage (4)](https://user-images.githubusercontent.com/43904702/128624276-bbaef5d6-01a0-4fbb-8001-14fc97933c5c.png) ![Webp net-resizeimage (3)](https://user-images.githubusercontent.com/43904702/128624286-aaad4a50-3f6f-4851-ac67-612cacfbf7db.png) ![Webp net-resizeimage (1)](https://user-images.githubusercontent.com/43904702/128624288-3c2f5d12-05b8-4b71-ae67-ac79cca78e1e.png)

![Webp net-resizeimage](https://user-images.githubusercontent.com/43904702/128624289-d6dea7f4-c2cd-4a39-8405-36030d227992.png) ![Webp net-resizeimage (3)](https://user-images.githubusercontent.com/43904702/128624377-55becfd6-97ae-4efa-b812-27316ecd27cc.png)
