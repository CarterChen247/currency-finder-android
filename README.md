# Currency Finder Android

<img width=250 src="https://github.com/user-attachments/assets/345fa91d-769c-430b-b114-80c04e967b8d"/>
<img width=250 src="https://github.com/user-attachments/assets/708aec93-821f-4ecd-96fc-fc9fa66c32a0"/>

## Note

This project is about more than just writing code—it showcases the full development process. From testing and quality checks to collaboration, it reflects how I handle real-world projects from start to finish.

Here’s what I’ve focused on:

- **CI/CD Pipelines**: I set up automated workflows for building and testing. In a production scenario, this pipeline would also handle deployment to Google Play.  
- **Code Reviews and Collaboration**: I used pull requests to ensure high-quality code and effective teamwork.  
- **Clean and Maintainable Code**: I followed best practices to create code that is easy to read, scale, and reuse.  
- **Commit Message Guidelines**: I follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification to maintain clear and consistent commit history.  

This project is not just about delivering features but about demonstrating a well-rounded, professional approach to development.


## Overview
- ✅ Kotlin
- ✅ Follow [Recommended app architecture](https://developer.android.com/topic/architecture#recommended-app-arch)
- ✅ Dependency Injection with **Hilt**
- ✅ Unit testing
- **MVVM Pattern**: - Combines Jetpack Compose, Navigation, Hilt, and Coroutines.
- **UI Structure**: - Includes `DemoActivity`, `CurrencyListFragment`, and Jetpack Compose.


## Features
<img width=250 src="https://github.com/user-attachments/assets/552d1815-f0f1-4ea9-b0f3-716043cd01d7"/>
<img width=250 src="https://github.com/user-attachments/assets/cd2bde2f-0872-4548-94f3-d22c6f7304e2"/>

- **UI (e.g., buttons, search feature, empty state)**: See [UI Design (#2)](https://github.com/CarterChen247/currency-finder-android/issues/2) for detailed information.
- **Search term matching rules**: See [CurrencyFilterTest](https://github.com/CarterChen247/currency-finder-android/blob/710040ef4ac7cfd7e3d1fde225ce18316b7a3322/app/src/test/java/com/carterchen247/currencyfinder/util/CurrencyFilterTest.kt) for detailed test cases and coverage.
- ✅ **Background Operations**:
  - All operations are performed off the UI thread.
  - Implemented in `RepositoryImpl` using `Dispatchers.IO`.

## Testing

The project emphasizes testing at multiple levels to ensure stability and reliability:

<img width=400 src="https://github.com/user-attachments/assets/c7978bce-a7b6-494f-ac0e-6d80007bfd94"/>

- **Unit Tests**: Validate individual components.
- **MockK**: Used for mocking dependencies.
- **Robolectric**: For unit tests with Android components.
- **Integration Tests**: For testing ViewModel, DataSource implementations and more.

## Architecture Design Journey

This project adheres to a clear architectural strategy. Details of the design journey and can be found [Architecture Design Journey (#16)](https://github.com/CarterChen247/currency-finder-android/issues/16).

| Design | Pull Request |
|-------|------|
| <img width=400 src="https://github.com/user-attachments/assets/b5d42f86-3f6a-484c-aaeb-498e2f671910"/> | [feat: Implement LocalDataSource for currency query with Clean Architecture (#8)](https://github.com/CarterChen247/currency-finder-android/pull/8) |
| <img width=400 src="https://github.com/user-attachments/assets/fc283ea2-6400-4ac7-a8a3-4e66ca818707"/> | [feat: Implement LocalDataSource for currency query with Clean Architecture (#8)](https://github.com/CarterChen247/currency-finder-android/pull/8) |
|<img width=400 src="https://github.com/user-attachments/assets/61b8e6e0-c332-4473-b892-d9bf629b3ea7"/>|[feat: add Repository and RepositoryImpl (#9)](https://github.com/CarterChen247/currency-finder-android/pull/9)|
|<img width=400 src="https://github.com/user-attachments/assets/f34a9a41-18b4-4095-bbbb-d828ad937d74"/>|[feat: connect CurrencyListViewModel with Repository (#10)](https://github.com/CarterChen247/currency-finder-android/pull/10)|
|<img width=400 src="https://github.com/user-attachments/assets/e524cbce-24c9-4693-96dd-e2c745709c07"/>|[feat: implement LocalDataSourceImpl with Room (#12)](https://github.com/CarterChen247/currency-finder-android/pull/12)|

## Other Design

| Design | Pull Request |
|-------|------|
|<img width=400 src="https://github.com/user-attachments/assets/6eed9a93-664b-4731-87fd-ff6fd95baabc"/>|[feat: handle only the latest user search request in CurrencyListViewModel (#11)](https://github.com/CarterChen247/currency-finder-android/pull/11)|


---

