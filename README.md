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

Value access through `get` operator:

`menu["id"]` => `"file"`, `menu["value"]` => `"File"`

Body access through `invoke` operator:

`val popup = menu("popup")`, `val menuitems = popup("menuitem")`

Array access through `get` operator specifying index:

`menuitems[0]["value"]` => `"New"`, `menuitems[3]["onclick"]` => `"CloseDoc()"`