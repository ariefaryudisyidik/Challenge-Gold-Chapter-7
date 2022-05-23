package com.binar.ariefaryudisyidik.challengegoldchapter7.ui.login

import android.util.Log
import androidx.lifecycle.*
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.binar.ariefaryudisyidik.challengegoldchapter7.R
import com.binar.ariefaryudisyidik.challengegoldchapter7.data.UserRepository
import com.binar.ariefaryudisyidik.challengegoldchapter7.data.local.User
import com.binar.ariefaryudisyidik.challengegoldchapter7.utils.UserDataStoreManager
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: UserRepository,
    private val pref: UserDataStoreManager
) : ViewModel() {

    private var _checkUser = MutableLiveData<User>()
    val checkUser: LiveData<User> = _checkUser

    private var _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private var _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val data = repository.checkUser(email, password)
                _checkUser.value = data
                if (email.isEmpty() || password.isEmpty()) {
                    _message.value = "Field cannot be empty"
                } else if (data == null) {
                    _message.value = "User does not exist"
                } else {
                    _success.value = true
                    findNavController(LoginFragment()).navigate(R.id.action_loginFragment_to_homeFragment)
                }

            } catch (e: Exception) {
                Log.d("LoginViewModel", "${e.message}")
            }
        }
    }

    fun saveUserDataStore(id: Int, status: Boolean) {
        viewModelScope.launch {
            pref.saveUser(id, status)
        }
    }

    fun getLoginStatus(): LiveData<Boolean> {
        return pref.getLoginStatus().asLiveData()
    }
}
