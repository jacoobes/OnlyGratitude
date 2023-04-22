# Compose Multiplatform Application

### Desktop
Run the desktop application: `./gradlew :composeApp:run`


### Copy onto local machine

- Ensure you have [intellij](https://www.jetbrains.com/idea/download/#section=windows) installed
- Ensure you have [git](https://git-scm.com/) installed 
- In a terminal, run `git clone https://github.com/jacoobes/OnlyGratitude.git`
- Your project should appear in OnlyGratitude directory


### Directories

- composeApp/
  - Where the app is held
  - src/
    - commonMain/ 
      - Anything that isn't specific to desktop applications can be put here. (Logic, methods, classes)
      - Will hypothetically contain all the logic and things needed to make core features work
      - You might see some UI stuff in there, but its there because I created a multiplatform project.
    - desktopMain/
      - Anything Compose related. This is where the main UI will be. 
      - Everything displayable will be put here.
### Files
- *.kt files
  - Any kotlin source file
- gitignore
  - Any files / folders that are specified here will be ignored by git.
- build.gradle.kts
  - File to configure how our app is run
