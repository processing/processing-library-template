# The Development Process

You are now ready to develop your library. This guide will walk you through resolving dependencies, configuring the build, creating release artifacts, and testing your library in Processing.

!!! Note
    If you haven't set up your environment yet, refer to the [Getting Started guide](getting-started.md).


## Developing the library
**Develop your library within `src/main/java/`.** Set the `package` at the top of your file to your
own group id and library name, all together. For instance, in the example library, the group id is
`com.myDomain` and the library name is `myLibrary`. The package is then `com.myDomain.myLibrary`.
These values will need to match the values input into your Gradle build file, `build.gradle.kts`.
More on this in [Configuring the Gradle build file](#configuring-the-gradle-build-file).

Intellij and Visual Studio Code will then direct you to move your code into nested folders, consistent
with your package name. For instance, if the package is `com.myDomain.myLibrary`, the code will
be in the folder structure `src/main/java/com/myDomain/myLibrary/`.

If you would like to use Processing functionality in your library, you can do so by importing Processing core
and referencing Processing objects from the PApplet instance. An example of how to do this is given in the
example library in this template.

## Resolving dependencies
In the `build.gradle.kts` file, there are a few places you should edit, to input your own values.
The part of the file that you are invited to edit is indicated by the comment 
`USER BUILD CONFIGURATIONS`. We cover here how to add dependencies for your library. The relevant
sections of the `build.gradle.kts` file are the `repositories` and `dependencies` sections.

**The locations where dependencies will be resolved from are the `repositories`.** Most
dependencies can be resolved from Maven. You can add additional repositories here if
your dependencies are hosted elsewhere. 

**Your dependencies should be listed within `dependencies`.** For example, if the example library
were to use the `commons-math3` package from `org.apache.commons` as a dependency, it would be listed within `dependencies` with the following structure:

```
implementation(group = "org.apache.commons", name = "commons-math3", version = "3.6.1")
```

!!! Note
    Dependencies added with `implementation` will be included in the release. Dependencies
    added with `compileOnly`, such as Processing core, are available for compilation, but won't
    be included in the release.

After you add these dependencies, **refresh Gradle**, and then you will be able to access them
in your code. Refresh Gradle by going to the Gradle menu (elephant) in your IDE
and click on the refresh icon, which is two arrows pointing at each other's ends in a circle.


## Configuring the Gradle build file
In the `build.gradle.kts` file, there are a few places you should edit, to input your own values.
The section that you are invited to edit is indicated by the comment `USER BUILD CONFIGURATIONS`.
We cover here how to configure the editable sections of the `build.gradle.kts` file, aside from
those sections for [resolving dependencies](#resolving-dependencies).

1.  **Edit the variable `libName` to contain the name of your library**. The name can only contain
    lower case alphanumeric characters, and "-". 
    This value is used by Gradle to name the built jar file, and the zip file holding
    your release artifacts. If this name does not match the name of the library in your code, 
    as discussed in the section [Developing the library](#developing-the-library),
    Processing will not find your library.
2.  **Edit the variable `group` with your own domain or organization name.** The group id 
    of your library uniquely identifies your project. It's often written in reverse domain name 
    notation. For example, if your website is "myDomain.com", your group ID would be 
    "com.myDomain". This group id should match the group id discussed in 
    section [Developing the library](#developing-the-library).
3.  The `sketchbookLocation` is determined programmatically by your operation system, and is
    where your Processing `sketchbook` folder is. This folder contains your installed libraries.
    It is needed if you:

    1. wish to copy the library to the Processing sketchbook, which installs the library locally
    2. have Processing library dependencies

    This variable is in the editable section, in case the location determined is incorrect. A
    symptom of an incorrect `sketchbookLocation` is that your library does not show up as a
    contributed library Processing, after being installed. Please look at our
    [troubleshooting guide](troubleshooting.md) if you suspect this is the case.


## Creating examples
Examples help users understand your library’s functionality, it is recommended that you include several clear and well-commented samples sketches in the `examples` folder.

!!! Note
    The example sketch included in this template outputs the image shown on the home page.

## Creating the release artifacts

1.  Fill in the file `release.properties` with information for your library. In the file itself,
    There are comments to guide you. This information will be used by Gradle to create the 
    `library.properties` file, which is one of the required release artifacts, used by the website 
    and Contribution Manager to describe your library. To create just the `library.properties` file without building the
    library, toggle `Tasks` > `processing` and double click `writeLibraryProperties`. This task will
    copy all the values in `release.properties`.
2.  **To build the library and create the release artifacts, run the Gradle task `buildReleaseArtifacts`.**
    This task will create a `release` folder with needed artifacts. To do this, go to the Gradle menu 
    (elephant), toggle `Tasks` > `processing` and double click `buildReleaseArtifacts`. This task 
    has bundled the following required tasks:
    1.  `build` task: this bundles a number of build tasks, including the `jar` task which creates a
        jar file. all build artifacts are in the folder `build`.
    2.  documentation build.
    3.  creation of the `library.properties` file: this file is built from the properties set in the
       `release.properties` file, plus the version, in the task `writeLibraryProperties`.
    4.  within the `buildReleaseArtifacts` task, the `release` folder is created, jars of the library and
        dependencies are copied, and the `library.properties` file is copied. Also, a zip file is made.
3.  When you would like to test your library in Processing, toggle `Tasks` > `processing` and double click
    `deployToProcessingSketchbook`, which will create the release artifacts, and copy them into the 
    sketchbook folder.


!!! Note
If you [release your library on Github](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository),
your release tag will be used for the value of the `prettyVersion` field, overwriting the value for `prettyVersion` 
defined in `release.properties`, as a helpful automation.


## Additional Documentation

See the following guides for more details:

  - [Library Overview](https://github.com/processing/processing4/wiki/Library-Overview): an introduction to Processing libraries in general.
  - [Library Basics](https://github.com/processing/processing4/wiki/Library-Basics): a basic guide for creating a Processing library from scratch, including structuring the library, registering methods with PApplet, handling events, and preparing the library for distribution.
  - [Library Guidelines](https://github.com/processing/processing4/wiki/Library-Guidelines): describes the requirements for a Processing library, including naming conventions, examples, and other best practices.
