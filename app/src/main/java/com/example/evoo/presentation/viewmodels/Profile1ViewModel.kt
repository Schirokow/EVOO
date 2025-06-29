package com.example.evoo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evoo.business.usecases.GetUsersUseCase
import com.example.evoo.data.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Profile1ViewModel: ViewModel() {
    private val getUsersUseCase: GetUsersUseCase = GetUsersUseCase()

    // StateFlow für Users
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users.asStateFlow()

    // Kontinuierlicher Flow
    init {
        viewModelScope.launch {
            getUsersUseCase.getUsersFlow().collect { users ->
                _users.value = users
            }
        }
    }
}