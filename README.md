# Processing Library Template

A prototype for a template for developing Processing libraries. This is a project of 
the 2024 New Beginnings (pr05) Grant from the Processing Foundation, to simplify the 
workflows for libraries, tools, and modes, mentored by @Stefterv. This template is 
based on Intellij IDE and Gradle for building and releasing the library. It is also 
heavily based on the following two existing Processing library templates, also based 
on Intellij and Gradle.

- https://github.com/processing/processing-library-template-gradle
- https://github.com/enkatsu/processing-library-template-gradle

I also wish to thank the developers of these repositories, who generously provided 
guidance and time. This template has been developed in collaboration with @enkatsu.

## How to use
This repository is designed to be built with Gradle. The best IDE experience is with
Intellij.

This repository can be built as is. On the right menu bar in Intellij, there is a 
Gradle menu. Toggle Tasks > build and double-click on build to run the build.
This will create a build artifacts in the `build` folder. The built jar can be found
in `build/libs`. 


## Project Diary

### Weeks 1 + 2 (July 1, 2024 - July 14, 2024)

Have taken a close look at two templates:
- https://github.com/processing/processing-library-template-gradle
  - The developer interaction for configuring the build is to fill out the 
    /resources/build.properties file with all build and release parameters
  - It resolves Processing by pointing to the locally installed jar files
  - The build.gradle file has 141 lines; processing-library.gradle 159 lines
  - It provides gradle tasks for releasing the library
  - This template provides fully documented example code, and an example
- https://github.com/enkatsu/processing-library-template-gradle
  - The developer interaction for configuring the build is to add dependencies
    to the build.gradle
  - It resolves Processing from Maven, using an unofficial version, 3.3.7
  - The build.gradle file has 56 lines

Stef suggested that the best way to resolve Processing at this time is to use
jitpack to compile a Github repository. There is an unofficial repository for
resolving Processing4 with jitpack at https://github.com/micycle1/processing-core-4/
Additionally, Stef suggested that we should move to Kotlin, as it is the current
default; the gradle files in both repositories described above use Groovy.
It's clear that both templates provide great value, and there is a benefit to
combining the two. 

I will provide this template in addition to the others, and conduct user testing,
to get a sense of what user interaction I would propose to release.
