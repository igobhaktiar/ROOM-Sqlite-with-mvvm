package rizkyfadilah.binar.synrgy6.android.challengechapter4.database.note_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// Buat anotasi untuk membuat database
@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {

    // Buat abstract function untuk mengakses DAO
    abstract fun getNoteDao(): NotesDao


    // Buat companion object untuk membuat database
    companion object{
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // Buat fungsi untuk membuat database
        fun getDatabase (context : Context): NoteDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}