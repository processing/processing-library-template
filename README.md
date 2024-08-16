# Processing Library Template [WIP]
This is a template to help developers of Processing libraries to develop and release. 

Three important outputs are required to contribute a library to Processing, and this template provides 
help and guidance on them. They are:
1. **The library's code** - This template will build your code into a jar file with Gradle.
2. **A url that serves the release artifacts** - We have already configured Gradle tasks to create the
release artifacts. If you host your code on Github, You can create a Github release that serves the 
release artifacts.
3. **A website for the library** - We recommend using [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/)
   to create a static website for your library. It allows you to write content for your website
   using markdown, and structure the site using a yml configuration file. We provide a skeleton
   MkDocs website as part of this template.

References for developing libraries for Processing can be found on the following Github wiki pages:
- https://github.com/benfry/processing4/wiki/Library-Basics
- https://github.com/benfry/processing4/wiki/Library-Guidelines
- https://github.com/benfry/processing4/wiki/Library-Overview



## How to use this template

For each of the three outputs that are required, we have a section to guide you in creating it.
1. [Developing your code](#developing_your_code)
2. [Providing the release artifacts](#providing_the_release_artifacts)
3. [Creating your website](#creating_your_website)

### Developing your code
We use Gradle to help build your code, and import dependencies.
Follow the instructions below for setting up the environment to 
to develop your library.

1. **Use this repository as a template repository.** Look for a highlighted button to the top right 
with the words "Use this template." Select "Create a new repository" and then name your repository as 
something else, as you'd like.
2. **Define the short name of your library as the `rootProject.name` in `settings.gradle.kts`.**
   The "short name" is the name of your library, without the domain.
   It will also be used to name the folder in the Processing sketchbook that holds the library files,
   and the release zip file.
3. **Define the `group` and `version` of your library in `build.gradle.kts`.** The group is your
domain, in reverse. The standard for version strings is semantic version (semver).
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


#### Using GitHub Actions for Library Release
You can automatically distribute the library using GitHub Actions. Libraries are distributed in `.zip` format to the [releases section](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository) of the GitHub repository. The trigger to execute the release workflow is pushing a git tag that starts with `v`. For example:
```sh
git tag v0.0.1
git push origin v0.0.1
```



### Creating your website
A requirement for having your library listed officially is to have a stable website.  Library developers
have clued us into [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/),
as a simple and easy to use way of creating content. If your library is on Github, then you can take
advantage of Github Pages to deploy and host the website. We configure this template for this scenario.

MkDocs is Python-based, so if you are not already a Python user, hopefully your OS already has a system
Python installed. You can check by running `python --version` in a terminal. If you do not already have
Python installed, or do not have Python 3, you will need to install Python 3 as a prerequisite to these
instructions.

1. Install [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/).
   Please refer to the documentation. For example, run `pip install mkdocs-material` in a terminal.
2. We already created a very barebones website in the template. you can
   see it in action by running `mkdocs serve`
3. As seen in the documentation, the configuration of the website
   is defined in the `mkdocs.yml` file. We have included some plugins, such
   as search, code block copy and annotations, and navigation bars. Of course,
   configure your website as you prefer.
4. The template has a Github workflow for deploying the MkDocs website
   as a Github Page. You'll need to activate Github Pages and Github Actions
   for your project. For Github Pages, set the Source to "Deploy from a branch."
   Set the branch to `gh-pages` ; this branch is created by the 
   command `mkdocs gh-deploy`. Serve from the root folder.

## Contributors

This template was created as part of the 2024 New Beginnings (pr05) Grant from the 
[Processing Foundation](https://github.com/processing), to simplify the
workflows for libraries, tools, and modes, mentored by [@Stefterv](https://github.com/stefterv), and lead by []().

It is based on and inspired by a number of Processing library templates, including:
- https://github.com/processing/processing-library-template-gradle
- https://github.com/enkatsu/processing-library-template-gradle
- https://github.com/hamoid/processing-library-template/

I wish to thank the developers of these repositories, who generously provided
guidance and time. This template has been developed in collaboration with
[@enkatsu](https://github.com/enkatsu).
