# LibGDX: Box2D Shape Factory
## Construct and place shapes in your world

https://github.com/user-attachments/assets/77429144-dd74-4606-9601-8776944b8baf

Creating and placing shapes is simple in Box2D, particularly when using libgdx and Kotlin. Being considerably less verbose/convoluted than Java, it is easier to create a factory through abstract class, interfaces and inheritance.

This app makes creating shapes in libgdx with Box2D somewhat easier, while still maintaining the same "low-level" abstraction used by libgdx. It uses the available Box2D methods, such as ```setAsBox``` or ```set```, and the most commonly employed shape classes (which inherit from Shape) such as ```Polygon``` or ```Circle```.

Although possible to build in terminal, it is recommended to build through gradle and an editor of your choice, such as Android Studio, IntelliJ or similar.

A signed android app is included to test on any of your android devices.   
