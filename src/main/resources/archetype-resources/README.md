# ${artifactId}

A terminal-window application generated from `terminal-window-archetype`.

## Run

Requires JDK 25 and OpenGL 3.3 core.

```sh
mvn package
java -jar target/${artifactId}-${version}.jar
```

On macOS, add `-XstartOnFirstThread`:

```sh
java -XstartOnFirstThread -jar target/${artifactId}-${version}.jar
```

`mvn package exec:exec` also works on Linux and Windows.

The default LWJGL native classifier is `${lwjglNatives}`. Change `lwjgl.natives`
in `pom.xml` for another host. The bundled font is CC0-1.0.
