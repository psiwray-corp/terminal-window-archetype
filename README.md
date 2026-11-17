# terminal-window-archetype

Maven archetype for a Kotlin [terminal-window](https://github.com/psiwray-corp/terminal-window)
executable. Requires JDK 25 and Maven 3.9+.

## Generate

```sh
mvn archetype:generate \
  -DarchetypeGroupId=com.psiwray.organization \
  -DarchetypeArtifactId=terminal-window-archetype \
  -DarchetypeVersion=2.0.0 \
  -DgroupId=com.example \
  -DartifactId=demo \
  -Dversion=1.0-SNAPSHOT \
  -Dpackage=com.example.demo
```

Defaults: `terminalWindowVersion=2.0.0`, `lwjglNatives=natives-linux`.
Change the classifier for the target host.

```sh
cd demo
mvn package
java -jar target/demo-1.0-SNAPSHOT.jar
```

On macOS, add `-XstartOnFirstThread` to the `java` command.

## Build

```sh
mvn verify
```

The build runs the archetype integration test: it generates a project and
packages it.

## Release

Versions are kept in lockstep:

- `pom.xml` — `<version>`
- `src/main/resources/META-INF/maven/archetype-metadata.xml` —
  `terminalWindowVersion`
- `src/test/resources/projects/basic/archetype.properties` —
  `terminalWindowVersion`

For each release, bump them, commit, and tag that commit with the released
version prefixed `v` (for example `v2.0.0`). Tag only version-bump commits.

```sh
mvn -Prelease verify   # build and sign locally
mvn -Prelease deploy   # upload to Central for manual publishing
```

Requires JDK 25, a local GPG key, and a `central` server in
`~/.m2/settings.xml` with a Central Portal token:

```xml
<server>
  <id>central</id>
  <username>TOKEN_USERNAME</username>
  <password>TOKEN_PASSWORD</password>
</server>
```

The upload is validated and left for manual publishing on
`central.sonatype.com`. If several GPG keys are present, pass
`-Dgpg.keyname=<key-id>`. `mvn -Prelease verify` builds and signs without
uploading.

Local installation, for testing without a release:

```sh
mvn install
```

## Layout

- `src/main/resources/archetype-resources/` — generated project template.
- `src/main/resources/META-INF/maven/archetype-metadata.xml` — archetype descriptor.
- `src/test/resources/projects/basic/` — integration test input.
- `src/central/` — placeholder sources and Javadoc JARs required by Central.

The bundled `ascii-8x16.twf` font is CC0-1.0.
