# General information of the project:  
  
- **Language**: Kotlin  
- **IDE**: Android Studio  
- **Design Pattern**: MVVM (Model - View - ViewModel) with LiveData  
- **DI**: Dagger Hilt  
- **Concurrency**:  Coroutines  
- **Unit Test Framework**: Spec  
- **UI Test Framework**: Espresso  
  
The project follows a feature structure, there is a feature folder where all additional features would be placed, in this example, the feature provided is `Kotlinrepos`  
  
Each feature manages the three layers of clear architecture (Data - Domain - UI).  
  
As DI framework I used Dagger Hilt, its configuration is done in the subfolder DI  
  
The management of the response is done with `Coroutines`, using `Moshi` library to adapt the response.  
  
I decided to create one model for the Network Response `KotlinRepoDTO` and the one used through the project is `KotlinRepo`, the transformation is done in a  `Mapper`  
  
The project use the `UseCases` pattern in order to help identify the different use cases the app could have  
  
In order to handle the navigation between the two fragments I decided to use the Android Navigation Component and adding an animation between the screens
  
For `UnitTest` I use the spec framework, as I think it improves the readability of the different use cases and as well the scalability for more complex input data structures.  
The  `UnitTest` test the 3 different layers of the feature  
  
I provide a `UITest` using espresso to test the displayed of the list in the fragment. This is done mocking the response of the view states. Please use an emulator with API 28 to run it.  

Please note, I didn't add end to end test, that should be added to test the navigation between the different screens.
  
The gradle file has 2 build types, one for debug and other for production, both of them refer to the same API URL.

For retrieving the list of trending kotlin respositories I used the following endpoint:   [https://ghapi.huchen.dev/repositories?language=kotlin](https://ghapi.huchen.dev/repositories?language=kotlin)
Its documentation can be find here: [https://githubtrendingapi.docs.apiary.io/#reference/0/repositories/list-trending-repositories](https://githubtrendingapi.docs.apiary.io/#reference/0/repositories/list-trending-repositories)

Also note this API doesn't provide a detail endpoint, so I had to pass the details of the repository from the list screen to the detail screen. Ideally, I would only have to pass an ID and then implement a network call to retrieve the detail that can be cached. 
  
# Scrum:  
In order to manage this project I followed the principles of _Scrum Methodology_ identifying the following tasks and estimating them as follows:  
  
- [x] **TaskA**: Setup the project -> Story Points: **2**  
- [x] **TaskB**: Establish the MVVM architecture with Data-Domain-UI -> Story Points: **5**  
- [x]  **TaskC**: Implement Dagger-> Story Points: **5**  
- [x] **TaskD**: Implement retrieving the data from backend-> Story Points: **3**  
- [x] **TaskE**: UI implementation of the list -> Story Points: **3**  
- [x] **TaskF**: UITest of the view -> Story Points: **3**  
  
Other tasks identified not implemented:  
  
- [ ] **TaskH**: Improve designs  
- [ ] **TaskI**: Define more custom styles for the app  
- [ ] **TaskJ**: Implement end to end tests
- [ ] **TaskK**: Implement UI test for details
- [ ] **TaskL**: Implement persistence for not network 
- [ ] **TaskM**: Implement pull to refresh  on the list
- [ ] **TaskN**: Migrate gradle to KTS  
- [ ] **TaskO**: Implement custom toolbar for details screen to handle back navigation
