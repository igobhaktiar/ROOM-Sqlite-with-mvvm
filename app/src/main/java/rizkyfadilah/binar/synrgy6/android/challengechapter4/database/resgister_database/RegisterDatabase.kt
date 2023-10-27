package rizkyfadilah.binar.synrgy6.android.challengechapter4.database.resgister_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// TODO 3 : Create Database for Register
@Database(entities = [RegisterEntity::class], version = 1, exportSchema = false)
// TODO 4 : Create abstract class for Register
abstract class RegisterDatabase : RoomDatabase() {

    // TODO 5 : Create abstract val for Register
    abstract val registerDatabaseDao: RegisterDatabaseDao

    // TODO 6 : Create companion object for Register
    companion object{
        @Volatile
        private var INSTANCE: RegisterDatabase? = null

        // TODO 7 : Create fun getInstance for Register
        fun getInstance(context: Context): RegisterDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RegisterDatabase::class.java,
                        "register_users_database"
                    ).fallbackToDestructiveMigration().build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}