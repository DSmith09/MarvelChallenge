# Marvel Unlimited Challenge

## Task
Implement New Comic Detail Screen
  - Comic Activity should launch as Instant App
  - Comic Meta-Data and Artwork should be retrieved from [Marvel's Developer API](developer.marvel.com)
  - Comic Data should be presented in preferred implementation
  - Implementation should include Unit Tests
  - Implementation should include offline caching support

## Implementation
  - I chose Kotlin as the language for pragmatic functional support. It allows me to use inline functions to keep code concise and simplistic while also being performant.
    - With the exception to Retrofit, I was able to remove all dependencies on the annotation processor, which allows for faster compilation with gradle
    - With lazy loading (using the **by lazy** delegated property), I was able to improve app performance by loading objects only when necessary
  
  - For my implementation I decided to use the following frameworks:
    - [Retrofit](https://square.github.io/retrofit/)
    - [GSON](https://github.com/google/gson)
    - [RxJava](https://github.com/ReactiveX/RxJava)
    - [RxAndroid - RxJava Extensions](https://github.com/ReactiveX/RxAndroid)
    - [Kodein](http://kodein.org/Kodein-DI/)
    - [Picasso](http://square.github.io/picasso/)
    
 - The Implementation works as follows:
   - App can launch from standalone module or from deep link using base url: https://dmsmith.marvel.com/comic (e.g. https://dmsmith.marvel.com/comic?id=12345)
     - App will launch at ComicDetailActivity
     - App follows single activity implementation, fragments are loaded into the fragment_container when needed
   - App loads ComicViewFragment onCreate... ComicViewFragment will display comic retrieved from **id** parameter
     - If parameter is null or invalid, ComicViewFragment will load page of comics with image from Marvel's API
   - User can tap on rendered comic view, tapping on rendered view will load ComicDetailFragment
   - ComicDetailFragment will display following comic information:
     - Comic Image
     - Comic Description (If available)
     - Comic Publication Date (If available)
     - Comic Creators (If available)
   - Since ComicDetailFragment is added to the back stack, user can tap back button to navigate back to ComicViewFragment and select a different comic
 
- The Implementation uses the MVP architectural pattern to separate the business logic from the presentation. This allows for easier unit testing
- The Implementation uses Kodein for Dependency Injection to inject the Networking Layer as a module. This allows for separation of the network call from the business logic in order to effectively test the MVP presenter.
- The Implementation supports Offline Caching using Retrofit and Contains Unit Tests for Business Logic
- The Implementation leverages ConstraintLayout to support multiple screen sizes
   
## Challenges
  - On rendering the ComicViewFragment, the data takes a bit to load content in parsing through the Marvel JSON response. This process for retrieval should be refactored in a later revision
  - I added network calls for the character(s) information as well... I wanted to display related character information and possibly link to related comics from the character selection. This could be an additional feature in a later release.
  - I wanted the UI for the Comic Detail View to resemble as closely as possible to the production Marvel Unlimited apps (I took elements from both apps (Android and iOS) that I thought looked nice). I included buttons for **Read Now**, **Sign In**, and **Recommended Series** but they only display a Toast as of today... extended functionality can be added in a later release.
   
