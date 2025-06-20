package com.example.evoo.data

import com.example.evoo.R
//import com.example.evoo.business.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val profilePicture: Int = R.drawable.default_avatar
)


private var users = mutableListOf<User>(
    User(
        id = 1,
        name = "Alex",
        email = "alex@test.de",
        password = "1234"
    ),
    User(
        id = 2,
        name = "Sergei",
        email = "sergei@test.de",
        password = "12345"
    ),
    User(
        id = 3,
        name = "Anna",
        email = "anna@test.de",
        password = "123456",
        profilePicture = R.drawable.avatar2
    ),
    User(
        id = 4,
        name = "Max",
        email = "max@test.de",
        password = "1234567"
    )
)

fun usersDataFlow(): Flow<List<User>> = flow {
    emit(users)
}

interface UsersRepository {
    fun getUsersFlow(): Flow<List<User>>
    fun getUserByIdFlow(id: Int): Flow<User?>
}

interface AddUser{
    fun addUser(user: User)
}

class UsersRepositoryImpl : UsersRepository, AddUser {

    // Hier käme normalerweise API/Datenbank-Zugriff
    override fun getUsersFlow(): Flow<List<User>> = usersDataFlow()

    override fun getUserByIdFlow(id: Int): Flow<User?> = flow {
        usersDataFlow().collect { users ->
            val user = users.find { it.id == id }
            emit(user)
        }
    }

    override fun addUser(user: User) {
        users.add(user)
    }
}





//object UsersRepository {
//    // Custom Getter/Setter für Datenkapselung
//    var userData: MutableList<User>
//        get() = PreferencesManager.loadUsers().toMutableList() // Lädt aus Prefs
//        set(value) = PreferencesManager.saveUsers(value) // Speichert bei Änderung
//
//    init {
//        // Wird beim ersten Zugriff auf UsersRepository ausgeführt
//        if (userData.isEmpty()) {
//            // Initiale Testdaten
//            userData = mutableListOf(
//                User("Alex", "alex@test.de", "1234"),
//                User("Sergei", "sergei@test.de", "12345"),
//                User("Anna", "anna@test.de", "123456", R.drawable.avatar2),
//                User("Max", "max@test.de", "1234567")
//            )
//        }
//    }
//}

//Zweck: Zentrale Schnittstelle für Benutzerdaten.

//Getter/Setter: Automatische Synchronisation mit SharedPreferences.

//init-Block: Initialisiert Testbenutzer beim ersten App-Start.

//Initialisierungsflow:
//Beim ersten Aufruf von UsersRepository.userData
//loadUsers() wird ausgeführt
//Wenn leer → Testdaten werden hinzugefügt
//saveUsers() speichert Testdaten persistent