# MovieListAssessmentProject

A simple Android app that lists Studio Ghibli movies, shows details for each movie, and allows users to mark favorites. Favorites are stored locally, so selections persist across app restarts.

## API Used
The app uses the **Studio Ghibli API**:  
[https://ghibliapi.vercel.app/films](https://ghibliapi.vercel.app/films)  

All movie data—including title, description, image, and release year—is fetched from this API.

## Architecture Overview
The app follows the **MVVM (Model-View-ViewModel)** architecture with a Repository layer to separate data handling from the UI.

**Layers:**
- **Data Layer:**  
  - Retrofit for API calls  
  - Room database for storing favorites locally  
- **Repository Layer:**  
  - Handles combining API data with local favorites  
- **Presentation Layer:**  
  - Jetpack Compose for UI  
  - ViewModels manage UI state and business logic  
- **State Management:**  
  - `StateFlow` is used to emit Loading, Success, or Error states  
  - UI reacts to state changes automatically  

## Coroutine Usage
- Network and database calls use **suspend functions**.  
- `viewModelScope` ensures coroutines run in a lifecycle-aware manner.  
- Room `Flow` is used to observe changes in the favorites database.  
- `StateFlow` allows the UI to reactively update when movie or favorite data changes.  

## How Favorites Are Persisted
- Favorites are stored in a **Room database**.  
- Each movie marked as favorite is inserted into the database.  
- Removing a favorite deletes it from the database.  
- The favorite state persists across app restarts.  
- The UI updates automatically when favorite status changes.  

## How to Run
1. Clone the repository.  
2. Open the project in **Android Studio**.  
3. Sync Gradle.  
4. Run on an emulator or a real Android device.  

## AI Usage vs Self-Implemented
**AI Usage:**  
- Initial project scaffolding (setting up modules, Gradle, Hilt, basic Compose setup)  
- Guidance on MVVM architecture structure  

**Self-Implemented:**  
- All core app logic including:  
  - Fetching movies from API  
  - Persisting favorites using Room  
  - UI implementation with Compose  
  - State management with `StateFlow`  
  - Navigation and filtering (All / Favorites)
