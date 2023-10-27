package rizkyfadilah.binar.synrgy6.android.challengechapter4.repository

import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database.RegisterDatabaseDao
import rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database.RegisterEntity

// TODO 4 : Create Repository
class RegisterRepository(private val dao : RegisterDatabaseDao){

    // TODO 5 : Create function to get all users
    val users = dao.getAllUsers()


    // TODO 6 : Create function to insert user
    suspend fun insert(user: RegisterEntity){
        return dao.insert(user)
    }

    // TODO 7 : Create function to get user by username
    suspend fun getUsername(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }
}