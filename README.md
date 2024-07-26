# Processing Library Template

A prototype for a template for developing Processing libraries. This is a project of 
the 2024 New Beginnings (pr05) Grant from the Processing Foundation, to simplify the 
workflows for libraries, tools, and modes, mentored by [@Stefterv](https://github.com/stefterv). This template is 
based on Intellij IDE and Gradle for building and releasing the library. It is also 
heavily based on the following two existing Processing library templates, also based 
on Intellij and Gradle.

- https://github.com/processing/processing-library-template-gradle
- https://github.com/enkatsu/processing-library-template-gradle

I also wish to thank the developers of these repositories, who generously provided 
guidance and time. This template has been developed in collaboration with 
[@enkatsu](https://github.com/enkatsu).

## How to use
This repository is designed to be built with Gradle. The best IDE experience is with
Intellij.

This repository can be built as is. On the right menu bar in Intellij, there is a 
Gradle menu. Toggle Run Configurations, and double click each configuration to run it. 
- `clean` deletes the `build` folder
- `build` will create build artifacts in the `build` folder. The built jar can be found
in `build/libs`. 
- `javadoc` creates documentation in `build/docs`
- `releaseProcessingLib` creates the release artifacts in the `release` folder


## Project Diary

### Weeks 1 - 3 (July 1 - 21, 2024)

Having discussed with Stef, my mentor, about the general approach to the entire 
project, I will tackle the library template first, then the workflow for adding 
library contributions, while conducting stakeholder interviews. After that, I will
take a look at the documentation, and hopefully have time to look at the process 
for tools and modes as well.

I value intuitiveness in a template, and to achieve this, I have interviewed 
members of the community who have developed libraries recently. Their responses 
have guided me on what has worked well, and what leaves room for confusion. I will 
provide more information on findings from these interviews at a later point.

There are two existing templates for developing libraries for Processing on 
Github, listed below. These first few weeks, I have worked with these two 
templates, to better understand them.

I was able to use both to build a small library, without hiccups. Some specific 
differences between the two templates:

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
  - It does not provide scripts for releasing the library
  - This template provides example code and example, but it is not commented 
    extensively

Here are some thoughts, related to each bullet point above:

- Which way of interacting with a template will be most intuitive, adding 
parameters to a text file that is then parsed, or editing directly into files? 
There is no clear winner for me at the moment. Best to let the users tell us - do 
user testing. Leave both options available for the duration of the grant, and 
solicit interviews with library developers.

- Stef suggested that the best way to resolve Processing at this time is to use
jitpack to compile a Github repository. There is an unofficial repository for
resolving Processing4 with jitpack at https://github.com/micycle1/processing-core-4/

- The build.gradle + processing-library.gradle files of the processing template 
are large, and aren't easy to digest on first look. That's because they are not 
designed to be edited. Is this a missed opportunity for gaining familiarity with 
gradle? enkatsu's template is simple, invites and requires editing to configure
to your library. As mentioned in the first comment, I'm not sure which user 
interaction is most intuitive, but will develop one with a simpler gradle file for
direct editing.

- It is desired to provide scripts for releasing the library. Will adopt the gradle 
tasks for releasing from processing template.

- It is desired for the repository be clear and intuitive to even a beginner at 
developing libraries. Ideally, the template should run and build and release, 
without input from the user. I want to extend the example build to require 
dependencies, to help show how to add them.

Additionally, Stef suggested that we should move to Kotlin, as it is the current
default; the gradle files in both repositories described above use Groovy.
It's clear that both templates provide great value, and there is a benefit to
combining the two. 
