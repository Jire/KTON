##KTON
_Object notation in pure Kotlin!_

This library is licensed under [GPL 3.0](http://www.gnu.org/licenses/gpl-3.0.en.html).

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
    "id".."file"
    "value".."File"
    "popup" {
        "menuitem" {
            arr { "value".."New"; "onclick".."CreateNewDoc()" }
            arr { "value".."Open"; "onclick".."OpenDoc()" }
            arr { "value".."Close"; "onclick".."CloseDoc()" }
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
val menuitems = popup("menuitems")

// Array access through get operator specifying index:
val newValue = menuitems[0]["value"] // "New"
val open = menuitems[1]
val openValue = open["value"] // "Open"
val closeOnClick = menuitems[2]["onclick"] // "CloseDoc()"
```