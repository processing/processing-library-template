import java.util.Properties

plugins {
    id("java")
    id("java-library")
}

//==========================
// USER BUILD CONFIGURATIONS

// the short name of your library. This string will name relevant files and folders.
// Such as:
// <libName>.jar will be the name of your build jar
// <libName>.zip will be the name of your release file
val libName = rootProject.name

// location of your sketchbook folder, required only if you have
// 1. Wish to copy the library to the Processing sketchbook
// 2. Processing library dependencies
val sketchbookLocation by extra(System.getProperty("user.home")+"/sketchbook")


// group id of your library. Often is reverse domain
group = "com.example"

// version of library, usually semver
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()

    // these two are needed to resolve processing4 from micycle1's repo
    maven { url = uri("https://jitpack.io") }
    maven { url = uri("https://jogamp.org/deployment/maven/") }
}

dependencies {
    // resolve Processing core
    compileOnly("com.github.micycle1:processing-core-4:4.3.1")

    // insert your external dependencies
    // TODO actually use dependency in library
    implementation("org.apache.commons:commons-math3:3.6.1")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.jar {
    archiveBaseName.set(libName)
    archiveClassifier.set("")
    archiveVersion.set("")
}

tasks.test {
    useJUnitPlatform()
}

// END USER BUILD CONFIGURATIONS
//==============================

//============================
// Tasks for releasing library

val libraryProperties = Properties().apply {
    load(rootProject.file("library.properties").inputStream())
}

val userHome = System.getProperty("user.home")
val libVersion = libraryProperties.getProperty("version")

val releaseRoot = "$rootDir/release"
val releaseName = libName
val releaseDirectory = "$releaseRoot/$releaseName"

// define the order of running, to ensure clean is run first
tasks.build.get().mustRunAfter("clean")
tasks.javadoc.get().mustRunAfter("build")

tasks.register("releaseProcessingLib") {
    group = "processing"
    dependsOn("clean","build","javadoc")
    finalizedBy("packageRelease")

    doFirst {
        println("Releasing library $libName")
        println(org.gradle.internal.jvm.Jvm.current())

        println("Cleaning release...")
        project.delete(files(releaseRoot))
    }

    doLast {
        println("Creating package...")

        println("Copy library...")
        copy {
            from(layout.buildDirectory.file("libs/${libName}.jar"))
            into("$releaseDirectory/library")
        }

        println("Copy dependencies...")
        copy {
            from(configurations.runtimeClasspath)
            into("$releaseDirectory/library")
        }

        println("Copy assets...")
        copy {
            from("$rootDir")
            include("shaders/**", "native/**")

            into("$releaseDirectory/library")
            exclude("*.DS_Store")
        }

        println("Copy javadoc...")
        copy {
            from(layout.buildDirectory.dir("docs/javadoc"))
            into("$releaseDirectory/reference")
        }

        println("Copy additional artifacts...")
        copy {
            from(rootDir)
            include("README.md", "readme/**", "library.properties", "examples/**", "src/**")

            into(releaseDirectory)
            exclude("*.DS_Store", "**/networks/**")
        }

        println("Copy repository library.txt...")
        copy {
            from(rootDir)
            include("library.properties")
            into(releaseRoot)
            rename("library.properties", "$libName.txt")
        }
    }
}

tasks.register<Zip>("packageRelease") {
    dependsOn("releaseProcessingLib")
    archiveFileName.set("${libName}.zip")
    from(releaseDirectory)
    into(libName)
    destinationDirectory.set(file(releaseRoot))
    exclude("**/*.DS_Store")
}

tasks.register("copyToLocalProcessing") {
    group = "processing"
    if (project.hasProperty("sketchbookLocation")) {
        println("Copy to sketchbook...")
        val installDirectory = "$sketchbookLocation/libraries/$libName"
        copy {
            from(releaseDirectory)
            include("library.properties",
                "examples/**",
                "library/**",
                "reference/**",
                "src/**"
            )
            into(installDirectory)
        }
    }
}
