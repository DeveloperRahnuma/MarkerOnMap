# Marker On Map
Android Studio project on kotlin + XML ( jetpack compose support will added later )

## App flow video
https://user-images.githubusercontent.com/31402470/202429506-c11dd280-39d1-4f91-8d31-b6d08bcbc822.mp4

## Different Layout for landscap and potrait 
Potrait layout   |  Ladscape Layout 
:-------------------------:|:-------------------------: 
![](https://user-images.githubusercontent.com/31402470/202425012-2015f887-2961-42e0-9072-75ed5a16e82f.png) |  ![](https://user-images.githubusercontent.com/31402470/202423886-2bd8e3ee-da6c-4a59-83a0-a6bbfb21bacc.png) | 


### To run this project on your device Please Use

    Android Studio Chipmunk | 2021.2.1 Patch 1
    kotlin version '1.7.10'
    minSdk 21
    targetSdk 32
    compileSdk 32

### Third-party libraries used in the project

    Dependency Injection -> Dagger Hilt
    Architecture -> Mvvm
   

## Screenshot Of App by functionality 
Google Map Implementation   |  Market on center on click  | multiple market add when click on map | different landscape ui 
:-------------------------:|:-------------------------: | :-------------------------: | :-------------------------:
![](https://user-images.githubusercontent.com/31402470/202425030-b963f1f3-32be-42d9-92a2-89af2d776407.png) | ![](https://user-images.githubusercontent.com/31402470/202425012-2015f887-2961-42e0-9072-75ed5a16e82f.png) | ![](https://user-images.githubusercontent.com/31402470/202423875-73886848-c9e9-432a-ae5f-37a7ef311637.png)  |  ![](https://user-images.githubusercontent.com/31402470/202423886-2bd8e3ee-da6c-4a59-83a0-a6bbfb21bacc.png) | 


## The feature will Implemented in Future (For future reference)
    Add Splash Screen
    route draw on two place


## Clarification on some point so don't commit as bug
    1-> click on map anywhere will add marker there so if user click multiple place one marker
    will add on each place that's part of feature.

    2-> clear on cross button when form is visible and you can see coordinate of center
    position of screeen will clear coordinate value and close the form also.
    
    3 -> click on close button will clear all the marker in one click
    
    4 -> different layout for potrait or ladscape orientation ( design for make app look more cool )

    5 -> click on button make map zoom too and that sometime it take time to load the map ( time depend on various factor
         test google map key is also one of the reason )


## Point Out Issue
I used the hilt for DI and according to rule view model instance get in activity with this
ViewModel: MainViewModel? By viewmodel () but its not working that is at issue with hilt used version
That's why I have to make the instance of view model in below way

    viewModel = ViewModelProvider(this)[MainViewModel::class.java]

No handle situation when user deny permisson from outside ( means first accept the
permission and then disable it from setting) 
