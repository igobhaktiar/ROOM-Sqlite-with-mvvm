package rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

// TODO 3 : Create Dao for Register
@Dao
interface RegisterDatabaseDao {

    // TODO 4 : Create Query for Register
    @Insert
    suspend fun insert(registerEntity: RegisterEntity)

    @Query("SELECT * FROM register_users_table ORDER BY userId DESC")
    fun  getAllUsers(): LiveData<List<RegisterEntity>>

    @Query("SELECT * FROM register_users_table WHERE user_name LIKE :userName")
    suspend fun getUsername(userName: String): RegisterEntity?


}