# Getting Started
We are excited that you wish to contribute to the Processing ecosystem.

This template is designed to make that process as simple as possible.

This guide will get you started on your journey, help orient you to the template. 


## Setting up the environment
These steps will help you create your own repository on Github, and install necessary software.

1. **Create your own repository on Github, using the repository [processing-library-template](https://github.com/mingness/processing-library-template)**
   as a template.
   ([A guide to creating a repository from a template.](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template))
   Select a different name for your repository that represents the library you will build. You can also 
   rename your repository at a later time.
2. **Clone your new repository,** aka create a local copy of your repository on your computer.
   ([A guide to cloning a repository](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository))
3. **Install [Processing](https://processing.org/download)**, if you haven't already. 
4. **Install an integrated development editor (IDE)** such as [Intellij](https://www.jetbrains.com/idea/download), 
   or [Visual Studio Code](https://code.visualstudio.com/), if you haven't already. 
   Intellij comes fully featured, and has a free community version. Additional features can be added to 
   Visual Studio Code by installing extensions, such as Gradle for Java, or Language support for Java.

## First steps
These steps use the template as is, to allow you to interact with the build environment, and with Processing.
You can use these interactions to test the library.

1. **Open up your new repository in your chosen IDE.**
2. **Run the Gradle task, deployToProcessingSketchbook**: The repository as is should build in Gradle. 
   In the Gradle menu (elephant) in your IDE, navigate to `Tasks` > `processing` > 
   `deployToProcessingSketchbook`, and double click on `deployToProcessingSketchbook`. 
   This will run the Gradle task named `deployToProcessingSketchbook`.
   Running this task will build the library, create the release artifacts, and copy them into 
   where your installed libraries are stored.
3. **The library can now be seen in the Contribution Manager.** Open Processing, and click on 
   `Sketch` > `Import Library ...` > `Manage Libraries ...`. This opens the Contribution Manager. 
   There should be an entry named "A Template Example Library" with a check mark next to it. 
   That is the entry for the example library that was installed in the previous step. 
   If the library does not appear in the Contribution 
   Manager, please check the [troubleshooting guide](troubleshooting.md).
4. **Customize what will be shown in the Contribution Manager for your library**:
   Open the file `release.properties` and change the value of the fields `name`, 
   `authors`, and `sentence`. Rerun the Gradle task, `deployToProcessingSketchbook`. This
   will reinstall the library with the updated values, and these values should be visible
   in the Contribution Manager.

## Next Steps
Great, now you should be a little more familiar with the workings of the library template.

Checkout some other guides on

1. [The development process](develop)
2. [Releasing your library](release)
3. [Troubleshooting](troubleshooting.md)
