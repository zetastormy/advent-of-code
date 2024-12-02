plugins {
    id("java")
}

group = "me.zetastormy"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}