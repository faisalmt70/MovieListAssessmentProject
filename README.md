# MovieListAssessmentProject

### API Used
Studio Ghibli API (https://ghibliapi.vercel.app/films)

### Architecture
MVVM with Repository pattern.
- data: Retrofit + Room
- presentation: ViewModel + Compose
- state via StateFlow

### Coroutine Usage
- suspend functions in API & Repository
- viewModelScope for UI calls
- Flow for Room favorites

### Favorites Persistence
Favorites stored locally using Room database.
Persist across app restarts.

### How to Run
- Clone repo
- Open in Android Studio
- Run on emulator or device

### AI Usage
AI was used for:
- Project scaffolding
- Architecture guidance
- Boilerplate generation

Business logic and structure implemented by developer.
