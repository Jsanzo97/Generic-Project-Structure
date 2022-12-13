# Steps to use the project
- Init repository on your local machine ```git init```
- Clone repository with ```git clone https://github.com/Jsanzo97/Generic-Project-Structure.git```
- Remove initial origin with ```git remote remove origin```
- Add your remote repository ```git remote add origin https://github.com/your-repository ```
- In Android studio, change the name of your aplication ```app/src/main/res/values/strings.xml``` setting new value
- Also change your package name and id in ```app/build.gradle.kts``` in the first line of ```defaultConfig```
- Change name of the directory on your file browser ```app/src/main/kotlin/com.example.movielist``` to ```app/src/main/your.package```
- Finally change the package name in the first line of ```app/src/main/kotlin/your.package/MainActivity.kt```

## Congratulations! Your project with Gradle DSL and some diferent modules is now configured!
