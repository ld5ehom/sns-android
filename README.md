# SNS Android App

## Project Overview 

- Utilized: Kotlin, Hilt, MVI, Orbit, Jetpack Compose 

-----
## Milestones
- M1 : App Basic Functionality Development
- M2 : Advanced Features and Testing

-----
**Task 1. Login Page**
- **Issues** : [task-1-login](https://github.com/ld5ehom/sns-android/tree/task-1-login)
- **Details** :
  - **Add login UI and ViewModel structure** - [commit b940785](https://github.com/ld5ehom/sns-android/commit/b940785f9d81e6ac7ecb2a41a4f8676c81d6d894)
    - Created LoginActivity with Retrofit and ViewModel integration
    - Designed basic login screen with Compose
    - Added user state handling for login success and failure
    - Implemented ViewModel for managing login logic
  - **Application Dependency Management** - [commit 6876356](https://github.com/ld5ehom/sns-android/commit/68763560103812d030eeb35c74f34f5874ddfb77)
    - Dependency Container Creation: An AppContainer class has been developed to act as a central repository for all dependency injections. This container manages the lifecycle and provision of various services and data sources required across the application.
    - Container Placement in App Class: The container is instantiated and stored within the App class. Placing the container in the App class makes it globally accessible to all other components of the application. This strategy ensures that dependencies are managed centrally and are consistently accessible.
  - **Refactoring Data Source References** - [commit 3b5f3a4](https://github.com/ld5ehom/sns-android/commit/3b5f3a4cde05dfb8441d9aeac6a2c3a20f83e61f)
    - Transitioned from localDataSource to userLocalDataSource : This change isolates data management to specific views, thereby decluttering activities and improving code clarity.
  - **Dependency Management with LoginContainer** - [commit 3b5f3a4](https://github.com/ld5ehom/sns-android/commit/3b5f3a4cde05dfb8441d9aeac6a2c3a20f83e61f)
    - Implemented a new LoginContainer : Focuses on managing dependencies exclusively for login functionalities, which enhances resource efficiency and streamlines dependency management.
  - **Hilt Gradle Plugin Setup** - [commit a0bb0ff](https://github.com/ld5ehom/sns-android/commit/a0bb0ffbbeb2ec548b3a3d13e6be78e15c5a507a)
    - Set up Hilt for dependency injection
      - Added Hilt Gradle plugin and related libraries in build.gradle 
      - Configured Hilt for dependency injection
    - Configure App Module
      - Defined the App module as the main module that integrates Presentation, Data, and Domain modules
      - Configured Hilt to handle dependency injection across all modules
    - Create Domain Module
      - Created a pure Kotlin module for business logic with no Android dependencies
      - Structured the module with usecase, model, and repository packages
    - Add Presentation Module
      - Developed an Android library to manage UI and ViewModel logic
      - Integrated the Domain module to retrieve and display business logic in the UI layer
    - Setup Data Module
      - Built an Android library to manage data sources, such as API calls and database access
      - Integrated the Domain module to provide data and execute business logic
    - Summarize Module Dependencies
      - App: Depends on Domain, Presentation, and Data modules
      - Domain: No external dependencies (Core module) 
      - Presentation: Depends on Domain module
      - Data: Depends on Domain module  
  - **Gradle Configuration Updates for Dependency Injection and Compatibility** - [commit ab11cd2](https://github.com/ld5ehom/sns-android/commit/ab11cd270b26e1a553f8420645388177588435a5)
    - Added kotlin-kapt to enable annotation processing for Kotlin.(build.gradle.kts(project: sns-android))
    - Integrated it with Hilt for dependency injection and configured kapt to correct error types during compilation.
    - The Gradle file for the Presentation module(build.gradle.kts(Module: presentation)) was updated to include Hilt for dependency injection and enable Jetpack Compose. 
    - Kotlin and Java 17 compatibility were configured, and the Domain module was added as a dependency.
  - **Custom Theme Setup** - [commit 8f62f55](https://github.com/ld5ehom/sns-android/commit/8f62f55baff6d95d37fe4949e436898da5cbc659)
    - Defines a color scheme using lightColorScheme with primary, surface, and background colors.
    - Uses SNSTheme to apply the theme, adjust the status bar color, and manage light/dark mode based on system settings.
  - **Login Domain and Module Refactoring** - [commit 52e62c7](https://github.com/ld5ehom/sns-android/commit/52e62c794ece91ade61595435f01a33f05331b80)
    - Relocating Activity Declaration to Presentation Module 
      - Moved the LoginActivity declaration from the app module's manifest to the presentation module's manifest to better organize the activity within the appropriate feature module.
    - Login Module Refactor and File Separation
      - Removed the existing login files and refactored the code by separating concerns into distinct components or modules for better organization and maintainability.
    - app/src/main/res/values/themes.xml
      -  Changed the parent theme to @style/Theme.AppCompat.NoActionBar to remove the action bar from the app
    - domain/LoginUseCase and SignUpUseCase
      - Implemented LoginUseCase and SignUpUseCase interfaces to handle authentication logic, each returning a token based on user credentials.
  - **Initial Build of Authentication UI**
    - presentation/Login/WelcomeScreen 
      - Designed a WelcomeScreen composable with a title, subtitle, and a button that navigates to the login screen, including a preview for testing the layout.
    - presentation/Login/LoginScreen
      - Designed a LoginScreen composable with input fields for ID and password, and a button to trigger the login action. The screen includes a navigation link to the sign-up screen and a preview for layout testing.
    - presentation/SignUp/SignUpScreen
      - Created a SignUpScreen composable with input fields for ID, username, and two password fields, along with a button to initiate the sign-up process. The screen also includes a preview for testing the layout.
    - presentation/Components/Buttons
      - Implemented custom FCButton and CustomButton components with configurable text and click actions, allowing them to be reused across the app's UI.
    - presentation/Components/TextFields
      - Built reusable CustomTextField components for user input, which are used in both the login and sign-up screens to handle input for ID, username, and passwords.
    



-----
## Progress Tracking
- **Overall Progress** : Task 1 In Progress


