package com.example.farm_store

import LoginPage
import SignUpPage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farm_store.pages.homePage
import com.example.farm_store.viewmodels.AuthViewModel

@Composable
fun mynavigation(modifier: Modifier , authViewModel: AuthViewModel){
val navController = rememberNavController()

NavHost(navController , startDestination = "login" , builder = {
    composable("login"){ LoginPage(
        modifier,navController ,authViewModel
    ) }
    composable("signUp"){ SignUpPage( modifier,navController ,authViewModel ) }
    composable("Home"){ homePage( modifier,navController ,authViewModel ) }
})
}