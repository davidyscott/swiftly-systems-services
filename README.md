# swiftly-systems-services

[![Build status](https://ci.appveyor.com/api/projects/status/gcgbbpos6qtaaqq8?svg=true)](https://ci.appveyor.com/project/davidyscott/swiftly-systems-services)

[![Build status](https://ci.appveyor.com/api/projects/status/gcgbbpos6qtaaqq8/branch/main?svg=true)](https://ci.appveyor.com/project/davidyscott/swiftly-systems-services/branch/main)

## Intro

This is a solution to the [Swiftly-Systems Code Exercise](https://github.com/Swiftly-Systems/code-exercise-services).

## Building

Note that JDK11+ is required.

### Simple build
```shell
./gradlew build
```

### Running tests
```shell
./gradlew test
```

### Running directly
```shell
./gradlew run --args <path_to_input_file>
```

### Building a runnable jar
```shell
./gradlew jar
```

Then run the jar file:
```shell
<JAVA_HOME>/bin/java -jar <path_to_jar_file> <path_to_input_file>
```
