# Processing Library Template [WIP]
This is a template for developing Processing libraries. This template is built and released
with Gradle.

## How to use

### Development
1. **Use this repository as a template repository.** Look for a highlighted button to the top right 
with the words "Use this template." Select "Create a new repository" and then name your repository as 
something else, as you'd like.
2. This repository will build as is. Feel free to test the release how-to, to get a sense of what
to expect with those steps.
3. **Define the short name of your library as the `rootProject.name` in `settings.gradle.kts`.**
   The "short name" is the name of your library, without the domain.
   It will also be used to name the folder in the Processing sketchbook that holds the library files,
   and the release zip file.
4. **Define the `group` and `version` of your library in `build.gradle.kts`.** The group is your
domain, in reverse. The standard for version strings is semantic version (semver).
5. **Define the location of your sketchbook folder.** This facilitates testing of your library
in Processing. If you wish to resolve a dependency on a locally installed Processing library,
you will also need to set file path.
6. **Add dependencies in the `dependencies` block in `build.gradle.kts`.** 
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
4. **Develop your library within `src/main/java/`.** Set the `package` to your own domain and name,
and set the version. Processing libraries are required to return the current installed version.



### Release
1. Fill in the file `release.properties` with information for your library. This information will be
used to create the required `library.properties` file, done by a Gradle task.
2. **To build the library and create the release artifacts run the Gradle task `releaseProcessingLib`.** 
This task will create a `release` folder with needed artifacts. On the right menu bar in Intellij, 
there is a Gradle menu. Toggle `Tasks` > `processing` and double click `releaseProcessingLib`. This 
task has bundled the following required tasks:
   1. `build` task: this bundles a number of build tasks, including the `jar` task which creates a 
   jar file. all build artifacts are in the folder `build`.
   2. documentation build. 
   3. creation of the `library.properties` file: this file is built from the properties set in the 
   `release.properties` file, plus the version, in the task `writeLibraryProperties`.
   4. within the `releaseProcessingLib` task, the `release` folder is created, jars of the library and
   dependencies are copied, and the `library.properties` file is copied. Also, a zip file is made.
3.`copyToLocalProcessing` will copy the contents of the release folder into the relevant
folder in the sketchbook folder, a folder that Processing creates that holds your installed
libraries, among other things. This installs your library into Processing, and you can test
the release with your examples.


## Contributors

This template was created as part of the 2024 New Beginnings (pr05) Grant from the 
[Processing Foundation](https://github.com/processing), to simplify the
workflows for libraries, tools, and modes, mentored by [@Stefterv](https://github.com/stefterv).

It is based on and inspired by a number of Processing library templates, including:
- https://github.com/processing/processing-library-template-gradle
- https://github.com/enkatsu/processing-library-template-gradle
- https://github.com/hamoid/processing-library-template/

I wish to thank the developers of these repositories, who generously provided
guidance and time. This template has been developed in collaboration with
[@enkatsu](https://github.com/enkatsu).
