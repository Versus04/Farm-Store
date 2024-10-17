# 🌾 FarmStore App

FarmStore is a mobile application designed to connect local farmers with consumers, providing a platform for buying and selling fresh, locally-sourced produce. 🚜🍎🥕

## ✨ Features

- 🔐 User Authentication: Secure login and signup functionality.
- 📋 Product Listings: Farmers can list their products with details and images.
- 🔍 Search and Filter: Users can easily find products based on categories, location, or keywords.
- 🛒 Shopping Cart: Users can add items to their cart and proceed to checkout.
- 📦 Order Management: Both farmers and consumers can track their orders.
- 👤 User Profiles: Customizable profiles for both farmers and consumers.
- ⭐ Ratings and Reviews: Users can rate and review products and sellers.

## 🛠️ Technologies Used

- 📱 Kotlin
- 🎨 Jetpack Compose for UI
- 🔥 Firebase Authentication
- 🗄️ Firebase Firestore for database
- 🖼️ Firebase Storage for image storage
- 🏗️ Android Architecture Components (ViewModel, LiveData)
- 🧵 Coroutines for asynchronous programming
- 💉 Dagger Hilt for dependency injection

## 🚀 Setup and Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/Farm-Store.git
   ```

2. Open the project in Android Studio.

3. Set up Firebase:
   - Create a new Firebase project in the Firebase Console.
   - Add an Android app to your Firebase project and follow the setup instructions.
   - Download the `google-services.json` file and place it in the app module of your project.

4. Enable Firebase Authentication and Firestore in the Firebase Console.

5. Build and run the app on an emulator or physical device.

## 📁 Project Structure

- `app/src/main/java/com/example/farm_store/`
  - `ui/`: Contains all the Composable functions for the UI.
  - `viewmodels/`: Contains ViewModels for managing UI-related data.
  - `models/`: Data classes representing the app's data structures.
  - `repositories/`: Classes for handling data operations.
  - `di/`: Dependency injection modules.
  - `utils/`: Utility classes and functions.

## 📸 Screenshots


## 🌟 Future Enhancements

- 📍 Geolocation services for finding nearby farms
- 💬 In-app messaging between farmers and consumers
- 📊 Analytics dashboard for farmers
- 🌐 Multi-language support

Happy farming and shopping! 🌱🛍️
