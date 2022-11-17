# Marker On Map
Android Studio project on kotlin + XML ( jetpack compose support will added later )

## App flow video
https://user-images.githubusercontent.com/31402470/201424619-db933dd5-0d43-4f55-b79a-7a3fdecdbe3a.mov

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
![](https://user-images.githubusercontent.com/31402470/201425388-f0596a7e-6f2b-46bf-ac33-0b1b02896699.png)  |  ![](https://user-images.githubusercontent.com/31402470/201425394-b103f162-1306-430d-9c97-1c0f75e23424.png) | ![](https://user-images.githubusercontent.com/31402470/201425400-59b9123a-50ab-43c2-a6cc-05df30a0efb7.png) | ![](https://user-images.githubusercontent.com/31402470/201425405-d0b4a259-3abb-487a-8444-ba4eef192822.png)


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