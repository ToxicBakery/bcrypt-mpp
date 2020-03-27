# Bcrypt MPP [![CircleCI](https://circleci.com/gh/ToxicBakery/bcrypt-mpp.svg?style=svg)](https://circleci.com/gh/ToxicBakery/bcrypt-mpp) [![Maven Central](https://img.shields.io/maven-central/v/com.ToxicBakery.library.bcrypt/bcrypt.svg)](https://oss.sonatype.org/content/repositories/releases/com/ToxicBakery/library/bcrypt/) [![Maven Central](https://img.shields.io/maven-metadata/v/https/oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/library/bcrypt/bcrypt/maven-metadata.xml.svg)](https://oss.sonatype.org/content/repositories/snapshots/com/ToxicBakery/library/bcrypt/)  
A multiplatform implementation of bcrypt for Node and the JVM

## Usage
Exposes Bcrypt hashing and function in two methods.

```kotlin
// Hash a given password using a given number of salt round.
val hash = Bcrypt.hash(password, SALT_ROUNDS)

// Verify a given password matches a previously hashed password
if (Bcrypt.verify(password, hash)) { 
    println("It's a match!") 
}
```

### JVM
The JVM implementation is build on `Bcrypt Java Library`.  
https://github.com/patrickfav/bcrypt

### NodeJS
The JS implementation only supports NodeJS and is based on `bcrypt` library.  
https://github.com/kelektiv/node.bcrypt.js

## Install
The output artifacts are maven artifacts for common code, Javascript, and Java
 
Common: Use this if you are depending on Joise in a common module of an MPP project.
```
implementation("com.ToxicBakery.library.bcrypt:bcrypt:+")
```

Java
```
implementation("com.ToxicBakery.library.bcrypt:bcrypt:+")
```

JavaScript
```
implementation("com.ToxicBakery.library.bcrypt:bcrypt:+")
```

## Build
Requires OpenJDK 8+

```bash
./gradlew build
```
