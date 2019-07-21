Markdown Reference (as of 11 Feb. 2019)
===================
Originally from tchapi with additions from MinhasKamal 

(https://github.com/tchapi/markdown-cheatsheet, https://gist.github.com/MinhasKamal/7fdebb7c424d23149140)

- - - - 

Comments:

[/]:# (insert comment text inside the brackets)

```
[/]:# (insert comment text inside the brackets)
```

Escape character:

To type \#, \*, or \!, use `\#, \*, or \!`.

# Heading 1 #

    Markup :  # Heading 1 #

    -OR-

    Markup :  ============= (below H1 text)

## Heading 2 ##

    Markup :  ## Heading 2 ##

    -OR-

    Markup: --------------- (below H2 text)

### Heading 3 ###

    Markup :  ### Heading 3 ###

#### Heading 4 ####

    Markup :  #### Heading 4 ####


Common text

    Markup :  Common text

_Emphasized text_

    Markup :  _Emphasized text_ or *Emphasized text*

~~Strikethrough text~~

    Markup :  ~~Strikethrough text~~

__Strong text__

    Markup :  __Strong text__ or **Strong text**

___Strong emphasized text___

    Markup :  ___Strong emphasized text___ or ***Strong emphasized text***

[Named Link](http://www.google.fr/ "Named link title")

    Markup :  [Named Link](http://www.google.fr/ "Named link title")
    
http://www.google.fr/

    Markup: http://www.google.fr/
    
<http://example.com/>

    Markup: <http://example.com/>

[heading-1](#heading-1 "Goto heading-1")
    
    Markup: [heading-1](#heading-1 "Goto heading-1")

Tables:

First Header  | Second Header
------------- | -------------
Content Cell  | Content Cell
Content Cell  | Content Cell

```
First Header  | Second Header
------------- | -------------
Content Cell  | Content Cell
Content Cell  | Content Cell
```

Table Header-1 | Table Header-2 | Table Header-3
:--- | :---: | ---:
Table Data-1 | Table Data-2 | Table Data-3
TD-4 | Td-5 | TD-6
Table Data-7 | Table Data-8 | Table Data-9

```
Place directly above the corresponding column of the table:
:--- for align left
:---: for align center
---: for align right

e.g.
Table Header-1 | Table Header-2 | Table Header-3
:--- | :---: | ---:
Table Data-1 | Table Data-2 | Table Data-3
TD-4 | Td-5 | TD-6
Table Data-7 | Table Data-8 | Table Data-9
```

- - - -

Inline code `code()`

    Markup :  Inline code `code()`
    
- - - -

Block code:

```javascript
    var specificLanguage_code = 
    {
        "data": {
            "lookedUpPlatform": 1,
            "query": "Kasabian+Test+Transmission",
            "lookedUpItem": {
                "name": "Test Transmission",
                "artist": "Kasabian",
                "album": "Kasabian",
                "picture": null,
                "link": "http://open.spotify.com/track/5jhJur5n4fasblLSCOcrTp"
            }
        }
    }
```

Language-specific code:

``` C
for(int i=0; i<10; i++){
    printf("Hallow World! \n");
}
```

    Markup : Encase block code with
             ```
             ```
             
             Encase language-specific code (e.g. Javascript or C) with
             ```javascript
             ```
             
             or
             ```C
             ```

- - - -

* Bullet list
    * Nested bullet
        * Sub-nested bullet etc
* Bullet list item 2

~~~
 Markup : * Bullet list
              * Nested bullet
                  * Sub-nested bullet etc
          * Bullet list item 2
          - item-2
            - sub-item-3
            - sub-item-4
          + item-3
            + sub-item-5
            + sub-item-6
~~~

1. A numbered list
    1. A nested numbered list
    2. Which is numbered
2. Which is numbered

~~~
 Markup : 1. A numbered list
              1. A nested numbered list
              2. Which is numbered
          2. Which is numbered
~~~

- [ ] An uncompleted task
- [x] A completed task

~~~
 Markup : - [ ] An uncompleted task
          - [x] A completed task
~~~

> Blockquote
>> Nested blockquote

    Markup :  > Blockquote
              >> Nested Blockquote

_Horizontal line :_
- - - -

    Markup :  - - - -
              ---
              ***
              ___

_Image with alt :_

![picture alt](http://www.brightlightpictures.com/assets/images/portfolio/thethaw_header.jpg "Title is optional")

    Markup : ![picture alt](http://www.brightlightpictures.com/assets/images/portfolio/thethaw_header.jpg "Title is optional")

Foldable text:

<details>
  <summary>Title 1</summary>
  <p>Content 1 Content 1 Content 1 Content 1 Content 1</p>
</details>
<details>
  <summary>Title 2</summary>
  <p>Content 2 Content 2 Content 2 Content 2 Content 2</p>
</details>

    Markup : <details>
               <summary>Title 1</summary>
               <p>Content 1 Content 1 Content 1 Content 1 Content 1</p>
             </details>

```html
<h3>HTML</h3>
<p> Some HTML code here </p>
```

Keyboard keys:

<kbd>⌘F</kbd>    
<kbd>⇧⌘F</kbd>

    Markup : <kbd>⌘F</kbd>

Key list:

| Key | Symbol |
| --- | --- |
| Option | ⌥ |
| Control | ⌃ |
| Command | ⌘ |
| Shift | ⇧ |
| Caps Lock | ⇪ |
| Tab | ⇥ |
| Esc | ⎋ |
| Power | ⌽ |
| Return | ↩ |
| Delete | ⌫ |
| Up | ↑ |
| Down | ↓ |
| Left | ← |
| Right | → |

Emoji:

:exclamation: :+1:

Look up emoji codes at [emoji-cheat-sheet.com](http://emoji-cheat-sheet.com/)

    Markup : Code appears between colons :EMOJICODE:
