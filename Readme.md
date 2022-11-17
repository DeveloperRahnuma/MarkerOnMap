# Marker On Map
Android Studio project on kotlin + XML ( jetpack compose support will added later )

## App flow video



### To run this project on your device Please Use

    Android Studio Chipmunk | 2021.2.1 Patch 1
    kotlin version '1.7.10'
    minSdk 21
    targetSdk 32
    compileSdk 32

### Third-party libraries used in the project

    Dependency Injection -> Dagger Hilt
    For Server Call -> Retrofit
    Architecture -> Mvvm

## Screenshot Of App by functionality 
Loading Screen             |  Animated Search Bar   | Images Display In Grid View  | Images Display In List View
:-------------------------:|:-------------------------: | :-------------------------: | :-------------------------:
![](https://user-images.githubusercontent.com/31402470/202423875-73886848-c9e9-432a-ae5f-37a7ef311637.png)  |  ![](https://user-images.githubusercontent.com/31402470/202423886-2bd8e3ee-da6c-4a59-83a0-a6bbfb21bacc.png) | ![](https://user-images.githubusercontent.com/31402470/202424404-1851d1e0-2c54-4322-8c9a-10313bf0b6a7.png) | ![](https://user-images.githubusercontent.com/31402470/202424388-5afe3366-f23d-48a1-b81e-091d8faf436f.png)


## The feature will Implemented in Future (For future reference)
    Add Splash Screen
    Add Shimmer Effect On Loading
    Add Suggestion From Search History
    Add Debence on search
    improve View Quality

## Point Out Issue
Current time and date in the app is not well formatted
For example - API response has json key timeAndDate with value 1667790933
While trying to convert it to date format it through the error Unpassable date: "1667790933"
so for now (displaying only string as it is returned from the API)



I used the hilt for DI and according to rule view model instance get in activity with this
ViewModel: MainViewModel? By viewmodel () but its not working that is at issue with hilt used version
That's why I have to make the instance of view model in below way

    viewModel = ViewModelProvider(this)[MainViewModel::class.java]

For some result total number of images show null, that's because the API has that result
