rootProject.name = "kotlin-workshop"
pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val ktorVersion: String by settings
        kotlin("jvm") version kotlinVersion
        id("io.ktor.plugin") version ktorVersion
        id("org.jetbrains.kotlin.plugin.serialization") version kotlinVersion
    }
}
