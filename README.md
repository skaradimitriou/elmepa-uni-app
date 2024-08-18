# Management Science & Technology (ELMEPA) University App (Android)

<p align="center" width="100%">
  <img src="https://github.com/skaradimitriou/elmepa-uni-app/assets/64270931/27b919a2-2b35-48bd-a6d8-d23a18aa7c92" alt="elmepa_uni_app"/>
</p>

This repository contains an Android application built & tailored specifically for our university community.

App Presentation 🎬  | Download App 📱 | Read More about the App 📖 | Releases Overview 🚀
------------- | ------------- | ------------- | ------------- 
[YouTube URL](https://youtu.be/S6V7wsez1-c)  | [Google Play](https://play.google.com/store/apps/details?id=com.stathis.elmepaunivapp)  |  [Elmepa URL](https://mst.hmu.gr/ypiresies/mobile-epharmogh-tmhmatos/) | [App Releases](https://github.com/skaradimitriou/elmepa-uni-app/releases)

## Features 💡

<details >
  <summary>Interactive Interface</summary>
  <br>
  Engaging user experience designed to cater to the needs of students, faculty, and staff.
</details>
<details >
  <summary>Department News</summary>
  <br>
  Stay informed with the news, announcements & events directly from the University.
</details>
<details >
  <summary>Faculty Contact Information</summary>
  <br>
  Access the "Personnel" screen and search your professor's contact information. Open the Personnel details screen and learn more about your professor.
</details>
<details >
  <summary>Academic Schedule</summary>
  <br>
  Easily access the Department's academic schedule in-app.
</details>
<details >
  <summary>Application Forms</summary>
  <br>
  Easily find the application form that you need to fill out in-app.
</details>
<details >
  <summary>Freshmen Students Information & Onboarding steps - Coming soon 🔜</summary>
  <br>
  Easily access the Freshmen Student's onboarding steps in-app.
</details>
<details >
  <summary>Engaging Syllabus</summary>
  <br>
  Easily access your schedule and syllabus while on the go (for both Undergraduate & Postgraduate students).
</details>
<details >
  <summary>Department FaQ</summary>
  <br>
  Do you have a question? Our FaQ section is here for you!
</details>
<details >
  <summary>Personalization</summary>
  <br>
  Dark theme or Light theme? Tailor the app to your own preference.
</details>

## App Architecture 🛠️

<p align="center" width="100%">
  <img src="https://github.com/skaradimitriou/elmepa-uni-app/assets/64270931/e1e2cb57-4ff2-4c60-9f59-b581b7b8a529" alt="mvvm_clean_architecture" width="30%" height="20%" />
</p>


## Modularization 🧩

<p align="center" width="100%">
  <img src="https://github.com/skaradimitriou/elmepa-uni-app/assets/64270931/e32082ca-6379-4b83-844a-b28375843d51" alt="app_modularization" width="40%" height="40%" />
  <p align="center" width="100%">Navigate on <ins>each module</ins> to see detailed dependencies.</p>
</p>

<details >
<summary>:app module</summary>
<br>
The :app module serves as the entry point for the application. It includes the main activity and is responsible for initializing the app and routing to the appropriate feature modules.
</details>

<details >
<summary>:core module</summary>
<br>
The :core module contains shared resources and utilities that are used across other modules. This includes common utilities, network operations, local database, datastore setup, and last but not least, the domain logic.
</details>

<details >
<summary>:feature module</summary>
<br>
The :feature module encapsulates specific, self-contained functionality related to a particular aspect of the app. (e.g department news).
</details>


## Tech Stack ⚙️

- [**Kotlin**](https://kotlinlang.org/): A modern programming language for building robust Android applications.
- [**Android Jetpack**](https://developer.android.com/jetpack): Utilize the latest Android Jetpack components for efficient development.
- [**MVVM Clean Architecture**](https://developer.android.com/topic/architecture): Implement a clean separation of concerns with Model-View-ViewModel architecture following Clean Architecture principles.
- [**Coroutines**](https://kotlinlang.org/docs/coroutines-overview.html): Leverage Kotlin Coroutines for asynchronous and non-blocking programming.
- [**Kotlin Flows**](https://developer.android.com/kotlin/flow): Utilize Kotlin Flows for reactive programming, enabling seamless data stream processing.
- [**Hilt**](https://developer.android.com/training/dependency-injection/hilt-android) Dependency injection framework for managing the lifecycle and dependencies.
- [**Firebase**](https://firebase.google.com/): Integrates with Firebase services (Firestore, Storage, Crashlytics, Performance) for data flow and app monitoring purposes.
- [**Room Database**](https://developer.android.com/jetpack/androidx/releases/room): Utilize Room Persistence Library for local data storage and caching.
- [**DataStore**](https://developer.android.com/topic/libraries/architecture/datastore): Jetpack library for managing key-value and typed object storage, replacing SharedPreferences.
- [**Jsoup**](https://jsoup.org/) HTML parser library for extracting and manipulating data from web pages.
- [**Paging3**](https://developer.android.com/topic/libraries/architecture/paging/v3-overview): Library for handling large datasets by loading and displaying data incrementally.
- [**Navigation Component**](XXXX): Framework for managing in-app navigation and deep linking within the app.
- [**Material 3**](https://m3.material.io/): Implement intuitive and visually appealing UI with Material Design 3 guidelines.
- [**Glide**](https://github.com/bumptech/glide): Efficiently load and display images with Glide, a fast and flexible image loading library.
- [**Gson**](https://github.com/google/gson): Library for serializing and deserializing to and from JSON.
- [**Timber**](https://github.com/JakeWharton/timber): Logging library for extensible and structured logging throughout the app.
- [**Shimmer**](https://github.com/facebookarchive/shimmer-android): Enhance UI loading experience with shimmer effects to indicate content loading.
- [**Kotlin DSL**](https://developer.android.com/build/migrate-to-kotlin-dsl): Utilize Kotlin DSL for configuring build scripts and project setup, enhancing build script readability and maintainability.


## Feedback

We value your feedback! If you have any suggestions, feature requests, or bug reports, please don't hesitate to [open an issue](https://github.com/skaradimitriou/elmepa-uni-app/issues) on GitHub.
