package com.example.evoo.business.usecases

import com.example.evoo.data.User
import com.example.evoo.data.UsersRepositoryImpl
import kotlinx.coroutines.flow.Flow

class GetUsersUseCase {
    private val users = UsersRepositoryImpl()

    fun getUsersFlow(): Flow<List<User>> {
        return users.getUsersFlow()
    }

    fun getUserByIdFlow(id: Int): Flow<User?> {
        return users.getUserByIdFlow(id)
    }

    fun addUser(user: User){
        users.addUser(user)
    }


}