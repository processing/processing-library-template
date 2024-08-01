import java.util.Properties
import org.gradle.internal.os.OperatingSystem


plugins {
    id("java")
}

//==========================
// USER BUILD CONFIGURATIONS

// group id of your library. Often is reverse domain
group = "com.example"

// version of library, usually semver
version = "1.0.0"

// the short name of your library. This string will name relevant files and folders.
// Such as:
// <libName>.jar will be the name of your build jar
// <libName>.zip will be the name of your release file
// this name defaults to the rootProject.name
val libName = rootProject.name

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

    // To add a dependency on a Processing library that is installed locally,
    // uncomment the line below, and replace <library folder> with the location of that library
    // compileOnly(fileTree("$sketchbookLocation/libraries/<library folder>/library"))

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

val releaseRoot = "$rootDir/release"
val releaseName = libName
val releaseDirectory = "$releaseRoot/$releaseName"

// The location of your sketchbook folder. The sketchbook folder holds your installed libraries, tools, and modes
// It is needed only if you
// 1. wish to copy the library to the Processing sketchbook, such that you can import it in Processing
// 2. have Processing library dependencies
// You can check the sketchbook location in your Processing application preferences.
var sketchbookLocation = ""
val userHome = System.getProperty("user.home")
val currentOS = OperatingSystem.current()
if(currentOS.isMacOsX) {
    sketchbookLocation = "$userHome/Documents/Processing/sketchbook"
} else if(currentOS.isWindows) {
    sketchbookLocation = "$userHome/My Documents/Processing/sketchbook"
} else {
    sketchbookLocation = "$userHome/sketchbook"
}

// read in user-defined properties in release.properties file
// to be saved in library.properties file, a required file in the release
// using task writeLibraryProperties
val libraryProperties = Properties().apply {
    load(rootProject.file("release.properties").inputStream())
}

tasks.register<WriteProperties>("writeLibraryProperties") {
    group = "processing"
    destinationFile = project.file("library.properties")

    property("name", libraryProperties.getProperty("name"))
    property("version", libraryProperties.getProperty("version"))
    property("prettyVersion", project.version)
    property("authors", libraryProperties.getProperty("authors"))
    property("url", libraryProperties.getProperty("url"))
    property("categories", libraryProperties.getProperty("categories"))
    property("sentence", libraryProperties.getProperty("sentence"))
    property("paragraph", libraryProperties.getProperty("paragraph"))
    property("minRevision", libraryProperties.getProperty("minRevision"))
    property("maxRevision", libraryProperties.getProperty("maxRevision"))
}

// define the order of running, to ensure clean is run first
tasks.build.get().mustRunAfter("clean")
tasks.javadoc.get().mustRunAfter("build")

tasks.register("releaseProcessingLib") {
    group = "processing"
    dependsOn("clean","build","javadoc", "writeLibraryProperties")
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
