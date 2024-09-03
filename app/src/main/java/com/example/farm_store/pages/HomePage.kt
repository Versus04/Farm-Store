package com.example.farm_store.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.farm_store.viewmodels.AuthState
import com.example.farm_store.viewmodels.AuthViewModel

@Composable
fun homePage( modifier: Modifier    ,navController: NavController , authViewModel: AuthViewModel){
    val authState=authViewModel.authState.observeAsState()
    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Unauthenticated ->navController.navigate("login")
            else -> Unit
        }
    }
    Column(){
        Spacer(Modifier.height(30.dp))
        Button(onClick = {authViewModel.signout()}) { }
    }

}