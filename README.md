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
Gradle menu. Toggle `Tasks` > `processing` and two tasks will be listed below:
- `releaseProcessingLib` creates the release artifacts in the `release` folder
- `copyToLocalProcessing` will copy the contents of the release folder into the relevant
folder in the sketchbook folder, a folder that Processing creates that holds your installed
libraries, among other things.

TO run a task, double click on it.

### Using GitHub Actions for Library Release
You can automatically distribute the library using GitHub Actions. Libraries are distributed in `.zip` format to the [releases section](https://docs.github.com/en/repositories/releasing-projects-on-github/managing-releases-in-a-repository) of the GitHub repository. The trigger to execute the release workflow is pushing a git tag that starts with `v`. For example:
```sh
git tag v0.0.1
git push origin v0.0.1
```
