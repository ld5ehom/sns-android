# SNS Android App

## Project Overview 
- Designed and developed an authentication system with Login and Sign-Up features using Kotlin, Jetpack Compose, and Hilt for dependency injection. The project followed Android Clean Architecture, separating the codebase into Domain, Presentation, and Data layers for better maintainability, testability, and scalability.
- Implemented state management and side-effect handling using Orbit MVI, ensuring smooth user interactions and asynchronous operations. Integrated Navigation Compose for seamless navigation between screens, with conditional routing based on authentication tokens checked in SplashActivity.
- Handled API interactions via Retrofit, standardizing responses using a CommonResponse model, and managed user tokens with DataStore, implementing custom use cases for token management.
- The clean architecture approach improved the scalability and maintainability of the project, while Hilt streamlined dependency management across the modular structure.
- Utilized: Kotlin, Hilt, MVI (Orbit), Jetpack Compose, Retrofit, DataStore, Android Clean Architecture.

-----
## Reference Site
- Android Guide to app architecture : https://developer.android.com/topic/architecture?hl=en
- Android Customize an image : https://developer.android.com/develop/ui/compose/graphics/images/customize?hl=en
- Kotlin : https://kotlinlang.org/docs/home.html

-----
## Milestones
- M1 : App Basic Functionality Development
- M2 : Advanced Features and Testing

