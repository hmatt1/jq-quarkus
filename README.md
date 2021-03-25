# jq-quarkus 

[jq](https://stedolan.github.io/jq/) is one of my favorite command line tools for parsing JSON.

Example usage:
```bash
echo '{"foo": {"bar": "1"}}' | jq .foo.bar
```

Imagine if we could use it natively from within Java applications?

```java
JQ.jq(input, ".foo.bar");
```

This is a POC to show how we could do that! Here is how it works.

1. Build the [jq grammar](https://github.com/hmatt1/jq-grammar) using [ANTLR](https://www.antlr.org/)
2. Implement the [jq language](https://github.com/hmatt1/jq-truffle) using GraalVM's [Truffle Framework](https://www.graalvm.org/graalvm-as-a-platform/language-implementation-framework/)
3. Build a project using Quarkus, and call natively into the language.

## Supported language features

Currently, this implemented only supports dot notation, such as `.foo.bar` for processing JSON. Contributions welcome 😄

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using:
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/jq-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
