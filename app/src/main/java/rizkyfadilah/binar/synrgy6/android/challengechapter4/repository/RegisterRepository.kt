package rizkyfadilah.binar.synrgy6.android.challengechapter4.repository

import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.RegisterDatabaseDao
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.RegisterEntity

class RegisterRepository(private val dao : RegisterDatabaseDao){

    val users = dao.getAllUsers()

    suspend fun insert(user: RegisterEntity){
        return dao.insert(user)
    }

    suspend fun getUsername(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }
}