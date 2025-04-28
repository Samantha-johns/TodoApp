package com.example.todo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.todo.presentation.screens.dashboard.DashboardScreen

// INSIDE THIS FILE WE WILL DEFINE NAVCONTROLLER : THIS IS USED TO NAVIGATE
// TO DIFFERENT COMPOSABLES / SCREENS
@Composable
fun TodoNavGraph(navController: NavHostController){
      NavHost(navController = navController, startDestination = "Dashboard") {
          composable("dashboard"){
               DashboardScreen(
                   //properties for the composable
               )

          }
          // here will define the addtoDo composable

      }

}










