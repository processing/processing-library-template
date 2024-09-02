# Troubleshooting
1. If the new library does not appear in the Contribution Manager, one possible issue is the location
   of your `sketchbook` folder. Processing stores installed libraries in the `sketchbook` folder. Where
   your `sketchbook` folder is located depends on your operating system. You can see where Gradle 
   thinks your sketchbook folder is in the task run output. Check that the folder exists, and contains
   the subfolder `libraries`. If not, you can check the sketchbook location in your Processing application 
   preferences.  Click on File > Preferences, and note the sketchbook folder location. If this location
   is different from what's in the Gradle build file, you will need to input the value manually. Also, 
   please submit an issue to this repository.
