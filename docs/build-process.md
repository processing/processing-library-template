# The Build Process

In addition to coding up your library, you'll need to resolve dependencies, and build your library.
Also, as a way to test your library, you'll want to create the release artifacts, which can be
manually installed into Processing, by copying them to the correct folder. Most of these tasks
are already taken care of within the Gradle build file `build.gradle.kts`. This guide will explain
some of the aspects of the gradle build file.

Before following this guide, we recommend to follow the steps in the [getting started guide](getting-started.md)
first.

## Configuring the Gradle build file
In the `build.gradle.kts` file, there are a few places you should edit, to input your own values.
The section that you are invited to edit is indicated by the comment `USER BUILD CONFIGURATIONS`.

1. **Edit the variable `libName` to contain the name of your library**, as it's represented
in your code. This value is used by Gradle to name the built jar file, and the zip file holding
your release artifacts. If this name does not match the name of the library in your code, 
Processing will not find your library.
2. **Edit the variable `group` with your own domain or organization name.** The group id
of your library uniquely identifies your project. It's often written in reverse domain name 
notation. For example, if your website is "myDomain.com", your group ID might be "com.myDomain".
3. **Define the `version` of your library in `build.gradle.kts`.** This value will also be
included in the release artifact `library.properties`.
4. The `sketchbookLocation` is determined programmatically by your operation system, and is
where your Processing `sketchbook` folder is. This folder contains your installed libraries.
This variable is in the editable section, in case the location determined is incorrect. A 
symptom of an incorrect `sketchbookLocation` is that your library does not show up in the
Contribution Manager in Processing, after being installed. Please look at our 
[troubleshooting guide](troubleshooting.md) if you suspect this is the case.
5. The locations where dependencies will be resolved from are the `repositories`. Most
dependencies can be resolved from `mavenCentral`. You can add additional repositories here if 
your dependencies are hosted elsewhere.
6. Your dependencies should be listed within `dependencies`. For example, the example
library uses `org.apache.commons:commons-math3` as a dependency
The processing core is
resolved with `compileOnly` because we don't want to include it in your jar. 

4. **Add dependencies in the `dependencies` block in `build.gradle.kts`.**
   The sample library code  uses the following remote dependency that is resolved with maven:
   ```
   implementation("org.apache.commons:commons-math3:3.6.1")
   ```
   Add your own dependencies using the same structure. After you add these dependencies, you can
   build with Gradle to download them to the `libs` folder, and then you will be able to import them
   in your code. Dependencies added to the `implementation` "configuration" will be included in the
   release as jars. Dependencies added to the `compileOnly` configuration, such as Processing core, are
   available for compilation, but won't be included in the release as jars. If you want to import
   libraries released on Github using jitpack, jitpack is already included as a repository.
5. **Develop your library within `src/main/java/`.** Set the `package` to your own domain and name,
   and set the version. Processing libraries are required to return the current installed version.
6. When you are ready to test your library, build and create the release artifacts, as described
   below. Then you can install the library into Processing. The Gradle task
   `deployToProcessingSketchbook` will copy the contents of the release folder into the relevant
   folder in the sketchbook folder, a folder that Processing creates that holds your installed
   libraries, among other things. This installs your library into Processing.


### Providing the release artifacts
We have included Gradle tasks for creating the release artifacts.
This repository will build as is. Feel free to test the release how-to, to get a sense of what
to expect with those steps.

1. Fill in the file `release.properties` with information for your library. This information will be
   used by Gradle to create the  `library.properties` file, which is one of the required release artifacts.
2. **To build the library and create the release artifacts run the Gradle task `releaseProcessingLib`.**
   This task will create a `release` folder with needed artifacts. On the right menu bar in Intellij,
   there is a Gradle menu, with the Gradle icon of an elephant. Toggle `Tasks` > `processing` and double click `releaseProcessingLib`. This
   task has bundled the following required tasks:
   1. `build` task: this bundles a number of build tasks, including the `jar` task which creates a
      jar file. all build artifacts are in the folder `build`.
   2. documentation build.
   3. creation of the `library.properties` file: this file is built from the properties set in the
      `release.properties` file, plus the version, in the task `writeLibraryProperties`.
   4. within the `releaseProcessingLib` task, the `release` folder is created, jars of the library and
      dependencies are copied, and the `library.properties` file is copied. Also, a zip file is made.
