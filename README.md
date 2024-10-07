# SNS Android App

## Project Overview 

- Utilized: Kotlin, Hilt

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
  - **Hilt Gradle Plugin Setup**
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

-----
## Progress Tracking
- **Overall Progress** : Task 1 In Progress


