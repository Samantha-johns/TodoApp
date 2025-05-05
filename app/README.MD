### DATA MANAGEMENT IN COMPOSABLES 
- A composable defines the layout and elements in an app screen 
- The composables will contain data that will allow our user to interact with and also view 
- Data inside a composable unit is called STATE  
- TO manage this state android-kotlin utilizes the concept of a viewmode

### VIEWMODEL 
- observes the state updates in a composable which determines how a composable is drawn on the 
android screen 
-  apart from observing the data it also encapsulates business logic i.e. functions working on the 
data 

DASHBOARD SCREEN    ||      DASHBOARD SCREEN     ||        DASHBOARD SCREEN
a - TodoItemCard x1      a                                b 
                         b                               c 
                         c  Todoitem x2                

### ROOM DATABASE 
Steps to work with the room persistence database library 
1. Add the library dependencies to the build.gradle
   implementation("androidx.room:room-runtime:2.7.1")
   implementation("androidx.room:room-ktx:2.7.1")
2. Create Entity : select the model and annotate it with the 
@Entity(tableName = "")
3. Create a DATABASE ACCESS OBJECT (DAO) :: this will provide methods that
will perform operations on a database table records 
4. Create the databases classes : this will be used to track the different 
database versions i.e. migrations , we cam\n also include seed data in this
file : i.e. seed refers to mock data 
5. Create a repository to work with viewmodel 
     UI (composables ie. screens) -> Viewmodel -> Repository class 
     -> Room Database (DAO)
= The repository acts as : 
a. A single source of truth your data 
b. An abstraction layer between data sources 
c. The component to handle all data operations 
6. To provide the viewmodel on compilation we will
add a dependency manager :: HILT 
To add hilt to the project : 
a. In the build.gradle project level add this hilt 
plugin :  
id("com.google.dagger.hilt.android") version "2.56.1" apply false
b. In the build.gradle app level add the hilt dependency
   plugins {
   id("com.google.devtools.ksp")
   id("com.google.dagger.hilt.android")
   }
dependencies {
implementation("com.google.dagger:hilt-android:2.56.1")
ksp("com.google.dagger:hilt-android-compiler:2.56.1")
}
























