[![Kotlin](https://img.shields.io/badge/kotlin-1.0.0--beta--4584-blue.svg)](http://kotlinlang.org) [![DUB](https://img.shields.io/dub/l/vibe-d.svg)](https://github.com/Jire/KTON/blob/master/LICENSE) [![Issues](https://img.shields.io/github/issues/Jire/KTON.svg)](https://github.com/Jire/KTON/issues?q=is%3Aopen)

##KTON
_Object notation in pure Kotlin!_

This library is licensed under [The MIT License](https://github.com/Jire/KTON/blob/master/LICENSE).

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
        "menuitem" {
            arr { "value" to "New"; "onclick" to "CreateNewDoc()" }
            arr { "value" to "Open"; "onclick" to "OpenDoc()" }
            arr { "value" to "Close"; "onclick" to "CloseDoc()" }
        }
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