-----
### Task 1. Login Page
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
  - **Initial Build of Authentication UI** - [commit 748dfb3](https://github.com/ld5ehom/sns-android/commit/748dfb3b794946b617dca82796ebde6a4fd9194e)
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
  - **Login Flow Update with Hilt and Navigation Component(presentation)** - [commit 5b702af](https://github.com/ld5ehom/sns-android/commit/5b702af4ce9659030e6f86972d5e7ed079a64d56)
    - Organizing Login Flow with Hilt and Navigation Compose
      - Since the login process is part of a single context, the navigation component was used to group it into one flow. Hilt and Navigation Compose were integrated to manage dependencies and navigation within the login flow.
    - Added Navigation and Hilt Integration (gradle : presentation)
      - Integrated navigation-compose and hilt-navigation-compose dependencies in the presentation module to manage navigation and dependency injection within the composable screens.
    - Login Navigation Host (LoginNavHost)
      - Created a host that manages three composables (LoginScreen, SignUpScreen, WelcomeScreen) within a single context, allowing seamless navigation between them.
    - Login Route (LoginRoute)
      - Instead of hardcoding route names directly in the composables, a LoginRoute sealed class was created to store and reference route names in a centralized and organized way, making them easier to manage and reducing errors. 
    - LoginActivity with Navigation and Theming
      - LoginActivity sets up the login UI using Jetpack Compose, applies the SNSTheme, and manages navigation between login-related screens (WelcomeScreen, LoginScreen, and SignUpScreen) via LoginNavHost for a cohesive login flow.
    - LoginViewModel with Hilt and Coroutines
      - The LoginViewModel handles the login logic using Hilt for dependency injection. It integrates the LoginUseCase to manage the login process, and uses viewModelScope to launch coroutines, ensuring that login operations are performed asynchronously when the user clicks the login button.
  - **LoginViewModel Refactoring and Error Fixes with Dagger and Orbit MVI** - [commit 2258ba2](https://github.com/ld5ehom/sns-android/commit/2258ba23aba26418981cff0cf2e4b274a9e9ece3)
    - Error : Dagger Missing binding 
      - The Login ViewModel requested the LoginUseCase, but the LoginUseCase implementation was not bound to the Hilt graph, causing an error.
      - To resolve this, the LoginUseCaseImpl was bound in the data module, considering the login functionality.
    - Error : LoginActivity Implement interface dagger.hilt 
      - The error occurred in presentation/login/LoginActivity requiring the implementation of a Dagger Hilt interface.
      - Added @AndroidEntryPoint to LoginActivity.
    - data/usecase/LoginUseCaseImpl 
      - LoginUseCaseImpl uses Dagger's @Inject for dependency injection, and the invoke function returns a token within a result.
    - Refactoring LoginViewModel with Orbit MVI and State Management
      - The LoginViewModel has been refactored to enhance state management and handle side effects more effectively using the Orbit MVI (Model-View-Intent) pattern. This update improves the structure of the login feature by managing user input, login logic, and error handling in a clearer and more maintainable way.
      - Integrated Orbit MVI Pattern: LoginViewModel now implements ContainerHost to manage login state (LoginState) and side effects (LoginSideEffect), providing clear state management.
      - Added LoginState: LoginState now holds the login credentials (ID, password), enabling cleaner and more predictable state updates.
      - Introduced Side Effect Handling: LoginSideEffect manages UI-related actions like showing toast messages, keeping business logic separate from the UI.
      - Improved Error Handling: A CoroutineExceptionHandler now handles login errors, showing error messages via side effects.
      - Input Handling Updates: Added onIdChange and onPasswordChange to update state when user input changes.
  - **Login Feature Updates: Password Masking, Orbit MVI Integration and Retrofit Setup** - [13f4a3e](https://github.com/ld5ehom/sns-android/commit/13f4a3e1e5d607da72b734bdc1905d40562784ac)
    - CustomTextField Update for Password Masking
      - Password Masking Added: visualTransformation was added to hide text input for password fields using PasswordVisualTransformation.
    - Enhancements in LoginScreen with Orbit MVI Integration
      - Orbit MVI for State and Side-Effect Management:
        - Integrated Orbit MVI for managing state and side effects. The collectAsState function is utilized to capture login credentials (id, password), and collectSideEffect manages side effects such as toast messages through LoginSideEffect.Toast.
      - Improved Input Handling and Event Triggering:
        - Added PasswordVisualTransformation to the password field for secure input handling. The login button now triggers onLoginClick in the LoginViewModel, linking UI actions to the ViewModel’s business logic.
    - RetrofitModule: Integrated OkHttpClient and JSON Converter for API Requests
      - OkHttpClient: Provides an OkHttpClient instance bound to the Dagger dependency graph to handle network requests. 
      - Retrofit Configuration: Configures Retrofit with kotlinx.serialization for JSON conversion, ignoring unknown keys during deserialization for flexibility. The API's base URL is set to the ld5ehom_HOST, and the OkHttpClient is attached for managing HTTP requests.
  - **Standardized API Interaction with Retrofit and CommonResponse** - [commit 8d015e8](https://github.com/ld5ehom/sns-android/commit/8d015e824489c5c7d59414148dda680e31735871)
    - data/retrofit/UserService 
      - An interface that defines network requests using Retrofit for handling API calls (Post, Get methods, etc.).
    - data/model/CommonResponse
      - CommonResponse is a generic data class in data/model used to standardize API responses. It includes fields for result status, data, and error details to ensure consistent handling of API responses.
    - RetrofitModule Update: Providing UserService for API Calls
      - UserService Update: An update was made in RetrofitModule to provide an instance of UserService using Retrofit. This allows UserService to be injected and utilized across the application for making API requests.
  - **Login API Request Handling: Data Model and Use Case Implementation** - [commit 5dad6c5](https://github.com/ld5ehom/sns-android/commit/5dad6c5df8a33497ea07d5745486962ab8d8a704)
    - data/model/LoginParam
      - The LoginParam class is a data model used to store the login credentials (loginId and password) for API requests. It includes a method toRequestBody() that converts the LoginParam object into a JSON-formatted RequestBody, which is required for making login API requests.
    - LoginUseCaseImpl Implementation with Dagger for API Integration
      - LoginUseCaseImpl leverages Dagger's @Inject for dependency injection, automatically providing UserService to handle login API calls. The invoke function converts user credentials into a request body, calls the login API, and safely handles the response using Kotlin’s runCatching.



### Task 2. Sign Up Page
- **Issues** : [task-2-signup](https://github.com/ld5ehom/sns-android/tree/task-2-signup)
- **Details** :
  - **Sign-Up Process Updates: State Management, Navigation, and Error Handling** - [commit 45b49ed](https://github.com/ld5ehom/sns-android/commit/45b49edd90d4bca2c2997f3879e2ff8d4b2a84bc)
    - Added navigation functionality from LoginScreen to SignUpScreen.
    - SignUpViewModel: State Management and Side Effects with Orbit MVI
      - Handles the sign-up process using SignUpUseCase with Orbit MVI for state management and side effects. 
      - User inputs such as id, username, password, and repeatPassword are updated in the state, and any errors during the process are handled with a toast side effect.
    - SignUpScreen Update: Added Navigation and Side-Effect Handling
      - SignUpScreen: Integrated side-effect handling to display toast messages for errors and navigate to the login screen upon successful sign-up. 
      - The screen now collects user input (id, username, password) and reflects changes through the SignUpViewModel.
    - Login Navigation Host Update
      - Updated the navigation flow to allow users to navigate from the SignUpScreen to the LoginScreen after successful sign-up.
  - **Sign-Up Process Updates: Use Case, API Integration, and Data Model** - [commit af807da](https://github.com/ld5ehom/sns-android/commit/af807dae3069211c8c5bd47d4eec129fa38cd26d)
    - SignUpUseCaseImpl(Sign-Up UseCase Implementation): Handling User Registration via UserService
      - The SignUpUseCaseImpl class manages user registration by creating a SignUpParam object, converting it to a JSON RequestBody, and sending it through userService.signUp(). 
      - It then checks if the API response is "SUCCESS" and returns the result.
    - data/UserService update: Added Sign-Up API Integration
      - Added a signUp method to the UserService interface, which sends a POST request to the "users/sign-up" endpoint with a JSON RequestBody. 
      - The method returns a CommonResponse<Long> to handle the server's response for the sign-up process.
    - data/SignUpParam: Data Model for Sign-Up API Request
      - SignUpParam is a serializable data class that holds the parameters required for the user sign-up API request, including loginId, name, password, extraUserInfo, and profileFilePath. 
      - It includes a toRequestBody function to convert the data into a JSON-formatted RequestBody for the API request.
    - data/UserModule Update
      - Binds SignUpUseCaseImpl to the SignUpUseCase interface
  - **Sign-Up Process Updates: State Management, Token Handling, and Navigation** - [commit a4cbd54](https://github.com/ld5ehom/sns-android/commit/a4cbd5444b44fb52301031356641f7451afaaa02)
    - LoginViewModel and SignUpViewModel blockingIntent Update 
      - Switched from intent to blockingIntent for handling state updates in text input fields to prevent errors and delays during rapid input. 
      - This ensures immediate and synchronous state updates, improving user experience and input handling in the application.
    - Navigation to MainActivity After Successful Login
      - Added navigation logic to redirect users to MainActivity after a successful login. 
      - The Intent flags FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_NEW_TASK are used to start MainActivity in a new task and clear the back stack, ensuring that the login screen is removed from the history.
    - GetTokenUseCaseImpl: Retrieving User Token from DataStore
      - GetTokenUseCaseImpl retrieves the user's authentication token from UserDataStore by implementing the GetTokenUseCase interface.
    - SetTokenUseCaseImpl: Handling Token Storage via UserDataStore
      - SetTokenUseCaseImpl handles saving the user's authentication token into UserDataStore. 
      - The invoke function takes the token and stores it by calling setToken() in the DataStore.
    - data/UserDataStore: Managing User Token in DataStore
      - build.gradle(data) : Added datastore dependency to the project in Gradle using implementation.
      - UserDataStore provides methods to manage the user's authentication token in Android's DataStore. 
      - It includes setToken for saving the token, getToken for retrieving it, and clear for removing all stored data.
  - **SplashActivity and Dagger Updates: Token Handling, Navigation and Context Binding** - [340e3a9](https://github.com/ld5ehom/sns-android/commit/340e3a96542bc0bfde25310f5d69cd3da3cae752)
    - SplashActivity: Token Check and Navigation Based on Login Status
      - The SplashActivity checks the user's login status by retrieving the token via GetTokenUseCase. 
      - If a token exists, it navigates to the MainActivity. If not, it directs the user to the LoginActivity. 
      - The activity uses Intent flags (FLAG_ACTIVITY_CLEAR_TASK and FLAG_ACTIVITY_NEW_TASK) to clear the task stack and prevent returning to the splash screen.
    - SplashActivity: Set as Main Entry Point in Android Manifest
      - Added SplashActivity to the AndroidManifest.xml file as the main entry point of the application. 
      - The intent filter with MAIN action and LAUNCHER category ensures that this activity is launched when the app is opened.
    - Dagger/MissingBinding Error : Binding Token UseCase Implementations to Interfaces with Dagger
      - Added bindings for the token-related use cases (GetTokenUseCaseImpl, SetTokenUseCaseImpl, and ClearTokenUseCaseImpl) to their corresponding interfaces in the Dagger dependency graph using @Binds. 
      - This ensures that the correct implementations are injected wherever the use case interfaces are required.
    - Dagger/MissingBinding Error -> AppModule: Binding Application Context for Dependency Injection
      - The AppModule class provides the Application context by binding the Application instance to the Context interface. 
      - This setup allows Dagger Hilt to inject the application context wherever the Context type is required within the application.

### Task 3. User Profile Page Setting
- **Issues** : [task-3-profile](https://github.com/ld5ehom/sns-android/tree/task-3-profile)
- **Details** :
  - **Main Navigation and Route Updates: Enhanced Structure and Navigation Flow** - [60ee919](https://github.com/ld5ehom/sns-android/commit/60ee9199d7caf4b509ada6a5e81f738262e566e4)
    - MainNavHost: Managing Navigation for Home, Profile, and Post Screens
      - The MainNavHost manages navigation between the app's main screens using a NavController. 
      - It includes navigation between the "Home", "Profile", and "Post" screens. 
      - The start destination is set to the "Home" screen, with a top bar showing the app title and a bottom navigation bar for switching between screens.
    - MainBottomBar: Handling Navigation and Icon Highlighting in the Bottom Bar
      - The MainBottomBar component provides navigation functionality within the app using the NavController. 
      - It determines the current route and navigates between the "Home", "Profile", and "Post" screens, with special handling for starting WritingActivity when the "Post" route is selected. 
      - The bottom bar highlights the currently selected icon using MaterialTheme.
      - For the "Post" route, a separate activity (PostActivity) is launched, while navigation for the other routes is handled within the same navigation graph using the NavController.
    - MainRoute Enum Update: Renaming Routes and Descriptions
      - The MainRoute enum was updated to reflect changes in route names and descriptions.
      - The corresponding routes and content descriptions were updated to "HomeScreen" (Post List), "PostScreen" (Create Post), and "ProfileScreen" (My Profile), aligning with the new functionality and navigation structure.
  - **Profile Screen UI and ViewModel Updates** 
    - Profile Screen UI
      - Profile Screen UI and Logic: Implements a screen for managing user profile settings such as username, profile image, and logout functionality. It handles navigation to the login screen and displays a toast message for certain actions.
      - Composable Structure: The ProfileScreen composable displays user profile settings and provides buttons for changing the profile image, editing the username, and logging out. The UI includes state management using the ProfileViewModel.
    - Profile Image Component
      - CustomProfileImage Component: Displays a profile image inside a circular frame. If no profile image URL is provided, a default person icon is displayed.
      - Customization: The component allows for customization of the image and border via the modifier and borderWidth parameters.
    - Profile Screen ViewModel and State Management
      - ProfileViewModel: Manages the state of the profile screen, including loading the user’s profile information (username and profile image) and handling the logout process.
      - State and Side Effect Management: Utilizes Orbit MVI to manage the state and side effects. It updates the screen state with user data and handles side effects like showing toast messages and navigating to the login screen on logout.
    - Error Handling in ClearToken Use Case
      - ClearTokenUseCase: The Result<Unit> return type was added to the ClearTokenUseCase interface to standardize the error-handling mechanism for clearing the user's token.
      - ClearTokenUseCaseImpl: In the ClearTokenUseCaseImpl implementation, kotlin.runCatching{} was introduced to wrap the token-clearing logic. This guarantees that any exceptions raised during the userDataStore.clear() operation are caught and returned as part of a Result<Unit>.
    - UserDTO Data Model and Domain Conversion
      - UserDTO: A serializable data transfer object (DTO) designed for use with APIs. It represents user-related data fields including id, loginId, userName, extraUserInfo, and profileFilePath.


**Task 4: Post Creation**
- **Issues** :
- Develop functionality for users to create and submit new posts.

**Task 5: Post List Screen**
- **Issues** :
- Build a screen that displays a list of posts in a feed or timeline format.

**Task 6: Comments**
- **Issues** :
- Implement a feature that allows users to view and add comments on posts.

**Task 7: Advanced Features and Testing**
- **Issues** :

-----
## Progress Tracking
- **Overall Progress** : Task 3 In Progress


