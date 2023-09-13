# User Listing

Listing is single-page application showcasing the users.

The main goal of this app is to be a sample of how to build an high quality Android application that uses the mutlimodule Architecture components,MVVM,Coroutines,Jetpack,Koin etc. in Kotlin.

## Development Approach
Selection of modularization approach to leverage the
- Reusability
- Scalability
- Testability
- Build time

## Architecture
The app uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.
- Koin is used for the dependency injection.
- Retrofit is used to api integration
- Feature module for the users list containing api, domain and presentation module
- outer domain module have sub module for network layer
- Feature domain layer Implement usecase to fetch data from repository fetch data from network layer
- View Model are the one responsible to communicate with the data layers

## UI
Application Design is based on flat designs.

Application have a single screen that is regarded as feature i.e. Recipe List.
It consist of three states.

- Loading State. Displaying loader when screen is launched and network call is on the ongoing to fetch the data.
- Error State. Display snackbar if any error happen with retry button.
- Recipe List Loaded. Display List of user if data is fetched successfully.


## Libraries used for feature development

<li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel</a></li>
<li><a href="https://material.io/develop/android/docs/getting-started/">Material Design</a></li>
<li><a href="https://developer.android.com/kotlin/coroutines">Coroutines</a></li>
<li><a href="https://insert-koin.io/">Koin</a></li>
<li><a href="https://square.github.io/retrofit/">Retrofit</a></li>
<li><a href="https://github.com/google/gson">Gson</a></li>
<li><a href="https://developer.android.com/jetpack/compose">Jetpack Compose</a></li>


## Testing Strategies
Unit testing is done using Junit.
<li><a href="https://github.com/junit-team/junit4">Junit</a></li>
Integration testing can be done using Espresso.
<li><a href="https://developer.android.com/training/testing/espresso">Espresso</a></li>