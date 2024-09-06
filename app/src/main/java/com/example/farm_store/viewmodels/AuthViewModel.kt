package com.example.farm_store.viewmodels

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>(AuthState.Unauthenticated)
    val authState: LiveData<AuthState> = _authState



    init {
        checkAuthStatus()
    }
    fun login(email : String , passwd : String){
        if(email.isEmpty() || passwd.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,passwd).addOnCompleteListener { task->
            if (task.isSuccessful){
                _authState.value = AuthState.Authenticated
                _isLoggedIn.value = true

            }else{
                _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
            }
        }
    }
    fun signup(email : String , passwd : String){
        if(email.isEmpty() || passwd.isEmpty()){
            _authState.value = AuthState.Error("Email and password cannot be empty")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email,passwd).addOnCompleteListener { task->
            if (task.isSuccessful){
                _authState.value = AuthState.Authenticated
                val loggedIn = true // Set this based on your actual auth check
                _isLoggedIn.value = loggedIn
            }else{
                _authState.value = AuthState.Error(task.exception?.message ?: "Unknown error")
            }
        }
    }
    fun signout(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
        _isLoggedIn.value = false

    }

    fun checkAuthStatus() {
        if (auth.currentUser == null) {
            _authState.value = AuthState.Unauthenticated
        } else {
            _authState.value = AuthState.Authenticated
            _isLoggedIn.value = true
        }
    }

}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    object CodeSent : AuthState()
    data class Error(val message: String) : AuthState()
}