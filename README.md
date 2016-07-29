##KTON
_Object notation in pure Kotlin!_

[![Build Status](https://travis-ci.org/Jire/KTON.svg?branch=master)](https://travis-ci.org/Jire/KTON)
[![Dependency Status](https://www.versioneye.com/user/projects/579a95b33815c8004c5f7bf2/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/579a95b33815c8004c5f7bf2)
[![license](https://img.shields.io/github/license/Jire/KTON.svg)](https://github.com/Jire/KTON/blob/master/LICENSE.txt)

### Gradle
```groovy
compile 'org.jire.kton:KTON:1.0.1'
```

### Maven
```xml
<dependency>
    <groupId>org.jire.kton</groupId>
    <artifactId>KTON</artifactId>
    <version>1.0.1</version>
</dependency>
```

---

`KTON` allows you to notate objects in an easy syntax using pure Kotlin. This is mostly a toy project
but it may be useful for certain cases. I'd like to make conversion to JSON and provide more advanced features.

The following shows the differences between JSON, XML, and KTON. (Data used from [http://json.org/example.html](http://json.org/example.html))

**JSON:**

```json
{
    "menu": {
        "id": "file",
        "value": "File",
        "popup": {
            "menuitem": [
                { "value": "New", "onclick": "CreateNewDoc()" },
                { "value": "Open", "onclick": "OpenDoc()" },
                { "value": "Close", "onclick": "CloseDoc()" }
            ]
        }
    }
}
```

**XML:**

```xml
<menu id="file" value="File">
    <popup>
        <menuitem value="New" onclick="CreateNewDoc()" />
        <menuitem value="Open" onclick="OpenDoc()" />
        <menuitem value="Close" onclick="CloseDoc()" />
    </popup>
</menu>
```

**KTON:**

```kotlin
val menu = kton {
    "id" to "file"
    "value" to "File"
    "popup" {
        "menuitem" [
            { "value" to "New"; "onclick" to "CreateNewDoc()" },
            { "value" to "Open"; "onclick" to "OpenDoc()" },
            { "value" to "Close"; "onclick" to "CloseDoc()" }
        ]
    }
}
```

---

Accessing data from a KTON is done with concise syntax. Using the above example...

```kotlin

// Value access through get operator:
val id = menu["id"] // "file"
val value = menu["value"] // "File"

// Body access through invoke operator:
val popup = menu("popup")
val menuitems = popup("menuitem")

// Array access through get operator specifying index:
val newValue = menuitems[0]["value"] // "New"
val open = menuitems[1]
val openValue = open["value"] // "Open"
val closeOnClick = menuitems[2]["onclick"] // "CloseDoc()"
```