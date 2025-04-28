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










