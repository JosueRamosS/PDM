/*
    Este archivo build.gradle.kts de nivel superior configura opciones comunes para todos los módulos del proyecto.
    
    - Plugins:
      • Se declaran los plugins para aplicación Android, librería Android y Kotlin Android, con sus respectivas versiones.    
    - Buildscript:
      • Se añade la dependencia para el plugin de Navigation Safe Args (versión especificada en la variable navVersion), 
        lo que facilita el paso seguro de argumentos entre fragments durante la navegación.
    
    Josué Carlos Alberto Ramos Suyoc, 11/05/2025
*/

plugins {
    id("com.android.application").version("8.1.2").apply(false)
    id("com.android.library").version("8.1.2").apply(false)
    id("org.jetbrains.kotlin.android").version("1.9.0").apply(false)
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val navVersion = "2.7.5"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$navVersion")
    }
}