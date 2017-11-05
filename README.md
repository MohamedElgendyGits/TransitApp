# TransitApp

Transit is and application that find all possible routes between point A to point B 

Application architecture : 
* Feature separation by packages , 
Instead of using a package by layer approach, application structured by package per feature. This greatly improves readability and modularizes the app in a way that parts of it can be changed independently from each other. Each key feature of the app is in its own Java package

*  Application follow Android Architecture Component along with MVVM.

Android architecture components is new library by Google that has the aim to help us design application that are “robust, testable, and maintainable”. In a nutshell, this library helps us to better handle the persisting of data across lifecycle events and configuration changes, whilst also helping us to create a better architecture application and avoid bloated classes that are difficult to maintain and test.

Application libraries : 
* Android Architeture Components collection of libraries that help design robust, testable, and maintainable apps.
* ButterKnife for injecting views
* GSON for json parsing
* retrofit for network calls
* Picasso for image loading



***



_All Routes Screen_**. 


![ScreenShot](https://raw.github.com/MohamedElgendyGits/TransitApp/master/screenshots/Screenshot1.png)


_Detail Route Screen_**. 


![ScreenShot](https://raw.github.com/MohamedElgendyGits/TransitApp/master/screenshots/Screenshot2.png)


